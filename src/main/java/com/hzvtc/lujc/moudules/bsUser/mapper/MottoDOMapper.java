package com.hzvtc.lujc.moudules.bsUser.mapper;

import com.hzvtc.lujc.moudules.bsUser.pojo.MottoDO;
import com.hzvtc.lujc.moudules.bsUser.pojo.MottoDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MottoDOMapper {
    int countByExample(MottoDOExample example);

    int deleteByExample(MottoDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MottoDO record);

    int insertSelective(MottoDO record);

    List<MottoDO> selectByExample(MottoDOExample example);

    MottoDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MottoDO record, @Param("example") MottoDOExample example);

    int updateByExample(@Param("record") MottoDO record, @Param("example") MottoDOExample example);

    int updateByPrimaryKeySelective(MottoDO record);

    int updateByPrimaryKey(MottoDO record);
}