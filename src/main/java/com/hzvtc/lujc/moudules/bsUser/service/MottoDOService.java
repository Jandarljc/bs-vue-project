package com.hzvtc.lujc.moudules.bsUser.service;

import com.hzvtc.lujc.moudules.bsUser.pojo.MottoDO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @Description: 名人名言接口
 * @Auther: 陆金超
 * @Date: 2019/12/17 17:22
 * @Version: V1.0
 */
@Service
public interface MottoDOService {
    ResponseEntity<MottoDO> getRandomMottoData();
}
