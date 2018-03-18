<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<link rel="stylesheet" href="<%=request.getContextPath() %>/lib/css/reset.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/lib/css/Site.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/lib/css/pagination.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/lib/css/easydialog.css">
<script src="<%=request.getContextPath() %>/lib/js/jquery-1.8.1.min.js"></script>
<script src="<%=request.getContextPath() %>/lib/js/jquery.pagination.js"></script>
<script src="<%=request.getContextPath() %>/lib/js/easydialog.min.js"></script>
<script src="<%=request.getContextPath() %>/lib/js/common.js"></script>
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
    function fhsybWin(id){
        /*初审*/
        top.layer.confirm('是否要返回上一步', {
            btn: ['确定','取消'] //按钮
        }, function(index, layero){
            /*ajax*/
            $.ajax({
                //提交数据的类型 POST GET
                type:"POST",
                //提交的网址
                url:contextPath+"/tJdJdxxController/fhsybWin",
                //提交的数据
                async:false,
                data:{
                    id:id
                },
                //返回数据的格式
                datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
                //在请求之前调用的函数
                beforeSend:function(){
                },
                //成功返回之后调用的函数
                success:function(data){
                    //console.log(data)
                    //提示删除成功
                    top.layer.msg(data.msg, {
                        icon: 1,
                        time: 3000 //3秒关闭（如果不配置，默认是3秒）
                    });
                    /* 删除成功后刷新grid */
                    getDataList(page_index,null);
                },
                //调用执行后调用的函数
                complete: function(XMLHttpRequest, textStatus){
                },
                //调用出错执行的函数
                error: function(){
                    //请求出错处理
                }
            });
        }, function(index, layero){
            top.layer.close(index)
        });
    }
</script>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Expires", "0");

%>
