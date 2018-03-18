<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html> <!-- Do not add ng-app here as we bootstrap AngularJS manually-->
<head>
    <title>接待管理系统</title>
    <meta charset="utf-8" />
    <jsp:include page="/views/home/Head-hygl.jsp"></jsp:include>
    <!-- 日期控件 -->
    <script type="text/javascript" src="<%=request.getContextPath() %>/lib/My97Date/WdatePicker.js"></script>
    <script src="<%=request.getContextPath() %>/lib/js/dwhyzxzWin.js"></script>
    <script>
        $(function(){
            $('.people input').click(function(){
                if($(this).parent().text()=='会议请假'){
                    $(this).parent().parent().siblings('.normal').hide()
                    $(this).parent().parent().siblings('.qj').show()
                }else{
                    $(this).parent().parent().siblings('.normal').show()
                    $(this).parent().parent().siblings('.qj').hide()
                }
            })
        })
    </script>
</head>
<body style="overflow-x:hidden;overflow-y:auto;">

<form id="singleForm" method="post" action="<%=request.getContextPath() %>/tHyChryController/savetHyChryBatch" accept-charset="utf-8" >
<div class="container">
    <div id="dialog" style="width:900px;">
        <div>
            <div class="btn_group" style="text-align: left">
                <button class="button" onclick="dwWinPlugin('${hyId}')">选择参会人员</button>
            </div>
            <table class="chry_tb">
                <tr id="grid_head_tr">
                    <th style="width:35px">选择</th>
                    <th style="width:65px">姓名</th>
                    <th style="width:120px">职务</th>
                    <th style="width:330px">参会类别</th>
                    <th style="width:90px">性别</th>
                    <th style="width:140px">住宿</th>
                    <th style="width:120px">联系方式</th>
                </tr>
            </table>

        </div>
    </div>
</div>
</form>
</body>
</html>