<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>会议管理系统</title>
    <meta charset="utf-8" />
    <jsp:include page="/views/home/HeadGrid-Hy.jsp"></jsp:include>
</head>
    <script type="text/javascript">
        var totalNum = 0;
        $(function(){
            loadChdwGridData();
            $("#chdwTotal_id").text(totalNum);
        })
        function loadChdwGridData() {
            //未签收：移除:查询参会单位列表
            $.ajax({
                type: "POST",
                async:false,
                url: contextPath+"/tHyHyController/loadChdwGridData?hyId=${hyEntity.id}",
                dataType: 'json',
                async:false,
                success: function(msg){
                    var idx = 0;
                    var gridBody = "<tr>";
                    $.each(msg.rows,function(idx,row){
                        var chdwName = row[0];
                        var remark = row[1];


                        if(chdwName){
                            totalNum=totalNum+1;
                        }
                        var chdwName = chdwName?chdwName:''
                        var remark = remark?remark:'';
                        //参会单位每两个换一行
                        gridBody += "<td>"+chdwName+"</td>\n" +
                            "<td><span class=\"wqs\">"+remark+"</span></td>";

                        if((idx+1) % 2 == 0){
                            gridBody += "</tr><tr>";
                        }
                        idx = idx+1;
                    });
                    gridBody += "</tr>";
                    $("#chdw_grid_list_id").after(gridBody);
                }
            });
        }
        /**
         * word
         * @param id
         */
        function uploadWord(id){
            window.open(contextPath + '/tChDwController/queryListWord?hyId='+id);
        }

        //chdw_grid_list_id
        function loadGridData(){
            $.ajax({
                type: "POST",
                async:false,
                url: contextPath+"/tChDwController/queryList?hyId=${hyEntity.id}",
                dataType: 'json',
                async:false,
                success: function(msg){
                    var idx = 0;
                    var gridBody = "<tr>";
                    $.each(msg.rows,function(idx,row){
                        //参会单位每两个换一行
                        var qsSj = getFormatDateByLong(row.qsSj, "yyyy-MM-dd hh:mm:ss");
                        gridBody += "<td>"+row.chdwName+"</td>\n" +
                            "<td><span class=\"wqs\">"+qsSj+"</span></td>";

                            if((idx+1) % 2 == 0){
                                gridBody += "</tr><tr>";
                            }
                            idx = idx+1;
                        totalNum=totalNum+1;
                    });
                    gridBody += "</tr>";
                    $("#chdw_grid_list_id").after(gridBody);
                }
            });
        }
    </script>
<body>
<div class="container">
    <div id="dialog">
        <h2 class="title">参会单位</h2>
        <div class="row">
            <label style="float:left;">参会单位 <span>（共<i style="font-style:normal"><span id="chdwTotal_id"></span></i>个)</span></label>
            <label style="float:right;margin:0"><a href="javascript:void(0);" onclick="uploadWord('${hyEntity.id}')">生成word</a></label>
        </div>
        <table class="chry_tb">
            <tr id="chdw_grid_list_id">
                <th style="width:30%">参会单位</th>
                <th style="width:20%">签收时间</th>
                <th style="width:30%">参会单位</th>
                <th style="width:20%">签收时间</th>
            </tr>
        </table>
    </div>
</div>
</body>
</html>