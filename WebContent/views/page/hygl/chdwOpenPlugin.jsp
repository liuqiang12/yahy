<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html> <!-- Do not add ng-app here as we bootstrap AngularJS manually-->
<head>
    <title>接待管理系统</title>
    <meta charset="utf-8" />
    <jsp:include page="/views/home/Head.jsp"></jsp:include>
    <!-- ztree形式 -->
    <script type="text/javascript" src="<%=request.getContextPath() %>/lib/zTree_v3/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/lib/zTree_v3/js/jquery.ztree.excheck.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/lib/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <!-- 主要是针对于布局easyui -->
    <script type="text/javascript" src="<%=request.getContextPath() %>/lib/jquery-easyui-1.5/jquery.easyui.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/lib/jquery-easyui-1.5/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/lib/jquery-easyui-1.5/themes/icon.css" type="text/css">
    <script type="application/javascript">
        var zTree ;
        var setting = {
            view : {
                showLine : true,
                selectedMulti : false,
                dblClickExpand : false
            },
            check: {
                enable: true,
                nocheckInherit: false
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
            zTree.checkNode(zTree.getNodeByParam("id",treeNode.id, null), true, true);
        }
        /*  单位信息  */
        $(document).ready(function() {
            var data = eval("("+'${treeNode}'+")");
            zTree = $.fn.zTree.init($("#treeDemo"), setting, data);
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
                nodes=treeObj.getCheckedNodes(true);
            var resJOSN = [];
            for(var i=0;i<nodes.length;i++){
                var item = {};
                item['id'] = nodes[i].id;
                item['name'] = nodes[i].name;
                resJOSN.push(item)
            }
            if(resJOSN != null && resJOSN.length > 0){
                return resJOSN;
            }else{
                return null;
            }

        }


    </script>
</head>
<body style="overflow-x:hidden;overflow-y:auto;">
    <ul id="treeDemo" class="ztree"></ul>
</body>
</html>