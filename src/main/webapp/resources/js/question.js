//获取url参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
    }
//这样调用：
//alert(getQueryString("id"));
    
var id = getQueryString("id");

var nav = new Vue({
	el:'#usernav',
		data:{
			img:'',
			name:''
		}
})
var Ques = new Vue({
	el:'#Ques',
	data:{
		todo:'',
		answers:'',
		theme:'',
		imgs:'',
		names:'',
		count:''
	}
})
function ques(){
	$.ajax({
		type:"post",
		url:"/nd2/question/Ques.do",
		data:{
			id:id
		},
		dataType:"json",
		success:function(data){
			Ques.todo=data.ques;
			Ques.theme=data.theme;
			nav.img=data.userimg;
			nav.name=data.username;
			alist();
		},
		error:function(msg){
			if(msg.status==200){
				alert('请先登录！');
			window.location.href='/nd2/html/login.html';
			}
			else{
				alert('服务器异常'+msg.status);
			}
			
		}
	});
}
window.onload = ques;
var page=1;
function next(){
	page=page+1;
	alist();
}
function pre(){
	if(page-1>0){
		page=page-1;
		alist();
	}else{
		alert("当前是第一页！")
	}
}
function alist(){
	$.ajax({
		type:"post",
		url:"/nd2/answer/alist.do",
		data:{
			qid:id,
			page:page
		},
		dataType:"json",
		success:function(data){
		  	if(data.page!=page){
		  		if(data.page!=0){
		  			alert("已经是最后一页！");
		  			page=page-1;
		  		}
		  	}else{
				Ques.answers=data.list
				Ques.imgs=data.imgs
				Ques.names=data.names
				Ques.count=data.count
		  	}
		},
		error:function(msg){
			if(msg.status==200){
				alert('请先登录！');
			window.location.href='/nd2/html/login.html';
			}
			else{
				alert('服务器异常'+msg.status);
			}
			
		}
	});
}

var editor = new wangEditor('a-content');
// 上传图片（举例）
editor.config.uploadImgUrl = '/nd2/upload';
// 配置自定义参数（举例）
editor.config.uploadParams = {
    token: 'file',
    user: 'bin'
};
// 设置 headers（举例）
editor.config.uploadHeaders = {
    'Accept' : 'text/x-json'
};
// 隐藏掉插入网络图片功能。该配置，只有在你正确配置了图片上传功能之后才可用。ture隐藏|false显示
editor.config.hideLinkImg = false;
editor.create();

$('#a-content').attr('style','height:auto;');
function push(){
	var html = $("#a-content").html();
	$.ajax({
		type:"post",
		url:"/nd2/answer/push.do",
		data:{
			qid:id,
			content:html
		},
		dataType:"json",
		success:function(data){
			alert("回答成功！")
			var html = $("#a-content").html('<p>请输入回答内容...</p>');
			alist();
		},
		error:function(msg){
			if(msg.status==200){
				alert('请先登录！');
			window.location.href='/nd2/html/login.html';
			}
			else{
				alert('服务器异常'+msg.status);
				
			}
		}
	});
}
function concern(){
	$.ajax({
		type:"post",
		url:"/nd2/Concern/concernQues.do",
		data:{
			qid:id
		},
		dataType:"json",
		success:function(data){
			if(data=='success'){
				alert("关注成功！")
			}else{
				alert("已经关注过！")
			}
			
		},
		error:function(msg){
			if(msg.status==200){
				alert('请先登录！');
			window.location.href='/nd2/html/login.html';
			}
			else{
				alert('服务器异常'+msg.status);
				
			}
		}
	});
}