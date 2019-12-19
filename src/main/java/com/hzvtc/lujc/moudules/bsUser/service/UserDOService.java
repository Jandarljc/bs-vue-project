package com.hzvtc.lujc.moudules.bsUser.service;

import com.hzvtc.lujc.moudules.bsUser.pojo.UserDO;
import com.oracle.deploy.update.UpdateInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description:  用户信息接口
 * @Auther: 陆金超
 * @Date: 2019/12/13 14:33
 * @Version: V1.0
 */
@Service
public interface UserDOService {
    void delete(String id);
    ResponseEntity<String> login(UserDO userDO, HttpServletRequest request, HttpServletResponse response);
    ResponseEntity<UserDO> getUserByToken(String token);
    ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response, String token);
    ResponseEntity<List<String>> getUserList();
    String updateInfo(String token,String avatar);
}
