package com.oracle.ddbookmarket.web;

import com.oracle.ddbookmarket.biz.BookTypeBiz;
import com.oracle.ddbookmarket.biz.SmallTypeBiz;
import com.oracle.ddbookmarket.bizImpl.BookTypeBizImpl;
import com.oracle.ddbookmarket.bizImpl.SmallTypeBizImpl;
import com.oracle.ddbookmarket.model.BookType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ToBookEditServlet",value = "/toBookEdit")
public class ToBookEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String strId=request.getParameter("id");
                int id=Integer.parseInt(strId);
                /*String strBid=request.getParameter("bid");
                int bid=Integer.parseInt(strBid);*/
                BookTypeBiz bookType=new BookTypeBizImpl();
                BookType book=bookType.findBookById(id);
                SmallTypeBiz smallTypeBiz=new SmallTypeBizImpl();
                int bid=smallTypeBiz.findBidById(book.getSid());
                request.setAttribute("book",book);
                request.setAttribute("bid",bid);
                request.getRequestDispatcher("/BookEdit.jsp").forward(request,response);
    }
}
