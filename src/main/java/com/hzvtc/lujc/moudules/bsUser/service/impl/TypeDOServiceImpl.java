package com.hzvtc.lujc.moudules.bsUser.service.impl;

import com.hzvtc.lujc.moudules.bsUser.mapper.TypeDOMapper;
import com.hzvtc.lujc.moudules.bsUser.pojo.CoinDOExample;
import com.hzvtc.lujc.moudules.bsUser.pojo.TypeDO;
import com.hzvtc.lujc.moudules.bsUser.pojo.TypeDOExample;
import com.hzvtc.lujc.moudules.bsUser.service.TypeDOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lujc
 * @ClassName: TypeDOServiceImpl
 * @Description:
 * @date: 2019/12/18 17:09
 */
@Service
public class TypeDOServiceImpl implements TypeDOService {
    @Autowired
    private TypeDOMapper mapper;

    @Override
    public ResponseEntity<List<TypeDO>> getTypeData() {
        TypeDOExample example = new TypeDOExample();
        return ResponseEntity.ok(mapper.selectByExample(example));
    }
}
