package com.ucf.first.dao;

import com.ucf.first.ao.NcfSetting;
import com.ucf.first.ao.NcfSetting_Example;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface NcfSettingMapper {
    @SelectProvider(type=NcfSettingSqlProvider.class, method="countByExample")
    int countByExample(NcfSetting_Example example);

    @DeleteProvider(type=NcfSettingSqlProvider.class, method="deleteByExample")
    int deleteByExample(NcfSetting_Example example);

    @Delete({
        "delete from ncf_setting",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into ncf_setting (id, set_name, ",
        "create_user, create_time, ",
        "set_value)",
        "values (#{id,jdbcType=INTEGER}, #{setName,jdbcType=CHAR}, ",
        "#{createUser,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{setValue,jdbcType=LONGVARCHAR})"
    })
    int insert(NcfSetting record);

    @InsertProvider(type=NcfSettingSqlProvider.class, method="insertSelective")
    int insertSelective(NcfSetting record);

    @SelectProvider(type=NcfSettingSqlProvider.class, method="selectByExampleWithBLOBs")
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="set_name", javaType=String.class, jdbcType=JdbcType.CHAR),
        @Arg(column="create_user", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="create_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="set_value", javaType=String.class, jdbcType=JdbcType.LONGVARCHAR)
    })
    List<NcfSetting> selectByExampleWithBLOBs(NcfSetting_Example example);

    @SelectProvider(type=NcfSettingSqlProvider.class, method="selectByExample")
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="set_name", javaType=String.class, jdbcType=JdbcType.CHAR),
        @Arg(column="create_user", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="create_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP)
    })
    List<NcfSetting> selectByExample(NcfSetting_Example example);

    @Select({
        "select",
        "id, set_name, create_user, create_time, set_value",
        "from ncf_setting",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="set_name", javaType=String.class, jdbcType=JdbcType.CHAR),
        @Arg(column="create_user", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="create_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="set_value", javaType=String.class, jdbcType=JdbcType.LONGVARCHAR)
    })
    NcfSetting selectByPrimaryKey(Integer id);

    @UpdateProvider(type=NcfSettingSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") NcfSetting record, @Param("example") NcfSetting_Example example);

    @UpdateProvider(type=NcfSettingSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") NcfSetting record, @Param("example") NcfSetting_Example example);

    @UpdateProvider(type=NcfSettingSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") NcfSetting record, @Param("example") NcfSetting_Example example);

    @UpdateProvider(type=NcfSettingSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(NcfSetting record);

    @Update({
        "update ncf_setting",
        "set set_name = #{setName,jdbcType=CHAR},",
          "create_user = #{createUser,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "set_value = #{setValue,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(NcfSetting record);

    @Update({
        "update ncf_setting",
        "set set_name = #{setName,jdbcType=CHAR},",
          "create_user = #{createUser,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(NcfSetting record);
}