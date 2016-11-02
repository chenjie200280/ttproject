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
    app.baseurl = "/appadvertsdk/";
    app.listRender = null;
    app.updateRender = null
    app.aid = 0;
    //{'aid':1}
    app.init = function () {
        var that = this;
        var sdkhtml = ""
        //加载SDK
        for(var key1 in base.appAdvertSdks){
            sdkhtml+="<option value='"+key1+"'>"+base.appAdvertSdks[key1]+"</option>";
        }
        var locationhtml = "";
        //加载位置
        for(var key2 in base.appAdvertLocations){
            locationhtml+="<option value='"+key2+"'>"+base.appAdvertLocations[key2]+"</option>";
        }
        $("#sdk").html(sdkhtml);
        $("#location").html(locationhtml);
        base.getMenu(function(){
            that.initControl();
            that.initEvent();
            that.aid = $.trim(that.getParamValue("aid"));
            $("#title").html(decodeURI(decodeURI($.trim(that.getParamValue("aname")))));
            that.findByMap();
        });
    };
    app.getParamValue=function(name){
        var reg = new RegExp("(^|&)"+name+"=([^&]*)(&|$)","i");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null){
            return unescape(r[2]);
        }else{
            return null;
        }
    },
    app.initControl = function () {
        this.initEtpl();
        this.updateRender = etpl.compile($("#updateTemp").text());
        this.listRender = etpl.compile($("#listTemp").text());
    };

    app.initEvent = function () {
        var that = this;
        $('.btn-setting').on("click", function (e) {
            e.preventDefault();
            $("#modaltitle").html("添加数据");
            $("#id").val("");
            //$("#locationid").val();
            //$("#sdkid").val();
            $("#appid").val("");
            $("#adid").val("");
            //$("#status").attr('checked',false);
            that.showModel();
        });
    };
    app.initEtpl = function () {
        etpl.addFilter("indexadd", function (source) {
            return parseInt(source) + 1;
        })
    };
    app.findByMap = function () {
        var that = this;
        var obj = {};
        obj.app_info_id = that.aid;
        base.sendPost(that.baseurl + "findByMap", JSON.stringify(obj), function (json) {
            if (json.result) {
                var html = "";
                var list = json.list;
                var count = list.length;
                if (count > 0) {
                    html = that.listRender(json);

                }
                $("#listBody").html(html);

                $(".updateBtn").unbind("click").bind("click", function (evt) {
                    //加载数据
                    var obj = list[$(this).attr("index")];
                    obj.isupdate = true;
                    $("#modaltitle").html("修改数据");
                    $("#id").val(obj.id);
                    $("#locationid").val(obj.locationid);
                    $("#sdkid").val(obj.sdkid);
                    $("#appid").val(obj.appid);
                    $("#adid").val(obj.adid);
                    $("#status").val(obj.status);
                    that.showModel();
                });

                $(".deleteBtn").unbind("click").bind("click", function (evt) {
                    var cf = window.confirm("是否真的删除!");
                    if (cf == true) {
                        var index = $(this).attr("index");
                        that.del(list[index].id);
                    }
                })

            } else {
                $("#listBody").html("没有查询到结果");
            }
        }, function (xhr) {
            $("#listBody").html("没有查询到结果");
        })
    };
    app.showModel = function () {
        var that = this;
        $('#myModal').modal('show');
        $("#okBtn").unbind("click").bind("click", function (evt) {
            var obj = {};
            obj.sdk = $("#sdk").val();
            obj.appid = $("#appid").val();
            obj.adid = $("#adid").val();
            obj.app_info_id = that.aid;
            obj.location =$("#location").val();
            var id = $.trim($("#id").val());
            if (id === "") {
                that.save(obj);
            } else {
                obj.id = id;
                that.update(obj);
            }

        })
    };
    app.save = function (obj) {
        var that = this;
        base.sendPost(this.baseurl + "add", JSON.stringify(obj), function (json) {
            if (json.result) {
                that.findByMap();
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
                that.findByMap();
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
                that.findByMap();
            } else {
                alert("删除失败");
            }
        }, function (xhr) {
            alert("删除失败");
        })
    };
    app.init();
});
