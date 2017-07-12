package com.hframe.tag.util;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;



@Deprecated
public class CommonServ {

	public static  WebApplicationContext webappcontext=null ;
	
	public Object findObjectByPk(){
		
		Object object=new Object();
		
		return object;
	}


	

//
//	public List<MyFile>  getFileList(HttpServletRequest request){
//		return   FileUtil.getAllFilesFromParDirectory(
//				new File(request.getRealPath("/")),new String[]{"META-INF","WEB-INF","image","js","theme","third","design"},new String[]{".jsp"});
//	}
//
//
//	public static void main(String[] args) {
//		String[] result=new CommonServ().getSqlBySqlId("111");
//	}
//
//
//
//
//	public List getMenuList(){
//		List menus=new ArrayList();
//		menus.add(new MenuVO("1","-1","AAAA","index.jsp"));
//			menus.add(new MenuVO("11","1","BBB","index.jsp"));
//			menus.add(new MenuVO("12","1","BBB->","index.jsp"));
//				menus.add(new MenuVO("121","12","CCC","index.jsp"));
//				menus.add(new MenuVO("122","12","CCCC","index.jsp"));
//				menus.add(new MenuVO("123","12","CCCC->","index.jsp"));
//					menus.add(new MenuVO("1231","123","我是菜单选项2-2-1","index.jsp"));
//					menus.add(new MenuVO("1232","123","我是菜单选项2-2-2","index.jsp"));
//				menus.add(new MenuVO("124","12","我是菜单选项1-2","index.jsp"));
//				menus.add(new MenuVO("125","12","我是菜单选项1-3","index.jsp"));
//
//
//
//		menus.add(new MenuVO("2","-1","我是菜单2","index.jsp"));
//			menus.add(new MenuVO("21","2","我是菜单2-1","index.jsp"));
//			menus.add(new MenuVO("22","2","我是菜单2-2","index.jsp"));
//				menus.add(new MenuVO("221","21","我是菜单2-2-1","index.jsp"));
//				menus.add(new MenuVO("222","21","我是菜单2-2-2","index.jsp"));
//				menus.add(new MenuVO("223","21","我是菜单2-2-3","index.jsp"));
//					menus.add(new MenuVO("2231","223","我是菜单2-2-3-1","index.jsp"));
//					menus.add(new MenuVO("2232","223","我是菜单2-2-3-2","index.jsp"));
//
//		menus.add(new MenuVO("3","-1","我是菜单3","index.jsp"));
//			menus.add(new MenuVO("31","3","我是菜单3-1","index.jsp"));
//			menus.add(new MenuVO("32","3","我是菜单3-2","index.jsp"));
//			menus.add(new MenuVO("33","3","我是菜单3-3","index.jsp"));
//				menus.add(new MenuVO("331","33","我是菜单3-3-1","index.jsp"));
//				menus.add(new MenuVO("332","33","我是菜单3-3-2","index.jsp"));
//				menus.add(new MenuVO("333","33","我是菜单3-3-3","index.jsp"));
//				menus.add(new MenuVO("334","33","我是菜单3-3-4","index.jsp"));
//					menus.add(new MenuVO("3341","334","我是菜单3-3-4-1","index.jsp"));
//					menus.add(new MenuVO("3342","334","我是菜单3-3-4-2","index.jsp"));
//					menus.add(new MenuVO("3343","334","我是菜单3-3-4-3","index.jsp"));
//						menus.add(new MenuVO("33431","3343","我是菜单3-3-4-3-1","index.jsp"));
//						menus.add(new MenuVO("33432","3343","我是菜单3-3-4-3-2","index.jsp"));
//							menus.add(new MenuVO("334321","33432","我是菜单3-3-4-3-2-1","index.jsp"));
//
//		menus.add(new MenuVO("4","-1","xxxx","index.jsp"));
//
//		return menus;
//	}
//
//	public TabGroup getTabGroup(){
//
//		//title jsp hight width style isIframe
//
//
//		TabGroup tabGroup=new TabGroup("1","demo");
//
//		TabItem item1=new TabItem("1","界面组件","framecontent.jsp",800,400,"true",null,"1");
//		TabItem item2=new TabItem("2","素材","tree.jsp",800,400,"true",null,"1");
//		TabItem item3=new TabItem("3","后台操作","framecontent2.jsp",800,400,"true",null,"1");
//		TabItem item4=new TabItem("4","后台操作","framecontent.jsp",800,400,"true",null,"1");
//
//		TabItem item5=new TabItem("5","用户信息","$('#usera')",800,400,"false",null,"1");
//		TabItem item6=new TabItem("6","用户信息2","$('#user')",800,400,"false",null,"1");
//
//
//
//		List<TabItem> tabItems=new ArrayList<TabItem>();
//		tabItems.add(item1);
//		tabItems.add(item2);
//		tabItems.add(item3);
//		tabItems.add(item4);
//		tabItems.add(item5);
//		tabItems.add(item6);
//
//		tabGroup.setTabItems(tabItems);
//
//		return tabGroup;
//
//	}
//
//	public List getArticleList(){
//
//
//		List list =new ArrayList();
//		list.add(new Article("N0001","关于爱情",new Date()));
//		list.add(new Article("N0002","笑话一首",new Date()));
//		list.add(new Article("N0003","史上最搞笑的视频",new Date()));
//
//		return list;
//	}
//
//	public List getEnums() {
//
//		List list=new ArrayList();
//		list.add("[1021]男,女");
//		list.add("[1022]四川,河南");
//		list.add("[1034]星期一,星期二,星期三,星期四,星期五,星期六,星期天");
//
//		return list;
//
//
//	}
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
}
