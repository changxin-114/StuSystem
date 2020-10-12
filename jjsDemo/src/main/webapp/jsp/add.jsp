<%--
  Created by IntelliJ IDEA.
  User: 吉俊烁
  Date: 2020/10/12
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        span {
            color: red;
        }
    </style>
    <script type="text/javascript" src="/static/js/jquery-1.12.4.js"></script>
</head>
<body>
<p id="error" align="center">${error}</p>
<form method="post" action="/doadd" enctype="multipart/form-data">
    <table border="1px" align="center">
        <tr>
            <td colspan="2" align="center"><h3>增加标准信息</h3></td>
        </tr>
        <tr>
            <td><span>*</span>标准号:</td>
            <td>
                <input type="text" name="stdNum"/><span id="span"></span>
            </td>
        </tr>
        <tr>
            <td><span>*</span>中文名称:</td>
            <td>
                <input type="text" name="zhname"/>
            </td>
        </tr>
        <tr>
            <td><span>*</span>版本:</td>
            <td>
                <input type="text" name="standardversion"/>
            </td>
        </tr>
        <tr>
            <td><span>*</span>关键字/词:</td>
            <td>
                <input type="text" name="standardkeys"/>
            </td>
        </tr>
        <tr>
            <td>发布日期(yyyy-MM-dd):</td>
            <td>
                <input type="text" name="releaseDate"/>
            </td>
        </tr>
        <tr>
            <td>实施日期(yyyy-MM-dd):</td>
            <td>
                <input type="text" name="implDate"/>
            </td>
        </tr>
        <tr>
            <td><span>*</span>附件:</td>
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
<script type="text/javascript">
    $("[name='stdNum']").blur(function () {
        var stdNum = $("[name='stdNum']").val();
        if (stdNum == "") {
            $("#span").text("标准号不能为空").css("color", "red");
            return false;
        }
        $.post("/selstdNum", {"stdNum": stdNum}, function (date) {
            if (date == true) {
                $("#span").text("标准号已存在").css("color", "red");
            } else {
                $("#span").text("可用").css("color", "green");
            }
        }, "json")
    })

</script>
