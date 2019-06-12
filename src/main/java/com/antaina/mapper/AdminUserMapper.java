package com.antaina.mapper;

import com.antaina.entity.admin.AdminUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface AdminUserMapper extends Mapper<AdminUser> {

    List<AdminUser> listByNotAdminId(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
