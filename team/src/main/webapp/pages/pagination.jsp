<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="/static/css/bootstrap.css">
<link rel="stylesheet" href="/static/css/bootstrap-theme.css">
<style>
    .page-number{
        width: 200px;
    }

    .input-mini{
        width: 70px;
        margin: -25px 20px 10px 60px;
    }
</style>
<!-- 分页信息 -->
<div id="pagination" class="row" style="align-content: center">
    <%--<div class="col-md-3 page-number paging" style="padding-top: 25px;">--%>
        <%--<a href="javascript:;">每页显示</a>--%>
        <%--<select class="form-control input-mini"--%>
                     <%--onchange="self.location.href=options[selectedIndex].value">--%>
        <%--<option value="?pageNum=1&pageSize=10&${searchParams}"--%>
                <%--<c:if test="${page.pageSize eq '10' }">selected</c:if>>10</option>--%>
        <%--<option value="?pageNum=1&pageSize=5&${searchParams}"--%>
                <%--<c:if test="${page.pageSize eq '5' }">selected</c:if>>5</option>--%>
        <%----%>

        <%--</select>--%>
    <%--</div>--%>

    <div class="col-md-12 paging" style="text-align: center">
        <ul class="pagination">
            <%--<li><a href="javascript:;">共 ${page.total} 条, 分 ${page.pages} 页</a></li>--%>
            <c:if test="${page.hasPreviousPage == false}">
                <li class="disabled"><a href="#">首页</a></li>
                <li class="disabled"><a href="#">上一页</a></li>
            </c:if>
            <c:if test="${page.hasPreviousPage == true}">
                <li><a
                        href="?pageNum=1&pageSize=${page.pageSize}&${searchParams}">首页</a></li>
                <li><a
                        href="?pageNum=${page.prePage}&pageSize=${page.pageSize}&${searchParams}">上一页</a></li>
            </c:if>

            <c:forEach var="i" begin="1" end="${page.pages}">
                <c:choose>
                    <c:when test="${i == page.pageNum}">
                        <li class="active"><a
                                href="?pageNum=${i}&pageSize=${page.pageSize}&${searchParams}">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a
                                href="?pageNum=${i}&pageSize=${page.pageSize}&${searchParams}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${page.hasNextPage == true}">
                <li><a
                        href="?pageNum=${page.nextPage}&pageSize=${page.pageSize}&${searchParams}">下一页</a></li>
                <li><a
                        href="?pageNum=${page.pages}&pageSize=${page.pageSize}&${searchParams}">末页</a></li>
            </c:if>
            <c:if test="${page.hasNextPage == false}">
                <li class="disabled"><a href="#">下一页</a></li>
                <li class="disabled"><a href="#">末页</a></li>
            </c:if>

        </ul>

    </div>
</div>
<!-- 分页信息结束 -->

