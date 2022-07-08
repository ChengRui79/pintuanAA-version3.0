<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% String context = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

	<title>注册</title>
	<meta name="keywords" content="">
	<meta name="description" content="">
	<link href="/static/css/bootstrap.min.css" rel="stylesheet">
	<link href="/static/css/animate.css" rel="stylesheet">
	<link href="/static/css/style.css" rel="stylesheet">
	<link href="/static/css/login.css" rel="stylesheet">
	<link rel="stylesheet" href="/static/layui/css/layui.css" />
</head>

<body class="signin">
<div class="signinpanel">
	<div class="row">
		<div class="col-sm-12">

            <ul class="nav nav-tabs" id="myTab">
                <li class="active" style="width: 50%"><a href="#home">注册</a></li>
            </ul>
			<div class="tab-content">
				<div class="tab-pane active" id="home">
					<form style="margin-top: -30px;">
						<input type="text" id="username" class="form-control uname" placeholder="用户名" value=""/>
						<input type="text" id="realName" class="form-control uname" placeholder="姓名" value=""/>
						<input type="text" id="phone" class="form-control layui-icon-cellphone" placeholder="手机号" value=""/>
						<input type="password" id="password" class="form-control pword m-b" placeholder="密码" value=""/>
						<input type="password" id="repassword" class="form-control pword m-b" placeholder="确认密码" value=""/>
						<input type="button" value="注册" class="btn btn-primary btn-block" onclick="register()"></input>
					</form>
				</div>

			</div>
		</div>
	</div>

</div>

</body>
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.js"></script>
<script type="text/javascript" src="/static/layui/layui.all.js"></script>
<script type="text/javascript">
	$('#myTab a').click(function (e) {
		e.preventDefault();
		$(this).tab('show');
	})
	function register() {
		var username = $("#username").val();
		var realName = $("#realName").val();
		var phone = $("#phone").val();
		var password = $("#password").val();
		var repassword = $("#repassword").val();
		if(username == ''){
			layer.msg('请输入用户名');
			return false;
		}
		if(realName == ''){
			layer.msg('请输入姓名');
			return false;
		}
		if(phone == ''){
			layer.msg('请输入手机号');
			return false;
		}
		if(password == ''){
			layer.msg('请输入密码');
			return false;
		}
		if(repassword == ''){
			layer.msg('请输入确认密码');
			return false;
		}
		$.ajax({
			cache: true,
			type: "POST",
			url: "/register",
			data: JSON.stringify({
				"username": username,
				"realName": realName,
				"phone": phone,
				"password": password
			}),
			async: false,
			dataType: 'json',
			contentType: "application/json;charset-UTF-8",
			error: function() {
				layer.alert("网络超时");
			},
			success : function(data) {
			    console.log(data);
				if (data.code == 0) {
					layer.msg('注册成功', {
						icon: 1,
						time: 1000
					}, function () {
						document.location.href="/signin";
					});
				} else {
					layer.msg(data.msg)
				}
			}
		});
	}
</script>
</html>
