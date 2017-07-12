package com.ucf.first.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT_DISTINCT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.ucf.first.ao.BeetleArticle;
import com.ucf.first.ao.BeetleArticle_Example.Criteria;
import com.ucf.first.ao.BeetleArticle_Example.Criterion;
import com.ucf.first.ao.BeetleArticle_Example;
import java.util.List;
import java.util.Map;

public class BeetleArticleSqlProvider {

    public String countByExample(BeetleArticle_Example example) {
        BEGIN();
        SELECT("count(*)");
        FROM("beetle_article");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(BeetleArticle_Example example) {
        BEGIN();
        DELETE_FROM("beetle_article");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(BeetleArticle record) {
        BEGIN();
        INSERT_INTO("beetle_article");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getResName() != null) {
            VALUES("res_name", "#{resName,jdbcType=CHAR}");
        }
        
        if (record.getLevel() != null) {
            VALUES("level", "#{level,jdbcType=CHAR}");
        }
        
        if (record.getNodeId() != null) {
            VALUES("node_id", "#{nodeId,jdbcType=CHAR}");
        }
        
        if (record.getTitle() != null) {
            VALUES("title", "#{title,jdbcType=CHAR}");
        }
        
        if (record.getSubtitle() != null) {
            VALUES("subtitle", "#{subtitle,jdbcType=CHAR}");
        }
        
        if (record.getIntro() != null) {
            VALUES("intro", "#{intro,jdbcType=CHAR}");
        }
        
        if (record.getPic() != null) {
            VALUES("pic", "#{pic,jdbcType=CHAR}");
        }
        
        if (record.getViewOrder() != null) {
            VALUES("view_order", "#{viewOrder,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            VALUES("status", "#{status,jdbcType=TINYINT}");
        }
        
        if (record.getPubTime() != null) {
            VALUES("pub_time", "#{pubTime,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateUserId() != null) {
            VALUES("update_user_id", "#{updateUserId,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateTime() != null) {
            VALUES("update_time", "#{updateTime,jdbcType=INTEGER}");
        }
        
        if (record.getCreateUserId() != null) {
            VALUES("create_user_id", "#{createUserId,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=INTEGER}");
        }
        
        if (record.getAllow() != null) {
            VALUES("allow", "#{allow,jdbcType=CHAR}");
        }
        
        if (record.getContent() != null) {
            VALUES("content", "#{content,jdbcType=LONGVARCHAR}");
        }
        
        return SQL();
    }

    public String selectByExampleWithBLOBs(BeetleArticle_Example example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("res_name");
        SELECT("level");
        SELECT("node_id");
        SELECT("title");
        SELECT("subtitle");
        SELECT("intro");
        SELECT("pic");
        SELECT("view_order");
        SELECT("status");
        SELECT("pub_time");
        SELECT("update_user_id");
        SELECT("update_time");
        SELECT("create_user_id");
        SELECT("create_time");
        SELECT("allow");
        SELECT("content");
        FROM("beetle_article");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String selectByExample(BeetleArticle_Example example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("res_name");
        SELECT("level");
        SELECT("node_id");
        SELECT("title");
        SELECT("subtitle");
        SELECT("intro");
        SELECT("pic");
        SELECT("view_order");
        SELECT("status");
        SELECT("pub_time");
        SELECT("update_user_id");
        SELECT("update_time");
        SELECT("create_user_id");
        SELECT("create_time");
        SELECT("allow");
        FROM("beetle_article");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        BeetleArticle record = (BeetleArticle) parameter.get("record");
        BeetleArticle_Example example = (BeetleArticle_Example) parameter.get("example");
        
        BEGIN();
        UPDATE("beetle_article");
        
        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getResName() != null) {
            SET("res_name = #{record.resName,jdbcType=CHAR}");
        }
        
        if (record.getLevel() != null) {
            SET("level = #{record.level,jdbcType=CHAR}");
        }
        
        if (record.getNodeId() != null) {
            SET("node_id = #{record.nodeId,jdbcType=CHAR}");
        }
        
        if (record.getTitle() != null) {
            SET("title = #{record.title,jdbcType=CHAR}");
        }
        
        if (record.getSubtitle() != null) {
            SET("subtitle = #{record.subtitle,jdbcType=CHAR}");
        }
        
        if (record.getIntro() != null) {
            SET("intro = #{record.intro,jdbcType=CHAR}");
        }
        
        if (record.getPic() != null) {
            SET("pic = #{record.pic,jdbcType=CHAR}");
        }
        
        if (record.getViewOrder() != null) {
            SET("view_order = #{record.viewOrder,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            SET("status = #{record.status,jdbcType=TINYINT}");
        }
        
        if (record.getPubTime() != null) {
            SET("pub_time = #{record.pubTime,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateUserId() != null) {
            SET("update_user_id = #{record.updateUserId,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateTime() != null) {
            SET("update_time = #{record.updateTime,jdbcType=INTEGER}");
        }
        
        if (record.getCreateUserId() != null) {
            SET("create_user_id = #{record.createUserId,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{record.createTime,jdbcType=INTEGER}");
        }
        
        if (record.getAllow() != null) {
            SET("allow = #{record.allow,jdbcType=CHAR}");
        }
        
        if (record.getContent() != null) {
            SET("content = #{record.content,jdbcType=LONGVARCHAR}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExampleWithBLOBs(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("beetle_article");
        
        SET("id = #{record.id,jdbcType=INTEGER}");
        SET("res_name = #{record.resName,jdbcType=CHAR}");
        SET("level = #{record.level,jdbcType=CHAR}");
        SET("node_id = #{record.nodeId,jdbcType=CHAR}");
        SET("title = #{record.title,jdbcType=CHAR}");
        SET("subtitle = #{record.subtitle,jdbcType=CHAR}");
        SET("intro = #{record.intro,jdbcType=CHAR}");
        SET("pic = #{record.pic,jdbcType=CHAR}");
        SET("view_order = #{record.viewOrder,jdbcType=INTEGER}");
        SET("status = #{record.status,jdbcType=TINYINT}");
        SET("pub_time = #{record.pubTime,jdbcType=INTEGER}");
        SET("update_user_id = #{record.updateUserId,jdbcType=INTEGER}");
        SET("update_time = #{record.updateTime,jdbcType=INTEGER}");
        SET("create_user_id = #{record.createUserId,jdbcType=INTEGER}");
        SET("create_time = #{record.createTime,jdbcType=INTEGER}");
        SET("allow = #{record.allow,jdbcType=CHAR}");
        SET("content = #{record.content,jdbcType=LONGVARCHAR}");
        
        BeetleArticle_Example example = (BeetleArticle_Example) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("beetle_article");
        
        SET("id = #{record.id,jdbcType=INTEGER}");
        SET("res_name = #{record.resName,jdbcType=CHAR}");
        SET("level = #{record.level,jdbcType=CHAR}");
        SET("node_id = #{record.nodeId,jdbcType=CHAR}");
        SET("title = #{record.title,jdbcType=CHAR}");
        SET("subtitle = #{record.subtitle,jdbcType=CHAR}");
        SET("intro = #{record.intro,jdbcType=CHAR}");
        SET("pic = #{record.pic,jdbcType=CHAR}");
        SET("view_order = #{record.viewOrder,jdbcType=INTEGER}");
        SET("status = #{record.status,jdbcType=TINYINT}");
        SET("pub_time = #{record.pubTime,jdbcType=INTEGER}");
        SET("update_user_id = #{record.updateUserId,jdbcType=INTEGER}");
        SET("update_time = #{record.updateTime,jdbcType=INTEGER}");
        SET("create_user_id = #{record.createUserId,jdbcType=INTEGER}");
        SET("create_time = #{record.createTime,jdbcType=INTEGER}");
        SET("allow = #{record.allow,jdbcType=CHAR}");
        
        BeetleArticle_Example example = (BeetleArticle_Example) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(BeetleArticle record) {
        BEGIN();
        UPDATE("beetle_article");
        
        if (record.getResName() != null) {
            SET("res_name = #{resName,jdbcType=CHAR}");
        }
        
        if (record.getLevel() != null) {
            SET("level = #{level,jdbcType=CHAR}");
        }
        
        if (record.getNodeId() != null) {
            SET("node_id = #{nodeId,jdbcType=CHAR}");
        }
        
        if (record.getTitle() != null) {
            SET("title = #{title,jdbcType=CHAR}");
        }
        
        if (record.getSubtitle() != null) {
            SET("subtitle = #{subtitle,jdbcType=CHAR}");
        }
        
        if (record.getIntro() != null) {
            SET("intro = #{intro,jdbcType=CHAR}");
        }
        
        if (record.getPic() != null) {
            SET("pic = #{pic,jdbcType=CHAR}");
        }
        
        if (record.getViewOrder() != null) {
            SET("view_order = #{viewOrder,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            SET("status = #{status,jdbcType=TINYINT}");
        }
        
        if (record.getPubTime() != null) {
            SET("pub_time = #{pubTime,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateUserId() != null) {
            SET("update_user_id = #{updateUserId,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateTime() != null) {
            SET("update_time = #{updateTime,jdbcType=INTEGER}");
        }
        
        if (record.getCreateUserId() != null) {
            SET("create_user_id = #{createUserId,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=INTEGER}");
        }
        
        if (record.getAllow() != null) {
            SET("allow = #{allow,jdbcType=CHAR}");
        }
        
        if (record.getContent() != null) {
            SET("content = #{content,jdbcType=LONGVARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }

    protected void applyWhere(BeetleArticle_Example example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
}