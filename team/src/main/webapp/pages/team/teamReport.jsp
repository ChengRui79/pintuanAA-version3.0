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
<div class="container">

    <div class="row">
        <div class="col-sm-12" style="padding-top: 20px">
            <form class="form-horizontal">
                <input type="hidden" name="id" value="${data.id}" id="id"/>
                <div class="form-group">
                    <label class="col-sm-2 control-label">活动名称：</label>
                    <div class="col-sm-6">
                        <input type="text" placeholder="活动名称" class="form-control" name="teamName" id="teamName" value="${data.teamName}" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">费用：</label>
                    <div class="col-sm-6">
                        <input type="text" placeholder="费用" class="form-control" name="cost" id="cost" value="${data.cost}" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">开始时间：</label>
                    <div class="col-sm-6">
                        <input type="text" placeholder="开始时间" class="form-control" name="startTime" id="startTime" value="${data.startTime}" readonly>

                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">结束时间：</label>
                    <div class="col-sm-6">
                        <input type="text" placeholder="结束时间" class="form-control" name="endTime" id="endTime" value="${data.endTime}" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">参团人数：</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="endTime" id="personNum" value="${personNum}" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"></label>
                    <div class="col-sm-6">
                        <button type="button" class="btn btn btn-sm btn-primary" id="btn">关闭</button>
                    </div>
                </div>

            </form>
        </div>
    </div>

</div>
<!-- ##########主体（end）########## -->

<!-- ##########页脚########## -->


<script src="/static/js/jquery-1.11.1.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/layui/layui.all.js"></script>
<script type="text/javascript">
    $(function(){
    });

    $("#btn").click(function () {
        layer.close(layer.index);
        window.parent.location.reload();
    });

</script>
</body>
</html>
