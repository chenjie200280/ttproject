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
	app.baseurl = "/taobaotask/";
	app.listRender = null;
	app.updateRender = null;
	app.moreRender = null;
	app.currentPageNum = 1;
	app.init = function() {
		var that = this;
		base.getMenu(function(){
			that.initControl();
			that.initEvent();
		});
	};

	app.initControl = function() {
		this.listRender = etpl.compile($("#listTemp").text());
		this.updateRender = etpl.compile($("#updateTemp").text());
		this.moreRender = etpl.compile($("#moreTemp").text());
		this.findByPage(1);
		var startDate = new Date();
		//上月总收入
		//this.LastTotalSalary(startDate,endDate);

		//$( "#datepicker" ).datepicker();
		$("#datepicker").datepicker(
			{
				defaultDate : new Date() //还可以是时间字符串，当前日期对应的时间数值
			}
		);
	};
	app.initEvent = function() {
		var that = this;
		$('.btn-setting').on("click",function(e){
			e.preventDefault();
			var obj = {};
			obj.isupdate=false;
			$("#myModal").html(that.updateRender(obj));
			that.showModel();
		});
	};


	app.totalSalary = function(startDate,endDate){
		var that = this;
		var obj = {};
		obj.startDate = startDate;
		obj.endDate = endDate;
		base.sendPost(this.baseurl + "findByMap", JSON.stringify(obj),function(json){
			if(json.result){
				var list = json.list;
				var salarycount = 0.00;
				for(var i=0;i<list.length;i++) {
					salarycount += parseFloat(list[i].salary);
				}
			}else{
				alert("查询失败");
			}
		},function(xhr){
			alert("查询失败");
		})
	}


	app.page = {};
    app.pageSize = 15;
	app.findByPage = function(pageNum) {
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
				//$("#totalcount").html("共"+list.length+"条数据");
				for(var i=0;i<list.length;i++){
					var obj = list[i];
					obj.totalMoney = (parseFloat(obj.pay)+parseFloat(obj.salary)).toFixed(2);
					obj.addtime = obj.addtime.replace("T"," ");
					obj.index = i;
					html+=that.listRender(list[i]);
				}
				$("#listBody").html(html);
				$(".updateBtn").unbind("click").bind("click",function(evt){
					//加载数据
					var obj = list[$(this).attr("index")];
					obj.isupdate=true;
					$("#myModal").html(that.updateRender(obj));
					that.showModel();
				});

				$(".deleteBtn").unbind("click").bind("click",function(evt){
					var cf=window.confirm("是否真的删除!");
					if (cf==true)
					{
						that.del($(this).attr("id"));
					}

				})

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
		},function(xhr){
			alert("查询失败");
		})
	};

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
	app.showModel=function(){
		var that = this;
		$('#myModal').modal('show');
		$("#okBtn").on("click",function(evt){
			var obj = {};
			obj.qq = $("#qq").val();
			obj.qt = $("#qt").val();
			obj.username = $("#username").val();
			obj.shopid = $("#shopid").val();
			obj.pay = $("#pay").val();
			obj.salary = $("#salary").val();
			obj.account = $("#account").val();

			if($("#ispay").attr("checked")==="checked"){
				obj.ispay = 1;
			}else{
				obj.ispay = 0;
			}
			obj.remark = $("#remark").val();
			var id = $.trim($("#id").val());
			if(id===""){
				that.save(obj);
			}else{
				obj.id = id;
				debugger;
				that.update(obj);
			}

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
	app.init();
});
