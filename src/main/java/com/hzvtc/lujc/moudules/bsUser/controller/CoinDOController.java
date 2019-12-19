package com.hzvtc.lujc.moudules.bsUser.controller;

import com.github.pagehelper.PageInfo;
import com.hzvtc.lujc.moudules.bsUser.pojo.CoinDO;
import com.hzvtc.lujc.moudules.bsUser.pojo.MottoDO;
import com.hzvtc.lujc.moudules.bsUser.service.CoinDOService;
import com.hzvtc.lujc.utils.PageResult;
import com.hzvtc.lujc.utils.SearchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lujc
 * @ClassName: CoinDOController
 * @Description: 游戏币交易
 * @date: 2019/12/18 12:07
 */
@Controller
@RequestMapping("/coin")
@CrossOrigin
public class CoinDOController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoinDOController.class);

    @Autowired
    private CoinDOService coinDOService;

    @RequestMapping(value = "/sendAddCoinData.json", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object insertCoinData(@RequestBody CoinDO coinDO,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        ResponseEntity<String> result = null;
        try {
            result = coinDOService.insertCoinDeal(coinDO);
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

    @RequestMapping(value = "/sendGetCoinData.json", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getCoinAllData(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        ResponseEntity<List<CoinDO>> result = null;
        try {
            result = coinDOService.getCoinData();
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

    @RequestMapping(value = "/sendGetCoinPageData.json", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> pageResult(SearchDTO<CoinDO> query) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("调用订单分页列表查询接口");
        }
        Map<String, Object> map = new HashMap<>();
        try {
            PageInfo<CoinDO> pageInfo = coinDOService.pageList(query);
            PageResult<CoinDO> pageResult = new PageResult<CoinDO>(pageInfo.getPageNum(), pageInfo.getPageSize(),
                    pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
            map.put("code",20000);
            map.put("data",pageResult);
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",40001);
            map.put("data",new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
            return ResponseEntity.ok(map);
        }
    }
}
