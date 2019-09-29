/*****************************************************************
                  jQuery Ajax封装通用类  (duanmuyingnan)       
*****************************************************************/
$(function(){
    /**
     * ajax封装
     * url 发送请求的地址
     * data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(), "state": 1}
     * async 默认值: true。默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。
     *       注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
     * type 请求方式("POST" 或 "GET")， 默认为 "GET"
     * dataType 预期服务器返回的数据类型，常用的如：xml、html、json、text
     * successfn 成功回调函数
     * errorfn 失败回调函数
     */
    jQuery.ax=function(obj) {
//    	$(".loadingbg").show();//设置加载层开启
        async = (obj.async==null || obj.async==="" || typeof(obj.async)=="undefined")? "true" : obj.async;
        type = (obj.type==null || obj.type=="" || typeof(obj.type)=="undefined")? "POST" : obj.type;
        dataType = (obj.dataType==null || obj.dataType=="" || typeof(obj.dataType)=="undefined")? "json" : obj.dataType;
        data = (obj.data==null || obj.data=="" || typeof(obj.data)=="undefined")? {"date": new Date().getTime()} : obj.data;
        if($.isFunction(data)){
        	data = data.call();
        }
        url = obj.url;
        $.ajax({
            type: type,
            async: async,
            data: data,
            url: url,
            dataType: dataType,
            cache : false,
            beforeSend:function (XMLHttpRequest) {
            	XMLHttpRequest.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
			},
            success: function(data, textStatus, jqXHR){
            	if(data.errorCode != "000000"){
    				$("#errtextmsg").text(data.errorMsg);
    				$('#myModal-errmsg').modal('show');
    				return;
    			}
            	obj.successfn(data);
//            	$(".loadingbg").hide();//设置加载层隐藏
            },
            error: function(XMLHttpRequest, textStatus, errorThrown){
            	$("#errtextmsg").text("页面超时，请重新登录!");
    			$('#myModal-errmsg').modal('show');
            	obj.errorfn(XMLHttpRequest, textStatus, errorThrown);
//            	$(".loadingbg").hide();//设置加载层隐藏
            }
        });
        
    };
});