package com.hframe.tag.util;

import com.hframe.tag.ButtonImpl;

import java.io.Writer;

/**
 *
 * @author zqh
 * 来源于原CodeCreateTemplate.java
 */
public class TagUtils {

//	public static String getPageContentByFileName(String pageName)  {
//		String filePath=projectBasePath+"WebRoot/"+pageName;
//		String fileContent = "";
//		try {
//			fileContent = FileUtil.readFile(filePath);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return fileContent;
//	}
//
//
//	public List getTreeElement(){
//		List menus=new ArrayList();
//
//	 	menus.add(new MenuVO("0","-1","四川","index.jsp"));
//	 	menus.add(new MenuVO("1","0","成都","index.jsp"));
//	 	menus.add(new MenuVO("2","0","南充","index.jsp"));
//	 	menus.add(new MenuVO("3","0","宜宾","index.jsp"));
//	 	menus.add(new MenuVO("4","0","眉山","index.jsp"));
//
//	 	menus.add(new MenuVO("11","1","华阳","index.jsp"));
//	 	menus.add(new MenuVO("12","1","武侯","index.jsp"));
//	 	menus.add(new MenuVO("13","1","温江","index.jsp"));
//	 	menus.add(new MenuVO("14","1","郫县","index.jsp"));
//
//
//	 	menus.add(new MenuVO("21","2","南部","index.jsp"));
//	 	menus.add(new MenuVO("22","2","西充","index.jsp"));
//	 	menus.add(new MenuVO("23","2","仪陇","index.jsp"));
//	 	menus.add(new MenuVO("24","2","阆中","index.jsp"));
//
//
//	 	return menus;
//
//	}
	public static void writeDefaultButton(String defaultBtn, Writer out) {

		ButtonImpl button;
		String[] btns=defaultBtn.split(",");
		for (String btn : btns) {
			if("save".equals(btn)){
				button=new ButtonImpl("1","1",null,"action-save","保存",null,null,"","","","","","");
				button.doStart(out);
			}else if("edit".equals(btn)){
				button=new ButtonImpl("1","1",null,"action-edit","修改",null,null,"","","","","","");
				button.doStart(out);
			}else if("reset".equals(btn)){
				button=new ButtonImpl("1","1",null,"current-reset","重置",null,null,"","","","","","");
				button.doStart(out);
			}else if("back".equals(btn)){
				button=new ButtonImpl("1","1",null,"current-back","返回",null,null,"","","","","","");
				button.doStart(out);
			}else if("delete".equals(btn)){
				button=new ButtonImpl("1","1",null,"action-delete","删除",null,null,"","","","","","");
				button.doStart(out);
			}else if("select".equals(btn)){
				button=new ButtonImpl("1","1",null,"tree-select","选择",null,null,"","","","","","");
				button.doStart(out);
			}else if(btn!=null&&btn.startsWith("ajax")){
				String relatForm=null;
				if(btn.contains("(")){
					relatForm=btn.substring(5,btn.length()-1);
				}
				button=new ButtonImpl("1",null,null,"action-ajax","保存",null,relatForm,"","","","","","");
				button.doStart(out);
			}
		}
	}

	public static String getDefaultButtonHtmlStr(String defaultBtn) {

		ButtonImpl button;
		String[] btns=defaultBtn.split(",");
		String result="";
		for (String btn : btns) {
			if("save".equals(btn)){
				button=new ButtonImpl("1","1",null,"action-save","保存",null,null,"","","","","","");
				result += button.getContent();
			}else if("edit".equals(btn)){
				button=new ButtonImpl("1","1",null,"action-edit","修改",null,null,"","","","","","");
				result += button.getContent();
			}else if("reset".equals(btn)){
				button=new ButtonImpl("1","1",null,"current-reset","重置",null,null,"","","","","","");
				result += button.getContent();
			}else if("back".equals(btn)){
				button=new ButtonImpl("1","1",null,"current-back","返回",null,null,"","","","","","");
				result += button.getContent();
			}else if("delete".equals(btn)){
				button=new ButtonImpl("1","1",null,"action-delete","删除",null,null,"","","","","","");
				result += button.getContent();
			}else if("select".equals(btn)){
				button=new ButtonImpl("1","1",null,"tree-select","选择",null,null,"","","","","","");
				result += button.getContent();
			}else if("query".equals(btn)){
				button=new ButtonImpl("1","1",null,"tree-select","查询",null,null,"","","","","","");
				result += button.getContent();
			}else if(btn!=null&&btn.startsWith("ajax")){
				String relatForm=null;
				if(btn.contains("(")){
					relatForm=btn.substring(5,btn.length()-1);
				}
				button=new ButtonImpl("1",null,null,"action-ajax","保存",null,relatForm,"","","","","","");
				result += button.getContent();
			}
		}
		return result;
	}




}
