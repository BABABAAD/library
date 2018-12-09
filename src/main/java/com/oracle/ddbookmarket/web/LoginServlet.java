package com.oracle.ddbookmarket.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.oracle.ddbookmarket.biz.AdminBiz;
import com.oracle.ddbookmarket.bizImpl.AdminBizImpl;
import com.oracle.ddbookmarket.model.Admin;
import com.oracle.ddbookmarket.uitl.MyBeanUtils;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
    	
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数值
		Admin admin = new Admin();
		MyBeanUtils.populate(admin, request.getParameterMap());
		/*String vcode=request.getParameter("vcode");
		String serverVcode= (String) request.getSession().getAttribute("validateCode");
		//验证码不区分大小写
		if (!serverVcode.equalsIgnoreCase(vcode)){
			request.setAttribute("msg","验证码错误");
			request.setAttribute("admin", admin);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		ValidatorFactory validatorFactory= Validation.buildDefaultValidatorFactory();
		Validator validation=validatorFactory.getValidator();
		Set<ConstraintViolation<Admin>> validateResult=validation.validate(admin);
		if(validateResult.size()>0){
			Map<String,String> errors=new HashMap<>();
			for (ConstraintViolation<Admin> cv:
					validateResult) {
					errors.put(cv.getPropertyPath().toString(),cv.getMessage());
			}
			request.setAttribute("errors",errors);
			request.setAttribute("admin", admin);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}*/
		Map<String,String> errors=new HashMap<>();
		String vcode=request.getParameter("vcode");
		String serverVcode= (String) request.getSession().getAttribute("validateCode");
		//验证码不区分大小写
		if (!serverVcode.equalsIgnoreCase(vcode)){
			errors.put("vcode","验证码错误");
		}
		//在Hibernate Validator文档中查找
		ValidatorFactory validatorFactory= Validation.buildDefaultValidatorFactory();
		Validator validation=validatorFactory.getValidator();
		Set<ConstraintViolation<Admin>> validateResult=validation.validate(admin);
		if(validateResult.size()>0){
			for (ConstraintViolation<Admin> cv:
					validateResult) {
				errors.put(cv.getPropertyPath().toString(),cv.getMessage());
			}
		}
		if (errors.size()>0){
			request.setAttribute("errors",errors);
			request.setAttribute("admin", admin);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		//到数据库中比对
		AdminBiz adminBiz=new AdminBizImpl();
		boolean ret = adminBiz.findAdmin(admin);
		//根据比对结果给用户一个界面
		if(ret) {
			//记录登录成功
			request.getSession().setAttribute("logined",true);
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}else {
			//把用户填写的user信息带过去
			request.setAttribute("admin", admin);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
