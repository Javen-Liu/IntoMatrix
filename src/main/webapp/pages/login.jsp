<%--
  Created by IntelliJ IDEA.
  User: 刘建雯
  Date: 2020/3/24
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Into Matrix - 请先登陆</title>
</head>
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

<script type="text/javascript">
    function toMain() {
        this.location.href="admin/main.jsp"
    }

    function checkSubmitForm() {
        var userName = document.getElementById("login_name").value;
        var password = document.getElementById("password").value;
        if(userName==null || userName===""){
            document.getElementById("error").innerText = "用户名不能为空！";
            return false;
        }
        if(password==null || password===""){
            document.getElementById("error").innerText = "密码不能为空！";
            return false;
        }
        return true;
    }
</script>

<body>
    <div class="bg-wrap">
        <div class="main-cont-wrap login-model">
            <form id="form" action="${pageContext.request.contextPath}/blogger/login.do" method="post" onsubmit="return checkSubmitForm()">
                <div class="form-title">
                    <span>IntoMatrix</span>
                </div>
                <div class="form-item">
                    <span class="form-txt">账号：</span>
                    <input type="text" class="form-input original" id="login_name" name="loginName" placeholder="请输入账号" value="${blogger.userName}"/>
                </div>
                <div class="form-item">
                    <span class="form-txt">密码：</span>
                    <input type="password" class="form-input original" id="password" name="password" placeholder="请输入密码" value="${blogger.password}"/>
                </div>
                <div>
                    <span class="tip-message" id="error">${errorInfo}</span>
                </div>
                <div class="form-btn">
                    <input type="submit" class="ui-button" id="save" value="登录"/>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
