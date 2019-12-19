package com.hzvtc.lujc.moudules.bsUser.service;

import com.github.pagehelper.PageInfo;
import com.hzvtc.lujc.moudules.bsUser.pojo.CoinDO;
import com.hzvtc.lujc.utils.SearchDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODO
 * @Auther: 陆金超
 * @Date: 2019/12/18 12:02
 * @Version: V1.0
 */
@Service
public interface CoinDOService {
    ResponseEntity<String> insertCoinDeal(CoinDO coinDO);
    ResponseEntity<List<CoinDO>> getCoinData();
    PageInfo<CoinDO> pageList(SearchDTO<CoinDO> query);
}
