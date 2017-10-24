var infoPanel = new Vue({
	el:"#infoPanel",
	data:{
		userInfo:{}
	},
	methods:{
		edit:function(){
			$("#editMode").css("display","block");
			$("#readMode").css("display","none");
			$(".editMode").css("display","block");
			$(".readMode").css("display","none");
			if(infoPanel.userInfo.sex === "男"){
				$("#male").attr("checked","checked");
			}else{
				$("#female").attr("checked","checked");
			}
			$("#introduce").text(infoPanel.userInfo.intro);
		},
		save:function(){
			updateUserInfo();
			$("#editMode").css("display","none");
			$("#readMode").css("display","block");
			$(".editMode").css("display","none");
			$(".readMode").css("display","block");
		}
	}
})

var userNav = new Vue({
	el:"#usernav",
	data:{
		userInfo:{}
	}
})

$(document).ready(function(){
	getUser();
})

function getUser(){
	$.ajax({
		type:"post",
		async:false,
		url:"/nd2/user/nav.do",
		dataType:"json",
		success:function(data){
			infoPanel.userInfo = data;
			navbar.userInfo = data;
		},
		error:function(){
			alert("登录已过期,请重新登录！");
			window.location.href = "login.html"
		}
	})
}

function editPwd(){
	$.ajax({
		type:"post",
		url:"/nd2/user/chgPwd.do",
		dataType:"json",
		data:{"pwd":hex_md5($("#oldPwd").val()),"newPwd":hex_md5($("#newPwd").val())}
		,
		success:function(data){
			if(data.state === "error"){
				alert("旧密码错误");
			}else if(data.state === "Excess license"){
				alert("登录已过期，请重新登录！");
				window.location.href = "login.html";
			}
		},
		error:function(){
			alert("登录已过期,请重新登录！");
			window.location.href = "login.html"
		}
	})
}

function updateUserInfo(){
	$.ajax({
		type:"post",
		url:"/nd2/user/updateUserInfo.do",
		dataType:"json",
		data:{"username":$("#nickname").val(),
			"sex":$('input:radio[name="sex"]:checked').val(),
			"birthday":$("#birthday").val(),
			"interest":$("#interest").val(),
			"intro":$("#introduce").text()
		}
		,
		success:function(data){
			if(data.state === "error"){
				alert("更新个人信息失败，请刷新页面后重试");
			}else{
				getUser();
			}
		},
		error:function(){
			alert("登录已过期,请重新登录！");
			window.location.href = "login.html"
		}
	})
}

function checkPwd(){
	if($("#newPwd").val().length<=6){
		alert("新密码长度应在6-18位");
	}
	if($("#newPwd").val()!=$("#confirmPwd").val()){
		alert("两次输入的密码不一致");
	}
	editPwd();
}

function logOut(){
	$.ajax({
		type:"post",
		url:"/nd2/user/logOut.do",
		success:function(){
			alert("退出账号成功");
			window.location.href = "login.html";
		}
	})
}

