package com.antaina.service.admin;

import com.antaina.constants.Constants;
import com.antaina.constants.ConstantsCode;
import com.antaina.entity.admin.AdminUser;
import com.antaina.enums.BaseResult;
import com.antaina.enums.MsgResult;
import com.antaina.exception.BusinessException;
import com.antaina.mapper.AdminUserMapper;
import com.antaina.model.BaseModel;
import com.antaina.model.LoginRedisEntity;
import com.antaina.model.LoginSuccessModel;
import com.antaina.model.admin.AdminLoginModel;
import com.antaina.model.admin.AdminUserModel;
import com.antaina.model.admin.UpdatePwdModel;
import com.antaina.resp.Message;
import com.antaina.util.PageUtil;
import com.antaina.util.UidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 添加后台管理账号
     *
     * @param aum
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Message add(AdminUserModel aum) {
        /*判断该账号是否存在*/
        AdminUser au = new AdminUser();
        au.setUseraccount(aum.getUseraccount());
        AdminUser adminUser = adminUserMapper.selectOne(au);
        if (adminUser != null && adminUser.getStatus() != 0) {
            return new Message(BaseResult.ERROR);
        }

        BeanUtils.copyProperties(aum, au);
        au.setId(UidUtil.getInstance().nextId());
        au.setCreateTime(new Date());
        au.setUpdateTime(new Date());
        au.setStatus(1);
        au.setPassword(aum.getPassword());

        // 权限相关
        au.setAccessPermission(aum.getAccessPermission());
        au.setAccessMenu(aum.getAccessMenu());

        adminUserMapper.insert(au);
        return new Message(BaseResult.SUCCESS);
    }

    /**
     * 修改密码
     *
     * @param upm
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Message updatePassword(UpdatePwdModel upm) {

        // 查询数据库是否有该id和密码的账号信息
        AdminUser au = new AdminUser();
        au.setId(upm.getId());
        au.setPassword(upm.getOldPwd());
        AdminUser adminUser = adminUserMapper.selectOne(au);
        if (adminUser == null || adminUser.getStatus() == 0) {
            return new Message(BaseResult.ERROR);
        }

        // 获取新密码明文
        String newPwd = upm.getNewPwd();
        if (!newPwd.equals(upm.getConfirmPwd())) {
            log.error("两次输入的密码不一致!");
            throw new BusinessException(ConstantsCode.TWO_PWD_NOT_MATCH, "两次输入的密码不一致");
        }
        if (newPwd.equals(upm.getOldPwd())) {
            log.error("新密码不能和旧密码一致!");
            throw new BusinessException(ConstantsCode.NEW_OLD_PWD_MATCH, "新密码不能和旧密码一致");
        }

        // 新密码明文aes加密, 修改db
        adminUser.setPassword(newPwd);
        adminUser.setUpdateTime(new Date());
        adminUserMapper.updateByPrimaryKey(adminUser);
        return new Message(BaseResult.SUCCESS);
    }

    /**
     * 列表查询, 排除自身账号
     * @param token
     * @param baseModel
     * @param startTime
     * @param endTime
     * @return
     */
    public PageInfo list(String token, BaseModel baseModel, String startTime, String endTime) {
        AdminUser adminUser = getByToken(token);
        if (adminUser != null) {
            PageHelper.startPage(baseModel.getPageNum(), baseModel.getPageSize());
            List<AdminUser> list = adminUserMapper.listByNotAdminId(startTime, endTime);
            list = getAccessPermissionAndMenuInfo(list);
            return PageUtil.create(list);
        }
        return new PageInfo();
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public AdminUser getById(Long id) {
        return adminUserMapper.selectByPrimaryKey(id);
    }

    /**
     * 管理员修改某个账号
     *
     * @param adminUserModel
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int update(AdminUserModel adminUserModel) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(adminUserModel.getId());
        if (adminUser == null) {
            throw new BusinessException(MsgResult.USER_NOT_FOUND);
        }
        BeanUtils.copyProperties(adminUserModel, adminUser);
        adminUser.setUpdateTime(new Date());

        // TODO: 2019/4/23  这里需要修改时把redis的缓存权限进行覆盖操作


        return adminUserMapper.updateByPrimaryKey(adminUser);
    }

    /**
     * 后端账号登陆
     *
     * @param alm
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public LoginSuccessModel adminLogin(AdminLoginModel alm) {
        AdminUser au = new AdminUser();
        au.setUseraccount(alm.getUseraccount());
        AdminUser adminUser = adminUserMapper.selectOne(au);
        if (adminUser == null) {
            throw new BusinessException(MsgResult.USERNAME_OR_PWD_ERROR);
        }
        if (!alm.getPassword().equals(adminUser.getPassword())) {
            throw new BusinessException(MsgResult.USERNAME_OR_PWD_ERROR);
        }
        if (adminUser.getStatus() != 1) {
            throw new BusinessException(MsgResult.STATUS_DEL_OR_FREEZE);
        }

        String token = UUID.randomUUID().toString().replace("-", "");
        String loginKey = Constants.ADMIN_LOGIN_SUCCESS_PREFIX + token;
        Map<String, String> loginMap = new HashMap<>(16);
        loginMap.put(LoginRedisEntity.USER_ID, adminUser.getId().toString());
        loginMap.put(LoginRedisEntity.ACCESS_TOKEN, token);
        loginMap.put(LoginRedisEntity.ADMIN_LOGIN_PERMISSIONS, adminUser.getAccessPermission());
        stringRedisTemplate.opsForHash().putAll(loginKey, loginMap);
        stringRedisTemplate.expire(loginKey, Constants.EXPIRE_TIME, TimeUnit.SECONDS);

        adminUser.setPassword(null);
        LoginSuccessModel loginSuccessModel = new LoginSuccessModel();
        loginSuccessModel.setUseraccount(adminUser.getUseraccount());
        loginSuccessModel.setUserId(adminUser.getId());
        loginSuccessModel.setUsername(adminUser.getUsername());
        loginSuccessModel.setToken(token);
        return loginSuccessModel;
    }

    /**
     * 根据token获取用户信息
     *
     * @param token
     * @return
     */
    public AdminUser getByToken(String token) {
        if (StringUtils.isBlank(token)) {
            throw new BusinessException(MsgResult.TOKEN_EMPTY);
        }

        // 读缓存获取用户ID
        Map<Object, Object> loginMap = stringRedisTemplate.opsForHash().entries(Constants.ADMIN_LOGIN_SUCCESS_PREFIX + token);
        if (CollectionUtils.isEmpty(loginMap)) {
            log.error("根据token在缓存中找不到登录信息");
            throw new BusinessException(MsgResult.TOKEN_INVALID);
        }
        Long userId = Long.valueOf(loginMap.get(LoginRedisEntity.USER_ID).toString());
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(userId);

        // 权限
        String permissions = adminUser.getAccessPermission();
        if (StringUtils.isNotBlank(permissions)) {
            String[] permissionArray = permissions.split(Constants.STRING_SPLITTER_SEMICOLON);
            List<String> permissionList = Arrays.asList(permissionArray);
            adminUser.setPermissionList(permissionList);
        }

        // 菜单
        String menu = adminUser.getAccessMenu();
        if (StringUtils.isNotBlank(menu)) {
            String[] menuArray = menu.split(Constants.STRING_SPLITTER_SEMICOLON);
            List<String> menuList = Arrays.asList(menuArray);
            adminUser.setMenuList(menuList);
        }

        return adminUser;
    }

    public boolean adminLogOut(String token) {
        if (StringUtils.isNotBlank(token)) {
            return stringRedisTemplate.delete(Constants.ADMIN_LOGIN_SUCCESS_PREFIX + token);
        }
        return true;
    }

    public List<AdminUser> export(String token, String startTime, String endTime) {
        AdminUser adminUser = getByToken(token);
        if (adminUser != null) {
            List<AdminUser> list = adminUserMapper.listByNotAdminId(startTime, endTime);
            list = getAccessPermissionAndMenuInfo(list);
            return list;
        }
        return new ArrayList<>(0);
    }

    public List<AdminUser> getAccessPermissionAndMenuInfo(List<AdminUser> list){
        if(!CollectionUtils.isEmpty(list)){
            list.forEach(e->{
                if (StringUtils.isNotBlank(e.getAccessPermission())) {
                    String[] permissionArray = e.getAccessPermission().split(Constants.STRING_SPLITTER_SEMICOLON);
                    List<String> permissionList = Arrays.asList(permissionArray);
                    e.setPermissionList(permissionList);
                }
                if (StringUtils.isNotBlank(e.getAccessMenu())) {
                    String[] menuArray = e.getAccessMenu().split(Constants.STRING_SPLITTER_SEMICOLON);
                    List<String> menuList = Arrays.asList(menuArray);
                    e.setMenuList(menuList);
                }
            });
        }
        return list;
    }
}
