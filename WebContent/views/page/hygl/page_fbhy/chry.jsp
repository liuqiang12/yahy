<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>接待管理系统</title>
    <meta charset="utf-8" />
    <jsp:include page="/views/home/HeadGrid-Hy.jsp"></jsp:include>
    <script src="<%=request.getContextPath() %>/lib/js/chry.js"></script>
    
    <script type="text/javascript">
        var totalNum = 0;
        function loadListData(flag) {
            $.ajax({
                type: "POST",
                async:false,
                url: contextPath+"/tHyChryController/queryWinList?hyId=${hyEntity.id}&lbZt=0",
                dataType: 'json',
                async:false,
                success: function(msg){
                    $(".chry_grid_tr_cls").empty()
                    totalNum=0
                    $.each(msg.rows,function(idx,row){
                        var gridBody = "";
                        var lbName = getLbName(row.lb);
                        var lxfs = row.lxfs?row.lxfs:'';
                        var xb = getXb(row.xb);
                        var bz = row.bz?row.bz:'';
                        var dwName = row.dwName?row.dwName:'';
                        var zs = getZsBz(row.zsBz);
                        if(flag == '-1'){
                            //刷新所有的
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
                        }else if(flag == '2' && row.xb!='male'){
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
        function refreshGrid(flag,obj) {
            loadListData(flag);
            
            $("span.personcls").removeClass("cssPersonCls");
            $(obj).find("span").addClass("cssPersonCls");
            
            $("#chdwTotal_id").text(totalNum);
        }
        $(function () {
            loadListData('-1');
            $("#chdwTotal_id").text(totalNum);
        })
    </script>
</head>
<body>
<div class="container">
    <div id="dialog">
        <h2 class="title">参会人员</h2>
        <div class="row">
            <label>参会人员 <span>（共<i style="font-style:normal"><span id="chdwTotal_id"></span></i>个)</span></label>
            <label style="float:right;margin:0"><a href="javascript:void(0);" onclick="uploadWordForChry('${hyEntity.id}','0')">生成word</a></label>
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