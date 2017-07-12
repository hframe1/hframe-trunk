package com.hframework.generator.web.sql.reverse;

import java.io.IOException;
import java.util.*;

import com.hframework.common.frame.cache.PropertyConfigurerUtils;

import com.hframework.common.util.*;
import com.hframework.common.ext.CollectionUtils;
import com.hframe.domain.model.HfmdEntity;
import com.hframe.domain.model.HfmdEntityAttr;
import com.hframe.domain.model.HfpmModule;
import com.hframe.domain.model.HfpmProgram;
import com.hframework.generator.web.constants.CreatorConst;
import com.hframework.generator.web.bean.HfModelContainer;
import com.hframework.generator.enums.HfmdEntityAttr1AttrTypeEnum;

/**
 * sql解析工具类
 * @author zhangqh6
 *
 */
public class SQLParseUtil {

	public static final String projectBasePath =
			PropertyConfigurerUtils.getProperty(CreatorConst.PROJECT_BASE_FILE_PATH);

	private static HfModelContainer modelContainer = null;

	private static long programId = 0L;
	private static long moduleId = 0L;

	private static long opId = 0L;
	private static Date curDate = null;

	private static int delFlag = 0;

	private static final String CREATE_TABLE = "create table";
	private static final String COMMENT = "comment";
	private static final String ADD_CONSTRAINT = "add constraint";
	private static final String ADD_COLUMN  = "add column";
	private static final String MODIFY_COLUMN  = "modify column";

	private static final String REFERENCES = "references";
	private static final String FOREIGN_KEY = "foreign key";
	private static final String ALTER_TABLE = "alter table";
	private static final String DROP_TABLE_IF_EXISTS = "drop table if exists";


	public static String parseSQL2Model(String content) {

		if(modelContainer == null) {
			modelContainer = new HfModelContainer();
		}

		if(StringUtils.isNotBlank(content)) {
			String[] cmds = content.split(";");
			if(cmds != null && cmds.length > 0) {
				for(String cmd : cmds) {
//					Pattern pattern = Pattern.compile("/\\*[ a-zA-Z:0-9_\\=. /]+\\*/");
//					Matcher matcher=null;
//					matcher=pattern.matcher(cmd);
//					
//					while(matcher.find()){
////						System.out.println(matcher.group());
//					}

					parseSQL2ModelContainer(modelContainer,
							cmd.replaceAll("/\\*[ a-zA-Z:0-9_\\=. /]+\\*/", "").replaceAll("`", "").trim());

					System.out.println(cmd.replaceAll("/\\*[ a-zA-Z:0-9_\\=. /]+\\*/", "").trim());
				}
			}
		}

		System.out.println(modelContainer);



		return null;
	}

	private static void parseSQL2ModelContainer(
			HfModelContainer modelContainer, String cmdStr) {
		if(cmdStr.toLowerCase().startsWith(CREATE_TABLE)) {
			String tableName = cmdStr.substring(CREATE_TABLE.length(),cmdStr.indexOf("(")).trim();
			HfmdEntity entity = modelContainer.getEntity(tableName);


			String tableContent =  cmdStr.substring(cmdStr.indexOf("(")+1,cmdStr.lastIndexOf(")")).trim();
			String tablesDesc = cmdStr.replace(tableContent, "");
//			System.out.println("==>" + tablesDesc + ";" +tablesDesc.toLowerCase().indexOf(COMMENT + "="));
			if(tablesDesc.toLowerCase().indexOf(COMMENT + "=") > 0) {
//				System.out.println("==>" + tablesDesc + ";" +tablesDesc.toLowerCase().indexOf(COMMENT + "="));
				String tmpString = tablesDesc.substring(tablesDesc.toLowerCase().indexOf(COMMENT + "=") + 8);
				tmpString = tmpString.substring(tmpString.indexOf("'") + 1);
//				System.out.println("==>" + tmpString);
				tmpString = tmpString.substring(0, tmpString.indexOf("'"));
				entity.setHfmdEntityName(tmpString);
				entity.setHfmdEntityDesc(tmpString);

//				System.out.println("==>" + tmpString);
			}
			if(tablesDesc.replaceFirst(tableName,"").toLowerCase().indexOf(COMMENT + " ") > 0) {
//				System.out.println("==>" + tablesDesc + ";" +tablesDesc.toLowerCase().indexOf(COMMENT + "="));
				String tmpString = tablesDesc.substring(tablesDesc.toLowerCase().indexOf(COMMENT + " ") + 8);
				tmpString = tmpString.substring(tmpString.indexOf("'") + 1);
//				System.out.println("==>" + tmpString);
				tmpString = tmpString.substring(0, tmpString.indexOf("'"));
				entity.setHfmdEntityName(tmpString);
				entity.setHfmdEntityDesc(tmpString);

//				System.out.println("==>" + tmpString);
			}

			String[] splitStrs = tableContent.split(",");
			String columnInfo = "";
			for (String splitStr : splitStrs) {
				columnInfo += splitStr;

				//表明为描述中带有",",该行信息并没有正常结束
				if(columnInfo.toLowerCase().indexOf(COMMENT)> 2 &&  columnInfo.indexOf("'") == columnInfo.lastIndexOf("'")) {
					continue;
				}

				//表明可能为字段类型可能为numric(10,2)形式，没有正常结束
				if(columnInfo.contains("(") && !columnInfo.contains(")")) {
                    columnInfo += ",";
					continue;
				}

				String comment = "";
				if(columnInfo.toLowerCase().indexOf(COMMENT)> 2){
					comment = columnInfo.substring(columnInfo.toLowerCase().indexOf(COMMENT)+7).trim();
					columnInfo = columnInfo.substring(0,columnInfo.toLowerCase().indexOf(COMMENT));
				}

				parseColumnInfo(entity,columnInfo,comment);
				columnInfo = "";
			}

		}else if(cmdStr.toLowerCase().startsWith(ALTER_TABLE)) {
			cmdStr = cmdStr.substring(ALTER_TABLE.length()).trim();
			String tableName = cmdStr.substring(0,cmdStr.indexOf(" ")).trim();
			String alterInfo = cmdStr.substring(cmdStr.indexOf(" ")).trim();

			HfmdEntity entity = modelContainer.getEntity(tableName);
			parseAlterInfo(entity,alterInfo);

		}else if(cmdStr.toLowerCase().startsWith(DROP_TABLE_IF_EXISTS)) {
			String tableName = cmdStr.substring(DROP_TABLE_IF_EXISTS.length()).trim();
			modelContainer.removeEntity(tableName);
		}


	}

	private static void parseAlterInfo(HfmdEntity entity, String alterInfo) {
		if(alterInfo.toLowerCase().contains(ADD_COLUMN)) {
			String tableContent = alterInfo.substring(ADD_COLUMN.length());
			String[] splitStrs = tableContent.split(",");
			String columnInfo = "";
			for (String splitStr : splitStrs) {
				columnInfo += splitStr;

				//表明为描述中带有",",该行信息并没有正常结束
				if(columnInfo.toLowerCase().indexOf(COMMENT)> 2 &&  columnInfo.indexOf("'") == columnInfo.lastIndexOf("'")) {
					continue;
				}

				//表明可能为字段类型可能为numric(10,2)形式，没有正常结束
				if(columnInfo.contains("(") && !columnInfo.contains(")")) {
					columnInfo += ",";
					continue;
				}

				String comment = "";
				if(columnInfo.toLowerCase().indexOf(COMMENT)> 2){
					comment = columnInfo.substring(columnInfo.toLowerCase().indexOf(COMMENT)+7).trim();
					columnInfo = columnInfo.substring(0,columnInfo.toLowerCase().indexOf(COMMENT));
				}

				parseColumnInfo(entity,columnInfo,comment);
				columnInfo = "";
			}

		}else if(alterInfo.toLowerCase().contains(MODIFY_COLUMN)) {
			String tableContent = alterInfo.substring(MODIFY_COLUMN.length());
			String[] splitStrs = tableContent.split(",");
			String columnInfo = "";
			for (String splitStr : splitStrs) {
				columnInfo += splitStr;

				//表明为描述中带有",",该行信息并没有正常结束
				if(columnInfo.toLowerCase().indexOf(COMMENT)> 2 &&  columnInfo.indexOf("'") == columnInfo.lastIndexOf("'")) {
					continue;
				}

				//表明可能为字段类型可能为numric(10,2)形式，没有正常结束
				if(columnInfo.contains("(") && !columnInfo.contains(")")) {
					columnInfo += ",";
					continue;
				}

				String comment = "";
				if(columnInfo.toLowerCase().indexOf(COMMENT)> 2){
					comment = columnInfo.substring(columnInfo.toLowerCase().indexOf(COMMENT)+7).trim();
					columnInfo = columnInfo.substring(0,columnInfo.toLowerCase().indexOf(COMMENT));
				}

				parseColumnInfo(entity,columnInfo,comment);
				columnInfo = "";
			}

		}else {
			if(alterInfo.toLowerCase().contains(COMMENT)) {
				entity.setHfmdEntityName(alterInfo.substring(alterInfo.toLowerCase().indexOf(COMMENT) + 7).trim().replaceAll("'",""));
				entity.setHfmdEntityDesc(alterInfo.substring(alterInfo.toLowerCase().indexOf(COMMENT)+7).trim().replaceAll("'",""));

			}
			if(alterInfo.toLowerCase().contains(ADD_CONSTRAINT)) {
				String attrName = alterInfo.substring(
						alterInfo.toLowerCase().indexOf(FOREIGN_KEY + " (")+13,alterInfo.indexOf(")")).trim();
				String relEntityInfo = alterInfo.substring(
						alterInfo.toLowerCase().indexOf(REFERENCES)+10,alterInfo.lastIndexOf(")")).trim();
				String relEntityName = relEntityInfo.split("\\(")[0].trim();
				String relEntityAttrName = relEntityInfo.split("\\(")[1].trim();
				HfmdEntityAttr entityAttr = modelContainer.getEntityAttr(entity.getHfmdEntityCode(), attrName);
				HfmdEntityAttr relEntityAttr = modelContainer.getEntityAttr(relEntityName, relEntityAttrName);
				entityAttr.setRelHfmdEntityAttrId(relEntityAttr.getHfmdEntityAttrId());
			}
		}


	}

	private static void parseColumnInfo(HfmdEntity entity,
										String columnInfo, String comment) {

		if(comment != null) {
			comment = comment.replaceAll("'","");
		}
		String[] splitStrs = columnInfo.trim().split("[ ]+");
		String columnName;
		String columnType = null;
		String size = null;
		boolean isNull = true;
		boolean isPK = false;

		if(splitStrs == null) {
			return;
		}

		//设置主键
		if(splitStrs[0].toLowerCase().matches("primary") && splitStrs[1].toLowerCase().matches("key")) {
			isPK = true;
			columnName = columnInfo.substring(
					columnInfo.indexOf("(")+1,columnInfo.indexOf(")")).trim();

            for (String tempColumnName : columnName.split(",")) {
                HfmdEntityAttr entityAttr = modelContainer.getEntityAttr(entity.getHfmdEntityCode(),tempColumnName.replaceAll("`",""));
                entityAttr.setIspk(isPK?1:0);
            }
			return;

		}else if(columnInfo.toUpperCase().trim().startsWith("KEY") ) {
            //TODO
            return;

        }else if(columnInfo.toUpperCase().contains("CONSTRAINT") && columnInfo.toUpperCase().contains("FOREIGN KEY")) {
            //TODO
            return;


        }else if(columnInfo.toUpperCase().contains("UNIQUE KEY")) {
            //TODO
            return;
        } else {
			columnName = splitStrs[0];
			columnType = splitStrs[1];

			if(splitStrs[1].contains("(")) {
				size = splitStrs[1].substring(
						splitStrs[1].indexOf("(")+1,splitStrs[1].indexOf(")")).trim();
				columnType = splitStrs[1].substring(0,splitStrs[1].indexOf("("));
			}

			if(columnInfo.toLowerCase().matches("not[ ]+null")) {
				isNull = false;
			}

			if(RegexUtils.find(columnInfo.toLowerCase(), "primary[ ]+key").length > 0) {
				isPK = true;
			}

			HfmdEntityAttr entityAttr = modelContainer.getEntityAttr(entity.getHfmdEntityCode(),columnName);
			entityAttr.setAttrType(HfmdEntityAttr1AttrTypeEnum.getIndex(columnType));
			entityAttr.setCreateTime(DateUtils.getCurrentDate());
			entityAttr.setIspk(isPK ? 1 : 0);
			entityAttr.setNullable(isNull ? 1 : 0);
			entityAttr.setSize(size);
			entityAttr.setIsBusiAttr(1);
			entityAttr.setHfmdEntityAttrName(comment!=null&&comment.length()>16?comment.substring(0,16):comment);
			entityAttr.setHfmdEntityAttrDesc(comment);
		}
	}
	public static HfModelContainer parseModelContainerFromSQLFile(String filePath, String programCode, String programName, String moduleCode, String moduleName) throws IOException {
		return parseModelContainerFromSQL(FileUtils.readFile(filePath),programCode,programName,moduleCode,moduleName);

	}

	public static HfModelContainer parseModelContainerFromSQL(String sql, String programCode, String programName, String moduleCode, String moduleName) {
		opId = 999L;
		curDate = new Date();
		programId = CommonUtils.uuidL();
		moduleId = CommonUtils.uuidL();
		modelContainer = null;

		//创建项目信息
		createHfpmProgram(programCode, programName);

		//创建模块信息
		createHfpmModule(moduleCode, moduleName);

		//创建实体信息
		parseSQL2Model(sql);

		autoFullEntityContainer();
		return modelContainer;

	}

	public static void main(String[] args) throws IOException {
		String rootClassPath = Thread.currentThread().getContextClassLoader ().getResource("").getPath();
		System.out.println(rootClassPath);
		String filePath = rootClassPath + "\\reversesql\\sql.sql";
		String programCode = "hframe";
		String programeName = "框架";
		String moduleCode = "hframe";
		String moduleName = "框架";
		HfModelContainer hfModelContainer = parseModelContainerFromSQLFile(
				filePath, programCode, programeName, moduleCode, moduleName);
		System.out.println(hfModelContainer);
	}

	private static void createHfpmModule(String moduleCode, String moduleName) {
		if(StringUtils.isNotBlank(moduleCode)) {
			HfpmModule hfpmModule = new HfpmModule();
			hfpmModule.setHfpmModuleId(moduleId);
			hfpmModule.setHfpmModuleName(moduleName);
			hfpmModule.setHfpmModuleCode(moduleCode);
			hfpmModule.setHfpmModuleDesc(moduleName);
			hfpmModule.setHfpmProgramId(programId);
			hfpmModule.setOpId(opId);
			hfpmModule.setCreateTime(curDate);
			hfpmModule.setModifyOpId(opId);
			hfpmModule.setModifyTime(curDate);
			hfpmModule.setDelFlag(delFlag);
			modelContainer.getModuleMap().put(moduleId, hfpmModule);
		}
	}

	private static void createHfpmProgram(String programCode, String programName) {

		if(modelContainer == null) {
			modelContainer = new HfModelContainer();
		}
		HfpmProgram hfpmProgram = new HfpmProgram();
		hfpmProgram.setHfpmProgramId(programId);
		hfpmProgram.setHfpmProgramName(programName);
		hfpmProgram.setHfpmProgramCode(programCode);
		hfpmProgram.setHfpmProgramDesc(programName);
		hfpmProgram.setOpId(opId);
		hfpmProgram.setCreateTime(curDate);
		hfpmProgram.setModifyOpId(opId);
		hfpmProgram.setModifyTime(curDate);
		hfpmProgram.setDelFlag(delFlag);
		modelContainer.setProgram(hfpmProgram);
	}

	private static void autoFullEntityContainer() {
		if(modelContainer != null) {
			//实体信息<entityName,HfmdEntity>
			if(modelContainer.getEntityMap() != null) {
				Map<String,HfmdEntity> entityMap = modelContainer.getEntityMap();
				for (HfmdEntity hfmdEntity : entityMap.values()) {
					hfmdEntity.setHfmdEntityType(0);//默认都为实体类
					hfmdEntity.setHfpmProgramId(programId);
					hfmdEntity.setHfpmModuleId(moduleId);
					hfmdEntity.setCreateTime(curDate);
					hfmdEntity.setOpId(opId);
					hfmdEntity.setModifyOpId(opId);
					hfmdEntity.setModifyTime(curDate);
					hfmdEntity.setDelFlag(delFlag);
				}
			}


			//实体属性信息<entityName.entityAttrName,HfmdEntityAttr>
			if(modelContainer.getEntityAttrMap() != null) {
				Map<String,HfmdEntityAttr> entityAttrMap = modelContainer.getEntityAttrMap();
				for (String attrId : entityAttrMap.keySet()) {
					HfmdEntityAttr hfmdEntityAttr = entityAttrMap.get(attrId);
					hfmdEntityAttr.setHfpmProgramId(programId);
					hfmdEntityAttr.setHfpmModuleId(moduleId);
					hfmdEntityAttr.setIsBusiAttr(1);
					hfmdEntityAttr.setOpId(opId);
					hfmdEntityAttr.setCreateTime(curDate);
					hfmdEntityAttr.setModifyOpId(opId);
					hfmdEntityAttr.setModifyTime(curDate);
					hfmdEntityAttr.setDelFlag(delFlag);
					if(modelContainer.getEntityMap().get(attrId.split("\\.")[0]) != null) {
						hfmdEntityAttr.setHfmdEntityId(
								modelContainer.getEntityMap().get(attrId.split("\\.")[0]).getHfmdEntityId());
					}
				}
			}

		}
	}

    public static List<String> getSqlFromHfModelContainer(HfModelContainer container) {
        List<String> result = new ArrayList<String>();

        //将所有属性分组
        Map<String, HfmdEntityAttr> entityAttrMap = container.getEntityAttrMap();
        Map<String,List<HfmdEntityAttr>> map = new HashMap<String, List<HfmdEntityAttr>>();
        for (String entityCodeEntityAttrCode : entityAttrMap.keySet()) {
            String entityCode = entityCodeEntityAttrCode.substring(0, entityCodeEntityAttrCode.indexOf("."));
            CollectionUtils.addMapValue(map,entityCode,entityAttrMap.get(entityCodeEntityAttrCode));
        }

        //将所有实体与属性进行组合与排序
        Map<String, HfmdEntity> entityMap = container.getEntityMap();
        for (String entityCode : entityMap.keySet()) {
            HfmdEntity entity = entityMap.get(entityCode);
            List<HfmdEntityAttr> hfmdEntityAttrList = CollectionUtils.getMapValue(map, entityCode);
            String sql = getSql(container.getContainerType(), entityCode, entity, hfmdEntityAttrList);
            System.out.println(sql);
            result.add(sql);
            map.remove(entityCode);
        }

        for (String entityCode : map.keySet()) {
            List<HfmdEntityAttr> hfmdEntityAttrList = CollectionUtils.getMapValue(map, entityCode);
            String sql = getSql(container.getContainerType(), entityCode, null, hfmdEntityAttrList);
            System.out.println(sql);
            result.add(sql);
        }

        return result;
    }

    private static String getSql(int type, String entityCode, HfmdEntity entity, List<HfmdEntityAttr> hfmdEntityAttrList) {

        Collections.sort(hfmdEntityAttrList, new Comparator<HfmdEntityAttr>() {
            public int compare(HfmdEntityAttr o1, HfmdEntityAttr o2) {
                return o1.getPri().compareTo(o2.getPri());
            }
        });

        StringBuffer sb=new StringBuffer();
        if(type == HfModelContainer.TYPE_ADD) {
            if(entity != null) {
                sb.append("create table "+ entityCode+" (");
                sb.append("\n");
                for (HfmdEntityAttr Column : hfmdEntityAttrList) {
                    sb.append(getColumnSql(Column) + ",\n");
                }
                return sb.toString().substring(0,sb.toString().length()-2)
                        + " \n) ENGINE=InnoDB DEFAULT CHARSET=utf8;\n";

            }else {
                for (HfmdEntityAttr Column : hfmdEntityAttrList) {
                    sb.append("alter table " + entityCode + " add column" + getColumnSql(Column) + ";\n");
                }
                return sb.toString();
            }

        }else if(type == HfModelContainer.TYPE_MOD) {
            if(entity != null) {
//                sb.append("create table "+ entityCode+" (");
//                sb.append("\n");
//                for (HfmdEntityAttr Column : hfmdEntityAttrList) {
//                    sb.append(getColumnSql(Column) + ",\n");
//                }
//                return sb.toString().substring(0,sb.toString().length()-2)
//                        + " \n) ENGINE=InnoDB DEFAULT CHARSET=utf8;\n";

            }else {
                for (HfmdEntityAttr Column : hfmdEntityAttrList) {
                    sb.append("alter table " + entityCode + " modify column" + getColumnSql(Column) + ";\n");
                }
                return sb.toString();
            }
        }
        return "bu zheng chang ";
    }

    private static String getColumnSql(HfmdEntityAttr Column) {
        StringBuffer sb=new StringBuffer();

        sb.append("  "+Column.getHfmdEntityAttrCode());

        if("varchar".equals(HfmdEntityAttr1AttrTypeEnum.getName(Column.getAttrType()))
                ||"char".equals(HfmdEntityAttr1AttrTypeEnum.getName(Column.getAttrType()))){

            sb.append("  "+HfmdEntityAttr1AttrTypeEnum.getName(Column.getAttrType())
                    +"("+Column.getSize()+")");
        }else{
            sb.append("  "+HfmdEntityAttr1AttrTypeEnum.getName(Column.getAttrType()));
            if(StringUtils.isNotBlank(Column.getSize())) {
                sb.append("("+Column.getSize()+")");
            }
        }

        if(0==Column.getNullable()){
            sb.append(" not null ");
        }
        if(1==Column.getIspk()){
            sb.append(" primary key auto_increment");
        }

        if(StringUtils.isNotBlank(Column.getHfmdEntityAttrName())) {
            sb.append(" COMMENT '" + Column.getHfmdEntityAttrName() + "'");
        }
        return sb.toString();
    }
}
