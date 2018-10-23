package com.shsxt.crm.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.dto.UserDto;
import com.shsxt.crm.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 使用DTO为了回显角色
 */
@Repository
public interface UserMapper extends BaseDao<UserDto>{
    public User queryUserByName(String userName);
    public Integer updateUserPwd(@Param("userPwd") String userPwd, @Param("id") Integer id);

    public List<String> queryAllAclValueByUserId(Integer userId);
}