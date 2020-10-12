<%--
  Created by IntelliJ IDEA.
  User: 吉俊烁
  Date: 2020/10/12
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    span {
        color: red;
    }
</style>
<body>
<form action="/doupd" method="post" enctype="multipart/form-data">
    <table border="1px" align="center">
        <tr>
            <td colspan="2" align="center"><h3>修改标准信息</h3></td>
        </tr>
        <tr>
            <td><span>*</span>标准号:</td>
            <td>
                <input type="hidden" name="id" value="${standard.id}"/>
                <input type="text" value="${standard.stdNum}" name="stdNum"/><span id="span"></span>
            </td>
        </tr>
        <tr>
            <td><span>*</span>中文名称:</td>
            <td>
                <input type="text" value="${standard.zhname}" name="zhname"/>
            </td>
        </tr>
        <tr>
            <td><span>*</span>版本:</td>
            <td>
                <input type="text" value="${standard.standardversion}" name="standardversion"/>
            </td>
        </tr>
        <tr>
            <td><span>*</span>关键字/词:</td>
            <td>
                <input type="text" value="${standard.standardkeys}" name="standardkeys"/>
            </td>
        </tr>
        <tr>
            <td>发布日期(yyyy-MM-dd):</td>
            <td>
                <input type="text" name="releaseDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${standard.releaseDate}"
                                        type="both"></fmt:formatDate>"
                <%--<fmt:formatDate pattern="yyyy-MM-dd" value="${standard.releaseDate}"
                                type="both"></fmt:formatDate>--%> />

            </td>
        </tr>
        <tr>
            <td>实施日期(yyyy-MM-dd):</td>
            <td>
                <input type="text" name="implDate" value="<fmt:formatDate value="${standard.implDate}" pattern="yyyy-MM-dd"
                                        type="both"></fmt:formatDate>"
                <%-- <fmt:formatDate value="${standard.implDate}" pattern="yyyy-MM-dd"
                                        type="both"></fmt:formatDate>--%> />
            </td>
        </tr>
        <tr>
            <td>附件:</td>
            <td>
                <input type="file" name="avatar_pic"/>
            </td>
        </tr>
        <tr>
            <td align="center" colspan="2">
                <input type="submit" value="保存"/>
                <input type="reset" value="取消"/>
            </td>
        </tr>
    </table>

</form>
</body>
</html>
