<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html> <!-- Do not add ng-app here as we bootstrap AngularJS manually-->
<head>
    <title>会议管理系统</title>
    <meta charset="utf-8" />
    <jsp:include page="/views/home/Head-hygl.jsp"></jsp:include>
    <!-- 日期控件 -->
    <script type="text/javascript" src="<%=request.getContextPath() %>/lib/My97Date/WdatePicker.js"></script>
    <script>
        $(function(){
            $('.people input').click(function(){
                if($(this).parent().text()=='会议请假'){
                    $(this).parent().parent().parent().siblings('.normal').hide()
                    $(this).parent().parent().parent().siblings('.qj').show()
                }else{
                    $(this).parent().parent().parent().siblings('.normal').show()
                    $(this).parent().parent().parent().siblings('.qj').hide()
                }
            });
            $("input:radio[name='sex']").each(function () {
                var val = this.value;
                var xb = "${chryEntity.xb}";
                if(xb == 'female' && val == 2){
                    $(this).attr("checked",true)
                }
                if(xb == 'male' && val == 1){
                    $(this).attr("checked",true)
                }
            });
            //
            $("input:radio[name='lb_int']").each(function () {
                var val = this.value;
                var lb = "${chryEntity.lb}";
                if(lb == 'Participant' && val == 0){
                    $(this).attr("checked",true)
                    $(this).parent().parent().parent().siblings('.normal').show()
                    $(this).parent().parent().parent().siblings('.qj').hide()
                }
                if(lb == 'Personnel' && val == 1){
                    $(this).attr("checked",true)
                    $(this).parent().parent().parent().siblings('.normal').show()
                    $(this).parent().parent().parent().siblings('.qj').hide()
                }
                if(lb == 'Driver' && val == 2){
                    $(this).attr("checked",true)
                    $(this).parent().parent().parent().siblings('.normal').show()
                    $(this).parent().parent().parent().siblings('.qj').hide()
                }
                if(lb == 'Leaved' && val == 3){
                    $(this).attr("checked",true)
                    $(this).parent().parent().parent().siblings('.normal').hide()
                    $(this).parent().parent().parent().siblings('.qj').show()
                }
            });
            $("input:radio[name='zs']").each(function () {
                var val = this.value;
                var zsBz = "${chryEntity.zsBz}";
                if(zsBz == 'notwant' && val == 0){
                    $(this).attr("checked",true)
                }
                if(zsBz == 'want' && val == 1){
                    $(this).attr("checked",true)
                }
            });

        })
        /**
         * 提交表单
         **/
        function form_sbmt_(){
            var resJSON = {};
            var access=true;
            access=$('#singleForm').validationEngine({returnIsValid:true});
            if(access==true){
                var lb_int = $("input:radio[name='lb_int']:checked").val();
                var lxfs = $("#lxfs").val();
                var bz = $("#bz").val();
                if(!lxfs && lb_int != 3){
                    top.layer.msg('请填写联系方式', {
                        icon: 1,
                        time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                    });
                    resJSON['validation'] = false;
                }
                if(!bz && lb_int == 3){
                    top.layer.msg('请填写请假事由', {
                        icon: 1,
                        time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                    });
                    resJSON['validation'] = false;
                }


                $("#singleForm").ajaxSubmit({
                    type: 'post',
                    async: false,
                    url: contextPath+"/tHyChryController/savetHyChry" ,
                    success: function(data){
                        resJSON['data'] = data;
                        if(data.success){
                            /* 其中保存完成后直接关闭窗口 */
                            top.layer.msg('成功', {
                                icon: 1,
                                time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                            });
                        }
                    },
                    error: function(XmlHttpRequest, textStatus, errorThrown){
                        alert( "error");
                    }
                });
            }
            resJSON['validation'] = access;
            return resJSON;
        }
    </script>
</head>
<body style="overflow-x:hidden;overflow-y:auto;">
<form id="singleForm" method="post" action="<%=request.getContextPath() %>/tHyChryController/savetHyChry" accept-charset="utf-8" >
    <!-- 主键 -->
    <input name="id" value="${chryEntity.id}" type="hidden">
    <input name="hy_id" value="${chryEntity.hyHyEntity.id}" type="hidden">
    <!-- 外部人员 -->
    <input name="nbStatus" value="0" type="hidden">
<div class="container">
    <div id="dialog" style="width:500px;">
        <table class="chry_tb bs_tb">
            <tr>
                <td class="bt" style="width:25%">单位</td>
                <!-- 单位选择 -->
                <td style="width:75%">
                    <input style="width:97%" type="text" name="dwName" class="validate[required]" value="${chryEntity.dwName}">
                </td>
            </tr>
            <tr>
                <td class="bt">姓名</td>
                <td><input style="width:97%" type="text"  name="xm" class="validate[required]" value="${chryEntity.xm}"></td>
            </tr>
            <tr>
                <td class="bt">性别</td>
                <td><label><input type="radio" name="sex" checked value="1"> 男</label><label><input type="radio" name="sex" value="2"> 女</label></td>
            </tr>
            <tr>
                <td class="bt">类别</td>
                <td class="people"><label><input type="radio" name="lb_int" checked value="0">参会人员</label><label><input type="radio" name="lb_int" value="1">工作人员</label><label><input type="radio" name="lb_int" value="2">驾驶员</label><label class="hyqj" style="color:red;"><input type="radio" name="lb_int" value="3">会议请假</label></td>
            </tr>
            <tr>
                <td class="bt">职务</td>
                <td><input style="width:97%" type="text" name="zw" class="validate[required]" value="${chryEntity.zw}"></td>
            </tr>
            <tr class="normal">
                <td class="bt">住宿</td>
                <td ><label><input type="radio" name="zs" value="1"> 住宿</label><label><input type="radio" name="zs" checked value="0"> 不住宿</label></td>
            </tr>
            <tr class="normal">
                <td class="bt">联系方式</td>
                <td><input style="width:97%" type="text" name="lxfs" id="lxfs" value="${chryEntity.lxfs}"></td>
            </tr>
            <tr class="qj" style="display:none">
                <td class="bt">事由</td>
                <td><textarea style="width:97%;height:80px;" name="bz" id="bz"><c:out value="${chryEntity.bz}"></c:out></textarea></td>
            </tr>
        </table>
    </div>
</div>
</form>
</body>
</html>