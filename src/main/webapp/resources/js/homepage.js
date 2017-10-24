var items = [];
var infos = [];
var nums ;
var questionPage;
var answerPage;
var articlePage;
function initDT(){
	$.ajax({
		type:"post"	,
		async:false,
		url:"/nd2/Concern/initCocernInfomation.do",
		dataType:"json",
		success:function(data){
			items = data;
			if(items.concernQuestions.length == 0 &&
					items.concernThemes.length == 0 &&
					items.concernArticles.length == 0){
				$("#noData").css("display","block");
			}
				
			dtPanel.cqs = items.concernQuestions;
			dtPanel.cts = items.concernThemes;
			dtPanel.cas = items.concernArticles;
		},
		error:function(){
			alert("登录已过期,请重新登录！");
			window.location.href = "login.html"
		}
	})
}
function initUserInfo(){
	$.ajax({
		type:"post",
		async:false,
		url:"/nd2/user/userDetailInfo.do?questionPage="+questionPage
			+"&answerPage="+answerPage
			+"&articlePage="+articlePage,
		dataType:"json",
		success:function(data){
			infos = data;
			articlePanel.items = infos[0];
			questionPanel.items = infos[1];
			answerPanel.items = infos[2];
			if(articlePanel.items.length == 0){
				$("#noArticle").css("display","block");
			}else{
				$("#noArticle").css("display","none");
			}
			if(questionPanel.items.length == 0){
				$("#noQuestion").css("display","block");
			}else{
				$("#noQuestion").css("display","none");
			}
			if(answerPanel.items.length == 0){
				$("#noAnswer").css("display","block");
			}else{
				$("#noAnswer").css("display","none");
			}
		},
		error:function(){
			alert("登录已过期,请重新登录！");
			window.location.href = "login.html"
		}
	})
}
function initConcernNums(){
	$.ajax({
		type:"post",
		async:false,
		url:"/nd2/Concern/concernNum.do",
		dataType:"json",
		success:function(data){
			numPanel.nums = data;
			console.log(numPanel.nums);
		},
		error:function(){
			alert("信息获取失败，请刷新页面后重试");
		}
	})
}
$(document).ready(function(){
	questionPage = 1;
	answerPage = 1;
	articlePage = 1;
	initDT();
	initUserInfo();
	initConcernNums();
	getUser();
})

var numPanel = new Vue({
	el:"#numPanel",
	data:{
		nums:{}
	}
})

var navbar = new Vue({
	el:"#navbar",
	data:{
		userInfo:{}
	}
})

var dtPanel = new Vue({
	el:"#dtPanel",
	data:{
		cqs:[],cts:[],cas:[]
		
	},
	methods:{
		ccTheme:function(concernId,$index){		
			$.ajax({
				type:"post",
				url:"/nd2/Concern/deleteConcernTheme.do",
				data:{"concernId":concernId},
				dataType:"json",
				success:function(data){
					if(data.state==="error"){
						alert("信息获取失败，请刷新页面后重试");
					}
					if(data.state==="success"){
						initDT();
						//dtPanel.cts.splice($index,1);
					}
				},
				error:function(data){
					alert("信息获取失败，请刷新页面后重试");
				}
			})
		},
		ccQuestion:function(concernId,$index){		
			$.ajax({
				type:"post",
				url:"/nd2/Concern/deleteConcernQuestion.do",
				data:{"concernId":concernId},
				dataType:"json",
				success:function(data){
					if(data.state==="error"){
						alert("信息获取失败，请刷新页面后重试");
					}
					if(data.state==="success"){
						initDT();
						//dtPanel.cts.splice($index,1);
					}
				},
				error:function(data){
					alert("信息获取失败，请刷新页面后重试");
				}
			})
		},
		ccArticle:function(concernId,$index){		
			$.ajax({
				type:"post",
				url:"/nd2/Concern/deleteConcernArticle.do",
				data:{"concernId":concernId},
				dataType:"json",
				success:function(data){
					if(data.state==="error"){
						alert("信息获取失败，请刷新页面后重试");
					}
					if(data.state==="success"){
						initDT();
						//dtPanel.cts.splice($index,1);
					}
				},
				error:function(data){
					alert("信息获取失败，请刷新页面后重试");
				}
			})
		}
		
	}
})

var answerPanel = new Vue({
	el:"#answerPanel",
	data:{
		items:[]
	}
})

var articlePanel = new Vue({
	el:"#articlePanel",
	data:{
		items:[]
	}
})

var questionPanel = new Vue({
	el:"#questionPanel",
	data:{
		items:[]
	}
})

function nextPage(type){
	if(type==="question"){
		questionPage = questionPage + 1;
	}else if(type==="answer"){
		answerPage = answerPage + 1;
	}else if(type==="article"){
		articlePage = articlePage + 1;
	}
	initUserInfo();
}

function previousPage(type){
	if(type==="question"){
		if(questionPage-1>0){
			questionPage = questionPage - 1;
		}else{
			alert("已经是第一页");
			return;
		}
	}else if(type==="answer"){
		if(answerPage-1>0){
			answerPage = answerPage - 1;
		}else{
			alert("已经是第一页");
			return;
		}
	}else if(type==="article"){
		if(articlePage-1>0){
			articlePage = articlePage - 1;
		}else{
			alert("已经是第一页");
			return;
		}
	}
	initUserInfo();
}

function getUser(){
	$.ajax({
		type:"post",
		async:false,
		url:"/nd2/user/nav.do",
		dataType:"json",
		success:function(data){
			userNav.userInfo = data;
			navbar.userInfo = data;
		},
		error:function(){
			alert("登录已过期,请重新登录！");
			window.location.href = "login.html"
		}
	})
}

var userNav = new Vue({
	el:"#usernav",
	data:{
		userInfo:{}
	}
})

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