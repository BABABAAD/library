package com.oracle.ddbookmarket.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.ddbookmarket.biz.BookTypeBiz;
import com.oracle.ddbookmarket.bizImpl.BookTypeBizImpl;
import com.oracle.ddbookmarket.model.BookType;
import com.oracle.ddbookmarket.model.PageConstant;

/**
 * Servlet implementation class BookListServlet
 */
@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BookListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookTypeBiz bookTypeBiz=new BookTypeBizImpl();

        String strcurrentPage=request.getParameter("currentPage");
        if(strcurrentPage==null) {
            strcurrentPage="1";
        }
        int currentPage=Integer.parseInt(strcurrentPage);
        String name=request.getParameter("name");
        String strsid=request.getParameter("sid")==null?"-1":request.getParameter("sid");
        int sid=Integer.parseInt(strsid);
        String strBid=request.getParameter("bid")==null?"-1":request.getParameter("bid");
        int bid=Integer.parseInt(strBid);
        System.out.println();
        int totaRow=bookTypeBiz.totaRow(name,sid);
        int totaPage=totaRow%PageConstant.PAGE_SIZE==0?totaRow/PageConstant.PAGE_SIZE:totaRow/PageConstant.PAGE_SIZE+1;
        System.out.println(totaPage+"========================");
        List<BookType>ls=bookTypeBiz.findAll(currentPage,name,sid);
        //查出总行数
        request.setAttribute("name", name);
        System.out.println(name+"1111111111111111111111111111111");
        request.setAttribute("ls", ls);
        request.setAttribute("bid", bid);
        request.setAttribute("sid", sid);
        request.setAttribute("totaPage", totaPage);
        request.setAttribute("currentPage", currentPage);
        request.getRequestDispatcher("BookList.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
