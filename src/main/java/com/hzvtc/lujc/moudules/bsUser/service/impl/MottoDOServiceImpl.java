package com.hzvtc.lujc.moudules.bsUser.service.impl;

import com.hzvtc.lujc.moudules.bsUser.mapper.MottoDOMapper;
import com.hzvtc.lujc.moudules.bsUser.pojo.MottoDO;
import com.hzvtc.lujc.moudules.bsUser.service.MottoDOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author lujc
 * @ClassName: MottoDOServiceImpl
 * @Description: 名人名言
 * @date: 2019/12/17 17:23
 */
@Service
public class MottoDOServiceImpl implements MottoDOService {
    @Autowired
    private MottoDOMapper mapper;

    @Override
    public ResponseEntity<MottoDO> getRandomMottoData() {
        int min = 601,max = 700;
        Long id = new Double(Math.random()*(max-min)+min).longValue();
        return ResponseEntity.ok(mapper.selectByPrimaryKey(id));
    }
}
