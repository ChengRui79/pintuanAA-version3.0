<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% String context = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>团队活动管理系统</title>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="/static/css/master.css" rel="stylesheet">
    <link href="/static/css/index.css" rel="stylesheet">
    <link href="/static/css/header_footer.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="/static/js/html5shiv.js"></script>
    <script src="/static/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>


<!-- ##########主体########## -->
<div class="container" style="min-height: 400px">

    <div class="row">
        <div class="col-sm-12" style="padding-top: 20px">
            <img src="/static/images/weixin.png"/>
            <div class="col-sm-6">
                <button type="button" class="btn btn btn-sm btn-primary" id="btn">关闭</button>
            </div>
        </div>
    </div>

</div>
<!-- ##########主体（end）########## -->



<script src="/static/js/jquery-1.11.1.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/layui/layui.all.js"></script>
<script type="text/javascript">
    $(function(){
        $("#teamId").val("${teamId}")
    });

    $("#btn").click(function () {
        layer.close(layer.index);
        window.parent.location.reload();
    });
</script>
</body>
</html>
