package com.hzvtc.lujc.moudules.bsUser.controller;

import com.alibaba.fastjson.JSONObject;
import com.hzvtc.lujc.moudules.bsUser.pojo.UserDO;
import com.hzvtc.lujc.moudules.bsUser.service.UserDOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
            ResponseEntity<String> result = userDOService.login(userDO, request, response);
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

    @RequestMapping(value = "/sendUserList.json", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getUserList() {
        Map<String, Object> map = new HashMap<>();
        ResponseEntity<List<String>> result = null;
        try {
            result =userDOService.getUserList();
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

    @RequestMapping("/sendUploadAvatarData.json")
    public ResponseEntity<Map<String, Object>> up(@RequestParam("file") MultipartFile picture, HttpServletRequest request) {
        System.out.println(picture.getContentType());
        //获取文件在服务器的储存位置
        String path = request.getSession().getServletContext().getRealPath("/upload");
        File filePath = new File(path);
        System.out.println("文件的保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }
        //获取原始文件名称(包含格式)
        String originalFileName = picture.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型：" + type);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        //设置文件新名称: 当前时间+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date + name + "." + type;
        System.out.println("新文件名称：" + fileName);

        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);

        Map<String, Object> map = new HashMap<>();
        ResponseEntity<String> result = null;
        //将文件保存到服务器指定位置
        try {
            picture.transferTo(targetFile);
            System.out.println("上传成功");
            result = ResponseEntity.ok(filePath+"\\"+fileName);
            map.put("code",20000);
            map.put("data",result.getBody());
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            //将文件在服务器的存储路径返回
            e.printStackTrace();
            System.out.println("上传失败");
            map.put("code",40001);
            map.put("data",new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
            return ResponseEntity.ok(map);
        }
    }
}
