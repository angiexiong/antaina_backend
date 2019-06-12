package com.antaina.service.admin;

import com.antaina.entity.admin.AdminPermission;
import com.antaina.enums.BaseResult;
import com.antaina.mapper.AdminPermissionMapper;
import com.antaina.resp.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AdminPermissionService {

    @Autowired
    private AdminPermissionMapper adminPermissionMapper;

    /**
     * 权限列表查询
     *
     * @return
     */
    public Message<List<AdminPermission>> list() {
        List<AdminPermission> adminPermissions = adminPermissionMapper.selectAll();
        return new Message<>(BaseResult.SUCCESS, adminPermissions);
    }

}
