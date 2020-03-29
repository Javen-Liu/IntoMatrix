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
        <h2>编写博客</h2>
        <!--右上角的返回按钮-->
        <a href="javascript:history.back();">
            <button class="button indigo radius-3" style="position: absolute;right: 20px;top: 16px;"><span class="icon-arrow_back"></span> 返回</button>
        </a>
        <input type="button" id="save" class="button green radius-3" style="position: absolute;right: 110px;top: 16px;" value="保存" />

        <!--正文内容-->
        <div class="main">
            <form id="form">
                <!--文本框-->
                <div class="unit clear">
                    <div class="left"><span class="required">*</span><p class="subtitle">文章名称</p></div>
                    <div class="right">
                        <input type="text" style="width: 50%" class="text" name="title" data-type="必填" error-msg="必须输入文章名称" error-pos="42"/>
                    </div>
                </div>

                <!--下拉选择框-->
                <div class="unit clear">
                    <div class="left"><span class="required">*</span><p class="subtitle">文章分类</p></div>
                    <div class="right">
                        <select id="select" >
                            <option value="">请选择</option>
                            <c:forEach items="${list}" var="type">
                                <option value="${type.id}">${type.typeName}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" id="typeId" name="typeId" value="" data-type="必填" error-msg="必须选择文章类型" error-pos="42"/>
                    </div>
                </div>

                <div class="unit clear">
                    <div class="left"><p class="subtitle">关键字</p></div>

                    <div class="right">
                        <input type="text" style="width: 50%" class="text" name="keyword"/>
                        <p class="hint">多个关键字之间使用","分隔</p>
                    </div>
                </div>

                <div class="unit clear">
                    <div class="left"><p class="subtitle">编写博客：</p></div>
                </div>

                <div class="javaex-edit-content">
                    ${fn:replace(contentHtml, "img src", "img data-original")}
                </div>
                <div id="edit" class="edit-container" style="width: 96%;margin: 2%"></div>
                <input type="hidden" id="content" name="content" value="" />
                <input type="hidden" id="summary" name="summary" value="" />

            </form>

        </div>

    </div>

</div>

    <script type="text/javascript">
        javaex.edit({
            id : "edit",
            image : {
                url : "${pageContext.request.contextPath}/upload/upload.json",	// 请求路径
                param : "file",		// 参数名称，Spring中与MultipartFile的参数名保持一致
                dataType : "url",	// 返回的数据类型：base64 或 url
                isShowOptTip : true,	// 是否显示上传提示
                rtn : "rtnData",	// 后台返回的数据对象，在前台页面用该名字存储
                imgUrl : "data.imgUrl"	// 根据返回的数据对象，获取图片地址。  例如后台返回的数据为：{code: "000000", message: "操作成功！", data: {imgUrl: "/1.jpg"}}
            },
            isInit : true,
            callback : function(rtn) {
                $("#content").val(rtn.html);
                $("#summary").val(rtn.text.substring(0,100));
            }
        });

        javaex.select({
            id : "select",
            callback : function (rtn) {
                $("#typeId").val($("#select option:selected").val());
            }
        });

        // 监听点击保存按钮事件
        $("#save").click(function() {
            javaex.confirm({
                content : "确定要保存么",
                callback : "saveInfo()"
            });
        });

        function saveInfo() {
            if (javaexVerify()) {
                var title = $(':input[name=title]').val();
                var typeId = $(':input[name=typeId]').val();
                var keyword = $(':input[name=keyword]').val();
                var content = $(':input[name=content]').val();
                var summary = $(':input[name=summary]').val();
                $.ajax({
                    url : "save.do",
                    type : "POST",
                    dataType : "json",
                    traditional : "true",
                    data : {
                        "title" : title,
                        "typeId" : typeId,
                        "keyword" : keyword,
                        "content" : content,
                        "summary" : summary,
                    },
                    success : function (rtn) {
                        var result = eval(rtn);
                        if(result.success){
                            javaex.optTip({
                                content : "保存成功",
                                type : "success"
                            });
                            setTimeout(function () {
                                window.location.href='${pageContext.request.contextPath}/pages/admin/blog/blogDisplayManager.jsp';
                            },2000);
                        }else{
                            javaex.optTip({
                                content : "保存失败",
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
