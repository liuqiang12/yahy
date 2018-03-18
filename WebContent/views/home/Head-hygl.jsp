<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<script src="<%=request.getContextPath() %>/lib/hygl/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/lib/validation/js/validationEngine-cn.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/lib/validation/js/validationEngine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/lib/js/jquery.form.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/lib/hygl/js/Site.js"></script>
<link href="<%=request.getContextPath() %>/lib/hygl/css/reset.css" type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath() %>/lib/hygl/css/Site.css" type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath() %>/lib/validation/skins/validateStyle.css" rel="stylesheet" type="text/css"/>
<%--<script type="text/javascript" src="<%=request.getContextPath() %>/lib/easydialog-v2.0/easydialog.js"></script>
<link href="<%=request.getContextPath() %>/lib/easydialog-v2.0/easydialog.css" type="text/css" rel="stylesheet" />--%>


<!-- layer  -->
<script type="text/javascript" src="<%=request.getContextPath() %>/lib/layer-v3.1.1/layer/layer.js"></script>
<link href="<%=request.getContextPath() %>/lib/layer-v3.1.1/layer/theme/default/layer.css" type="text/css" rel="stylesheet" />

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
<script>
    var contextPath = "${pageContext.request.contextPath}";
    Date.prototype.format = function (format) {
        var o = {
            "M+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes(),
            "s+": this.getSeconds(),
            "q+": Math.floor((this.getMonth() + 3) / 3),
            "S": this.getMilliseconds()
        }
        if (/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
            }
        }
        return format;
    }
    function getFormatDateByLong(l, pattern) {
        return getFormatDate(new Date(l), pattern);
    }
    function getFormatDate(date, pattern) {
        if (date == undefined) {
            date = new Date();
        }
        if (pattern == undefined) {
            pattern = "yyyy-MM-dd hh:mm:ss";
        }
        return date.format(pattern);
    }
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
