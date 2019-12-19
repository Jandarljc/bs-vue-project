package com.hzvtc.lujc.moudules.bsUser.controller;

import com.hzvtc.lujc.moudules.bsUser.pojo.CoinDO;
import com.hzvtc.lujc.moudules.bsUser.pojo.TypeDO;
import com.hzvtc.lujc.moudules.bsUser.service.CoinDOService;
import com.hzvtc.lujc.moudules.bsUser.service.TypeDOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lujc
 * @ClassName: TypeDOController
 * @Description: 游戏种类
 * @date: 2019/12/18 17:07
 */
@Controller
@RequestMapping("/type")
@CrossOrigin
public class TypeDOController {
    @Autowired
    private TypeDOService typeDOService;

    @RequestMapping(value = "/sendGetTypeData.json", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getCoinAllData() {
        Map<String, Object> map = new HashMap<>();
        ResponseEntity<List<TypeDO>> result = null;
        try {
            result = typeDOService.getTypeData();
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
