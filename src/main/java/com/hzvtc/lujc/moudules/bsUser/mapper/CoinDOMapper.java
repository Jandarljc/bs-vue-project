package com.hzvtc.lujc.moudules.bsUser.mapper;

import com.hzvtc.lujc.moudules.bsUser.pojo.CoinDO;
import com.hzvtc.lujc.moudules.bsUser.pojo.CoinDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface CoinDOMapper {
    int countByExample(CoinDOExample example);

    int deleteByExample(CoinDOExample example);

    int deleteByPrimaryKey(String id);

    int insert(CoinDO record);

    int insertSelective(CoinDO record);

    List<CoinDO> selectByExample(CoinDOExample example);

    CoinDO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CoinDO record, @Param("example") CoinDOExample example);

    int updateByExample(@Param("record") CoinDO record, @Param("example") CoinDOExample example);

    int updateByPrimaryKeySelective(CoinDO record);

    int updateByPrimaryKey(CoinDO record);
}