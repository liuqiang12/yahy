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
        <!-- 上报的人员列查询所有的人员信息 -->
        var totalNum = 0;
        function loadListData(flag) {
            $.ajax({
                type: "POST",
                async:false,
                url: contextPath+"/tHyChryController/queryWinList?hyId=${hyEntity.id}&lbZt=2",
                dataType: 'json',
                async:false,
                success: function(msg){
                    $.each(msg.rows,function(idx,row){
                        var gridBody = "";
                        var lbName = row.lb=='Leaved'?'请假人员':
                            row.lb=='Driver'?'驾驶人员':
                                row.lb=='Personnel'?'工作人员':
                                    row.lb=='Participant'?'参会人员':'';
                        var lxfs = row.lxfs?row.lxfs:'';
                        var xb = row.xb=='male'? '男':'女';
                        var bz = row.bz?row.bz:'';
                        var dwName = row.dwName?row.dwName:'';
                        var zs = row.zsBz=='notwant'? '不住宿':'住宿';
                        if(flag == '-1'){
                            gridBody = "<tr class=\"chry_grid_tr_cls\">\n" +
                                "                    <td>"+row.dwName+"</td>\n" +
                                "                    <td>"+row.xm+"</td>\n" +
                                "                    <td>"+row.zw+"</td>\n" +
                                "                    <td>"+lxfs+"</td>\n" +
                                "                    <td>"+xb+"</td>\n" +
                                "                    <td>"+zs+"</td>\n" +
                                "                </tr>";
                            totalNum=totalNum+1;
                        }else if(flag == '1' &&　row.xb=='male'){
                            //刷新男
                            gridBody = "<tr class=\"chry_grid_tr_cls\">\n" +
                                "                    <td>"+row.dwName+"</td>\n" +
                                "                    <td>"+row.xm+"</td>\n" +
                                "                    <td>"+row.zw+"</td>\n" +
                                "                    <td>"+lxfs+"</td>\n" +
                                "                    <td>"+xb+"</td>\n" +
                                "                    <td>"+zs+"</td>\n" +
                                "                </tr>";
                            totalNum=totalNum+1;
                        }else if(flag == '2' &&　row.xb!='male'){
                            //刷新女

                            gridBody = "<tr class=\"chry_grid_tr_cls\">\n" +
                                "                    <td>"+row.dwName+"</td>\n" +
                                "                    <td>"+row.xm+"</td>\n" +
                                "                    <td>"+row.zw+"</td>\n" +
                                "                    <td>"+lxfs+"</td>\n" +
                                "                    <td>"+xb+"</td>\n" +
                                "                    <td>"+zs+"</td>\n" +
                                "                </tr>";
                            totalNum=totalNum+1;
                        }else if(flag == '3' &&　row.xb!='notwant'){
                            //刷新住宿

                            gridBody = "<tr class=\"chry_grid_tr_cls\">\n" +
                                "                    <td>"+row.dwName+"</td>\n" +
                                "                    <td>"+row.xm+"</td>\n" +
                                "                    <td>"+row.zw+"</td>\n" +
                                "                    <td>"+lxfs+"</td>\n" +
                                "                    <td>"+xb+"</td>\n" +
                                "                    <td>"+zs+"</td>\n" +
                                "                </tr>";
                            totalNum=totalNum+1;
                        }else if(flag == '4' &&　row.xb=='notwant'){
                            //刷新不住宿

                            gridBody = "<tr class=\"chry_grid_tr_cls\">\n" +
                                "                    <td>"+row.dwName+"</td>\n" +
                                "                    <td>"+row.xm+"</td>\n" +
                                "                    <td>"+row.zw+"</td>\n" +
                                "                    <td>"+lxfs+"</td>\n" +
                                "                    <td>"+xb+"</td>\n" +
                                "                    <td>"+zs+"</td>\n" +
                                "                </tr>";
                            totalNum=totalNum+1;
                        }
                        $("#chry_grid_tr").after(gridBody);
                    });
                }
            });
        }
        $(function () {
            loadListData('-1');
            $("#chdwTotal_id").text(totalNum);
        })
        function refreshGrid(flag,obj) {
            loadListData(flag);
            
            $("span.personcls").removeClass("cssPersonCls");
            $(obj).find("span").addClass("cssPersonCls");
            
            $("#chdwTotal_id").text(totalNum);
        }
        function deleteById(id) {
            //ajax
            $.ajax({
                //提交数据的类型 POST GET
                type:"POST",
                async:false,
                //提交的网址
                url:contextPath+"/tHyChryController/deleteById?id="+id,
                //返回数据的格式
                datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
                //在请求之前调用的函数
                beforeSend:function(){
                },
                //成功返回之后调用的函数
                success:function(data){
                    loadListData();
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
        function updateOpen(id) {
            // 弹出窗口
            var laySum = top.layer.open({
                type: 2,
                title: '上报参会人员',
                shadeClose: false,
                shade: 0.8,
                btn: ['确定','关闭'],
                maxmin : true,
                area: ['550px', '500px'],
                content: contextPath+"/tHyChryController/chryWinForUpdate?id="+id,
                /*弹出框*/
                cancel: function(index, layero){
                    top.layer.close(index);
                },yes: function(index, layero){
                    var childIframeid = layero.find('iframe')[0]['name'];
                    var chidlwin = top.document.getElementById(childIframeid).contentWindow;
                    var resJSON = chidlwin.form_sbmt_();
                    //console.log(resJSON)
                    if(!resJSON['validation']){
                        top.layer.msg('请填写必填项!', {
                            icon: 1,
                            time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                        });
                    }else if(resJSON['data'].isExists){
                        top.layer.msg(resJSON['data'].msg, {
                            icon: 1,
                            time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                        });
                    } else if(resJSON['data'] && resJSON['data'].success && !resJSON['data'].isExists){
                        top.layer.msg(resJSON['data'].msg, {
                            icon: 1,
                            time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                        });
                        /* 删除成功后刷新grid */
                        /*getDataList(0,null);*/
                        loadListData();
                        top.layer.close(index)
                    }else{
                        //存在了相同的数据项或者后台数据报错
                        top.layer.msg(resJSON['data'].msg, {
                            icon: 1,
                            time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                        });
                    }

                },no: function(index, layero){
                    top.layer.close(index)
                }
            });
        }



        /**
         * 获取统计信息：参会人员数  工作人员数  驾驶人员数 请假数
         * @param id
         */
        function refreshTjInfo(id){
            /* ajax */
            $.ajax({
                //提交数据的类型 POST GET
                type:"POST",
                //提交的网址
                async:false,
                url:contextPath+"/tHyHyController/refreshTjInfo?id="+id,
                //返回数据的格式
                datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
                //在请求之前调用的函数
                beforeSend:function(){
                },
                //成功返回之后调用的函数
                success:function(data){
                    //console.log(data)
                    $("#chry_num_id").text(10);
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

        /**
         * 从单位用户中选择人员
         * @param id
         */
        function dwhyzxzWin(id) {
            var laySum = top.layer.open({
                type: 2,
                title: '上报参会人员',
                shadeClose: false,
                shade: 0.8,
                btn: ['确定','关闭'],
                maxmin : true,
                area: ['950px', '500px'],
                content: contextPath+"/tHyHyController/dwhyzxzWin?id="+id,
                /*弹出框*/
                cancel: function(index, layero){
                    top.layer.close(index);
                },yes: function(index, layero){
                    var childIframeid = layero.find('iframe')[0]['name'];
                    var chidlwin = top.document.getElementById(childIframeid).contentWindow;
                    var resJSON = chidlwin.form_sbmt_();
                    //console.log(resJSON)
                    if(!resJSON['validation']){
                        top.layer.msg('请填写必填项!', {
                            icon: 1,
                            time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                        });
                    }else if(resJSON['data'].isExists){
                        top.layer.msg(resJSON['data'].msg, {
                            icon: 1,
                            time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                        });
                    } else if(resJSON['data'] && resJSON['data'].success && !resJSON['data'].isExists){
                        top.layer.msg(resJSON['data'].msg, {
                            icon: 1,
                            time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                        });
                        /* 删除成功后刷新grid */
                        /*getDataList(0,null);*/
                        loadListData();
                        top.layer.close(index)
                    }else{
                        //存在了相同的数据项或者后台数据报错
                        top.layer.msg(resJSON['data'].msg, {
                            icon: 1,
                            time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                        });
                    }

                },no: function(index, layero){
                    top.layer.close(index)
                }
            });

        }
        /**
         * 上报参会人员
         * @param id
         */
        function sbchryWin(id) {
            var laySum = top.layer.open({
                type: 2,
                title: '上报参会人员',
                shadeClose: false,
                shade: 0.8,
                btn: ['确定','关闭'],
                maxmin : true,
                area: ['550px', '500px'],
                content: contextPath+"/tHyHyController/bscyryWin?id="+id,
                /*弹出框*/
                cancel: function(index, layero){
                    top.layer.close(index);
                },yes: function(index, layero){
                    var childIframeid = layero.find('iframe')[0]['name'];
                    var chidlwin = top.document.getElementById(childIframeid).contentWindow;
                    var resJSON = chidlwin.form_sbmt_();
                    //console.log(resJSON)
                    if(!resJSON['validation']){
                        top.layer.msg('请填写必填项!', {
                            icon: 1,
                            time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                        });
                    }else if(resJSON['data'].isExists){
                        top.layer.msg(resJSON['data'].msg, {
                            icon: 1,
                            time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                        });
                    } else if(resJSON['data'] && resJSON['data'].success && !resJSON['data'].isExists){
                        top.layer.msg(resJSON['data'].msg, {
                            icon: 1,
                            time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                        });
                        /* 删除成功后刷新grid */
                        /*getDataList(0,null);*/
                        loadListData();
                        top.layer.close(index)
                    }else{
                        //存在了相同的数据项或者后台数据报错
                        top.layer.msg(resJSON['data'].msg, {
                            icon: 1,
                            time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                        });
                    }

                },no: function(index, layero){
                    top.layer.close(index)
                }
            });
        }
    </script>
</head>
<body>
<div class="container">
    <div id="dialog">
        <h2 class="title">参会人员</h2>
        <div class="row">
            <label>参会人员 <span>（共<i style="font-style:normal"><span id="chdwTotal_id"></span></i>个)</span></label>
            <label style="float:right;margin:0"><a href="javascript:void(0);" onclick="uploadWordForChry('${hyEntity.id}','2')">生成word</a></label>
            <label style="float:right;margin-right:50px">分类显示： <span><a href="javascript:void(0);" onclick="refreshGrid('-1',this)"><span class="personcls aaaPersonCls">所有人员</span></a><a href="javascript:void(0);" onclick="refreshGrid('1',this)"><span class="personcls aaaPersonCls">男</span></a><a href="javascript:void(0);" onclick="refreshGrid('2',this)"><span class="personcls aaaPersonCls">女</span></a><a href="javascript:void(0);"onclick="refreshGrid('3',this)"><span class="personcls aaaPersonCls">住宿</span></a><a href="javascript:void(0);" onclick="refreshGrid('4',this)"><span class="personcls aaaPersonCls">不住宿</span></a></span></label>
        </div>
        <table class="chry_tb">
            <tr id="chry_grid_tr">
                <th style="width:25%">单位</th>
                <th style="width:10%">姓名</th>
                <th style="width:35%">职务</th>
                <th style="width:14%">联系方式</th>
                <th style="width:8%">性别</th>
                <th style="width:8%">住宿</th>
            </tr>
        </table>
    </div>
</div>
</body>
</html>