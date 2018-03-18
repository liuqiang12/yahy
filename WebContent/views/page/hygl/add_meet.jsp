<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>会议管理系统</title>
    <meta charset="utf-8" />
    <jsp:include page="/views/home/Head-hygl.jsp"></jsp:include>
    <script type="text/javascript" src="<%=request.getContextPath() %>/lib/My97Date/WdatePicker.js"></script><!-- 日期控件 -->
    <script src="<%=request.getContextPath() %>/lib/js/add_meet.js"></script>
</head>
<body style="overflow-x:hidden;overflow-y:auto;">
<form id="singleForm" method="post" action="<%=request.getContextPath() %>/tHyHyController/savetHyHy"  enctype="multipart/form-data" accept-charset="utf-8" >
<div class="container">
    <div id="dialog">
        <h2 class="title">雅安市委办公室会议通知</h2>
        <div class="row">
            <!-- 【发起单位和发起人】:默认设置 -->
            <input id="fqdw_id" type="hidden" name="fqdw_id" value="${xtRyEntity.xtDwEntity.id}"/>
            <input id="fqr_id" type="hidden" name="fqr_id" value="${xtRyEntity.id}"/>
            <label>发起单位：<span id="fqdw_text">${xtRyEntity.xtDwEntity.jgMc}</span></label>
            <label>发起人：<input type="text" value="${xtRyEntity.xm}" id="fqr_txt"/></label>
            <label style="margin-right:0;">发起时间：
                <input id="fqSj" name="fqSj" class="validate[required,custom[date]] Wdate" type="text" onFocus="WdatePicker({lang:'zh-cn'})"/>
            </label>
        </div>
        <div class="row">
            <label style="margin:5px 0;">承办单位：
                <input type="hidden" name="cbdwId" id="cbdwId" value="" class="validate[required]" readonly="readonly" >
                <input type="text" name="cbdwName" id="cbdwName" value="" class="validate[required]" readonly="readonly" style="width:655px;">
                <button class="button" onclick="cbdwOpenWin()">选择</button></label>
        </div>
        <table>
            <tr>
                <td class="bt">会议名称</td>
                <td colspan="3">
                    <input type="text" style="width:670px;" name="mc"  class="validate[required,length[0,200]]">
                </td>
            </tr>
            <tr>
                <td class="bt">密级</td>
                <td colspan="3">
                    <select name="mj" id="" style="padding:4px;border:1px solid #ccc;" class="validate[required]">
                        <option value="1">一级</option>
                        <option value="2">二级</option>
                        <option value="3">三级</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="bt" width="100" style="min-width:100px;">会议时间</td>
                <td>
                    <input id="hySj" name="hySj" class="validate[required,custom[date]] Wdate" type="text" onFocus="WdatePicker({lang:'zh-cn'})"/>
                </td>
                <td class="bt" width="100" style="min-width:100px;">会议地点</td>
                <td>
                    <input style="width:250px;" type="text" name="hhyd_name" class="validate[required]" id="hhyd_name">
                    <input style="width:250px;" type="hidden" name="hhyd_id" id="hhyd_id">
                    <button class="button" onclick="hyddWinPlugin()">选择</button>
                </td>
            </tr>
            <tr>
                <td class="bt">参会单位</td>
                <td colspan="3">
                    <input type="hidden" name="chdw_ids" id="chdw_ids" value="" class="validate[required]" readonly="readonly" style="width:655px;">
                    <textarea style="width:597px;height:100px;" readonly="readonly" name="chdw_names" id="chdw_names" class="validate[required]"></textarea>
                    <button class="button" onclick="dwzOpenWin()">单位组</button>
                </td>
            </tr>
            <tr>
                <td class="bt">上报材料单位</td>
                <td colspan="3">
                    <input type="hidden" name="sbcldw_ids" id="sbcldw_ids" value="" class="validate[required]" readonly="readonly" style="width:655px;">
                    <textarea style="width:597px;height:100px;" readonly="readonly" name="sbcldw_names" id="sbcldw_names" class="validate[required]"></textarea>
                    <button class="button" onclick="sbcldwOpenWin()">单位组</button>
                </td>
            </tr>
            <tr>
                <td class="bt">备注</td>
                <td colspan="3">
                    <textarea style="width:670px;height:80px;" name="bz" class="validate[required]"><c:out value="" default="  "></c:out></textarea>
                </td>
            </tr>
            <tr>
                <td class="bt">通知原件</td>
                <td colspan="3"><input type="file" style="width:615px" name="tzyj" ></td>
            </tr>
            <tr>
                <td class="bt">会议座次</td>
                <td colspan="3"><input type="file" style="width:615px" name="hyzc"></td>
            </tr>
        </table>
        <div class="row button_group"><button class="button" id="jdFromSubmit" onclick="jdFromSubmitFun()">保存</button><button class="button" onclick="jdQueryForPage()">关闭</button></div>
    </div>
</div>
</form>
</body>
</html>