package com.hframe.tag.servlet;

import com.hframe.tag.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

/**
 * User: zhangqh6
 * Date: 2015/11/17 10:53:53
 */
public class TagRefreshServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/html; charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String type=request.getParameter("tag_Type");
            String width=request.getParameter("tag_Width");
            String height=request.getParameter("tag_Height");
            String title=request.getParameter("tag_Title");

            title= URLDecoder.decode(title, "utf-8");
            String view=request.getParameter("tag_View");
            String id=request.getParameter("tag_Id");
            String direction=request.getParameter("tag_Direction");
            String checkbox=request.getParameter("tag_Checkbox");
            String editable=request.getParameter("tag_Editable");
            String src=request.getParameter("tag_Src");
            String style=request.getParameter("tag_Style");
            String isFrame=request.getParameter("tag_Isframe");
            String canClick=request.getParameter("tag_CanClick");
            String rootId=request.getParameter("tag_RootId");
            String colNum=request.getParameter("tag_ColNum");
            String condition=request.getParameter("tag_Condition");
            String defaultBtn=request.getParameter("tag_DefaultBtn");

            String objectId=request.getParameter("tag_ObjectId");
            String url=request.getParameter("tag_Url");
            String param=request.getParameter("tag_Param");

            if("frame".equals(type)){
                FrameImpl frameImpl=new FrameImpl(id,isFrame,width,height,title,src,style,null);
                frameImpl.doStart(out);
            }else if("menu".equals(type)){

                MenuImpl menuImpl=new MenuImpl(id,width,height,title,view,canClick,checkbox,null,condition,direction,null);
                menuImpl.doStart(out);
            }else if("tab".equals(type)){

                TabImpl tabImpl=new TabImpl(id,width,height,view,null,condition,null);
                tabImpl.doStart(out);
            }else if("dtree".equals(type)){

                DtreeImpl dtreeImpl=new DtreeImpl(id,width,height,title,view,checkbox,editable,null,null,rootId,condition,null,null);
                dtreeImpl.getDhtmTreeXml(out);
            }else if("myform".equals(type)){
                AutoFormImpl autoFormImpl=new AutoFormImpl(id,width,height,title,view,colNum,editable,null,null,objectId,condition,null,null,url,"false",defaultBtn);
                autoFormImpl.doStart(out);
                try {
                    autoFormImpl.doEnd(out);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else if("mygrid".equals(type)){

                AutoGridImpl autoGridImpl=new AutoGridImpl(id,width,height,title,view,editable,null,null,condition,null,null,url,null,defaultBtn,param);
                autoGridImpl.doStart(out);
                autoGridImpl.doEnd(out);

            }else if("mylist".equals(type)){

                AutoListImpl autoListImpl=new AutoListImpl(id,width,height,title,view,editable,null,null,condition,null,null,defaultBtn);
                autoListImpl.doStart(out);
                autoListImpl.doEnd(out);

            }else if("select".equals(type)){
                SelectImpl selectImpl=new SelectImpl();
                selectImpl.setView(view);
                selectImpl.setCondition(condition);
                selectImpl.getAjaxContent(out);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            out.close();
        }





    }
}
