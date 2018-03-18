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
        var items_per_page = 8;//每页分页条数
        var page_index = 0;////页号
        function getDataList(index){
            var pageIndex = index;
            $(".addGrid_data_tr").empty();
            $.ajax({
                type: "POST",
                async:false,
                url: contextPath+"/tHyHyInstController/queryPageList?flagStatus=${flagStatus}&sbZtInt=${sbZtInt}",
                data: "pageNo="+(pageIndex+1)+'&pageSize='+items_per_page,
                dataType: 'json',
                /*contentType: "application/x-www-form-urlencoded",*/
                success: function(msg){

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
                                        "   <td><a  href='javascript:void(0);'  onclick=\"bsmdWinPlugin(\'"+row.id+"\')\"><img src='"+contextPath+"/lib/hygl/images/md.png'' alt=''></a></td>"+
                                        "   <td><a href='javascript:;'  onclick=\"downFile('T_HY_HY','"+hyHyEntity.id+"','tzyj')\" ><img src='"+contextPath+"/lib/hygl/images/cl.png' alt=''></a></td>";
                        if("${flagStatus}" == '1' && "${sbZtInt}" == '0'){
                            //对方    未报情况才有办结按钮
                            //如果是已经办结的，就提示已经办结
                            if(row.bjBz == 'banjied'){
                                gridBody +=  "   <td>已办</td>";
                            }else{
                                gridBody +=  "   <td><a href='javascript:;' onclick=\"banjieFun('"+row.id+"')\"><img  src='"+contextPath+"/lib/hygl/images/bj.png' alt=''></a></td>";
                            }
                        }
                        gridBody += "</tr>";
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
        function banjieFun(id){
            top.layer.confirm('你确定要办结吗？', {
                btn: ['确定','取消'] //按钮
            }, function(index, layero){
                $.ajax({
                    //提交数据的类型 POST GET
                    type:"POST",
                    //提交的网址
                    url:contextPath+"/tHyHyController/isCHryExist?hyInstId="+id,
                    async:false,
                    //返回数据的格式
                    datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
                    //在请求之前调用的函数
                    beforeSend:function(){
                    },
                    //成功返回之后调用的函数
                    success:function(data){
                        if(!data.isExist){
                            top.layer.msg("没有人员可以办结，请核查数据！", {
                                icon: 1,
                                time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                            });
                        }else{
                            /**
                             * ajax 修改状态已经上报
                             */
                            $.ajax({
                                //提交数据的类型 POST GET
                                type:"POST",
                                //提交的网址:会议实例
                                url:contextPath+"/tHyHyInstController/updateBanjieZt?hyInstId="+id,
                                async:false,
                                //返回数据的格式
                                datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
                                //在请求之前调用的函数
                                beforeSend:function(){
                                },
                                //成功返回之后调用的函数
                                success:function(data){
                                    if(data.success){
                                        top.layer.msg("提交办结！", {
                                            icon: 1,
                                            time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                                        });
                                        top.layer.close(index);
                                        /* 刷新 */
                                        getDataList(page_index);
                                    }else{
                                        top.layer.msg("办结失败！", {
                                            icon: 1,
                                            time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                                        });
                                    }
                                },
                                //调用执行后调用的函数
                                complete: function(XMLHttpRequest, textStatus){
                                },
                                //调用出错执行的函数
                                error: function(){
                                    //请求出错处理
                                }
                            });
                        }
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
        function pageselectCallback(page_index, jq){
            getDataList(page_index);
        }
        $(document).ready(function(){
            getDataList(page_index);
        });
        function bsmdWinPlugin(id) {
            /* 弹出框 */
            var laySum = top.layer.open({
                type: 2,
                title: "报送名单",
                shadeClose: false,
                shade: 0.8,
                btn: ['确定','关闭'],
                maxmin : true,
                area: ['940px', '550px'],
                content: contextPath+"/tHyHyController/bsmdWinPlugin?hyInstId="+id,
                /*弹出框*/
                cancel: function(index, layero){
                    top.layer.close(index);
                },yes: function(rootIndex, layero){
                    var childIframeid = layero.find('iframe')[0]['name'];
                    var chidlwin = top.document.getElementById(childIframeid).contentWindow;

                    top.layer.confirm('你确定要报送所选用户吗？', {
                        btn: ['确定','取消'] //按钮
                    }, function(index, layero){
                        /* 然后还回会议id */
                        $.ajax({
                            //提交数据的类型 POST GET
                            type:"POST",
                            //提交的网址
                            url:contextPath+"/tHyHyController/isCHryExist?hyInstId="+id,
                            async:false,
                            //返回数据的格式
                            datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
                            //在请求之前调用的函数
                            beforeSend:function(){
                            },
                            //成功返回之后调用的函数
                            success:function(data){
                                if(!data.isExist){
                                    top.layer.msg("没有人员可以上报，请核查数据！", {
                                        icon: 1,
                                        time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                                    });
                                }else{
                                    /**
                                     * ajax 修改状态已经上报
                                     */
                                    $.ajax({
                                        //提交数据的类型 POST GET
                                        type:"POST",
                                        //提交的网址
                                        url:contextPath+"/tHyHyInstController/updateSbZt?hyInstId="+id,
                                        async:false,
                                        //返回数据的格式
                                        datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
                                        //在请求之前调用的函数
                                        beforeSend:function(){
                                        },
                                        //成功返回之后调用的函数
                                        success:function(data){
                                            if(data.success){
                                                top.layer.msg("上报成功！", {
                                                    icon: 1,
                                                    time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                                                });
                                                top.layer.close(index);
                                                top.layer.close(rootIndex);
                                                /* 刷新 */
                                                getDataList(page_index);
                                            }else{
                                                top.layer.msg("上报失败！", {
                                                    icon: 1,
                                                    time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                                                });
                                            }
                                        },
                                        //调用执行后调用的函数
                                        complete: function(XMLHttpRequest, textStatus){
                                        },
                                        //调用出错执行的函数
                                        error: function(){
                                            //请求出错处理
                                        }
                                    });
                                }
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
                    <th style="min-width:60px;width:5%;">办结</th>
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