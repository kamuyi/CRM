/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserService
 * Author:   Yuan
 * Date:     2018/10/13 11:29
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.shsxt.crm.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.constants.CrmConstant;
import com.shsxt.crm.dao.UserMapper;
import com.shsxt.crm.dao.UserRoleMapper;
import com.shsxt.crm.dto.UserDto;
import com.shsxt.crm.model.UserInfo;
import com.shsxt.crm.po.User;
import com.shsxt.crm.po.UserRole;
import com.shsxt.crm.query.UserQuery;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.Md5Util;
import com.shsxt.crm.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 *
 *
 *
 * @author Yuan
 * @create 2018/10/13
 * @since 1.0.0
 */
@Service
public class UserService extends BaseService<UserDto> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 添加或这更新用户
     *
     */
    public void saveOrUpdateUser(UserDto user,Integer[] roleIds){
        //校验参数
        checkUserParams(user);
        //补全参数
        user.setUpdateDate(new Date());
        Integer userId=user.getId();

        if(userId==null){
            //添加操作
            user.setIsValid(1);
            user.setCreateDate(new Date());
            //设置默认密码，要加密
            user.setUserPwd(Md5Util.encode("123456"));
            AssertUtil.isTrue(userMapper.save(user)<1, CrmConstant.OPS_FAILED_MSG);

        }else{
            //更新操作
            /**
             * 1. 用户名不允许修改
             * 2. 更新角色
             *      方案:
             *      123 -> 12  删除
             *      123 -> 1234 添加
             *      123 -> 134  删除添加
             *      先全部删除, 再全部添加
             */
            AssertUtil.isTrue(!user.getUserName().equals(userMapper.queryById(userId).getUserName()),"用户名不能修改");
            AssertUtil.isTrue(userMapper.update(user)<1, CrmConstant.OPS_FAILED_MSG);
            //先判断有没有角色
            Integer roleNum = userRoleMapper.queryUserRolesByUserId(userId);
            if(roleNum>0){
                //存在角色，先删除然后增加
                AssertUtil.isTrue(userRoleMapper.deleteUserRolesByUserId(userId)<1,CrmConstant.OPS_FAILED_MSG);
            }
        }

        /***
         * 角色添加
         * 1. 判断roleIds 是否为空
         * 2. 获取用户ID
         * */
        if(null!=roleIds && roleIds.length>0){
            //有角色ID
            Integer id=user.getId();
            List<UserRole> userRoles=new ArrayList<>();
            for(Integer roleId:roleIds){
                //创建userRole对象
                UserRole userRole=new UserRole();
                userRole.setUserId(id);
                userRole.setCreateDate(new Date());
                userRole.setUpdateDate(new Date());
                userRole.setRoleId(roleId);
                userRoles.add(userRole);
            }

            AssertUtil.isTrue(userRoleMapper.saveBatch(userRoles)<userRoles.size(),CrmConstant.OPS_FAILED_MSG);
        }

        }

    private void checkUserParams(User user) {
        String userName=user.getUserName();
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getTrueName()),"真实姓名为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getEmail()),"邮箱为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getPhone()),"联系方式为空");


    }

    /**
     * 修改密码
     */
    public void updateUserPwd(String oldPassword,
                              String newPassword,
                              String confirmPassword,
                              Integer userId){

        /**
         * 检查参数是否为空
         */
        checkUpdateUserPwd(oldPassword,newPassword,confirmPassword,userId);

        /**
         * 检查旧密码是否正确
         */
        User user=userMapper.queryById(userId);
        AssertUtil.isTrue(null==user, "用户不存在或已注销");
        AssertUtil.isTrue(!Md5Util.encode(oldPassword).equals(user.getUserPwd()),"旧密码不正确");

        /**
         * 存储新密码
         */
        //加密密码
        String encodeNewPassword = Md5Util.encode(newPassword);
        Integer integer = userMapper.updateUserPwd(encodeNewPassword, userId);
        AssertUtil.isTrue(integer<1,"修改密码失败");


    }

    private void checkUpdateUserPwd(String oldPassword, String newPassword, String confirmPassword, Integer userId) {
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword),"旧密码为空");
        AssertUtil.isTrue(StringUtils.isBlank(newPassword),"新密码为空");
        AssertUtil.isTrue(!newPassword.equals(confirmPassword),"两次密码不一致");
    }



    /**
     * 用户登录
     */
    public UserInfo login(String userName, String userPwd){

        /**
         * 1.校验参数非空
         * 2.判断用户名是否存在
         * 3.判断密码是否正确
         *
         */

        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名为空");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"密码为空");

        //通过用户名查询
        User user=userMapper.queryUserByName(userName);

        AssertUtil.isTrue(null==user,"用户名不存在或已注销");

        //判断密码，先加密
        AssertUtil.isTrue(!Md5Util.encode(userPwd).equals(user.getUserPwd()),"用户名或者密码不正确");

        return createUserInfo(user);

    }

    private UserInfo createUserInfo(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setRealName(user.getTrueName());
        userInfo.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userInfo.setUserName(user.getUserName());
        return userInfo;
    }

    public Map<String,Object> queryForPage(UserQuery baseQuery) throws DataAccessException {
        PageHelper.startPage(baseQuery.getPageNum(),baseQuery.getPageSize());
        List<UserDto> entities=userMapper.queryByParams(baseQuery);
        PageInfo<UserDto> pageInfo=new PageInfo<UserDto>(entities);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        entities = pageInfo.getList();
        /***
         * 需要把 1,2,3  变成 [1,2,3]
         * */
        for(UserDto userDto : entities){
            String roleIdsStr = userDto.getRoleIdsStr();
            if (null!=roleIdsStr){
                String[] roleIdArr = userDto.getRoleIdsStr().split(",");
                List<Integer> roleIdList = new ArrayList<>();
                for (String roleId : roleIdArr) {
                    roleIdList.add(Integer.valueOf(roleId));
                }
                userDto.setRoleIds(roleIdList);
            }

        }
        return map;
    }

    public void deleteUsers(Integer[] ids){
        if(null!=ids && ids.length>0){
            for (Integer id:ids){
                //删除用户
                AssertUtil.isTrue(userMapper.delete(id)<1,"用户删除失败");

                Integer rolesNum = userRoleMapper.queryUserRolesByUserId(id);
                if(rolesNum>0){
                    // 删除
                    AssertUtil.isTrue(userRoleMapper.deleteUserRolesByUserId(id)<rolesNum, CrmConstant.OPS_FAILED_MSG);
                }
            }
        }
    }

    public List<String> queryAllAclValueByUserId(Integer userId){
        return userMapper.queryAllAclValueByUserId(userId);
    }
}
