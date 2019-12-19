package com.hzvtc.lujc.moudules.bsUser.service;

import com.hzvtc.lujc.moudules.bsUser.pojo.CoinDO;
import com.hzvtc.lujc.moudules.bsUser.pojo.TypeDO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODO
 * @Auther: 陆金超
 * @Date: 2019/12/18 17:09
 * @Version: V1.0
 */
@Service
public interface TypeDOService {
    ResponseEntity<List<TypeDO>> getTypeData();
}
