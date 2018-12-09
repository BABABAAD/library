<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="com.oracle.ddbookmarket.model.Admin" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
<!-- 告诉浏览器不变 -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- 要个css -->
<link href="bower_components/bootswatch/dist/sketchy/bootstrap.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container-fluid" style="width: 80%">
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-body">
						<%--
							Map<String,String> errors= (Map<String, String>) request.getAttribute("errors");
							if(errors!=null){
								Set<String> keySet=errors.keySet();
								for (String key:
										keySet) {
									out.print(key+"----->"+errors.get(key));
								}
							}
						--%>
						<form method="post" autocomplete="off" action="login" id="loginFrom">
						<%
							Map<String,String> errors= (Map<String, String>) request.getAttribute("errors");
						Admin admin = (Admin)request.getAttribute("admin");
						if(admin!=null){
							
						%>
						<div class="form-group row">
							<%--label作用是当你点击他内部的文字，for告诉那个输入框获取输入焦点--%>
								<label for="inputName" class="col-sm-2 col-form-label text-right">用户名</label>
								<div class="col-sm-10">
									<%--如果有错误，input高亮显示，同时显示错误消息，否则正常显示--%>
								<%
								if (errors!=null&&errors.get("name")!=null){
								%>
									<input type="text" class="form-control is-invalid" id="inputName" placeholder="用户名" name="name" value="<%=admin.getName()%>">
								<div class="invalid-feedback">
										<%=errors.get("name")%>
									</div>
									<%
									}else{
									%>
									<input type="text" class="form-control" id="inputName" placeholder="用户名" name="name" value="<%=admin.getName()%>">
									<%
										}
								%>
								</div>
							</div>
							<div class="form-group row">
								<label for="inputPwd" class="col-sm-2 col-form-label text-right">密码</label>
								<div class="col-sm-10">
									<%
									if (errors!=null&&errors.get("pwd")!=null){
									    %>
									<input type="password" class="form-control is-invalid" id="inputPwd" placeholder="密码" name="pwd">
									<div class="invalid-feedback">
										<%=errors.get("pwd")%>
									</div>
									<%
									}else {
									  %>
									<input type="password" class="form-control" id="inputPwd" placeholder="密码" name="pwd">
									<%
									}
									%>
								</div>
							</div>
							<div class="form-group row">
								<label for="inputVcode" class="col-sm-2 col-form-label text-right">验证码</label>
								<div class="col-sm-5">
									<%
									if(errors!=null&&errors.get("vocde")!=null){
									%>
									<input class="form-control is-invalid" id="inputVcode" placeholder="验证码" name="vcode">
									<div class="invalid-feedback">
										<%=errors.get("vcode")%>
									</div>
									<%
									}else{
									%>
									<input  class="form-control" id="inputVcode" placeholder="验证码" name="vcode">
									<%
									}
									%>
								</div>
								<div class="col-sm-5">
									<img src="/vcode.png" id="vcodeImg" title="点击更换验证码">
								</div>
								<div class="valid-feedback">
									<%
									if(request.getAttribute("msg")!=null){
									    %>
									<%=request.getAttribute("msg")%>
									<%
									}
									%>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<button type="submit" class="btn btn-primary">登录</button>
								</div>
							</div>
						<% 
						}else{
							%>
							<div class="form-group row">
								<label for="inputName" class="col-sm-2 col-form-label text-right">用户名</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputName" placeholder="用户名" name="name">
								</div>
							</div>
							<div class="form-group row">
								<label for="inputPwd" class="col-sm-2 col-form-label text-right">密码</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="inputPwd" placeholder="密码" name="pwd">
								</div>
							</div>
							<div class="form-group row">
								<label for="inputVcode" class="col-sm-2 col-form-label text-right">验证码</label>
								<div class="col-sm-5">
									<input  class="form-control" id="inputVcode" placeholder="验证码" name="vcode">
								</div>
								<div class="col-sm-5">
									<img src="/vcode.png" id="vcodeImg" title="点击更换验证码">
								</div>
								<div class="valid-feedback">
									<%
									if (request.getAttribute("msg")!=null){
									    %>
									<%=request.getAttribute("msg")%>
									<%
									}
									%>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<button type="submit" class="btn btn-primary">登录</button>
								</div>
							</div>
							<%
						}
						%>	
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="bower_components/jquery/dist/jquery.slim.js"></script>
	<%--样式--%>
	<script type="text/javascript" src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
	<script type="text/javascript">
        $(function () {
            $("#vcodeImg").click(function () {
                $(this).attr("src","vcode.png?t="+Math.random());
            });
        });
	</script>
	<%--添加文件判断--%>
	<script type="text/javascript" src="bower_components/jquery-validation/dist/jquery.validate.js"></script>
	<%--中文显示--%>
	<script type="text/javascript" src="bower_components/jquery-validation/src/localization/messages_zh.js"></script>
	<script type="text/javascript">
        $(function () {
            $("#loginFrom").validate({
                rules:{//验证规则
                    name:{
                        required: true,
                        maxlength:40,
						minlength:3
                    },
                    pwd:{
                        required: true,
                        maxlength:40,
                        minlength:3,
                    },
					vcode:{
                        required: true,
						maxlength:4,
						minlength:4
					}
                },
                errorElement:"div",
                errorClass:"invalid-feedback",//错误消息的样式
                highlight: function(element, errorClass, validClass) {
                    $(element).addClass("is-invalid").removeClass(validClass);
                    //bootstrap4不支持label样式
                    // $(element.form).find("label[for=" + element.id + "]")
                    //     .addClass(errorClass);
                },
                unhighlight: function(element, errorClass, validClass) {
                    $(element).removeClass("is-invalid").addClass(validClass);
                    // $(element.form).find("label[for=" + element.id + "]")
                    //     .removeClass(errorClass);
                },
                validClass:"is-valid"//指定正确时候输入的框的样式
            });
        });
	</script>
</body>
</html>