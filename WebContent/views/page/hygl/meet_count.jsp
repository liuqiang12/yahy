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
        function getDataList(){
            $(".addGrid_data_tr").empty();
            $.ajax({
                type: "POST",
                url: contextPath+"/tHyHyController/countList",
                dataType: 'json',
                async:false,
                success: function(msg){
                    var total = msg.total;
                    $.each(msg.rows,function(idx,row){
                        var gridBody = "<tr class=\"tr addGrid_data_tr\">\n" +
                            "                    <td colspan=\"2\">"+row[0]+"</td>\n" +
                            "                    <td>"+row[1]+"</td>\n" +
                            "                    <td>"+row[2]+"</td>\n" +
                            "                    <td>"+row[3]+"</td>\n" +
                            "                    <td>"+row[4]+"</td>\n" +
                            "                    <td>"+row[5]+"</td>\n" +
                            "                    <td>"+row[6]+"</td>\n" +
                            "                </tr>";

                        $("#grid_head_tr").after(gridBody);
                    });
                }
            });
        }
        $(document).ready(function(){
            getDataList();
        });
    </script>
</head>
<body>
<div class="container">
    <div id="table" class="mt10">
        <div class="box span10 oh">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr>
                    <td class="title" colspan="2" rowspan="2" style="min-width:160px;width:16%">年度</td>
                    <td class="title" colspan="3" style="min-width:400px;width:42%">召开会议</td>
                    <td class="title" colspan="3" style="min-width:400px;width:42%">参加会议</td>
                </tr>
                <tr class="tr" id="grid_head_tr">
                    <td class="title">开会次数</td>
                    <td class="title">参会人员</td>
                    <td class="title">会议请假</td>
                    <td class="title">参会次数</td>
                    <td class="title">参会人员</td>
                    <td class="title">会议请假</td>
                </tr>
                <tr class="tr addGrid_data_tr">
                    <td colspan="2">2017</td>
                    <td>aad</td>
                    <td>aad</td>
                    <td>aad</td>
                    <td>aad</td>
                    <td>aad</td>
                    <td>aad</td>
                </tr>
            </table>
        </div>
    </div>
</div>
<div id="Pagin">
    <div id="pages" class="pages">
        <div id="Pagination" class="pagination"></div>
    </div>
</div>
</body>
</html>