/**
 * Created by chenjie on 2015/9/20.
 */
requirejs.config({
	paths : {
		"base" : "../base",
		"jquery" : "../common/jquery-1.9.1.min",
		"md5":"../common/md5",
		"etpl": "/admin/js/common/etpl"
	},
	shim: {
		"base": ["jquery","md5","etpl"]
	}
});
var options = [ "jquery", "base","md5"];
require(options, function($, base,md5) {
	var app = {};
	app.baseurl = "/userinfo/"
		app.init = function() {
		this.initControl();
		this.initEvent();
	};
	app.initControl = function() {

	};
	app.initEvent = function() {
		var that = this;
		$("#loginBtn").on("click",function(evt){
			that.login();
		})
	};
	app.login = function() {
		$("#errResult").css("display","none");
		var username = $("#username").val();
		var password =hex_md5($("#password").val());
		var param = '{"username":"'+username+'","password":"'+password+'"}';
		base.sendPost(this.baseurl + "login",param.toString(), function(json) {
			if(json.result){
				document.cookie="token="+json.token;
				window.location.href="index.html"
			}else{
				$("#errResult").css("display","block");
			}
		}, function(xhr) {
			debugger;
		})
	};
	app.init();
});
