package com.hzvtc.lujc.moudules.bsUser.controller;

import com.hzvtc.lujc.moudules.bsUser.pojo.MottoDO;
import com.hzvtc.lujc.moudules.bsUser.service.MottoDOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lujc
 * @ClassName: MottoDOController
 * @Description: 随机获取名人名言
 * @date: 2019/12/17 17:28
 */
@Controller
@RequestMapping("/motto")
@CrossOrigin
public class MottoDOController {
    @Autowired
    private MottoDOService mottoDOService;

    @RequestMapping(value = "/sendRandomMottoData.json", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getUserByToken() {
        Map<String, Object> map = new HashMap<>();
        ResponseEntity<MottoDO> result = null;
        try {
            result = mottoDOService.getRandomMottoData();
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
}
