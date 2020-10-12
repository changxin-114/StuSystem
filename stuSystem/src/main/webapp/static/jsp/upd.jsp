<%--
  Created by IntelliJ IDEA.
  User: zhushuoyuan
  Date: 2020/10/12
  Time: 18:28
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
<form action="/doupd" method="post" enctype="multipart/form-data">
    <table border="1px" align="center" width="500px">
        <tr>
            <td colspan="8" align="center"><h2>修改标准信息</h2></td>
        </tr>
        <tr>
            <td><span style="color: red">*</span>标准号：</td>
            <td><input type="text" name="stdNum" value="${ standard.stdNum}"></td>
        </tr>
        <tr>
            <td><span style="color: red">*</span>中文名称：</td>
            <td><input type="text" name="zhname" value="${ standard.zhname}"></td>
            <<input type="hidden" name="id" value="${standard.id}">
        </tr>
        <tr>
            <td><span style="color: red">*</span>版本：</td>
            <td><input type="text" name="versionn" value="${ standard.versionn}"></td>
        </tr>
        <tr>
            <td><span style="color: red">*</span>关键字/词：</td>
            <td><input type="text" name="keyss" value="${ standard.keyss}"></td>
        </tr>
        <tr>
            <td>发布日期(yyyy-MM-dd)：</td>
            <td><input type="text" name="releaseDate" value="${ standard.releaseDate}"></td>
        </tr>
        <tr>
            <td>实施日期(yyyy-MM-dd)：</td>
            <td><input type="text" name="implDate" value="${ standard.implDate}"></td>
        </tr>
        <tr>
            <td><span style="color: red">*</span>附件：</td>
            <td><input type="file" name="packagePath1" value="${ standard.packagePath}"></td>
        </tr>
        <tr>
            <td colspan="8" align="center">
                <input type="submit" value="保存">
                <input type="button" value="取消" onclick="javascript:history.back(-1)">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
<script type="text/javascript">
    $("form").submit(function () {
        var stdNum=$("[name='stdNum']").val();
        var zhname=$("[name='zhname']").val();
        var versionn=$("[name='versionn']").val();
        var keyss=$("[name='keyss']").val();
        var releaseDate=$("[name='releaseDate']").val();
        var implDate=$("[name='implDate']").val();
        var reg="implDate";
        if(stdNum==""||zhname==""||versionn==""||keyss==""){
            alert("请完整填写所有信息！！！！");
            return false;
        }else if(reg.text(releaseDate)){
            alert("发布日期格式不正确！！！");
            return false;
        }else  if(reg.text(implDate)){
            alert("实施日期格式不正确！！！");
            return false;
        }
        return true;

    })
</script>
