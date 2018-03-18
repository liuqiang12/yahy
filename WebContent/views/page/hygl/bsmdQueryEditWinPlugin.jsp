<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html> <!-- Do not add ng-app here as we bootstrap AngularJS manually-->
<head>
    <title>接待管理系统</title>
    <meta charset="utf-8" />
    <jsp:include page="/views/home/Head-hygl.jsp"></jsp:include>
    <!-- 日期控件 -->
    <script type="text/javascript" src="<%=request.getContextPath() %>/lib/My97Date/WdatePicker.js"></script>
    <script type="text/javascript">
        $(function(){
            /*设置被选中的select*/
            $("#mj option[value='${hyHyEntity.mj}']").attr("selected",true);
        })
        function defaultInfo() {
            $.ajax({
                //提交数据的类型 POST GET
                type:"POST",
                async:false,
                //提交的网址
                url:contextPath+"/tXtRyController/defaultInfo",
                //返回数据的格式
                datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
                //在请求之前调用的函数
                beforeSend:function(){
                },
                //成功返回之后调用的函数
                success:function(data){
                    $("#fqdw_text").text(data['xtDwEntity']['jgMc'])
                    $("#fqdw_id").val(data['xtDwEntity']['id']);
                    $("#fqr_txt").val(data['ryEntity']['xm']);
                    $("#fqr_id").val(data['ryEntity']['id']);
                    //当前时间
                    $("#fqSj").val(getFormatDateByLong(new Date().getTime(),"yyyy-MM-dd"));
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
        $(document).ready(function () {
            //defaultInfo();
        });
    </script>
</head>
<body style="overflow-x:hidden;overflow-y:auto;">
<form id="singleForm" method="post" action="<%=request.getContextPath() %>/tHyHyController/savetHyHy"  enctype="multipart/form-data" accept-charset="utf-8" >
    <input name="id" id="id" type="hidden" value="${hyHyEntity.id}">
<div class="container">
    <div id="dialog">
        <h2 class="title">雅安市委办公室会议通知</h2>
        <div class="row">
            <!-- 【发起单位和发起人】:默认设置 -->
            <input id="fqdw_id" type="hidden" name="fqdw_id" value="${hyHyEntity.fqrEntity.xtDwEntity.id}"/>
            <input id="fqr_id" type="hidden" name="fqr_id" value="${hyHyEntity.fqrEntity.id}"/>
            <label>发起单位：<span id="fqdw_text">${hyHyEntity.fqrEntity.xtDwEntity.jgMc}</span></label>
            <label>发起人：<input type="text" value="${hyHyEntity.fqrEntity.xm}" id="fqr_txt"/></label>
            <label style="margin-right:0;">发起时间：
                <input id="fqSj" name="fqSj" value="${hyHyEntity.fqSj}" class="validate[required,custom[date]] Wdate" type="text" onFocus="WdatePicker({lang:'zh-cn'})"/>
            </label>
        </div>
        <div class="row">
            <label style="margin:5px 0;">承办单位：
                <input type="hidden"  name="cbdwId" id="cbdwId" value="${hyHyEntity.cbdwId}" class="validate[required]" readonly="readonly"  >
                <input type="text" name="cbdwName" id="cbdwName" value="${hyHyEntity.cbdwName}" class="validate[required]" readonly="readonly" style="width:655px;">
                <button class="button" onclick="cbdwOpenWin()">选择</button></label>
        </div>
        <table>
            <tr>
                <td class="bt">会议名称</td>
                <td colspan="3">
                    <input type="text" style="width:670px;"  name="mc" value="${hyHyEntity.mc}"  class="validate[required,length[0,200]]">
                </td>
            </tr>
            <tr>
                <td class="bt">密级</td>
                <td colspan="3">
                    <select name="mj" id="mj" style="padding:4px;border:1px solid #ccc;" class="validate[required]">
                        <option value="1">一级</option>
                        <option value="2">二级</option>
                        <option value="3">三级</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="bt" width="100" style="min-width:100px;">会议时间</td>
                <td>
                    <input id="hySj" name="hySj" value="${hyHyEntity.hySj}" class="validate[required,custom[date]] Wdate" type="text" onFocus="WdatePicker({lang:'zh-cn'})"/>
                </td>
                <td class="bt" width="100" style="min-width:100px;">会议地点</td>
                <td>
                    <input style="width:250px;" type="text" name="hhyd_name" value="${hyHyEntity.hyddEntity.mc}" class="validate[required]" id="hhyd_name">
                    <input style="width:250px;" type="hidden" name="hhyd_id" value="${hyHyEntity.hyddEntity.id}" id="hhyd_id">
                    <button class="button" onclick="hyddWinPlugin()">选择</button>
                </td>
            </tr>
            <tr>
                <td class="bt">参会单位</td>
                <td colspan="3">
                    <input type="hidden" name="chdw_ids" id="chdw_ids" value="${chdwIds}" class="validate[required]" readonly="readonly" style="width:655px;">
                    <textarea style="width:597px;height:100px;" readonly="readonly" name="chdw_names" id="chdw_names" class="validate[required]"><c:out value="${chdwNames}"></c:out></textarea>
                    <button class="button" onclick="dwzOpenWin()">单位组</button></label>
                </td>
            </tr>
            <tr>
                <td class="bt">上报材料单位</td>
                <td colspan="3">
                    <input type="hidden" name="sbcldw_ids" id="sbcldw_ids" value="${sbdwIds}" class="validate[required]" readonly="readonly" style="width:655px;">
                    <textarea style="width:597px;height:100px;"  readonly="readonly" name="sbcldw_names" id="sbcldw_names" class="validate[required]"><c:out value="${sbdwNames}"></c:out></textarea>
                    <button class="button" onclick="sbcldwOpenWin()">单位组</button>
                </td>
            </tr>
            <tr>
                <td class="bt">备注</td>
                <td colspan="3">
                    <textarea style="width:670px;height:80px;" name="bz"  class="validate[required]"><c:out value="${hyHyEntity.bz}"></c:out></textarea>
                </td>
            </tr>
            <tr>
                <td class="bt">通知原件</td>
                <td colspan="3">
                    <!-- 通知原件 -->
                    <span onclick="downFile('T_HY_HY','${tzyj_wjEntity.relationalValue}','tzyj')" title="${tzyj_fjEntity.mc}" id="tzyj_xxx_text">${tzyj_fjEntity.nickMc}</span><!-- a标签，目的是可以下载附件 -->
                    <c:if test="${not empty tzyj_fjEntity.nickMc}">
                        <span style="color: red"  onclick="deleteFile('T_HY_HY','${tzyj_wjEntity.relationalValue}','tzyj',this)">删除</span>
                     </c:if>
                     <input type="file" style="width:615px" name="tzyj" class="validate[required]">
                </td>
            </tr>
            <tr>
                <td class="bt">会议座次</td>
                <td colspan="3">
                	<span onclick="downFile('T_HY_HY','${hyzc_wjEntity.relationalValue}','hyzc')" title="${hyzc_fjEntity.mc}" id="hyzc_xxx_text">${hyzc_fjEntity.nickMc}</span><!-- a标签，目的是可以下载附件 -->
                	<c:if test="${not empty hyzc_fjEntity.nickMc}">
                        <span style="color: red"  onclick="deleteFile('T_HY_HY','${tzyj_wjEntity.relationalValue}','hyzc',this)">删除</span>
                     </c:if>
                    <input type="file" style="width:615px" name="hyzc" class="validate[required]">
                </td>
            </tr>
        </table>
    </div>
</div>
</form>
<script type="text/javascript">
/* 删除附件 */
function deleteFile(logicTablename,relationalValue,ogicColumn,obj){
 	/* ajax */
 	top.layer.confirm('你确定要删除附件吗?', {
        btn: ['确定','取消'] //按钮
    }, function(index, layero){
        /*ajax*/
        $.ajax({
            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:contextPath+"/tWjWjController/delLocalFile",
            //提交的数据
            async:false,
            data:{
            	logicTablename:logicTablename,
            	relationalValue:relationalValue,
            	ogicColumn:ogicColumn
            },
            //返回数据的格式
            datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
            //在请求之前调用的函数
            beforeSend:function(){
            },
            //成功返回之后调用的函数
            success:function(data){
                //console.log(data)
                //提示删除成功
                top.layer.msg(data.msg, {
                    icon: 1,
                    time: 3000 //3秒关闭（如果不配置，默认是3秒）
                });
                if(data.success){
                	 $(obj).remove();
                	 $("#"+ogicColumn+"_xxx_text").text('');
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

    /*提交相关的数据到后台然后保存*/
    function jdFromSubmitFun(){
        var resJSON = {};
        var access=true;
        access=$('#singleForm').validationEngine({returnIsValid:true});
        if(access==true){
            $("#singleForm").ajaxSubmit({
                type: 'post',
                async: false,
                url: contextPath+"/tHyHyController/savetHyHy" ,
                success: function(data){
                    resJSON['data'] = data;
                    if(data.success){
                        top.layer.msg(data.msg, {
                            icon: 1,
                            time: 3000 //3秒关闭（如果不配置，默认是3秒）
                        });
                    }
                    //关闭窗口


                },
                error: function(XmlHttpRequest, textStatus, errorThrown){
                    top.layer.msg("后台报错，请联系管理员", {
                        icon: 1,
                        time: 3000 //3秒关闭（如果不配置，默认是3秒）
                    });
                }
            });
        }else{
            top.layer.msg("表单验证不通过，请核查!", {
                icon: 1,
                time: 3000 //3秒关闭（如果不配置，默认是3秒）
            });
            return false;
        }
        resJSON['validation'] = access;
        return resJSON;
    }

    function cbdwOpenWin() {
        /**
         * 弹出框
         */

        var laySum = top.layer.open({
            type: 2,
            title: "承办单位",
            shadeClose: false,
            shade: 0.8,
            btn: ['确定','关闭'],
            maxmin : true,
            area: ['550px', '450px'],
            content: contextPath+"/hyGlController/cbdwOpenPlugin",
            /*弹出框*/
            cancel: function(index, layero){
                top.layer.close(index);
            },yes: function(index, layero){
                var childIframeid = layero.find('iframe')[0]['name'];
                var chidlwin = top.document.getElementById(childIframeid).contentWindow;
                var resJOSN = chidlwin.getCheckedNode();
                if(resJOSN == null){
                    top.layer.msg('至少选择一个单位', {
                        icon: 1,
                        time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                    });
                }else{
                    $("#cbdwId").val(resJOSN['id']);
                    $("#cbdwName").val(resJOSN['name']);
                    top.layer.close(index)
                }

            },no: function(index, layero){
                top.layer.close(index)
            }
        });
    }

    function sbcldwOpenWin() {

        /**
         * 参会单位:其中上报材料单位是从  参会单位   中选择
         */
        var chzIds = $("#chdw_ids").val();
        //必须先选择参会单位
        if(chzIds == null || chzIds == ''){
            top.layer.msg('至少选择一个参会单位', {
                icon: 1,
                time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
            });
            return false;
        }


        var laySum = top.layer.open({
            type: 2,
            title: '上报材料单位',
            shadeClose: false,
            shade: 0.8,
            btn: ['确定','关闭'],
            maxmin : true,
            area: ['850px', '500px'],
            content: contextPath+"/hyGlController/sbcldwOpenPluginForGrid?chzIds="+chzIds,
            /*弹出框*/
            cancel: function(index, layero){
                top.layer.close(index);
            },yes: function(index, layero){
                var childIframeid = layero.find('iframe')[0]['name'];
                var chidlwin = top.document.getElementById(childIframeid).contentWindow;
                var resJOSN = chidlwin.getCheckedNode();
                if(resJOSN == null){
                    top.layer.msg('至少选择一个上报材料单位', {
                        icon: 1,
                        time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                    });
                }else{
                    /*参会组名称*/
                    var sbcldwNames = "",sbcldwIds="";
                    for(var i = 0  ;i < resJOSN.length-1; i++){
                        sbcldwNames += resJOSN[i]['name']+';'
                    }
                    sbcldwNames += resJOSN[resJOSN.length-1]['name'];
                    for(var i = 0  ;i < resJOSN.length-1; i++){
                        sbcldwIds += resJOSN[i]['id']+';'
                    }
                    sbcldwIds += resJOSN[resJOSN.length-1]['id'];

                    $("#sbcldw_names").val(sbcldwNames);
                    $("#sbcldw_ids").val(sbcldwIds);


                    top.layer.close(index)
                }

            },no: function(index, layero){
                top.layer.close(index)
            }
        });
    }
    function dwzOpenWin() {
        /**
         * 参会单位
         */
        var laySum = top.layer.open({
            type: 2,
            title: '参会单位',
            shadeClose: false,
            shade: 0.8,
            btn: ['确定','关闭'],
            maxmin : true,
            area: ['550px', '450px'],
            content: contextPath+"/hyGlController/chdwOpenPlugin",
            /*弹出框*/
            cancel: function(index, layero){
                top.layer.close(index);
            },yes: function(index, layero){
                var childIframeid = layero.find('iframe')[0]['name'];
                var chidlwin = top.document.getElementById(childIframeid).contentWindow;
                var resJOSN = chidlwin.getCheckedNode();
                if(resJOSN == null){
                    top.layer.msg('至少选择一个单位', {
                        icon: 1,
                        time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                    });
                }else{
                    /*参会组名称*/
                    var chzNames = "",chzIds="";
                    for(var i = 0  ;i < resJOSN.length-1; i++){
                        chzNames += resJOSN[i]['name']+';'
                    }
                    chzNames += resJOSN[resJOSN.length-1]['name'];
                    for(var i = 0  ;i < resJOSN.length-1; i++){
                        chzIds += resJOSN[i]['id']+';'
                    }
                    chzIds += resJOSN[resJOSN.length-1]['id'];

                    $("#chdw_names").val(chzNames);
                    $("#chdw_ids").val(chzIds);


                    top.layer.close(index)
                }

            },no: function(index, layero){
                top.layer.close(index)
            }
        });
    }
    /**
     * 会议地点
     */
    function hyddWinPlugin(){
        var laySum = top.layer.open({
            type: 2,
            title: '会议地点',
            shadeClose: false,
            shade: 0.8,
            btn: ['确定','关闭'],
            maxmin : true,
            area: ['950px', '450px'],
            content: contextPath+"/hyGlController/hyddWinPlugin",
            /*弹出框*/
            cancel: function(index, layero){
                top.layer.close(index);
            },yes: function(index, layero){
                var childIframeid = layero.find('iframe')[0]['name'];
                var chidlwin = top.document.getElementById(childIframeid).contentWindow;
                var resJOSN = chidlwin.getCheckedNode();
                if(resJOSN == null){
                    top.layer.msg('至少选择一个地点!', {
                        icon: 1,
                        time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                    });
                }else{
                    $("#hhyd_name").val(resJOSN['name']);
                    $("#hhyd_id").val(resJOSN['id']);
                    //console.log(resJOSN)
                    top.layer.close(index)
                }

            },no: function(index, layero){
                top.layer.close(index)
            }
        });
    }
</script>
</body>
</html>