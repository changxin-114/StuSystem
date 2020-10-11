<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="/static/js/jquery-1.12.4.js"></script>
</head>
<body>
    <form method="post" action="/selectAll">
        <input type="text" name="zhname" />
        <input type="submit" value="提交检索" />&nbsp;&nbsp;&nbsp;
        <input type="button" value="新增标准" />&nbsp;&nbsp;&nbsp;
        <input type="button" value="删除标准" />

        <table border="1px" width="800px">
            <tr>
                <td><input type="checkbox" name="checkbox" /></td>
                <td>标准号</td>
                <td>中文名称</td>
                <td>版本</td>
                <td>发布日期</td>
                <td>实施日期</td>
                <td>操作</td>
            </tr>
            <c:forEach var="item" items="${pageInfo.list}">
                <tr>
                    <td><input type="checkbox" name="checkbox" /></td>
                    <td>${item.stdNum}</td>
                    <td>${item.zhname}</td>
                    <td>${item.standardversion}</td>
                    <td>${item.releaseDate}</td>
                    <td>${item.implDate}</td>
                    <td><a href="#">下载</a>&nbsp;&nbsp;&nbsp;<a href="#">修改</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>

    <div>
        <a href="/selectAll?pageNum=${pageInfo.firstPage}">首页</a>
        <c:if test="${pageInfo.hasPreviousPage}">
            <a href="/selectAll?pageNum=${pageInfo.prePage}">上一页</a>
        </c:if>
        <c:forEach items="${pageInfo.navigatepageNums}" var="i">
            <a href="/selectAll?pageNum=${i}">${i}</a>
        </c:forEach>
        <c:if test="${pageInfo.hasNextPage}">
            <a href="/selectAll?pageNum=${pageInfo.nextPage}">下一页</a>
        </c:if>
        <a href="/selectAll?pageNum=${pageInfo.lastPage}">尾页</a>
    </div>


</body>
</html>
<script type="text/javascript">
    $("tr:even").css("background-color","#ccc");

</script>
