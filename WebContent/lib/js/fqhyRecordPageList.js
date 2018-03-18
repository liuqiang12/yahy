function bsmdQueryEditWinPlugin(id) {
    /* 弹出框 */
    var laySum = top.layer.open({
        type: 2,
        title: "编辑",
        shadeClose: false,
        shade: 0.8,
        btn: ['保存','关闭'],
        maxmin : true,
        area: ['940px', '550px'],
        content: contextPath+"/tHyHyController/bsmdQueryEditWinPlugin?hyInstId="+id,
        /*弹出框*/
        cancel: function(index, layero){
            top.layer.close(index);
        },yes: function(rootIndex, layero){
            var childIframeid = layero.find('iframe')[0]['name'];
            var chidlwin = top.document.getElementById(childIframeid).contentWindow;
            /*修改相关数据*/
            var resJSON = chidlwin.jdFromSubmitFun();
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
                getDataList(0,null);
                top.layer.close(rootIndex)
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
function delRecord(id) {
    //删除后制动刷新

    /** 确定要删除吗 **/
    top.layer.confirm('你确定要删除吗？', {
        btn: ['确定','取消'] //按钮
    }, function(index, layero){
        /*ajax*/
        $.ajax({
            //提交数据的类型 POST GET
            type:"POST",
            async:false,
            //提交的网址
            url:contextPath+"/tHyHyInstController/delTHyHyInst",
            //提交的数据
            data:{
                id:id
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
                /* 删除成功后刷新grid */
                getDataList(page_index);
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
function banjieFun(id){
    top.layer.confirm('你确定要办结吗？', {
        btn: ['确定','取消'] //按钮
    }, function(index, layero){
        $.ajax({
            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:contextPath+"/tHyHyController/isCHryExist?hyInstId="+id,
            async:false,
            //返回数据的格式
            datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
            //在请求之前调用的函数
            beforeSend:function(){
            },
            //成功返回之后调用的函数
            success:function(data){
                if(!data.isExist){
                    top.layer.msg("没有人员可以办结，请核查数据！", {
                        icon: 1,
                        time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                    });
                }else{
                    /**
                     * ajax 修改状态已经上报
                     */
                    $.ajax({
                        //提交数据的类型 POST GET
                        type:"POST",
                        //提交的网址
                        url:contextPath+"/tHyHyInstController/updateBanjieZt?hyInstId="+id,
                        async:false,
                        //返回数据的格式
                        datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
                        //在请求之前调用的函数
                        beforeSend:function(){
                        },
                        //成功返回之后调用的函数
                        success:function(data){
                            if(data.success){
                                top.layer.msg("提交办结！", {
                                    icon: 1,
                                    time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                                });
                                top.layer.close(index);
                                /* 刷新 */
                                getDataList(page_index);
                            }else{
                                top.layer.msg("办结失败！", {
                                    icon: 1,
                                    time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                                });
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