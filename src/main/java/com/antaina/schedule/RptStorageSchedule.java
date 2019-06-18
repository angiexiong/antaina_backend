package com.antaina.schedule;

import com.antaina.entity.product.ProductInfo;
import com.antaina.entity.storage.RptStorage;
import com.antaina.mapper.ProductInfoMapper;
import com.antaina.mapper.RptStorageMapper;
import com.antaina.mapper.StorageInputMapper;
import com.antaina.mapper.StorageOutputMapper;
import com.antaina.model.storage.StorageInputOutputQueryModel;
import com.antaina.util.UidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RptStorageSchedule {

    private static final String TIME_UNIT_HOUR = "HOUR";
    private static final String TIME_UNIT_DAY = "DAY";
    private static final String TIME_UNIT_MONTH = "MONTH";

    private static final int FREQUENCY_TYPE_HOUR = 0;
    private static final int FREQUENCY_TYPE_DAY = 1;
    private static final int FREQUENCY_TYPE_WEEK = 2;
    private static final int FREQUENCY_TYPE_MONTH = 3;
    private static final int FREQUENCY_TYPE_YEAR = 4;

    @Autowired
    private StorageInputMapper storageInputMapper;

    @Autowired
    private StorageOutputMapper storageOutputMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private RptStorageMapper rptStorageMapper;

    /**
     * 每小时统计一次
     *
     * @Scheduled(cron = "03 0 0/1  * * ?")
     * @Scheduled(cron = "0 0/1 *  * * ?")
     */
    @Scheduled(cron = "03 0 0/1  * * ?")
    public void doStatisticsByHour() {
        // 获取所有物料信息
        List<ProductInfo> productInfoList = productInfoMapper.selectAll();
        if (CollectionUtils.isEmpty(productInfoList)) {
            log.info("系统中没有待统计的物料，本次定时任务不执行");
            return;
        }

        // 获取过去一小时内的入库记录
        List<StorageInputOutputQueryModel> inputList = storageInputMapper.getListByStatisticFlag();
        List<Long> inputIds = null;
        Map<String, List<StorageInputOutputQueryModel>> inputMap = new HashMap<>(productInfoList.size());
        if (!CollectionUtils.isEmpty(inputList)) {
            inputMap = inputList.stream().collect(Collectors.groupingBy(StorageInputOutputQueryModel::getProductCode));
            inputIds = inputList.stream().map(StorageInputOutputQueryModel::getId).collect(Collectors.toList());
        }

        // 获取过去一小时内的出库记录
        List<StorageInputOutputQueryModel> outputList = storageOutputMapper.getListByStatisticFlag();
        List<Long> outputIds = null;
        Map<String, List<StorageInputOutputQueryModel>> outputMap = new HashMap<>(productInfoList.size());
        if (!CollectionUtils.isEmpty(outputList)) {
            outputMap = outputList.stream().collect(Collectors.groupingBy(StorageInputOutputQueryModel::getProductCode));
            outputIds = outputList.stream().map(StorageInputOutputQueryModel::getId).collect(Collectors.toList());
        }

        List<RptStorage> resultList = doStatistics(productInfoList, inputMap, outputMap, FREQUENCY_TYPE_HOUR);

        // 入库
        rptStorageMapper.batchInsert(resultList);

        // 更新入库和出库记录的统计状态
        if (!CollectionUtils.isEmpty(inputIds)) {
            storageInputMapper.batchUpdateStatisticFlag(inputIds);
        }

        if (!CollectionUtils.isEmpty(outputIds)) {
            storageOutputMapper.batchUpdateStatisticFlag(outputIds);
        }
    }

    private List<RptStorage> doStatistics(List<ProductInfo> productInfoList, Map<String, List<StorageInputOutputQueryModel>> inputMap, Map<String, List<StorageInputOutputQueryModel>> outputMap, int frequencyTypeHour) {
        List<RptStorage> resultList = new ArrayList<>(productInfoList.size());
        productInfoList.forEach(e -> {
            String productCode = e.getProductCode();
            BigDecimal inputAmount = BigDecimal.ZERO;
            List<StorageInputOutputQueryModel> inputList = inputMap.get(productCode);
            if (!CollectionUtils.isEmpty(inputList)) {
                inputAmount = inputList.stream().map(StorageInputOutputQueryModel::getAmount).reduce(BigDecimal::add).get();
            }

            BigDecimal outputAmount = BigDecimal.ZERO;
            List<StorageInputOutputQueryModel> outputList = outputMap.get(productCode);
            if (!CollectionUtils.isEmpty(outputList)) {
                outputAmount = outputList.stream().map(StorageInputOutputQueryModel::getAmount).reduce(BigDecimal::add).get();
            }

            RptStorage rptStorage = constructReport(productCode, inputAmount, outputAmount, frequencyTypeHour);
            resultList.add(rptStorage);
        });
        return resultList;
    }

    /**
     * 每天统计一次
     *
     * @Scheduled(cron = "10 0 0 * * ?")
     * @Scheduled(cron = "0 0/3 *  * * ?")
     */
    @Scheduled(cron = "10 0 0 * * ?")
    public void doStatisticsByDay() {
        List<RptStorage> rptStorageList = rptStorageMapper.getListByTimeInterval(FREQUENCY_TYPE_HOUR, -1, TIME_UNIT_HOUR);
        Map<String, List<RptStorage>> rptStorageMap = null;
        if (!CollectionUtils.isEmpty(rptStorageList)) {
            rptStorageMap = rptStorageList.stream().collect(Collectors.groupingBy(RptStorage::getProductCode));
        }
        processStatistics(rptStorageMap, FREQUENCY_TYPE_DAY);
    }

    /**
     * 每周统计一次
     *
     * @Scheduled(cron = "15 0 0 0/7 * ?")
     * @Scheduled(cron = "0 0/4 *  * * ?")
     */
    @Scheduled(cron = "15 0 0 0/7 * ?")
    public void doStatisticsByWeek() {

        List<RptStorage> rptStorageList = rptStorageMapper.getListByTimeInterval(FREQUENCY_TYPE_DAY, -7, TIME_UNIT_DAY);
        Map<String, List<RptStorage>> rptStorageMap = null;
        if (!CollectionUtils.isEmpty(rptStorageList)) {
            rptStorageMap = rptStorageList.stream().collect(Collectors.groupingBy(RptStorage::getProductCode));
        }
        processStatistics(rptStorageMap, FREQUENCY_TYPE_WEEK);
    }

    /**
     * 每月统计一次
     *
     * @Scheduled(cron = "20 0 0 1 * ?")
     * @Scheduled(cron = "0 0/5 *  * * ?")
     */
    @Scheduled(cron = "20 0 0 1 * ?")
    public void doStatisticsByMonth() {

        List<RptStorage> rptStorageList = rptStorageMapper.getListByTimeInterval(FREQUENCY_TYPE_DAY, -1, TIME_UNIT_MONTH);
        Map<String, List<RptStorage>> rptStorageMap = null;
        if (!CollectionUtils.isEmpty(rptStorageList)) {
            rptStorageMap = rptStorageList.stream().collect(Collectors.groupingBy(RptStorage::getProductCode));
        }
        processStatistics(rptStorageMap, FREQUENCY_TYPE_MONTH);
    }

    /**
     * 每年统计一次
     *
     * @Scheduled(cron = "25 0 0 1 * ?")
     * @Scheduled(cron = "0 0 0 1 1 *)
     */
    @Scheduled(cron = "30 0 0 1 1 *")
    public void doStatisticsByYear() {
        List<RptStorage> rptStorageList = rptStorageMapper.getListByTimeInterval(FREQUENCY_TYPE_MONTH, -12, TIME_UNIT_MONTH);
        Map<String, List<RptStorage>> rptStorageMap = null;
        if (!CollectionUtils.isEmpty(rptStorageList)) {
            rptStorageMap = rptStorageList.stream().collect(Collectors.groupingBy(RptStorage::getProductCode));
        }
        processStatistics(rptStorageMap, FREQUENCY_TYPE_YEAR);
    }

    RptStorage constructReport(String productCode, BigDecimal inputAmount, BigDecimal outputAmount, Integer type) {
        RptStorage rptStorage = new RptStorage();
        rptStorage.setId(UidUtil.getInstance().nextId());
        rptStorage.setProductCode(productCode);
        rptStorage.setInputAmount(inputAmount);
        rptStorage.setOutputAmount(outputAmount);
        rptStorage.setType(type);
        rptStorage.setCreateTime(new Date());
        rptStorage.setUpdateTime(new Date());
        return rptStorage;
    }

    private void processStatistics(Map<String, List<RptStorage>> rptStorageMap, int destFrequencyType) {
        // 获取所有物料信息
        List<ProductInfo> productInfoList = productInfoMapper.selectAll();
        if (CollectionUtils.isEmpty(productInfoList)) {
            log.info("系统中没有待统计的物料，本次定时任务放弃执行");
            return;
        }

        List<RptStorage> resultList = new ArrayList<>(productInfoList.size());
        productInfoList.forEach(e -> {
            String productCode = e.getProductCode();
            BigDecimal inputAmount = BigDecimal.ZERO;
            BigDecimal outputAmount = BigDecimal.ZERO;
            List<RptStorage> rptStorageList = rptStorageMap.get(productCode);
            if (!CollectionUtils.isEmpty(rptStorageList)) {
                inputAmount = rptStorageList.stream().map(RptStorage::getInputAmount).reduce(BigDecimal::add).get();
                outputAmount = rptStorageList.stream().map(RptStorage::getOutputAmount).reduce(BigDecimal::add).get();
            }

            RptStorage rptStorage = constructReport(productCode, inputAmount, outputAmount, destFrequencyType);
            resultList.add(rptStorage);
        });

        // 入库
        rptStorageMapper.batchInsert(resultList);
    }
}
