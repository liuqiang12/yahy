<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>会议管理系统</title>
    <meta charset="utf-8" />
    <jsp:include page="/views/home/HeadGrid-Hy.jsp"></jsp:include>
    <script src="<%=request.getContextPath() %>/lib/js/hywin.js"></script>
    <script type="text/javascript">
        //参会人员加载  chry_grid_list_id
        function loadListData() {
            $.ajax({
                type: "POST",
                url: contextPath+"/tHyChryController/queryList?hyId=${hyEntity.id}",
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
                        if('Leaved' == row.lb){
                            //请假数据
                            gridBody =
                                "                <tr class=\"qjchry_grid_tr_cls\">\n" +
                                "                    <td style=\"width:10%\">"+row.xm+"</td>\n" +
                                "                    <td style=\"width:42%\">"+row.zw+"</td>\n" +
                                "                    <td style=\"width:10%\">会议请假</td>\n" +
                                "                    <td style=\"width:28%\">"+bz+"</td>\n" +
                                "                </tr>\n" ;
                            $("#qjchry_grid_list_id").after(gridBody);
                        }else{
                            gridBody = "<tr class=\"chry_grid_tr_cls\">\n" +
                                "                    <td>"+row.xm+"</td>\n" +
                                "                    <td>"+row.zw+"</td>\n" +
                                "                    <td>"+lbName+"</td>\n" +
                                "                    <td>"+lxfs+"</td>\n" +
                                "                    <td>"+xb+"</td>\n" +
                                "                    <td>"+zs+"</td>\n" +
                                "                </tr>";
                            $("#chry_grid_list_id").after(gridBody);
                        }

                    });
                }
            });
        }
        $(function () {
            loadListData();
        })
    </script>
</head>
<body>
<div class="container">
    <div id="dialog">
        <h2 class="title">雅安市委宣传部会议通知</h2>
        <div class="row">
            <label>发起单位：<span>${hyEntity.fqrEntity.xtDwEntity.jgMc}</span></label>
            <label style="margin-left:120px">发起人：<span>${hyEntity.fqrEntity.xm}</span></label>
            <label style="margin-right:0;float:right">发起时间：<span>${hyEntity.fqSj}</span></label>
        </div>
        <div class="row">
            <label style="margin:5px 0;">承办单位：<span>${hyEntity.fqrEntity.xtDwEntity.jgMc}</span></label>
        </div>
        <table>
            <tr>
                <td class="bt">会议名称</td>
                <td colspan="3"><span>${hyEntity.mc}</span></td>
            </tr>
            <tr>
                <td class="bt" width="100" style="min-width:100px;">会议时间</td>
                <td><span>${hyEntity.hySj}</span></td>
                <td class="bt" width="100" style="min-width:100px;">会议地点</td>
                <td>${hyEntity.hyddEntity.mc}</td>
            </tr>
            <tr>
                <td class="bt">备注</td>
                <td colspan="3">
                    <p class="bz_txt">${hyEntity.bz}</p>
                </td>
            </tr>
            <tr>
                <td class="bt">通知原件</td>
                <td colspan="3"><a href="javascript:void(0);"  onclick="downFile('T_HY_HY','${hyEntity.id}','tzyj')">${wjFjEntity.mc}</a></td>
            </tr>
            <tr>
                <td class="bt">参会人员</td>
                <td colspan="3">
                    <%--<button class="button" style="float:right;margin-bottom:5px;margin-top:2px;">上报参会人员</button>--%>
                    <table class="chry_tb">
                        <tr id="chry_grid_list_id">
                            <th style="width:10%">姓名</th>
                            <th style="width:42%">职务</th>
                            <th style="width:15%">参会类别</th>
                            <th style="width:15%">联系方式</th>
                            <th style="width:8%">性别</th>
                            <th style="width:10%">住宿</th>
                        </tr>
                    </table>
                    <table class="chry_tb qjmd">
                        <tr id="qjchry_grid_list_id"></tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td class="bt">上报材料</td>
                <td colspan="3">
                    <p class="cl_txt"><a href="javascript:void(0);"  onclick="downFile('T_HY_HY','${hyEntity.id}','bscl')">${wjFjEntityTmp.mc}</a></p>
                </td>
            </tr>
            <tr>
                <td class="bt">会议座次</td>
                <td colspan="3"><a href="javascript:;">无</a></td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>