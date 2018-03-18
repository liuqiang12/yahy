<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>接待管理系统</title>
    <meta charset="utf-8" />
    <jsp:include page="/views/home/HeadGrid-Hy.jsp"></jsp:include>
    <script type="text/javascript">
        //加载报送材料
       var totalNum = 0;
        function loadListData() {
            $.ajax({
                type: "POST",
                url: contextPath+"/tHyHyController/queryBsclList?hyId=${hyEntity.id}",
                dataType: 'json',
                async:false,
                success: function(msg){
                    var bsdwName = msg.dwStrs;
                    var rows = msg.rows;

                    $.each(rows,function(idx,row){
                        var bt = row.bsclBt?row.bsclBt:'';
                        var gridBody = "<tr>\n" +
                            "                <td>"+bsdwName+"</td>\n" +
                            "                <td>"+bt+"</td>\n" +
                            "                <td><a href=\"javascript:;\" onclick=\"downFile('T_HY_HY','${hyEntity.id}','bscl')\">查看</a></td>\n" +
                            "            </tr>";
                        totalNum=totalNum+1;
                        $("#chry_grid_tr").after(gridBody);
                    });
                }
            });
        }
        $(function () {
            loadListData();
            $("#chdwTotal_id").text(totalNum);
        })
        function uploadFile(id) {
            alert("下载文件")
        }
    </script>
</head>
<body>
<div class="container">
    <div id="dialog">
        <h2 class="title">会议材料</h2>
        <div class="row">
            <label>会议材料 <span>（共<i style="font-style:normal"><span id="chdwTotal_id"></span></i>份)</span></label>
        </div>
        <table class="chry_tb">
            <tr id="chry_grid_tr">
                <th style="width:25%">报送单位</th>
                <th style="width:65%">材料标题</th>
                <th style="width:10%">查看</th>
            </tr>

        </table>
    </div>
</div>
</body>
</html>