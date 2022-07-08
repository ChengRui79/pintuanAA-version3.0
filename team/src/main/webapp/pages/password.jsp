<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title></title>
    <jsp:include page="include.jsp"></jsp:include>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 修改密码</strong></div>
    <div class="body-content">
        <form id="form1" method="post" class="form-x" action="">
            <input type="hidden" name="id" id="id" value="${user.id}"/>

            <div class="form-group">
                <div class="label">
                    <label>旧密码：</label>
                </div>
                <div class="field">
                    <input type="password" class="input" name="password" id="password" value="" style="width: 25%; float: left;" value="" />
                    <div class="tipss"><font color="red">*</font></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>新密码：</label>
                </div>
                <div class="field">
                    <input type="password" class="input" name="newPassword" id="newPassword" value="" style="width: 25%; float: left;" value="" />
                    <div class="tipss"><font color="red">*</font></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>确认新密码：</label>
                </div>
                <div class="field">
                    <input type="password" class="input" name="rePassword" id="rePassword" value="" style="width: 25%; float: left;" value="" />
                    <div class="tipss"><font color="red">*</font></div>
                </div>
            </div>


            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check" type="button" onclick="save()"> 确定</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
    layui.use('form', function(){
        var form = layui.form;
        form.render('select');
    });
    $(function(){

    })
    function save() {
        var formData = new FormData(document.getElementById("form1"));
        if(formData.get("password")==''){
            layer.msg('请输入旧密码')
            return false
        }
        if(formData.get("newPassword")==''){
            layer.msg('请输入新密码')
            return false
        }
        if(formData.get("rePassword")==''){
            layer.msg('请输入确认新密码')
            return false
        }
        if(formData.get("newPassword")!=formData.get("rePassword")){
            layer.msg('两次输入密码不一致')
            return false
        }
        $.ajax({
            url : "/user/editPwd",
            type: "post",
            dataType: "json",
            cache: false,
            data: formData,
            processData: false,// 不处理数据
            contentType: false, // 不设置内容类型
            error : function(request) {
                layer.msg("网络超时");
            },
            success : function(data) {
                if (data.code == 0) {
                    layer.msg('修改成功', {
                        icon: 1,
                        time: 1000
                    });
                } else {
                    layer.alert(data.msg)
                }

            }
        });
    }

</script>
</body>
</html>
