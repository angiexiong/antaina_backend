package com.antaina.config;

import com.antaina.serializer.ToBigDecimalSerializer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

@Configuration
public class JackSonConfig {

    @Bean
    public ObjectMapper getObjectMapper() {
        // 全局配置序列化返回 JSON 处理
        final ObjectMapper objectMapper = new ObjectMapper();

        // 如果null, 忽略
        // objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 返回时间格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 接口調用端多传过来参数时，后端忽略多余参数
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        SimpleModule simpleModule = new SimpleModule();
        // Long -> String
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(BigDecimal.class, ToBigDecimalSerializer.instance);
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }

}