<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="title-logo" style="height: 200px;">
    <br>
    <div>
        <div style="font-size: 30px;color: white;padding-left: 20px;padding-top-top: 120px;">团队活动管理系统</div>
        <% if(session.getAttribute("loginUser")==null){ %>
        <div style="float: right;margin-top: -40px;padding-right: 20px;">
            <button class="btn btn btn-sm btn-primary" onclick="toLogin()">登录</button>
        </div>
        <% } %>
        <% if(session.getAttribute("loginUser")!=null){ %>
        <div style="float: right;margin-top: -40px;padding-right: 20px;">
            <span style="color: white" style="padding-right: 10px">欢迎您，${sessionScope.loginUser.realName}</span>
            <button class="btn btn btn-sm btn-primary" onclick="logout()">退出</button>
        </div>
        <% } %>
    </div>

</div>
<!-- 导航条 -->
<nav class="navbar navbar-default" role="navigation">
    <div class="container">

        <ul class="nav navbar-nav" style="width:100%;">

            <li class="dropdown nav-top">
                <a href="/team/index" class="dropdown-toggle on">活动列表</a>
            </li>
            <% if(session.getAttribute("loginUser")!=null && ((Integer)session.getAttribute("role")==1)){ %>
            <li class="dropdown nav-top">
                <a href="/team/manage" class="dropdown-toggle on">管理活动</a>
            </li>
            <% } %>
            <li class="dropdown nav-top">
                <a href="/team/toAdd" class="dropdown-toggle on">创建活动</a>
            </li>

            <li class="dropdown nav-top">
                <a href="/joinRecord/index" class="dropdown-toggle on">参团记录</a>
            </li>

            <%--<li class="dropdown nav-top">--%>
                <%--<a href="/bill/index" class="dropdown-toggle on">账单列表</a>--%>
            <%--</li>--%>


        </ul>

    </div>
</nav>

<div class="line theme"></div>
<script src="/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/static/layui/layui.all.js"></script>
<script>

    /*导航条标题点击事件*/
    $(".dropdown-toggle").click(function () {

        if ($(this).attr('href')) {
            window.location = $(this).attr('href');
        }

    });
    /*导航条*/
    $(function () {
        $(".dropdown").mouseover(function () {

            $(this).addClass("open");
        });

        $(".dropdown").mouseleave(function () {

            $(this).removeClass("open");
        });
    });
    function toLogin() {
        document.location.href = '/signin';
    }
    function logout() {
        $.ajax({
            url: '/logout',
            type: "get",
            data: {},
            async: false,
            error: function () {
                layer.alert("网络超时");
            },
            success: function (data) {
                if (data.code == 0) {
                    layer.msg('退出成功', {
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
