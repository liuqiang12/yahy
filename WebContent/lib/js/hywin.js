/**
 * 通过lb类型获取中文描述
 * @param lb
 * @returns {string}
 */
function getLbName(lb) {
    return lb=='Leaved'?'请假人员':
              lb=='Driver'?'驾驶人员':
                lb=='Personnel'?'工作人员':
                    lb=='Participant'?'参会人员':''
}
/**
 * 通过xb类型获取中文描述
 * @param xb
 * @returns {string}
 */
function getXb(xb) {
    return xb=='male'? '男':'女';
}
function getZsBz(zsBz) {
    return zsBz=='notwant'? '不住宿':'住宿';
}
/**
 * 上报参会人员
 * @param id
 */
function transmitParticipantsOpen(id) {
    // 弹出窗口
    var laySum = top.layer.open({
        type: 2,
        title: '上报参会人员',
        shadeClose: false,
        shade: 0.8,
        btn: ['确定','关闭'],
        maxmin : true,
        area: ['550px', '500px'],
        content: contextPath+"/tHyChryController/transmitParticipants?id="+id,
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
    });/*


     //通过id  修改数据  ajax
     $.ajax({
     //提交数据的类型 POST GET
     type:"POST",
     //提交的网址
     url:contextPath+"/tHyChryController/updateById?id="+id,
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
     });*/
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