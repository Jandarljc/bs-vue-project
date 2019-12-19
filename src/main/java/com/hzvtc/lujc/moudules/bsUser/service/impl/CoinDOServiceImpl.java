package com.hzvtc.lujc.moudules.bsUser.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzvtc.lujc.moudules.bsUser.mapper.CoinDOMapper;
import com.hzvtc.lujc.moudules.bsUser.pojo.CoinDO;
import com.hzvtc.lujc.moudules.bsUser.pojo.CoinDOExample;
import com.hzvtc.lujc.moudules.bsUser.service.CoinDOService;
import com.hzvtc.lujc.utils.SearchDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lujc
 * @ClassName: CoinDOServiceImpl
 * @Description: 游戏币交易
 * @date: 2019/12/18 12:02
 */
@Service
public class CoinDOServiceImpl implements CoinDOService {

    @Autowired
    private CoinDOMapper mapper;

    @Override
    public ResponseEntity<String> insertCoinDeal(CoinDO coinDO) {
        mapper.insert(coinDO);
        return null;
    }

    @Override
    public ResponseEntity<List<CoinDO>> getCoinData() {
        CoinDOExample example = new CoinDOExample();
        return ResponseEntity.ok(mapper.selectByExample(example));
    }

    @Override
    public PageInfo<CoinDO> pageList(SearchDTO<CoinDO> query) {
        CoinDOExample example = new CoinDOExample();
        PageHelper.startPage(query.getPage(), query.getRows());
        List<CoinDO> list = mapper.selectByExample(example);
        PageInfo<CoinDO> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

}
