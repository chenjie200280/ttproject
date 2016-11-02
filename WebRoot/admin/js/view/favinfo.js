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
require(options, function(base, etpl) {
	var app = {};
	app.baseurl = "/favinfo/";
	app.listRender = null;
	app.updateRender = null;
	app.moreRender = null;
	app.init = function() {
		var that = this;
		base.getMenu(function(){
			that.initControl();
			that.initEvent();
			favmenu.initFavMenu();
		});
	};
	app.initControl = function() {
		this.listRender = etpl.compile($("#listTemp").text());
		this.updateRender = etpl.compile($("#updateTemp").text());
		this.moreRender = etpl.compile($("#moreTemp").text());
		this.findByPage(1);
	};
	app.initEvent = function() {
		var that = this;
		$('.btn-setting').on("click",function(e){
			e.preventDefault();
			var obj = {};
			obj.isupdate=false;
			obj.menus = favmenu.menu;
			$("#myModal").html(that.updateRender(obj));
			that.showModel();
		});


	};
	app.showModel=function(){
		var that = this;
		$('#myModal').modal('show');
		$("#okBtn").on("click",function(evt){
			var obj = {};
			obj.title = $("#title").val();
			obj.content = $("#remark").val();
			obj.typeid = $("#typeid").val();
			obj.url = $("#url").val();
			obj.imageUrl = $("#imageUrl").val();
			var id = $.trim($("#id").val());
			if(id===""){
				that.save(obj);
			}else{
				obj.id = id;
				that.update(obj);
			}

		})
	};


	app.page = {};
	app.pageSize = 15;

	app.findByPage = function(pageNum){
		var that = this;
		var obj = {};
		that.page = {};
		that.page.pageSize = that.pageSize;
		that.page.pageNum = pageNum;
		obj.page = that.page;
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
				$(".updateBtn").unbind("click").bind("click",function(evt){
					//加载数据
					var obj = list[$(this).attr("index")];
					obj.isupdate=true;
					obj.menus = favmenu.menu;
					$("#myModal").html(that.updateRender(obj));
					that.showModel();
				});

				$(".deleteBtn").unbind("click").bind("click",function(evt){
					var cf=window.confirm("是否真的删除!");
					if (cf==true)
					{
						that.del($(this).attr("id"));
					}
				});
				$(".moreBtn").unbind("click").bind("click",function(e){
					e.preventDefault();
					var obj = list[$(this).attr("index")];
					$("#myModal").html(that.moreRender(obj));
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
	}

	app.save = function(obj){
		var that = this;

		base.sendPost(this.baseurl+"add",JSON.stringify(obj),function(json){
		    if(json.result){
				that.findByPage(that.page.pageNum);
		    	$('#myModal').modal('hide');
		    	
		    }else{
		    	alert("添加失败");
		    }
		},function(xhr){
			alert("添加失败");
		})
	};
	app.update=function(obj){
		var that = this;
		base.sendPost(this.baseurl+"update",JSON.stringify(obj),function(json){
			if(json.result){
				that.findByPage(that.page.pageNum);
				$('#myModal').modal('hide');

			}else{
				alert("添加失败");
			}
		},function(xhr){
			alert("添加失败");
		})
	}
	app.del = function(id){
		var that = this;
		var obj = {};
		obj.id = id;
		base.sendPost(this.baseurl+"delete",JSON.stringify(obj),function(json){
		    if(json.result){
				that.findByPage(that.page.pageNum);
		    }else{
		    	alert("删除失败");
		    }
		},function(xhr){
			alert("删除失败");
		})
	}
	var favmenu = {};
	favmenu.menu=null,
	favmenu.baseurl = "/favmenu/",
	favmenu.initFavMenu = function(){
		var that = this;
		base.sendGet(this.baseurl+"findByAll",function(json){
			if(json.result){
				if(json.list.length>0){
					that.menu = json.list;
				}
			}else{

			}
		},function(xhr){

		});
	}
	app.init();

});
