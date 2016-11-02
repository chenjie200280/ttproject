/**
 * Created by chenjie on 2015/5/20.
 * 基类
 */

var options = ["etpl"];
define(options,function(etpl){
	var base={};
    base.siteurl = "http://127.0.0.1:8010";
	base.baseurl = base.siteurl + "/api";
    //上传文件
    base.fileupload = function(fileId,success,error){
        var uploadUrl = this.baseurl + "/fileinfo/upload";
        $.ajaxFileUpload({
            url : uploadUrl,
            secureuri : false,//一般设置为false
            fileElementId : fileId,//文件上传空间的id属性  <input type="file" id="file" name="file" />
            dataType : 'json',//返回值类型 一般设置为json
            success : function(data,status) //服务器成功响应处理函数
            {
                success(data);//从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
            },
            error:function(data, status, e)//服务器响应失败处理函数
            {
                error(e);
            }
        });
    }

	base.sendPost=function(url,param,success,error){
         param = encodeURIComponent(param);
		 $.ajax({
             async: true,
             url: this.baseurl+url,
             type: 'GET',
             dataType: 'json',
             data: {param:param},
             success: function (json) {
                 success(json);
             },
             error: function(xhr){
                 error(xhr);
             }
         });
	};
	base.sendGet=function(url,success,error){
		 $.ajax({
             async: true,
             url: this.baseurl+url,
             type: 'GET',
             dataType: 'json',
             success: function (json) {
                 success(json);
             },
             error: function(xhr){
                 error(xhr);
             }
         });
	};
    base.getParamValue=function(name){
        var reg = new RegExp("(^|&)"+name+"=([^&]*)(&|$)","i");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null){
            return unescape(r[2]);
        }else{
            return null;
        }
    };
    base.getCookie=function(name)
    {
        var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
        if(arr=document.cookie.match(reg))
            return (arr[2]);
        else
            return null;
    };

    base.getMenu=function(success){
        var that = this;
        var token = base.getCookie("token");
        if(token!=null&&token.length>0){
            var obj = {};
            obj.token = token;
            that.sendPost("/userinfo/" + "findByMap",JSON.stringify(obj), function(json) {
                if(!json.result){
                    window.location.href="/admin/login.html";
                }else{
                    //加载菜单
                    var  listRender = etpl.compile($("#menu_tmp").text());
                    that.sendPost("/usermenu/" + "findByMap",JSON.stringify(obj), function(json) {
                        $(".main-menu").html(listRender(json));
                        /* ---------- Submenu  ---------- */
                        $('.dropmenu').click(function(e){
                            e.preventDefault();
                            $(this).parent().find('ul').slideToggle();
                        });
                        /* ---------- Add class .active to current link  ---------- */
                        $('ul.main-menu li a').each(function(){
                            if($($(this))[0].href==String(window.location)) {

                                $(this).parent().addClass('active');
                            }
                        });

                        $('ul.main-menu li ul li a').each(function(){
                            if($($(this))[0].href==String(window.location)) {
                                $(this).parent().addClass('active');
                                $(this).parent().parent().show();
                            }
                        });
                        success();
                    },function(xhr){
                        window.location.href="/admin/login.html";
                    });
                }
            }, function(xhr) {
                window.location.href="/admin/login.html";
            })
        }else{
            window.location.href="/admin/login.html";
        }
    };

    base.totalPage = 0;

    base.getPageHtml = function(totalCount,pageNum){
        //每页显示多少页数
        var pageCnt = 5;
        var pageSize = 15;
        if(totalCount==0){
            return "";
        }
        this.totalPage = parseInt(totalCount/pageSize) + ((totalCount%pageSize)!=0?1:0);
        var html = "";

        if(this.totalPage===1){
            html+='<li class="disabled"><a class="pageA" href="javasrcipt:void(0);">上一页</a></li><li class="disabled"><a class="pageA" href="javasrcipt:void(0);">1</a></li><li class="disabled"><a class="pageA" href="javasrcipt:void(0);">下一页</a></li>';
            return html;
        }
        if(pageNum===1){
            html+='<li class="disabled"><a class="pageA" href="javasrcipt:void(0);">上一页</a></li>';
        }else{
            html+='<li><a class="pageA" href="javasrcipt:void(0);">上一页</a></li>';
        }
        if(this.totalPage<=pageCnt){
            for(var i =1;i<=this.totalPage;i++){
                if(pageNum===i){
                    html+='<li class="active"><a class="pageA" href="javascript:void(0)">'+i+'</a></li>';
                }else{
                    html+='<li><a class="pageA" href="javascript:void(0)">'+i+'</a></li>';
                }
            }
            if(pageNum===this.totalPage){
                html+='<li class="disabled"><a class="pageA" href="javascript:void(0)">下一页</a></li>';
            }else{
                html+='<li><a class="pageA" href="javascript:void(0)">下一页</a></li>';
            }

            return html;
        }

        for(var i =1;i<=pageCnt;i++){
            var flag = i+(pageNum-1);
            if(flag>this.totalPage){
                break;
            }
            if(pageNum===1 && i==1){
                html+='<li class="active"><a class="pageA" href="javascript:void(0)">1</a></li>';
            }
            else if(pageNum === flag){
                html+='<li class="active"><a class="pageA" href="javascript:void(0)">'+flag+'</a></li>';
            }else{
                html+='<li><a class="pageA" href="javascript:void(0)">'+flag+'</a></li>';
            }
        }
        html+='<li><a class="pageA" href="javascript:void(0)">下一页</a></li>';
        return html;
    }

    base.init=function(){
        etpl.addFilter("indexadd", function (source) {
            return parseInt(source) + 1;
        })
        etpl.addFilter("substr", function (source) {
            var result = source;
            if(source.length>15){
                result = source.substr(0,15)+"...";
            }
            return result;
        })
        etpl.addFilter("getAppAdvertSdkValue", function (source) {
          return base.appAdvertSdks[source];
        })
        etpl.addFilter("getAppAdvertLocationValue", function (source) {
            return base.appAdvertLocations[source];
        })
        etpl.addFilter("getAppOssValue", function (source) {
            return base.appOss[source];
        })
    }
    base.appAdvertSdks = {"baidu":"百度","qq":"腾讯"};
    base.appOss = {"iphone":"iphone","ipad":"ipad","aphone":"aphone","apad":"apad"};
    base.appShops = {"360":"360","baidu":"百度","qq":"应用宝"};
    base.appAdvertLocations = {"bannerAd":"横幅","interAd":"插屏"};
    base.init();
    return base;
});
