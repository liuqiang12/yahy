<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>接待管理系统</title>
    <meta charset="utf-8" />
    <jsp:include page="/views/home/HeadGrid-Hy.jsp"></jsp:include>
    <script>
        $(function(){
            $('#hy_box li').click(function(){
                $(this).addClass('active').siblings().removeClass('active');
            })
        })
    </script>
</head>
<body style="overflow:hidden;">
<div id="hy_box">
    <div class="nav">
        <div class="menu">
            <p class="title">发布会议管理</p>
            <ul>
                <li class="active"><a href="<%=request.getContextPath() %>/hyGlController/navLink/hytz?hyInstId=${hyInstId}"  target="mainFrame">会议通知</a></li>
                <li><a href="<%=request.getContextPath() %>/hyGlController/navLink/chdw?hyInstId=${hyInstId}" target="mainFrame">参会单位</a></li>
                <li><a href="<%=request.getContextPath() %>/hyGlController/navLink/chry?hyInstId=${hyInstId}" target="mainFrame">参会人员</a></li>
                <li><a href="<%=request.getContextPath() %>/hyGlController/navLink/gzry?hyInstId=${hyInstId}" target="mainFrame">工作人员</a></li>
                <li><a href="<%=request.getContextPath() %>/hyGlController/navLink/jsry?hyInstId=${hyInstId}" target="mainFrame">驾驶人员</a></li>
                <li><a href="<%=request.getContextPath() %>/hyGlController/navLink/hyqj?hyInstId=${hyInstId}" target="mainFrame">会议请假</a></li>
                <li><a href="<%=request.getContextPath() %>/hyGlController/navLink/hycl?hyInstId=${hyInstId}" target="mainFrame">会议材料</a></li>
            </ul>
        </div>
    </div>
    <div class="content">
        <iframe name="mainFrame" id="mainFrame" title="mainFrame" src="<%=request.getContextPath() %>/hyGlController/navLink/hytz?hyInstId=${hyInstId}" width="100%" height="100%" frameborder="0"></iframe>
    </div>
</div>
</body>
</html>