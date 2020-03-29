<%--
  Created by IntelliJ IDEA.
  User: 刘建雯
  Date: 2020/3/24
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>IntoMatrix - 博客文章类别管理器</title>
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
        <h2>文章类型管理</h2>
        <!--右上角的返回按钮-->
        <a href="javascript:history.back();">
            <button class="button indigo radius-3" style="position: absolute;right: 20px;top: 16px;"><span class="icon-arrow_back"></span> 返回</button>
        </a>

        <!--正文内容-->
        <div class="main">

            <!--表格上方的操作元素，添加、删除等-->
            <div class="operation-wrap">
                <div class="buttons-wrap">
                    <button id="add" class="button blue radius-3"><span class="icon-plus"></span> 添加</button>
                    <button id="delete" class="button red radius-3"><span class="icon-close2"></span> 删除</button>
                    <button id="save" class="button green radius-3"><span class="icon-check2"></span> 保存</button>
                </div>
            </div>

            <table id="table" class="table color2">
                <thead>
                <tr>
                    <th class="checkbox" style="width: 10%;text-align: center"><input type="checkbox" class="fill listen-1" /> </th>
                    <th style="text-align: center">类型编号</th>
                    <th>文章类型</th>
                    <th style="text-align: center">排序优先级</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="type" varStatus="status">
                        <tr>
                            <td class="checkbox" style="width: 7%;text-align: center"><input type="checkbox" name="id" value="${type.id}" class="fill listen-1-2"/> </td>
                            <td style="width: 10%;text-align: center">${type.id}</td>
                            <td style="text-align: center"><input type="text" class="text" name="name" data-type="必填" error-msg="必须输入类型名称" value="${type.typeName}" /></td>
                            <td style="width: 10%;text-align: center">
                                <input type="text" class="text" name="sort" data-type="正整数" error-msg="必须输入正整数" value="${type.orderNo}" style="text-align: center"/>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var idArr = [];
    var nameArr = [];
    var sortArr = [];

    //动态添加tr行
    $("#add").click(function () {
        var html = '<tr>';
        html += '<td class="checkbox"><input type="checkbox" name="id" class="fill listen-1-2"/> </td>';
        html += '<td> </td>';
        html += '<td><input type="text" class="text" name="name" data-type="必填" error-msg="必须输入类型名称"/></td>';
        html += '<td><input type="text" class="text" name="sort" data-type="正整数" error-msg="必须输入正整数"/></td>';
        $("#table tbody").append(html);
        javaex.render();
    });

    //确认保存
    $("#save").click(function() {
        javaex.confirm({
            content : "确定要保存么",
            callback : "saveInfo()"
        });
    });

    function saveInfo() {
        if(javaexVerify()){
            idArr = [];
            nameArr = [];
            sortArr = [];
            $(':checkbox[name="id"]').each(function () {
                idArr.push($(this).val());
            });
            $(':text[name="name"]').each(function () {
                nameArr.push($(this).val());
            });
            $(':text[name="sort"]').each(function () {
                sortArr.push($(this).val());
            });
            $.ajax({
                url : "save.do",
                type : "POST",
                dataType : "json",
                traditional : "true",
                data : {
                    "idArr" : idArr,
                    "nameArr" : nameArr,
                    "sortArr" : sortArr
                },
                success : function (rtn) {
                    var result = eval(rtn);
                    if(result.success){
                        javaex.optTip({
                            content : "保存成功",
                            type : "success"
                        });
                        setTimeout(function () {
                            window.location.reload();
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

    //删除
    $("#delete").click(function() {
        javaex.confirm({
            content : "确定要删除么",
            callback : "deleteInfo()"
        });
    });

    function deleteInfo() {
        idArr = [];
        $(':checkbox[name="id"]:checked').each(function () {
            console.log($(this).val());
            if($(this).val() !== "on"){
                idArr.push($(this).val());
            }
        });
        if(idArr.length === 0){
            window.location.reload();
            return;
        }
        $.ajax({
            url : "delete.do",
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
                        content : "删除成功",
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
</script>
</html>