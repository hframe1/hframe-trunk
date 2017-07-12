package com.ucf.first.dao;

import com.ucf.first.ao.BeetleArticle;
import com.ucf.first.ao.BeetleArticle_Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BeetleArticleMapper {
    int countByExample(BeetleArticle_Example example);

    int deleteByExample(BeetleArticle_Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(BeetleArticle record);

    int insertSelective(BeetleArticle record);

    List<BeetleArticle> selectByExampleWithBLOBs(BeetleArticle_Example example);

    List<BeetleArticle> selectByExample(BeetleArticle_Example example);

    BeetleArticle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BeetleArticle record, @Param("example") BeetleArticle_Example example);

    int updateByExampleWithBLOBs(@Param("record") BeetleArticle record, @Param("example") BeetleArticle_Example example);

    int updateByExample(@Param("record") BeetleArticle record, @Param("example") BeetleArticle_Example example);

    int updateByPrimaryKeySelective(BeetleArticle record);

    int updateByPrimaryKeyWithBLOBs(BeetleArticle record);

    int updateByPrimaryKey(BeetleArticle record);
}