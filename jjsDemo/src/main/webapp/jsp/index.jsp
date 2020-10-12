<%--
  Created by IntelliJ IDEA.
  User: 吉俊烁
  Date: 2020/10/12
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="/static/js/jquery-1.12.4.js"></script>
<body>
<h3 align="center">标准信息列表</h3>

<table align="center" border="1px">
    <tr>
        <td colspan="7" align="right">
            <form action="/" method="post">
                <input type="text" value="${zhname}" name="zhname"/>
                <input type="submit" value="提交索引"/>
                <input type="button" id="add" value="新增标准"/>
                <input type="button" id="del" value="删除标准"/>
            </form>
        </td>
    </tr>
    <tr>
        <td><input type="checkbox" name="qxk"/></td>
        <td>标准号</td>
        <td>中文名称</td>
        <td>版本</td>
        <td>发布日期</td>
        <td>实施日期</td>
        <td>操作</td>
    </tr>
    <c:forEach var="s" items="${pageInfo.list}">
        <tr>
            <td><input type="checkbox" name="cbx" id="${s.id}"/></td>
            <td>${s.stdNum}</td>
            <td>${s.zhname}</td>
            <td>${s.standardversion}</td>
            <td><fmt:formatDate value="${s.releaseDate}" pattern="yyyy-MM-dd" type="both"></fmt:formatDate></td>
            <td><fmt:formatDate value="${s.implDate}" pattern="yyyy-MM-dd" type="both"></fmt:formatDate></td>
            <td><a href="/doxz?filename=${s.packagePath}">下载</a>
                <a href="/toupd?id=${s.id}">修改</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="7" align="right">
            <c:if test="${pageInfo.isFirstPage==false}">
                <a href="/?pageNum=${pageInfo.firstPage}&zhname=${zhname}">首页</a>
                <a href="/?pageNum=${pageInfo.prePage}&zhname=${zhname}">上一页</a>
            </c:if>
            <c:if test="${pageInfo.isLastPage==false}">
                <a href="/?pageNum=${pageInfo.nextPage}&zhname=${zhname}">下一页</a>
                <a href="/?pageNum=${pageInfo.lastPage}&zhname=${zhname}">尾页</a>
            </c:if>
            <span>第${pageInfo.pageNum}页/共${pageInfo.pages}页</span>
        </td>
    </tr>
</table>
<p align="center">${success}</p>
</body>
</html>
<script type="text/javascript">
    $("#del").click(function () {
        var size = $("[name='cbx']:checked").size();
        if (size == 0) {
            alert("请选择删除项.");
            return false;
        }
        var flag = confirm("确定要删除吗？");
        if (flag) {
            var id = "";
            $("[name='cbx']:checked").each(function () {
                id = id + $(this).attr("id") + ",";
            })
            location.href = "/del?idlist=" + id;
        }
    })

    $("[name='qxk']").click(function () {
        if ($("[name='qxk']:checked").size() != 0) {
            alert("1");
            $("[name='cbx']").attr("checked", true);
        } else {
            alert("2");
            $("[name='cbx']").attr("checked", false);
        }
    })
    $("#add").click(function () {
        location.href = "/toadd";
    })
</script>