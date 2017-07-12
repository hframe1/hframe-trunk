package com.hframe.tag.servlet;

import com.hframe.tag.AutoListImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * User: zhangqh6
 * Date: 2015/11/17 10:53:53
 */
public class PaginationRefreshServlet extends HttpServlet {


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

            String listId=request.getParameter("tag_Id");
            String width=request.getParameter("tag_Width");
            String height=request.getParameter("tag_Height");
            String title=request.getParameter("tag_Title");
            String view=request.getParameter("tag_View");
            String editable=request.getParameter("tag_Editable");
            String initial=request.getParameter("tag_Initial");
            String condition=request.getParameter("tag_Condition");
            String pageSize=request.getParameter("pageSize");
            String pageIndex=request.getParameter("pageIndex");


            if(!"".equals(pageSize)&&!"".equals(pageIndex)){
                AutoListImpl autoListImpl=new AutoListImpl(listId,width,height,title,view,editable,null,null,condition,null,null,Integer.valueOf(pageSize),Integer.valueOf(pageIndex));
                autoListImpl.getNextPageData(out);
            }else{
                AutoListImpl autoListImpl=new AutoListImpl(listId,width,height,title,view,editable,null,null,condition,null,null,null);
                autoListImpl.getNextPageData(out);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            out.close();
        }





    }
}
