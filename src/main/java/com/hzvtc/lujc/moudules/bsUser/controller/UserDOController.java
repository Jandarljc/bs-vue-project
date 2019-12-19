package com.hzvtc.lujc.moudules.bsUser.controller;

import com.hzvtc.lujc.moudules.bsUser.pojo.UserDO;
import com.hzvtc.lujc.moudules.bsUser.service.UserDOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lujc
 * @ClassName: UserDOController
 * @Description: 获取用户信息
 * @date: 2019/12/13 14:47
 */
@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserDOController {
    @Autowired
    private UserDOService userDOService;

    @PostMapping("/add")
    public ResponseEntity<Void> add(UserDO userDO) {
        try {
            ResponseEntity<Void> result = userDOService.createUser(userDO);
            return result;
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(String id) {
        userDOService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserDO userDO,
                                        HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        try {
            ResponseEntity<String> result = userDOService.login(userDO.getUsername(), userDO.getPassword(), request, response);
            map.put("code",20000);
            map.put("data",result);
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",40001);
            map.put("data",ResponseEntity.badRequest().body(e.getMessage()));
            return ResponseEntity.ok(map);
        }
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getUserByToken(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String token  = request.getParameter("token");
        ResponseEntity<UserDO> result = null;
        try {
            result = userDOService.getUserByToken(token);
            map.put("code",20000);
            map.put("data",result.getBody());
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",40001);
            map.put("data",new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
            return ResponseEntity.ok(map);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> logout(HttpServletRequest request,
                                       HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        String token = request.getParameter("token");
        try {
            userDOService.logout(request, response, token);
            map.put("code",20000);
            map.put("data","注销成功！");
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
