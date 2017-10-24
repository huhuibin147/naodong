var nav = new Vue({
	el:'#usernav',
		data:{
			img:'',
			name:''
		}
})
var topic = new Vue({
		el:'#topic',
		data:{
			tlist:'',
			topics:'',
			size:''
		},
		methods:{
			change:function(){
		    this.$nextTick(function(){
		      show();
		    })
	  		},
	  		encode:function(i){
	  			var href = "topic.html?t="+i;
	  			var ehref = encodeURI(href);
	  			window.location.href=ehref
	  		}
    	}
})
var se = new Vue({
	el:'#search',
		data:{
			topic:''
		}
})
stopic='';
var page=1;
function next(){
	page=page+1;
	list();
}
function encode(){
	page=1;
	stopic=se.topic;
	list();
}
function pre(){
	if(page-1>0){
		page=page-1;
		list();
	}else{
		alert("当前是第一页！")
	}
}


function list(){
	$.ajax({
		type:"post",
		url:"/nd2/topic/getTopicList.do",
		data:{
			page:page,
			topic:stopic
		},
		dataType:"json",
		success:function(data){
			if(data.page!=page){
		  		if(data.page!=0){
		  			alert("已经是最后一页！");
		  			page=page-1;
		  		}
		  	}else{
		  		topic.tlist=data.tlist;
		  		topic.size=data.size;
		  		topic.topics=data.lists;
				nav.img=data.userimg;
				nav.name=data.username;
				
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
window.onload = list;
function concern(id){
	$.ajax({
		type:"post",
		url:"/nd2/Concern/concernTopic.do",
		data:{
			tid:id
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