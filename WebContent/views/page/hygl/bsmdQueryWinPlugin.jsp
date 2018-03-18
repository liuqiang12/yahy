<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>接待管理系统</title>
    <meta charset="utf-8" />
    <jsp:include page="/views/home/HeadGrid-Hy.jsp"></jsp:include>
    <script src="<%=request.getContextPath() %>/lib/js/bsmdQueryWinPlugin.js"></script>
    <script type="text/javascript">
        var chrsNum = 0,gzrsNum=0,jsrRsNum=0,qjRsNum=0;
        function loadListData() {
            $.ajax({
                type: "POST",
                url: contextPath+"/tHyChryController/queryList?hyId=${hyHyEntity.id}",
                dataType: 'json',
                async:false,
                success: function(msg){
                    $(".chry_grid_tr_cls").empty();
                    $(".qjchry_grid_tr_cls").empty();

                    $.each(msg.rows,function(idx,row){
                        var gridBody = "";
                        var lbName = getLbName(row.lb);
                        var lxfs = row.lxfs?row.lxfs:'';
                        var xb = getXb(row.xb);
                        var bz = row.bz?row.bz:'';
                        var zs = getZsBz(row.zsBz);
                        if(row.lb == 'Leaved'){ qjRsNum=qjRsNum+1;
                        }else if(row.lb == 'Driver'){ jsrRsNum=jsrRsNum+1;
                        }else if(row.lb == 'Personnel'){ gzrsNum=gzrsNum+1;
                        }else if(row.lb == 'Participant'){ chrsNum=chrsNum+1;}

                        if('Leaved' == row.lb){//请假数据
                            gridBody =
                                "                <tr class=\"qjchry_grid_tr_cls\">\n" +
                                "                    <td style=\"width:10%\">"+row.xm+"</td>\n" +
                                "                    <td style=\"width:42%\">"+row.zw+"</td>\n" +
                                "                    <td style=\"width:10%\">会议请假</td>\n" +
                                "                    <td style=\"width:28%\">"+bz+"</td>\n" +
                                "                </tr>\n" ;
                            $("#qjchry_grid_tr").after(gridBody);
                        }else{
                            gridBody = "<tr class=\"chry_grid_tr_cls\">\n" +
                                "                    <td>"+row.xm+"</td>\n" +
                                "                    <td>"+row.zw+"</td>\n" +
                                "                    <td>"+lbName+"</td>\n" +
                                "                    <td>"+lxfs+"</td>\n" +
                                "                    <td>"+xb+"</td>\n" +
                                "                    <td>"+zs+"</td>\n" +
                                "                </tr>";
                            $("#chry_grid_tr").after(gridBody);
                        }

                    });
                }
            });
        }
        $(function () {
            loadListData();
            $("#chry_num_id").text(chrsNum);
            $("#gzry_num_id").text(gzrsNum);
            $("#jsry_num_id").text(jsrRsNum);
            $("#qjry_num_id").text(qjRsNum);
        })
    </script>
    <!---->
</head>
<body style="overflow-x:hidden;overflow-y:auto;">
<div class="container">
    <div id="dialog" style="width:900px;">
        <div class="row">
            <label>会议标题：<span>${hyHyEntity.mc}</span></label>
        </div>
        <div class="row">
            <label style="margin:5px 0;">报送情况：<span>参会人员 <b>（<span id="chry_num_id"></span>位）</b></span><span>工作人员 <b>（<span id="gzry_num_id"></span>位）</b></span><span>驾驶员 <b>（<span id="jsry_num_id"></span>位）</b></span><span>请假 <b>（<span id="qjry_num_id"></span>位）</b></span></label>
        </div>
        <div>
            <table class="chry_tb">
                <tr id="chry_grid_tr">
                    <th style="width:10%">姓名</th>
                    <th style="width:42%">职务</th>
                    <th style="width:10%">参会类别</th>
                    <th style="width:10%">联系方式</th>
                    <th style="width:8%">性别</th>
                    <th style="width:10%">住宿</th>
                </tr>
                <tr class="chry_grid_tr_cls"></tr>
            </table>
            <table class="chry_tb qjmd " >
                <tr class="qjchry_grid_tr_cls" id="qjchry_grid_tr"></tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>