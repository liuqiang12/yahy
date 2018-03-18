$(document).ready(function () {
    $("#fqSj").val(getFormatDateByLong(new Date().getTime(),"yyyy-MM-dd"));
});
/* 关闭按钮直接跳转到查询界面 */
function jdQueryForPage(){
	//未办结[--  --]
	top.document.getElementById("rightFrame").src = contextPath+"/hyGlController/fqhyRecordPageList?flagStatus=2&bjBzInt=0";
	/* 然后去除其他选中样式 */
	top.removeIframeMenuAct();
	top.addIframeMenuClass("hygl_wbj_init_id");
}
function jdFromSubmitFun(){
    var access=true;
    access=$('#singleForm').validationEngine({returnIsValid:true});
    if(access==true){
        /* 【是否选择了附件信息】：验证 */
        if($('input[name="tzyj"]').val()==null || $('input[name="tzyj"]').val()==''){

            top.layer.msg("必须上传[通知原件]", {
                icon: 1,
                time: 3000 //3秒关闭（如果不配置，默认是3秒）
            });
            return false;
        }
        //会议座次
        /*if($('input[name="hyzc"]').val()==null || $('input[name="hyzc"]').val()==''){

            top.layer.msg("必须上传[会议座次]", {
                icon: 1,
                time: 3000 //3秒关闭（如果不配置，默认是3秒）
            });
            return false;
        } */
        
        $("#singleForm").ajaxSubmit({
            type: 'post',
            url: contextPath+"/tHyHyController/savetHyHy?timestamp_long="+new Date().getTime() ,
            success: function(data){
            	 
                if(data.success){
                    top.layer.msg(data.msg, {
                        icon: 1,
                        time: 3000 //3秒关闭（如果不配置，默认是3秒）
                    });
                    if(!data.isExists){
                        var id = data['id'];
                        $("#id_").val(id);
                        jdQueryForPage();
                    }
                }else{
                	top.layer.msg("保存失败!", {
                        icon: 1,
                        time: 3000 //3秒关闭（如果不配置，默认是3秒）
                    });
                }
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
}
/*提交相关的数据到后台然后保存*/
function cbdwOpenWin() {
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