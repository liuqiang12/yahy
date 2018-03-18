function dwWinPlugin(hyId) {

    /* 弹出框人员选择 */
    var laySum = top.layer.open({
        type: 2,
        title: "人员选择",
        shadeClose: false,
        shade: 0.8,
        btn: ['确定','关闭'],
        maxmin : true,
        area: ['880px', '550px'],
        content: contextPath+"/tXtRyController/userSelectOpenPluginForHy?hyId="+hyId,
        /*弹出框*/
        cancel: function(index, layero){
            top.layer.close(index);
        },yes: function(index, layero){
            var childIframeid = layero.find('iframe')[0]['name'];
            var chidlwin = top.document.getElementById(childIframeid).contentWindow;
            var resJOSN = chidlwin.getSelectedGridRecord();

            if(resJOSN == null){
                top.layer.msg('至少选择一个人员', {
                    icon: 1,
                    time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                });
            }else{
                var trIndx = $(".addGrid_tr_cls").length;
                $.each(resJOSN,function (index,item) {
                    /* 判断是否已经存在了相同的ID */
                    if(!isExists(item['id'])){
                        //console.log(item['id']+":=====:"+item['name']+"=="+item['zw']+"=="+item['xb']+"=="+item['lxfs']);
                        var gridBody = "<tr class='addGrid_tr_cls'>"+
                            "<input type='hidden' id='dwId"+trIndx+"' value="+item['dwId']+">"+
                            "<input type='hidden' id='hyId"+trIndx+"' value="+hyId+">"+
                            "                   <td><input type='checkbox' checked name='id"+trIndx+"' value="+item['id']+"></td>"+
                            "                   <td><input type='hidden' id='name"+trIndx+"' value="+item['name']+">"+item['name']+"</td>"+
                            "                   <td><input style='width:100px' type='text' class='validate[required]' id='zw"+trIndx+"' name='zw"+trIndx+"' value='"+item['zw']+"'></td>"+
                            "                   <td class='people'><label><input type='radio' name='chry"+trIndx+"' checked value='0'>参会人员</label><label><input type='radio' name='chry"+trIndx+"' value='1'>工作人员</label><label><input type='radio' name='chry"+trIndx+"' value='2'>驾驶员</label><label class='hyqj'><input type='radio' name='chry"+trIndx+"' value='3'>会议请假</label></td>"+
                            "                   <td class='normal'><label><input type='radio' name='sex"+trIndx+"' checked value='1'>男</label><label><input type='radio' name='sex"+trIndx+"' value='2'>女</label></td>"+
                            "                   <td class='normal'><label><input type='radio' name='zs"+trIndx+"' value='1'>住宿</label><label><input type='radio' name='zs"+trIndx+"' checked value='1'>不住宿</label></td>"+
                            "                   <td class='normal'><input style='width:100px' type='text'  name='lxfs"+trIndx+"'  id='lxfs"+trIndx+"'  value='"+item['lxfs']+"'></td>"+
                            "                   <td class='qj' colspan='3' style='display:none'><input style='width:96.2%'  name='bz"+trIndx+"' id='bz"+trIndx+"'  type='text' value=''></td>"+
                            "           </tr>";

                        $("#grid_head_tr").after(gridBody);
                        trIndx = trIndx+1;
                    }
                })
                $('.people input').click(function(){
                    if($(this).parent().text()=='会议请假'){
                        $(this).parent().parent().siblings('.normal').hide()
                        $(this).parent().parent().siblings('.qj').show()
                    }else{
                        $(this).parent().parent().siblings('.normal').show()
                        $(this).parent().parent().siblings('.qj').hide()
                    }
                })
                top.layer.close(index)
            }

        },no: function(index, layero){
            top.layer.close(index)
        }
    });
}
function isExists(id){
    var isHave = false;
    $("input:checkbox").each(function () {
        if (this.value == id){
            isHave = true;
        }
    })
    return isHave;
}
/**
 * 提交表单
 **/
function form_sbmt_(){
    var resJSON = {};
    var access=true;
    access=$('#singleForm').validationEngine({returnIsValid:true});
    if(access==true){
        /* 获取列表中的选中的人员 */
        var resourcesData = [];
        $("input:checkbox:checked").each(function(){
            var name = this.name;
            var value = this.value;
            var idx = name.split("id")[1];

            resourcesData.push({
                id : value,
                dwId : $("#dwId"+idx).val(),
                nbStatus : 1,
                xm : $("#name"+idx).val(),
                zw:$("#zw"+idx).val(),
                xb : $("input:radio[name=sex"+idx+"]:checked").val(),
                lb:$("input:radio[name=chry"+idx+"]:checked").val(),
                zsBz:$("input:radio[name=zs"+idx+"]:checked").val(),
                lxfs:$("#lxfs"+idx).val(),
                bz:$("#bz"+idx).val(),
                hyId:$("#hyId"+idx).val()
            })
        })
        $("#singleForm").ajaxSubmit({
            type: 'post',
            async: false,
            url: contextPath+"/tHyChryController/savetHyChryBatch" ,
            data:{resourceSettingData:JSON.stringify(resourcesData)},
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