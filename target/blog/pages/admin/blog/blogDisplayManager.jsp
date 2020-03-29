<%--
  Created by IntelliJ IDEA.
  User: 刘建雯
  Date: 2020/3/26
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>文章列表</title>
    <!--字体图标-->
    <link href="${pageContext.request.contextPath}/static/javaex/pc/css/icomoon.css" rel="stylesheet" />
    <!--动画-->
    <link href="${pageContext.request.contextPath}/static/javaex/pc/css/animate.css" rel="stylesheet" />
    <!--骨架样式-->
    <link href="${pageContext.request.contextPath}/static/javaex/pc/css/common.css" rel="stylesheet" />
    <!--皮肤（缇娜）-->
    <link href="${pageContext.request.contextPath}/static/javaex/pc/css/skin/tina.css" rel="stylesheet" />
    <!--jquery，不可修改版本-->
    <script src="${pageContext.request.contextPath}/static/javaex/pc/lib/jquery-1.7.2.min.js"></script>
    <!--全局动态修改-->
    <script src="${pageContext.request.contextPath}/static/javaex/pc/js/common.js"></script>
    <!--核心组件-->
    <script src="${pageContext.request.contextPath}/static/javaex/pc/js/javaex.min.js"></script>
    <!--表单验证-->
    <script src="${pageContext.request.contextPath}/static/javaex/pc/js/javaex-formVerify.js"></script>
</head>
<body>
<!--主体内容-->
<div class="list-content">
    <!--块元素-->
    <div class="block">
        <!--页面有多个表格时，可以用于标识表格-->
        <h2>文章列表</h2>
        <!--右上角的返回按钮-->
        <a href="javascript:history.back();">
            <button class="button indigo radius-3" style="position: absolute;right: 20px;top: 16px;"><span class="icon-arrow_back"></span> 返回</button>
        </a>

        <!--正文内容-->
        <div class="main">
            <!--表格上方的搜索操作-->
            <div class="admin-search">
                <div class="input-group">
                    <input name="searchInfo" type="text" class="text" placeholder="文章名称" />
                    <button id="search" class="button blue">搜索</button>
                </div>
            </div>

            <!--表格上方的操作元素，添加、删除等-->
            <div class="operation-wrap">
                <div class="buttons-wrap">
                    <button id="add" class="button blue radius-3"><span class="icon-plus"></span> 添加文章</button>
                    <button id="delete" class="button red radius-3"><span class="icon-close2"></span> 删除</button>
                </div>
            </div>

            <table id="table" class="table color2">
                <thead>
                <tr>
                    <th class="checkbox" style="width: 7%;text-align: center"><input type="checkbox" class="fill listen-1" /> </th>
                    <th style="width: 10%;text-align: center">编号</th>
                    <th style="width: 35%;text-align: center">文章标题</th>
                    <th style="text-align: center">发布日期</th>
                    <th style="text-align: center">博客类别</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    window.onload=function(){
        $.ajax({
            url : "${pageContext.request.contextPath}/admin/blog/blogList.do",
            type : "POST",
            dataType : "json",
            traditional : "true",
            data : {
                "page" : 0,
                "rows" : 10,
                "status" : 0
            },
            success : function (rtn) {
                var result = eval(rtn);
                console.log(result);
                var list = result.blog_list;
                len=list.length;
                if(result.success){
                    if(len > 0){
                        for(j=0;j<len;j++){
                            var html = '<tr>';
                            html += '<td class="checkbox"><input name="id" type="checkbox" value="'+list[j].id+'" class="fill listen-1-2"/> </td>';
                            html += '<td style="text-align: center">'+list[j].id+'</td>';
                            html += '<td style="text-align: center">'+list[j].title+'</td>';
                            html += '<td style="text-align: center">'+list[j].releaseDate+'</td>';
                            html += '<td style="text-align: center">'+list[j].blogType.typeName+'</td>';
                            $("#table tbody").append(html);
                            javaex.render();
                        }
                    }else{
                        javaex.message({
                            content: "目前文章哦",
                            type: "success",
                            live: 1500
                        })
                    }
                }else{
                    javaex.optTip({
                        content : "获取失败",
                        type : "error"
                    });
                }
            }
        })
    };

    //跳转到编写博客页面
    $("#add").click(function () {
        window.location.href = '${pageContext.request.contextPath}/admin/blog/edit.do';
    });

    //删除
    $("#delete").click(function() {
        javaex.confirm({
            content : "确定要删除么？",
            callback : "deleteInfo()"
        });
    });

    function deleteInfo() {
        idArr = [];
        $(':checkbox[name="id"]:checked').each(function () {
            idArr.push($(this).val());
        });
        if(idArr.length === 0){
            javaex.optTip({
                content : "未选中任何文章",
                type : "error"
            });
            return;
        }
        $.ajax({
            url : "${pageContext.request.contextPath}/admin/blog/deleteIntoRecycle.do",
            type : "POST",
            dataType : "json",
            traditional : "true",
            data : {
                "idArr" : idArr
            },
            success : function (rtn) {
                var result = eval(rtn);
                if(result.success){
                    javaex.optTip({
                        content : "删除成功，已删除的文件将放入回收站",
                        type : "success"
                    });
                    setTimeout(function () {
                        window.location.reload();
                    },2000);
                }else{
                    javaex.optTip({
                        content : "删除失败",
                        type : "error"
                    });
                }
            }
        })
    }

    $("#search").click(function() {
        var searchInfo = $(':input[name=searchInfo]').val();
        if(searchInfo!=null && searchInfo!==''){
            window.location.href = '${pageContext.request.contextPath}/admin/blog/search.do?searchInfo='+searchInfo;
        }
    });
</script>
</html>