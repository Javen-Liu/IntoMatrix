<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: 刘建雯
  Date: 2020/3/23
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Into Matrix - 后台管理</title>
    <!--字体图标-->
    <link href="http://cdn.javaex.cn/javaex/pc/css/icomoon.css" rel="stylesheet" />
    <!--动画-->
    <link href="http://cdn.javaex.cn/javaex/pc/css/animate.css" rel="stylesheet" />
    <!--骨架样式-->
    <link href="http://cdn.javaex.cn/javaex/pc/css/common.css" rel="stylesheet" />
    <!--皮肤（缇娜）-->
    <link href="http://cdn.javaex.cn/javaex/pc/css/skin/tina.css" rel="stylesheet" />
    <!--jquery，不可修改版本-->
    <script src="http://cdn.javaex.cn/javaex/pc/lib/jquery-1.7.2.min.js"></script>
    <!--全局动态修改-->
    <script src="http://cdn.javaex.cn/javaex/pc/js/common.js"></script>
    <!--核心组件-->
    <script src="http://cdn.javaex.cn/javaex/pc/js/javaex.min.js"></script>
    <!--表单验证-->
    <script src="http://cdn.javaex.cn/javaex/pc/js/javaex-formVerify.js"></script>
    <title>后台管理 - javaex前端框架</title>
    <style>
    </style>
</head>

<body>
    <!--顶部导航-->
    <div class="admin-navbar">
        <div class="admin-container-fluid clear">
            <!--logo名称-->
            <div class="admin-logo">IntoMatrix</div>

            <!--导航-->
            <ul class="admin-navbar-nav fl">
                <li class="active"><a href="${pageContext.request.contextPath}/index.do">博客首页</a></li>
                <li><a href="${pageContext.request.contextPath}/pages/admin/main.jsp">管理</a></li>
                <li><a href="/admin/nav2">友链</a></li>
            </ul>

            <!--右侧-->
            <ul class="admin-navbar-nav fr">
                <li>
                    <a href="javascript:">欢迎您，${currentUser.nickName}</a>
                    <ul class="dropdown-menu" style="right: 10px;">
                        <li><a href="${pageContext.request.contextPath}/blogger/logout.do">退出当前账号</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>

    <!--主题内容-->
    <div class="admin-mian">
        <!--左侧菜单-->
        <div class="admin-aside admin-aside-fixed">
            <h1><span class="admin-nav-name">总览</span></h1>
            <div id="admin-toc" class="admin-toc">
                <div class="menu-box">
                    <div id="menu" class="menu">
                        <ul>
                            <li class="menu-item hover">
                                <a href="${pageContext.request.contextPath}/pages/admin/home.jsp" target="page">管理中心</a>
                            </li>
                            <li class="menu-item">
                                <a href="javascript:">个人中心<i class="icon-keyboard_arrow_left"></i></a>
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/pages/admin/blogger/bloggerInfoManager.jsp" target="page">修改用户信息</a></li>
                                    <li><a href="${pageContext.request.contextPath}/pages/admin/blogger/verifyPasswordManager.jsp" target="page">修改密码</a></li>
                                </ul>
                            </li>
                            <li class="menu-item">
                                <a href="javascript:">评论管理<i class="icon-keyboard_arrow_left"></i></a>
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/pages/admin/blog_comment/blogCommentReviewManager.jsp" target="page">评论审核</a></li>
                                    <li><a href="${pageContext.request.contextPath}/pages/admin/blog_comment/blogCommentManager.jsp" target="page">评论管理</a></li>
                                </ul>
                            </li>
                            <li class="menu-item">
                                <a href= "${pageContext.request.contextPath}/admin/blog/edit.do" target="page">编写博客</a>
                            </li>
                            <li class="menu-item">
                                <a href="javascript:">文章管理<i class="icon-keyboard_arrow_left"></i></a>
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/admin/blogType/list.do?page=0&rows=500" target="page">文章分类</a></li>
                                    <li><a href="${pageContext.request.contextPath}/pages/admin/blog/blogDisplayManager.jsp" target="page">文章列表</a></li>
                                    <li><a href="${pageContext.request.contextPath}/pages/admin/blog/blogRecycleManager.jsp"  target="page">回收站</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <!--iframe载入内容-->
        <div class="admin-markdown">
            <iframe name="page" src="home.jsp"></iframe>
        </div>
    </div>
    </body>
    <script>
        var hightUrl = "xxxx";
        javaex.menu({
            id : "menu",
            isAutoSelected : true,
            key : "",
            url : hightUrl
        });

        $(function() {
            // 设置左侧菜单高度
            setMenuHeight();
        });

        /**
         * 设置左侧菜单高度
         */
        function setMenuHeight() {
            var height = document.documentElement.clientHeight - $("#admin-toc").offset().top;
            height = height - 10;
            $("#admin-toc").css("height", height+"px");
        }

        // 控制页面载入
        function setPage(url) {
            $("#page").attr("src", url);
        }
    </script>
</html>