package com.mao.mapper;

import com.mao.entity.security.UserDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mao by 14:44 2020/3/13
 */
@Repository
@Mapper
public interface SystemMapper {

    /**
     * 根据app_id查询用户详情
     * @param key app_id
     * @return UserDetails
     */
    UserDetails getUserDetail(@Param("key") long key);

    /**
     * 根据角色id查询权限列表
     * @param role 角色id
     * @return 权限列表
     */
    List<String> getPermissions(@Param("role") long role);

}