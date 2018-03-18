<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html> <!-- Do not add ng-app here as we bootstrap AngularJS manually-->
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="<%=request.getContextPath() %>/lib/hygl/js/jquery-1.8.1.min.js"></script>
    <link href="<%=request.getContextPath() %>/lib/hygl/css/reset.css" type="text/css" rel="stylesheet" />
    <style>
        #dialog{width:900px;margin:8px auto;}
        body{background:#F8FCF9}
        td{text-align:center;line-height:50px;font-size:14px;}
    </style>
    <script type="text/javascript">
        /* ajax加载数据 */
        //获取发起单位和默认发起人名称
        var contextPath = "${pageContext.request.contextPath}";
        $(document).ready(function(){
            getDataList();
        });
        function getDataList(){
            $(".grid_head_tr").empty();
            $.ajax({
                type: "POST",
                async:false,
                url: contextPath+"/tXtHyddController/queryList",
                dataType: 'json',
                success: function(msg){
                    var gridBody = "<tr>";
                    $.each(msg.rows,function(idx,row){
                        var total = msg.total;
                        gridBody += "<td style='text-align:left;cursor:pointer' onclick=\"clickRadioToChecked('"+idx+"')\"><input id=\"name_"+idx+"\" type='hidden' value='"+row.mc+"'><input id=\""+idx+"\" type='radio' name='address' value='"+row.id+"'>"+row.mc+"</td>";
                        if( (idx +1)%4 == 0){
                            gridBody += "</tr><tr>";
                        }
                    });
                    gridBody += "</tr>";
                    $(".grid_head_tr").append(gridBody);
                }
            });
        }
        function clickRadioToChecked(idx) {
            $("#"+idx).attr("checked",true);
        }
        /* 被选中的节点  */
        function getCheckedNode() {
            var checkedNode = $("input:radio[name='address']:checked");
            var id = $(checkedNode).attr("id");
            var resJSON = {};
            if(checkedNode && checkedNode.length > 0){
                return {
                    id : $("#"+id).val(),
                    name : $("#name_"+id).val()
                }
            }else{
                return null;
            }
            //console.log(checkedNode)
        }
    </script>
</head>
<body style="overflow-x:hidden;overflow-y:auto;">
    <table id="dialog" class="grid_head_tr">
    </table>
</body>
</html>