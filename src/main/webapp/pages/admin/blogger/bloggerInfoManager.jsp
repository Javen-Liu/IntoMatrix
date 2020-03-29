<%--
  Created by IntelliJ IDEA.
  User: 刘建雯
  Date: 2020/3/25
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>博客文章类别管理器</title>
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
        .edit-editor-body .edit-body-container {
            height: 580px;
        }
    </style>
</head>
<body>


<!--主体内容-->
<div class="list-content">
    <!--块元素-->
    <div class="block">
        <!--页面有多个表格时，可以用于标识表格-->
        <h2>更改个人信息</h2>
        <!--右上角的返回按钮-->
        <a href="javascript:history.back();">
            <button class="button indigo radius-3" style="position: absolute;right: 20px;top: 16px;"><span class="icon-arrow_back"></span> 返回</button>
        </a>
        <input type="button" id="save" class="button green radius-3" style="position: absolute;right: 110px;top: 16px;" value="保存更改" />

        <!--正文内容-->
        <div class="main">
            <form id="form">
                <!--文本框-->
                <div class="unit clear">
                    <div class="left"><p class="subtitle">用户名</p></div>
                    <div class="right">
                        <input type="text"  class="text readonly" autocomplete="off"
                               error-pos="42" style="width: 40%" name="userName"/>
                    </div>
                </div>

                <div class="unit clear">
                    <div class="left"><p class="subtitle">昵称</p></div>
                    <div class="right">
                        <input type="text" class="text" style="width: 40%" name="nickName"/>
                    </div>
                </div>

                <div class="unit clear">
                    <div class="left"><p class="subtitle">个性签名</p></div>
                    <div class="right">
                        <input type="text" class="text" style="width: 40%" name="sign"/>
                    </div>
                </div>

                <div class="unit clear">
                    <div class="left"><p class="subtitle">个人简介</p></div>
                    <div class="right">
                        <textarea class="desc" placeholder="请填写简介" name="profile"></textarea>
                        <!--提示说明-->
                        <p class="hint">请填写个人简介。简介中不得包含令人反感的信息，且长度应在10到255个字符之间。</p>
                    </div>
                </div>

            </form>

        </div>

    </div>

</div>

<script type="text/javascript">
    window.onload=function(){
        $.ajax({
            url : "${pageContext.request.contextPath}/blogger/user_info.do",
            type : "POST",
            dataType : "json",
            traditional : "true",
            data : {
                "id" : ${currentUser.id}
            },
            success : function (rtn) {
                var user = eval(rtn.user);
                console.log(user);
                $(':input[name=userName]').val(user.userName);
                $(':input[name=nickName]').val(user.nickName);
                $(':input[name=sign]').val(user.sign);
                $(':input[name=profile]').val(user.profile);
            },
            error : function () {
                javaex.optTip({
                    content : "获取用户信息失败",
                    type : "error"
                });
            }
        })
    };


    // 监听点击保存按钮事件
    $("#save").click(function() {
        javaex.confirm({
            content : "确定要保存么",
            callback : "saveInfo()"
        });
    });

    function saveInfo() {
        if (javaexVerify()) {
            var profile = $(':input[name=profile]').val();
            var nickName = $(':input[name=nickName]').val();
            var sign = $(':input[name=sign]').val();
            $.ajax({
                url : "${pageContext.request.contextPath}/blogger/update.do",
                type : "POST",
                dataType : "json",
                traditional : "true",
                data : {
                    "id" : ${currentUser.id},
                    "profile" : profile,
                    "nickName" : nickName,
                    "sign" : sign
                },
                success : function (rtn) {
                    var result = eval(rtn);
                    if(result.success){
                        javaex.optTip({
                            content : "更新成功",
                            type : "success"
                        });
                        setTimeout(function () {
                            window.location.reload();
                        },2000);
                    }else{
                        javaex.optTip({
                            content : "更新失败",
                            type : "error"
                        });
                    }
                }
            })

        }
    }


</script>

</body>
</html>
