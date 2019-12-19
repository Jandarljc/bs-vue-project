package com.hzvtc.lujc.moudules.bsUser.service;

import com.hzvtc.lujc.moudules.bsUser.pojo.UserDO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:  用户信息接口
 * @Auther: 陆金超
 * @Date: 2019/12/13 14:33
 * @Version: V1.0
 */
@Service
public interface UserDOService {
    ResponseEntity<Void> createUser(UserDO userDO);
    void delete(String id);
    ResponseEntity<String> login(String username, String password, HttpServletRequest request, HttpServletResponse response);
    ResponseEntity<UserDO> getUserByToken(String token);
    ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response, String token);
}
