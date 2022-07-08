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
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>&nbsp;</h5>
                </div>
                <div class="ibox-content">

                    <div class="table-responsive">
                        <table id="table">
                        </table>
                    </div>

                </div>
            </div>
        </div>
    </div>

</div>
<!-- ##########主体（end）########## -->

<!-- ##########页脚########## -->
<jsp:include page="../footer.jsp"/>


<script src="/static/js/jquery-1.11.1.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="/static/layui/layui.all.js"></script>
<script type="text/javascript">
    $(function () {
        getData();
    })
    function getData() {
        $('#table').bootstrapTable({
            url:"/team/listByPage",   //请求地址
            striped : true, //是否显示行间隔色
            pageNumber : 1, //初始化加载第一页
            pagination : true,//是否分页
            sidePagination : 'server',//server:服务器端分页|client：前端分页
            pageSize : 5,//单页记录数
            pageList : [ 5, 10, 20],//可选择单页记录数
            showRefresh : true,//刷新按钮
            queryParams : function(params) {//上传服务器的参数
                var temp = {
                    page : params.offset+1,// SQL语句起始索引
                    limit : params.limit  // 每页显示数量
                };
                return temp;
            },columns : [ {
                title : 'ID',
                field : 'id',
                sortable : true
            }, {
                title : '活动名称',
                field : 'teamName'
            }, {
                title : '费用',
                field : 'cost',
                sortable : true
            },{
                title : '开始时间',
                field : 'startTime'
            },{
                title : '结束时间',
                field : 'endTime'
            },{
                title : '状态',
                field : 'stateStr'
            }, {
                field: 'operate',
                title: '操作',
                formatter: btnGroup,    // 自定义方法，添加按钮组
                events: {               // 注册按钮组事件
                    'click #edit': function (event, value, row, index) {
                        edit(row.id)
                    },
                    'click #setup': function (event, value, row, index) {
                        updateState(row.id, 2)
                    },
                    'click #finish': function (event, value, row, index) {
                        updateState(row.id, 3)
                    },
                    'click #appendCost': function (event, value, row, index) {
                        appendCost(row.id)
                    },
                    'click #genReport': function (event, value, row, index) {
                        genReport(row.id)
                    },
                    'click #billList': function (event, value, row, index) {
                        billList(row.id)
                    },
                    'click #excel': function (event, value, row, index) {
                        excel(row.id)
                    }
                }
            }]
        });
    }

    function btnGroup () {   // 自定义方法，添加操作按钮 data-target="xxx" 为点击按钮弹出指定名字的模态框
        var html =
            '<a href="####" class="btn btn-sm btn-primary" id="edit" data-toggle="modal" data-target="#editinfo" title="修改">' +
            '<span>修改活动</span></a>&nbsp;' +
            '<a href="####" class="btn btn-sm btn-primary" id="setup" title="确定成立">' +
            '<span>确定成立</span></a>&nbsp;' +
            '<a href="####" class="btn btn-sm btn-primary" id="finish" title="结束活动">' +
            '<span>结束活动</span></a>&nbsp;' +
            '<a href="####" class="btn btn-sm btn-primary" id="genReport" data-toggle="modal" data-target="#genReport" title="生成报告">' +
            '<span>生成报告</span></a>&nbsp;' +
            '<a href="####" class="btn btn-sm btn-primary" id="billList">' +
            '<span>账单列表</span></a>&nbsp;' +
            '<a href="####" class="btn btn-sm btn-primary" id="appendCost" data-toggle="modal" data-target="#appendCost" title="修改">' +
            '<span>追加费用</span></a>'
        return html
    };



    function edit(id) {
        layer.open({
            type: 2,
            title: '编辑活动',
            maxmin: true,
            skin: 'layui-layer-grey',
            shadeClose: true, //点击遮罩关闭层
            area : ['800px' , '600px'],
            content:'/team/toEdit?id='+id//弹框显示的url,对应的页面
        });
    }
    function appendCost(id) {
        layer.open({
            type: 2,
            title: '追加费用',
            maxmin: true,
            skin: 'layui-layer-grey',
            shadeClose: true, //点击遮罩关闭层
            area : ['800px' , '600px'],
            content:'/team/toAppendCost?teamId='+id//弹框显示的url,对应的页面
        });
    }
    function genReport(id) {
        layer.open({
            type: 2,
            title: '生成报告',
            maxmin: true,
            skin: 'layui-layer-grey',
            shadeClose: true, //点击遮罩关闭层
            area : ['800px' , '600px'],
            content:'/team/genReport?id='+id//弹框显示的url,对应的页面
        });
    }
    function updateState(id, state) {
        $.ajax({
            cache: true,
            type: "POST",
            url: "/team/updateState",
            data: JSON.stringify({
                "teamId": id,
                "state": state
            }),
            async: false,
            dataType: 'json',
            contentType: "application/json;charset-UTF-8",
            error: function (res) {
                layer.alert("网络超时");
            },
            success: function (data) {
                if (data.code == 0) {
                    layer.msg('操作成功', {
                        icon: 1,
                        time: 2000
                    });
                } else {
                    layer.alert(data.msg)
                }
            }
        });
    }
    function excel(id) {
        location.href="/bill/exportExcel?teamId="+id
    }
    function billList(id) {
        location.href="/bill/index?teamId="+id
    }

</script>
</body>
</html>
