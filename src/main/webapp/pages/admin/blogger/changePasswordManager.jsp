<%--
  Created by IntelliJ IDEA.
  User: 刘建雯
  Date: 2020/3/29
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>IntoMatrix - 修改密码</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <!--字体图标-->
    <link href="${pageContext.request.contextPath}/static/javaex/pc/css/icomoon.css" rel="stylesheet" />
    <!--动画-->
    <link href="${pageContext.request.contextPath}/static/javaex/pc/css/animate.css" rel="stylesheet" />
    <!--骨架样式-->
    <link href="${pageContext.request.contextPath}/static/javaex/pc/css/common.css" rel="stylesheet" />
    <!--皮肤（缇娜）-->
    <link href="${pageContext.request.contextPath}/static/javaex/pc/css/skin/tina.css" rel="stylesheet" />
    <!--代码高亮-->
    <link href="javaex/pc/lib/highlight/highlight.css" rel="stylesheet" />

    <script src="javaex/pc/lib/highlight/highlight.min.js"></script>
    <!--切记：下面这行代码只能在展示页面和无需回显编辑的页面使用-->
    <script>hljs.initHighlightingOnLoad();</script>
    <!--jquery，不可修改版本-->
    <script src="${pageContext.request.contextPath}/static/javaex/pc/lib/jquery-1.7.2.min.js"></script>
    <!--全局动态修改-->
    <script src="${pageContext.request.contextPath}/static/javaex/pc/js/common.js"></script>
    <!--核心组件-->
    <script src="${pageContext.request.contextPath}/static/javaex/pc/js/javaex.min.js"></script>
    <!--表单验证-->
    <script src="${pageContext.request.contextPath}/static/javaex/pc/js/javaex-formVerify.js"></script>

    <style>
        .bg-wrap, body, html {height: 100%;}
        input{line-height: normal;-webkit-appearance: textfield;background-color: white;-webkit-rtl-ordering: logical;cursor: text;padding: 1px;border-width: 2px;border-style: inset;border-color: initial;border-image: initial;}
        input[type="text"], input[type="password"]{border: 0;outline: 0;}
        input, button{text-rendering: auto;color: initial;letter-spacing: normal;word-spacing: normal;text-transform: none;text-indent: 0px;text-shadow: none;display: inline-block;text-align: start;margin: 0em;font: 400 13.3333px Arial;}
        input[type=button]{-webkit-appearance: button;cursor: pointer;}
        .bg-wrap {position: relative;background: url(http://img.javaex.cn/FipOsQoe90u_7i3dOVpaeX5QD7c6) top left no-repeat;background-size: cover;overflow: hidden;}
        .main-cont-wrap{z-index: 1;position: absolute;top: 60%;left: 50%;margin-left: -190px;margin-top: -255px;box-sizing: border-box;width: 380px;padding: 30px 30px 40px;background: #fff;box-shadow: 0 20px 30px 0 rgba(63,63,65,.06);border-radius: 10px;}
        .form-title{margin-bottom: 40px;}
        .form-title>span{font-size: 20px;color: #2589ff;}
        .form-item{margin-bottom: 30px;position: relative;height: 40px;line-height: 40px;border-bottom: 1px solid #e3e3e3;box-sizing: border-box;}
        .form-txt{display: inline-block;width: 100px;color: #595961;font-size: 14px;margin-right: 10px;}
        .form-input{border: 0;outline: 0;font-size: 14px;color: #595961;width: 175px;}
        .form-btn{margin-top: 40px;}
        .ui-button{display: block;width: 320px;height: 50px;text-align: center;color: #fff;background: #2589ff;border-radius: 6px;font-size: 16px;border: 0;outline: 0;}
        .ui-button:hover{display: block;width: 320px;height: 50px;text-align: center;color: #fff;background: #0066FF;border-radius: 6px;font-size: 16px;border: 0;outline: 0;}
        .ui-button:active{display: block;width: 320px;height: 50px;text-align: center;color: #fff;background: #2589ff;border-radius: 6px;font-size: 16px;border: 0;outline: 0;}
        .tip-message{color: red;}
    </style>
</head>
<body>
<div class="bg-wrap">
    <div class="main-cont-wrap login-model">
        <form id="form">
            <div class="form-title">
                <span>IntoMatrix</span>
            </div>
            <div class="form-item">
                <span class="form-txt">输入新密码:</span>
                <input type="password" class="form-input original" id="password" name="password"/>
            </div>
            <div class="form-item">
                <span class="form-txt">确认新密码:</span>
                <input type="password" class="form-input original" id="repeatPassword" name="repeatPassword"/>
            </div>
            <div>
                <span class="tip-message" id="error"></span>
            </div>
            <div class="form-btn">
                <input type="button" class="ui-button" id="save" value="确认"/>
            </div>
        </form>
    </div>
</div>
</body>

<script type="text/javascript">
    $("#save").click(function () {
        var password = $(':input[name=password]').val();
        var repeatPassword = $(':input[name=repeatPassword]').val();
        if(password === repeatPassword){
            $.ajax({
                url : "${pageContext.request.contextPath}/blogger/changePassword.do",
                type : "POST",
                dataType : "json",
                traditional : "true",
                data : {
                    "id" : ${currentUser.id},
                    "password" : password
                },
                success : function (rtn) {
                    var result = eval(rtn);
                    if(result.success){
                        javaex.optTip({
                            content : "修改密码成功",
                            type : "success"
                        });
                        setTimeout(function () {
                            window.location.href = "${pageContext.request.contextPath}/pages/admin/home.jsp";
                        },2000);
                    }else{
                        document.getElementById("error").innerText = result.errorInfo;
                    }
                }
            })
        }else{
            document.getElementById("error").innerText = "两次输入密码需一样";
            javaex.render();
        }
    })
</script>
</html>
