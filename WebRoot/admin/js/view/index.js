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

    app.init = function () {
        var that = this;
        base.getMenu(function(){
            that.initControl();
            that.initEvent();
        });
    };
    app.initControl = function () {

    };
    app.initEvent = function(){

    };

    app.init();
})