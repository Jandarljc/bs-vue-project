package com.hzvtc.lujc.moudules.bsUser.mapper;

import com.hzvtc.lujc.moudules.bsUser.pojo.TypeDO;
import com.hzvtc.lujc.moudules.bsUser.pojo.TypeDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface TypeDOMapper {
    int countByExample(TypeDOExample example);

    int deleteByExample(TypeDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TypeDO record);

    int insertSelective(TypeDO record);

    List<TypeDO> selectByExample(TypeDOExample example);

    TypeDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TypeDO record, @Param("example") TypeDOExample example);

    int updateByExample(@Param("record") TypeDO record, @Param("example") TypeDOExample example);

    int updateByPrimaryKeySelective(TypeDO record);

    int updateByPrimaryKey(TypeDO record);
}