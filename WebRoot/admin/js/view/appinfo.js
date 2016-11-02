/**
 * Created by chenjie on 2015/9/20.
 */
requirejs.config({
    paths : {
        "base": "../base",
        "etpl": "/admin/js/common/etpl"
    },
    shim: {
        "base": ["etpl"]
    }
});
var options = ["base","etpl"];
require(options, function (base, etpl) {
    var app = {};
    app.baseurl = "/appinfo/";
    app.listRender = null;
    app.updateRender = null;
    app.seeInfoRender = null;
    app.init = function () {
        var that = this;
        base.getMenu(function(){
            that.initControl();
            that.initEvent();
        });
    };
    app.initControl = function () {
        this.initEtpl();
        this.listRender = etpl.compile($("#listTemp").text());
        this.updateRender = etpl.compile($("#updateTemp").text());
        this.seeInfoRender = etpl.compile($("#seeInfoTemp").text());
        this.findByPage(1);
    };

    app.initOptions = function(obj){
        var osHtml = ""
        //加载SDK
        for(var key1 in base.appOss){
            if(obj!=null && obj.os === key1){
                osHtml+="<option value='"+key1+"' selected=\"selected\">"+base.appOss[key1]+"</option>";
            }else{
                osHtml+="<option value='"+key1+"'>"+base.appOss[key1]+"</option>";
            }
        }
        var shopHtml = "";
        //加载位置
        for(var key2 in base.appShops){
            if(obj!=null && obj.shop === key2) {
                shopHtml += "<option value='" + key2 + "' selected=\"selected\">" + base.appShops[key2] + "</option>";
            }else{
                shopHtml += "<option value='" + key2 + "'>" + base.appShops[key2] + "</option>";
            }
        }
        $("#os").html(osHtml);
        $("#shop").html(shopHtml);
    };

    app.initEtpl = function () {
        etpl.addFilter("geturl", function (source) {
            return  base.siteurl+"/apps/"+source;
        })
    };

    app.initEvent = function () {
        var that = this;
        $('.btn-setting').on("click", function (e) {
            e.preventDefault();
            var obj = {};
            obj.isupdate = false;
            $("#myModal").html(that.updateRender(obj));
            that.initOptions(null);
            that.showModel();
        });

    };

    app.showModel = function () {
        var that = this;
        $('#myModal').modal('show');
        $("#okBtn").unbind("click").bind("click", function (evt) {
            var obj = {};
            obj.appname = $("#appname").val();
            obj.packagename = $("#packagename").val();
            obj.version = $("#version").val();
            obj.os = $("#os").val();
            obj.shop=$("#shop").val();
            obj.url = $("#url").val();
            obj.remark = $("#remark").val();
            var id = $.trim($("#id").val());
            if (id === "") {
                that.save(obj);
            } else {
                obj.id = id;
                that.update(obj);
            }
        });


        $("#loading").ajaxStart(function() {
            $(this).show();
        })//开始上传文件时显示一个图片
            .ajaxComplete(function() {
                $(this).hide();
                $("#file").remove();
                var input = " <input type=\"file\" id=\"file\" name=\"file\"  accept=\"apk,ipk\"  style=\"display: none\"/>";
                $("#url").before(input);
                that.initFileEvent();
        });//文件上传完成将图片隐藏起来
        that.initFileEvent();
    }

    app.initFileEvent = function(){
        $("#file").unbind("change").bind("change",function(){
            base.fileupload("file",function(data){
                $("#url").val(data.msg);
            },function(data){
                $("#url").val("");
            });
        });
    }
    app.pageSize=15;

    app.pageNum = 1;

    app.findByPage = function(pageNum){
        var that = this;
        var obj = {};
        var page = {};
        that.pageNum = pageNum;
        page.pageNum = that.pageNum;
        page.pageSize = that.pageSize;
        obj.page = page;
        base.sendPost(this.baseurl + "findByPage", JSON.stringify(obj),function(json){
            if(json.result){
                var html = "";
                var list = json.list;
                var count = list.length;
                $("#totalcount").html("共"+count+"条数据");
                if(count>0){
                    html =that.listRender(json);
                }
                $("#listBody").html(html);

                $(".updateBtn").unbind("click").bind("click", function (evt) {
                    //加载数据
                    var obj = list[$(this).attr("index")];
                    obj.isupdate = true;
                    $("#myModal").html(that.updateRender(obj));
                    that.initOptions(obj);
                    that.showModel();
                });

                $(".deleteBtn").unbind("click").bind("click", function (evt) {
                    var cf = window.confirm("是否真的删除!");
                    if (cf == true) {
                        that.del($(this).attr("id"));
                    }
                })

                $(".seeInfoBtn").unbind("click").bind("click", function (evt) {
                    //加载数据
                    var obj = list[$(this).attr("index")];
                    $("#myModal").html(that.seeInfoRender(obj));
                    $("#advertBtn").unbind("click").bind("click", function (evt) {
                        var index = $(this).attr("index");
                        var url="appadvert.html?aid="+$(this).attr("aid")+"&aname="+encodeURI(encodeURI($(this).attr("aname"))); //转向网页的地址;
                        var name="广告管理"; //网页名称，可为空;
                        var iWidth=860; //弹出窗口的宽度;
                        var iHeight=500; //弹出窗口的高度;
                        var iTop = (window.screen.height-30-iHeight)/2; //获得窗口的垂直位置;
                        var iLeft = (window.screen.width-10-iWidth)/2; //获得窗口的水平位置;
                        window.open(url,name,'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=no,resizeable=no,location=no,status=no');
                    })
                    $("#advertSdkBtn").unbind("click").bind("click", function (evt) {
                        var index = $(this).attr("index");
                        //var text = encodeURI(encodeURI('中国'));
                        //alert(decodeURI(decodeURI(text)));  //js 解码
                        var url="appadvertsdk.html?aid="+$(this).attr("aid")+"&aname="+encodeURI(encodeURI($(this).attr("aname"))); //转向网页的地址;
                        var name="广告SDK管理"; //网页名称，可为空;
                        var iWidth=860; //弹出窗口的宽度;
                        var iHeight=500; //弹出窗口的高度;
                        var iTop = (window.screen.height-30-iHeight)/2; //获得窗口的垂直位置;
                        var iLeft = (window.screen.width-10-iWidth)/2; //获得窗口的水平位置;
                        window.open(url,name,'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=no,resizeable=no,location=no,status=no');
                    })
                    that.initOptions(obj);
                    that.showModel();

                });


                //添加分页
                var page = json.page;
                $("#pagediv").html(base.getPageHtml(page.totalCount,page.pageNum));
                $(".pageA").unbind("click").bind("click",function(evt){
                    var val = $(this).text();
                    var pageNum = 1;
                    if(val==="上一页"){
                        if(page.pageNum==1){
                            return;
                        }else{
                            pageNum = page.pageNum - 1;
                        }
                    }else if(val==="下一页"){
                        var totalPage = parseInt(page.totalCount/that.pageSize) + ((page.totalCount%that.pageSize)!=0?1:0);
                        if(page.pageNum===totalPage){
                            return;
                        }else{
                            pageNum = page.pageNum + 1;
                        }
                    }else{
                        pageNum = val;
                    }
                    that.findByPage(pageNum);
                })
            }else{
                $("#listBody").html("没有查询到结果");
            }
        }, function(xhr) {
            alert("没有查询到结果")
        });
    };
    app.save = function (obj) {
        var that = this;

        base.sendPost(this.baseurl + "add", JSON.stringify(obj), function (json) {
            if (json.result) {
                that.findByPage(that.pageNum);
                $('#myModal').modal('hide');

            } else {
                alert("添加失败");
            }
        }, function (xhr) {
            alert("添加失败");
        })
    };
    app.update = function (obj) {
        var that = this;
        base.sendPost(this.baseurl + "update", JSON.stringify(obj), function (json) {
            if (json.result) {
                that.findByPage(that.pageNum);
                $('#myModal').modal('hide');

            } else {
                alert("添加失败");
            }
        }, function (xhr) {
            alert("添加失败");
        })
    }
    app.del = function (id) {
        var that = this;
        var obj = {};
        obj.id = id;
        base.sendPost(this.baseurl + "delete", JSON.stringify(obj), function (json) {
            if (json.result) {
                that.findByPage(that.pageNum);
            } else {
                alert("删除失败");
            }
        }, function (xhr) {
            alert("删除失败");
        })
    }
    app.init();

    var advert = {};
    advert.baseurl = "/appadvert/";
    advert.listRender = null;
    //{'aid':1}
    advert.init = function () {
        this.initControl();
        this.initEvent();
    };
    advert.initControl = function () {
        this.listRender = etpl.compile($("#advertListTemp").text());
    };
    advert.initEvent = function () {

    };
    advert.findByMap = function (aid) {
        var that = this;
        var obj = {};
        obj.aid = aid;
        base.sendPost(that.baseurl + "findByMap", JSON.stringify(obj), function (json) {
            if (json.result) {
                var html = "";
                var list = json.list;
                var count = list.length;
                if (count > 0) {
                    html = that.listRender(json);
                }
                $("#advertListBody").html(html);
            } else {
                $("#advertListBody").html("没有查询到结果");
            }
        }, function (xhr) {
            alert("没有查询到结果")
        })
    }
    advert.init();

});
