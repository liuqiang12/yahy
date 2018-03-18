<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>已报任务</title>
    <meta charset="utf-8" />
    <jsp:include page="/views/home/HeadGrid-Hy.jsp"></jsp:include>
    <script>
        var items_per_page = 8;//每页分页条数
        var page_index = 0;////页号
        function getDataList(index){
            var pageIndex = index;
            $(".addGrid_data_tr").empty();
            $.ajax({
                type: "POST",
                url: contextPath+"/tHyHyInstController/queryPageList?flagStatus=${flagStatus}&sbZtInt=${sbZtInt}",
                data: "pageNo="+(pageIndex+1)+'&pageSize='+items_per_page,
                dataType: 'json',
                async:false,
                /*contentType: "application/x-www-form-urlencoded",*/
                success: function(msg){
                    //console.log(msg.rows)
                    var total = msg.total;
                    $.each(msg.rows,function(idx,row){
                        var hyHyEntity=row.hyHyEntity;//会议信息
                        var hyddEntity = hyHyEntity.hyddEntity;//会议地点
                        var fqrEntity = hyHyEntity.fqrEntity;//发起人信息
                        var xtDwEntity = fqrEntity.xtDwEntity;//发起人单位


                        var fbdw = xtDwEntity?xtDwEntity['jgMc']:'';
                        var hymc = hyHyEntity.mc?hyHyEntity.mc:'';
                        var hySj = hyHyEntity.hySj;/* 会议时间 */
                        var hydd = hyddEntity?hyddEntity['mc']:'';
                        var gridBody = "<tr class='addGrid_data_tr'>"+
                                            "<td>"+fbdw+"</td>"+
                                        "   <td><a href='javascipt:void(0);'  onclick=\"openWinInfo(\'"+row.id+"\')\">"+hymc+"</a><a href='javascript:void(0);' onclick=\"openWinInfo(\'"+row.id+"\')\" class='fj'>【CEB】</a></td>"+
                                        "   <td>"+getFormatDateByLong(hySj, "yyyy-MM-dd")+"</td>"+
                                        "   <td>"+hydd+"</td>"+
                                        "   <td><a href='javascript:;'><img src='"+contextPath+"/lib/hygl/images/zc.png' alt=''></a></td>"+
                                        "   <td><a  href='javascript:void(0);' onclick=\"bsmdQueryWinPlugin('"+row.id+"')\"><img src='"+contextPath+"/lib/hygl/images/md.png'' alt=''></a></td>"+
                                        "   <td><a href='javascript:;'  onclick=\"downFile('T_HY_HY','"+hyHyEntity.id+"','tzyj')\" ><img src='"+contextPath+"/lib/hygl/images/cl.png' alt=''></a></td>"+
                            "          </tr>";

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
        function bsmdQueryWinPlugin(id) {
            /* 弹出框 */
            var laySum = top.layer.open({
                type: 2,
                title: "报送名单",
                shadeClose: false,
                shade: 0.8,
                btn: ['关闭'],
                maxmin : true,
                area: ['940px', '550px'],
                content: contextPath+"/tHyHyController/bsmdQueryWinPlugin?hyInstId="+id,
                /*弹出框*/
                cancel: function(index, layero){
                    top.layer.close(index);
                },no: function(index, layero){
                    top.layer.close(index)
                }
            });
        }
    </script>
</head>
<body>
<div class="container">
    <div id="table" class="mt10">
        <div class="box span10 oh">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                <tr id="grid_head_tr">
                    <th style="min-width:180px;width:15%;">发布单位</th>
                    <th style="min-width:300px;width:40%;">会议名称</th>
                    <th style="min-width:100px;width:10%;">开会时间</th>
                    <th style="min-width:180px;width:15%;">会议地点</th>
                    <th style="min-width:60px;width:5%;">座次表</th>
                    <th style="min-width:60px;width:5%;">报送名单</th>
                    <th style="min-width:60px;width:5%;">材料</th>
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