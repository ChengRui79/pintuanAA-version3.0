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
<jsp:include page="../header.jsp"/>

<!-- ##########主体########## -->
<div class="container" style="min-height: 400px">

    <div class="row">
        <div class="col-sm-12" style="padding-top: 20px">
            <form class="form-horizontal">
                <input type="hidden" name="id" value="${data.id}" id="id"/>
                <div class="form-group">
                    <label class="col-sm-2 control-label">活动名称：</label>
                    <div class="col-sm-6">
                        <input type="text" placeholder="活动名称" class="form-control" name="teamName" id="teamName" value="${data.teamName}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">费用：</label>
                    <div class="col-sm-6">
                        <input type="text" placeholder="费用" class="form-control" name="cost" id="cost" value="${data.cost}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">开始时间：</label>
                    <div class="col-sm-6">
                        <input type="text" placeholder="开始时间" class="form-control" name="startTime" id="startTime" value="${data.startTime}">

                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">结束时间：</label>
                    <div class="col-sm-6">
                        <input type="text" placeholder="结束时间" class="form-control" name="endTime" id="endTime" value="${data.endTime}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"></label>
                    <div class="col-sm-6">
                        <button class="btn btn btn-sm btn-primary" id="btn">提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>
<!-- ##########主体（end）########## -->

<!-- ##########页脚########## -->
<jsp:include page="../footer.jsp"/>

<script src="/static/js/jquery-1.11.1.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/layui/layui.all.js"></script>
<script type="text/javascript">
    $(function(){
    });

    $("#btn").click(function () {
        if(!$("#teamName").val()){
            layer.msg('活动名称不能为空')
            return false;
        }
        if(!$("#cost").val()){
            layer.msg('费用不能为空')
            return false;
        }
        if(!$("#startTime").val()){
            layer.msg('开始时间不能为空')
            return false;
        }
        if(!$("#endTime").val()){
            layer.msg('结束时间不能为空')
            return false;
        }
        var url = "/team/add"
        if($("#id").val()){
            url = "/team/update"
        }
        $.ajax({
            cache: true,
            type: "POST",
            url: url,
            data: JSON.stringify({
                "id": $("#id").val(),
                "teamName": $("#teamName").val(),
                "cost": $("#cost").val(),
                "startTime": $("#startTime").val(),
                "endTime": $("#endTime").val()
            }),
            async: false,
            dataType: 'json',
            contentType: "application/json;charset-UTF-8",
            error: function (request) {
                layer.alert("网络超时");
            },
            success: function (data) {
                if (data.code == 0) {
                    layer.msg('提交成功', {
                        icon: 1,
                        time: 2000
                    }, function () {
                        document.location.href="/";
                    });
                } else {
                    layer.msg(data.msg)
                }
            }
        });
    });
</script>
</body>
</html>
