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
                /*if(!data.isExist){
                	//参会单位:mycxdwList
                    top.layer.msg("单位未报名给出提示，请核查数据！", {
                        icon: 1,
                        time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                    });
                }else{*/
                	if(data.hymcj){
                		top.layer.confirm('还有单位没参加,确定要办结吗？', {
                	        btn: ['确定','取消'] //按钮
                	    }, function(index, layero){
                	    	/**
                             * ajax 修改状态已经上报
                             */
                            $.ajax({
                                //提交数据的类型 POST GET
                                type:"POST",
                                //提交的网址:会议实例
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
                	    });
                	}else{
                		/**
                         * ajax 修改状态已经上报
                         */
                        $.ajax({
                            //提交数据的类型 POST GET
                            type:"POST",
                            //提交的网址:会议实例
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
                /*}*/
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
function bsmdWinPlugin(id,sbzt) {
  /* 弹出框 */
   /* 未上报且未办结 */
  if(sbzt == 1){
      var laySum = top.layer.open({
          type: 2,
          title: "报送名单",
          shadeClose: false,
          shade: 0.8,
          btn: ['关闭'],
          maxmin : true,
          area: ['940px', '550px'],
          content: contextPath+"/tHyHyController/bsmdQueryWinPlugin?hyInstId="+id,
          /*弹出框*/
          cancel: function(index, layero){
              top.layer.close(index);
          },no: function(index, layero){
              top.layer.close(index)
          }
      });
  }else{
      var laySum = top.layer.open({
          type: 2,
          title: "报送名单",
          shadeClose: false,
          shade: 0.8,
          btn: ['确定','关闭'],
          maxmin : true,
          area: ['1100px', '550px'],
          content: contextPath+"/tHyHyController/bsmdWinPlugin?hyInstId="+id,
          /*弹出框*/
          cancel: function(index, layero){
              top.layer.close(index);
          },yes: function(rootIndex, layero){
              var childIframeid = layero.find('iframe')[0]['name'];
              var chidlwin = top.document.getElementById(childIframeid).contentWindow;

              top.layer.confirm('你确定要报送所选用户吗？', {
                  btn: ['确定','取消'] //按钮
              }, function(index, layero){
                /* 然后还回会议id */
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
                    	  if(data.hymcj){
                      		top.layer.confirm('还有单位没参加,确定要办结吗？', {
                      	        btn: ['确定','取消'] //按钮
                      	    }, function(index, layero){
                      	    	/**
                                 * ajax 修改状态已经上报
                                 */
                                $.ajax({
                                    //提交数据的类型 POST GET
                                    type:"POST",
                                    //提交的网址
                                    url:contextPath+"/tHyHyInstController/updateSbZt?hyInstId="+id,
                                    async:false,
                                    //返回数据的格式
                                    datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
                                    //在请求之前调用的函数
                                    beforeSend:function(){
                                    },
                                    //成功返回之后调用的函数
                                    success:function(data){
                                        if(data.success){
                                            top.layer.msg("上报成功！", {
                                                icon: 1,
                                                time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                                            });
                                            top.layer.close(index);
                                            top.layer.close(rootIndex);
                                          /* 刷新 */
                                            getDataList(page_index);
                                        }else{
                                            top.layer.msg("上报失败！", {
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
                      	    	
                      	    });
                    	  }else{
                    		  /**
                               * ajax 修改状态已经上报
                               */
                              $.ajax({
                                  //提交数据的类型 POST GET
                                  type:"POST",
                                  //提交的网址
                                  url:contextPath+"/tHyHyInstController/updateSbZt?hyInstId="+id,
                                  async:false,
                                  //返回数据的格式
                                  datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
                                  //在请求之前调用的函数
                                  beforeSend:function(){
                                  },
                                  //成功返回之后调用的函数
                                  success:function(data){
                                      if(data.success){
                                          top.layer.msg("上报成功！", {
                                              icon: 1,
                                              time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                                          });
                                          top.layer.close(index);
                                          top.layer.close(rootIndex);
                                        /* 刷新 */
                                          getDataList(page_index);
                                      }else{
                                          top.layer.msg("上报失败！", {
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
                    	  }/*
                          if(!data.isExist){
                              top.layer.msg("没有人员可以上报，请核查数据！", {
                                  icon: 1,
                                  time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                              });
                          }else{
                             
                          }*/
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
          },no: function(index, layero){
              top.layer.close(index)
          }
      });
  }
}