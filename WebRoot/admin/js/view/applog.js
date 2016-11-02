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
    app.baseurl = "/applog/";
    app.listRender = null;
    app.init = function () {
        var that = this;
        base.getMenu(function(){
            that.initControl();
            that.initEvent();
        });
    };
    app.initControl = function () {
        this.listRender = etpl.compile($("#listTemp").text());
        this.findByStatistics(1);
    };

    app.initEvent = function () {

    };

    app.pageSize=15;

    app.findByStatistics = function(pageNum){
        var that = this;
        var obj = {};
        var page = {};
        page.pageNum = pageNum;
        page.pageSize = that.pageSize;
        obj.page = page;
        base.sendPost(this.baseurl + "findByStatistics", JSON.stringify(obj),function(json){
            if(json.result){
                var html = "";
                var list = json.list;
                var count = list.length;
                $("#totalcount").html("共"+count+"条数据");
                if(count>0){
                    html =that.listRender(json);
                }
                $("#listBody").html(html);

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
                    that.findByStatistics(pageNum);
                })
            }else{
                $("#listBody").html("没有查询到结果");
            }
        }, function(xhr) {
            alert("没有查询到结果")
        });
    }
    app.init();
});
