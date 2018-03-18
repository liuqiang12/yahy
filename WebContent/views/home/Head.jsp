<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<script src="<%=request.getContextPath() %>/lib/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/lib/validation/js/validationEngine-cn.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/lib/validation/js/validationEngine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/lib/js/jquery.form.js"></script>

<link href="<%=request.getContextPath() %>/lib/validation/skins/validateStyle.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/lib/js/Site.js"></script>

<%--<script type="text/javascript" src="<%=request.getContextPath() %>/lib/easydialog-v2.0/easydialog.js"></script>
<link href="<%=request.getContextPath() %>/lib/easydialog-v2.0/easydialog.css" type="text/css" rel="stylesheet" />--%>


<!-- layer  -->
<script type="text/javascript" src="<%=request.getContextPath() %>/lib/layer-v3.1.1/layer/layer.js"></script>
<link href="<%=request.getContextPath() %>/lib/layer-v3.1.1/layer/theme/default/layer.css" type="text/css" rel="stylesheet" />

<link href="<%=request.getContextPath() %>/lib/css/reset.css" type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath() %>/lib/css/Site.css" type="text/css" rel="stylesheet" />

<script>
    var contextPath = "${pageContext.request.contextPath}";

    $(document).ready(function(){
        $("button").click(function(event){
            event.preventDefault();
        });
    });
    function downFile(logicTablename,relationalValue,ogicColumn){
        /* 创建iframe下载相应的附件信息 */
        window.open(contextPath + '/tWjWjController/downLoadFile?logicTablename='+logicTablename+"&relationalValue="+relationalValue+"&ogicColumn="+ogicColumn);
    }
</script>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Expires", "0");

%>
