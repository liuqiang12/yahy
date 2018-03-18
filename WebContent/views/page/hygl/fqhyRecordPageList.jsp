<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>会议管理系统</title>
    <meta charset="utf-8" />
    <jsp:include page="/views/home/HeadGrid-Hy.jsp"></jsp:include>
    <script src="<%=request.getContextPath() %>/lib/js/fqhyRecordPageList.js"></script>
    <script>
        var items_per_page = 8;//每页分页条数
        var page_index = 0;////页号
        function getDataList(index){
            var pageIndex = index;
            $(".addGrid_data_tr").empty();
            $.ajax({
                type: "POST",
                async:false,
                url: contextPath+"/tHyHyInstController/queryPageList?flagStatus=${flagStatus}&bjBzInt=${bjBzInt}",
                data: "pageNo="+(pageIndex+1)+'&pageSize='+items_per_page,
                dataType: 'json',
                /*contentType: "application/x-www-form-urlencoded",*/
                success: function(msg){
                    var total = msg.total;
                    $.each(msg.rows,function(idx,row){
                        var hyHyEntity=row.hyHyEntity;//会议信息
                        var hyId=hyHyEntity.id;//会议ID
                        var hyddEntity = hyHyEntity.hyddEntity;//会议地点
                        var fqrEntity = hyHyEntity.fqrEntity;//发起人信息
                        var xtDwEntity = fqrEntity.xtDwEntity;//发起人单位
                        var fqr = fqrEntity.xm;//发起人
                        var fbdw = xtDwEntity?xtDwEntity['jgMc']:'';
                        var hymc = hyHyEntity.mc?hyHyEntity.mc:'';
                        var hySj = hyHyEntity.hySj;/* 会议时间1 */
                        var hydd = hyddEntity?hyddEntity['mc']:'';
                        var gridBody ="";
                        if("${flagStatus}" == '2' && "${bjBzInt}" == '0'){
                            //对方发起的会议。//未上报
                            gridBody =
                            "<tr class='addGrid_data_tr'>"+
                            "   <td>"+getFormatDateByLong(hySj, "yyyy-MM-dd")+"</td>"+
                            "   <td>"+hydd+"</td>"+
                            "   <td><a href='javascipt:void(0);' onclick=\"openWinInfo(\'"+row.id+"\')\">"+hymc+"</a><a href='javascript:void(0);' onclick=\"openWinInfo(\'"+row.id+"\')\" class='fj'>【CEB】</a></td>"+
                            "   <td>"+fqr+"</td>"+
                            "   <td>"+getFormatDateByLong(hySj, "yyyy-MM-dd")+"</td>"+
                            "   <td><a href='javascript:void(0);' onclick=\"downFile('T_HY_HY','"+hyId+"','hyzc')\"><img src='"+contextPath+"/lib/hygl/images/zc.png' alt=''></a></td>"+
                            "   <td><a href='javascript:;' onclick=\"bsmdQueryEditWinPlugin('"+row.id+"')\"><img src='"+contextPath+"/lib/hygl/images/edit.png' alt=''></a></td>"+
                            "   <td><a  href='javascript:void(0);' onclick=\"delRecord('"+row.id+"')\"><img src='"+contextPath+"/lib/hygl/images/del.png'' alt=''></a></td>"+
                            "   <td><a href='javascript:;'  onclick=\"banjieFun('"+row.id+"')\" ><img   src='"+contextPath+"/lib/hygl/images/bj.png' alt=''></a></td>"+
                            "</tr>";
                        }else if("${flagStatus}" == '2' && "${bjBzInt}" == '1'){
                            //对方发起的会议。//已经上报
                            gridBody =
                                "<tr class='addGrid_data_tr'>"+
                                "   <td>"+getFormatDateByLong(hySj, "yyyy-MM-dd")+"</td>"+
                                "   <td>"+hydd+"</td>"+
                                "   <td><a href='javascipt:void(0);' onclick=\"openWinInfo(\'"+row.id+"\')\">"+hymc+"</a><a href='javascript:void(0);' onclick=\"openWinInfo(\'"+row.id+"\')\" class='fj'>【CEB】</a></td>"+
                                "   <td>"+fqr+"</td>"+
                                "   <td>"+getFormatDateByLong(hySj, "yyyy-MM-dd")+"</td>"+
                                "   <td><a href='javascript:void(0);' onclick=\"downFile('T_HY_HY','"+hyId+"','hyzc')\"><img src='"+contextPath+"/lib/hygl/images/zc.png' alt=''></a></td>"+
                                "</tr>";
                        }
                        $("#grid_head_tr").after(gridBody);
                    });
                    //分页-只初始化一次
                    if($("#Pagination").html().length == ''){
                        $("#Pagination").pagination(total, {
                            'items_per_page'      : items_per_page,
                            'num_display_entries' : 10,
                            'num_edge_entries'    : 2,
                            'prev_text'           : "上一页",
                            'next_text'           : "下一页",
                            'callback'            : pageselectCallback
                        });
                    }
                }
            });
        }
        function pageselectCallback(page_index, jq){
            getDataList(page_index);
        }
        $(document).ready(function(){
            getDataList(page_index);
        });
    </script>
</head>
<body>
<div class="container">
    <div id="table" class="mt10">
        <div class="box span10 oh">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <c:if test="${flagStatus == 2 and bjBzInt == 0}">
                    <!-- 参加会议的未报情况 -->
                    <tr id="grid_head_tr">
                        <th style="min-width:100px;width:10%;">开会时间</th>
                        <th style="min-width:180px;width:15%;">会议地点</th>
                        <th style="min-width:300px;width:40%;">会议名称</th>
                        <th style="min-width:60px;width:5%;">发起人</th>
                        <th style="min-width:100px;width:10%;">发起时间</th>
                        <th style="min-width:60px;width:5%;">座次表</th>
                        <th style="min-width:60px;width:5%;">编辑</th>
                        <th style="min-width:60px;width:5%;">删除</th>
                        <th style="min-width:60px;width:5%;">办结</th>
                    </tr>
                </c:if>
                <c:if test="${flagStatus == 2 and bjBzInt == 1}">
                    <!-- 参加会议的已报情况 -->
                    <tr id="grid_head_tr">
                        <th style="min-width:100px;width:10%;">开会时间</th>
                        <th style="min-width:180px;width:15%;">会议地点</th>
                        <th style="min-width:300px;width:40%;">会议名称</th>
                        <th style="min-width:60px;width:5%;">发起人</th>
                        <th style="min-width:100px;width:10%;">发起时间</th>
                        <th style="min-width:60px;width:5%;">座次表</th>
                    </tr>
                </c:if>
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