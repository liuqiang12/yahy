<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html> <!-- Do not add ng-app here as we bootstrap AngularJS manually-->
<head>
    <title>接待管理系统</title>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/lib/css/reset.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/lib/css/Site.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/lib/css/pagination.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/lib/css/easydialog.css">
    <script src="<%=request.getContextPath() %>/lib/js/jquery-1.8.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/lib/js/jquery.pagination.new.js"></script>
    <script src="<%=request.getContextPath() %>/lib/js/common.js"></script>
    <!-- ztree形式 -->
    <script type="text/javascript" src="<%=request.getContextPath() %>/lib/zTree_v3/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/lib/zTree_v3/js/jquery.ztree.excheck.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/lib/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <!-- 主要是针对于布局easyui -->
    <script type="text/javascript" src="<%=request.getContextPath() %>/lib/jquery-easyui-1.5/jquery.easyui.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/lib/jquery-easyui-1.5/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/lib/jquery-easyui-1.5/themes/icon.css" type="text/css">
    <script type="application/javascript">
        var contextPath = "${pageContext.request.contextPath}";

        var zTree ;
        var items_per_page = 8;//每页分页条数:每页三条记录
        var page_index = 0;////页号
        var setting = {
            view : {
                showLine : true,
                selectedMulti : false,
                dblClickExpand : false
            },
            check: {
                enable: true,
                nocheckInherit: false,
                chkStyle: "radio",  //单选框
                radioType: "all"   //对所有节点设置单选
            },
            data : {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pid",
                    rootPId: 'root'
                }
            },
            callback : {
                onClick : this.onClick,
                beforeExpand: beforeExpand,
                onAsyncSuccess: onAsyncSuccess,
                onAsyncError: onAsyncError
            },
            async: {
                enable: false,
                url: getUrl
            }
        };
        //过滤节点的机制 直接return node表示不做任何过滤
        function filter(node) {
            return node;
        }

        function onClick(e, treeId, treeNode) {
            /*zTree.checkNode(zTree.getNodeByParam("id",treeNode.id, null), true, true);*/
            getDataList(0,treeNode.id);
        }
        /*  单位信息  */
        $(document).ready(function() {
            var data = eval("("+'${treeNode}'+")");
            zTree = $.fn.zTree.init($("#treeDemo"), setting, data);
            getDataList(page_index,null);
        });
        function beforeExpand(treeId, treeNode) {
            //alert("beforeExpand-----------");
            if (!treeNode.isAjaxing) {
                startTime = new Date();
                treeNode.times = 1;

                ajaxGetNodes(treeNode, "refresh");
                return true;
            } else {
                //alert("zTree 正在下载数据中，请稍后展开节点。。。");
                return false;
            }
        }
        //手动添加异步加载
        function ajaxGetNodes(treeNode, reloadType) {
            // alert("ajaxGetNodes-----------");
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            if (reloadType == "refresh") {
                zTree.updateNode(treeNode);
            }
            zTree.reAsyncChildNodes(treeNode, reloadType, true);
        }
        function getUrl(treeId, treeNode) {
            if(treeNode){
                return contextPath+"/tXtDwController/queryZtreeRecord.do?pid="+treeNode.id;
            }else{
                return contextPath+"/tXtDwController/queryZtreeRecord.do?pid=";
            }
        }
        function onAsyncSuccess(event, treeId, treeNode, msg) {
            //alert("onAsyncSuccess-----------");
            if (!msg || msg.length == 0) {
                return;
            }
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            if(treeNode){
                var totalCount = treeNode.count;
                if (treeNode.children.length < totalCount) {
                    setTimeout(function() {ajaxGetNodes(treeNode);}, perTime);
                } else {
                    treeNode.icon = "";
                    zTree.updateNode(treeNode);
                    zTree.selectNode(treeNode.children[0]);
                }
            }
        }
        function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
            //alert("onAsyncError-----------");
            zTree = $.fn.zTree.getZTreeObj("treeDemo");
            // alert("异步获取数据出现异常。");
            treeNode.icon = "";
            zTree.updateNode(treeNode);
        }
        function getCheckedNode(){
            var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
                nodes=treeObj.getSelectedNodes();
            var resJOSN = {};
            for(var i=0;i<nodes.length;i++){
                resJOSN['id'] = nodes[i].id;
                resJOSN['name'] = nodes[i].name;
                return resJOSN;
            }
            return null;
        }
        function checkedRow(idx){
            $("#grid_row_"+idx).attr("checked", true);
        }
        function checkedSingleRow(idx){
            /* 去掉其他的选项 */
            /*$("input[id^='grid_row_']").each(function(){
                $(this).attr("checked", false);
            })*/
           $("#grid_row_"+idx).attr("checked", !$("#grid_row_"+idx).is(":checked"));
        }
        function getSelectedGridRecord(){
            /* 获取被选中的人员信息 */
            var selectedInput = $("input:checkbox[id^='grid_row_']:checked");
            if(selectedInput && selectedInput.length > 0){

                var resJSON = [];
                $.each($("input:checkbox[id^='grid_row_']:checked"),function (indx) {
                  var id = this.id;//grid_row_0
                    var i = id.split("grid_row_")[1];
                  resJSON.push({
                      id : this.value,
                      name : $("#grid_row_text_"+i).val(),
                      zw : $("#grid_zw_"+i).val(),
                      xb : $("#grid_xb_"+i).val(),
                      lxfs : $("#grid_lxfs_"+i).val(),
                      dwId:$("#grid_dw_"+i).val()
                  })
                })
                //console.log(resJSON)
                return resJSON;
            }else{
                return null;
            }
        }
        function getDataList(index,id){
            var pageIndex = index;
            $(".addGrid_data_tr").empty();
            $.ajax({
                type: "POST",
                async:false,
                url: contextPath+"/tXtRyController/queryPageListOhterHyId?hyId=${hyId}",
                data: "pageNo="+(pageIndex+1)+'&pageSize='+items_per_page+'&dwId='+id,
                dataType: 'json',
                /*contentType: "application/x-www-form-urlencoded",*/
                success: function(msg){
                    var total = msg.total;
                    var rows = msg.rows;
                    $.each(msg.rows,function(idx,row){
                         var gridBody = "<tr   class='addGrid_data_tr'>"+
                        "<td style='text-align:left;' ><input id='grid_row_text_"+idx+"' type='hidden' value='"+row.xm+"' /><input type='checkbox' id='grid_row_"+idx+"' value='"+row.id+"'></input><span onclick='checkedSingleRow("+idx+")'>"+row.xm+"</span></td>"+
                        "   <td onclick='checkedSingleRow("+idx+")'>"+(row.xb=='male'?'男':'女')+"</td>"+
                        "   <td onclick='checkedSingleRow("+idx+")'><input id='grid_zw_"+idx+"' type='hidden' value='"+row.zw+"' /><input id='grid_dw_"+idx+"' type='hidden' value='"+id+"' /><input id='grid_xb_"+idx+"' type='hidden' value='"+row.xb+"' /><input id='grid_lxfs_"+idx+"' type='hidden' value='"+row.lxfs+"' />"+row.zw+"</td>"+
                        "   <td onclick='checkedSingleRow("+idx+")'>"+row.lxfs+"</td>"
                        "   </tr>";
                        $("#grid_head_tr").after(gridBody);
                    });
                    //分页-只初始化一次
                    if($("#Pagination").html().length == ''){
                        $("#Pagination").easyPagination(total, {
                            'items_per_page'      : items_per_page,
                            'num_display_entries' : 8,
                            'num_edge_entries'    : 1,
                            'prev_text'           : "上一页",
                            'next_text'           : "下一页",
                            'callback'            : pageselectCallback
                        });
                    }
                }
            });
        }
        function pageselectCallback(page_index, jq){
            getDataList(page_index,null);
        }
    </script>
</head>
<body style="overflow-x:hidden;overflow-y:auto;" class="easyui-layout">
    <!-- 左右布局 -->
    <div data-options="region:'west',title:'',split:true" style="width:200px;">
        <ul id="treeDemo" class="ztree"></ul>
    </div>
    <div data-options="region:'center',title:''">
        <!-- 查询toolbar：分页grid -->
        <div class="container">
            <div id="table" class="mt10">
                <div class="box span10 oh">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="list_table">
                        <tr id="grid_head_tr">
                            <th style="width:12%;min-width:40px;">名称</th>
                            <th style="width:2%;min-width:15px;">性别</th>
                            <th style="width:7%;min-width:40px;">职务</th>
                            <th style="width:5%;min-width:40px;">联系方式</th>
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
    </div>
</body>
</html>