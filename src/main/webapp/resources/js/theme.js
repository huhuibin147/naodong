
var nav = new Vue({
	el:'#usernav',
		data:{
			img:'',
			name:''
		}
})
var se = new Vue({
	el:'#search',
		data:{
			topic:''
		}
})
var topic='';
var page=1;
var Ques = new Vue({
		el:'#QuesList',
		data:{
			tlist:'',
			lists:'',
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
	  		},
	  		toTopic:function(i){
	  			topic=i;
	  			page=1;
	  			list();
	  			$(".title-t").text(i)
	  		}
    	}
})

			
function encode(){
	var href = "topic.html?t="+se.topic;
	var ehref = encodeURI(href);
	window.location.href=ehref
}
function next(){
	page=page+1;
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
		url:"/nd2/topic/getlist.do",
		data:{
			page:page,
			topic:topic
		},
		dataType:"json",
		success:function(data){
			if(data.page!=page){
		  		if(data.page!=0){
		  			alert("已经是最后一页！");
		  			page=page-1;
		  		}
		  	}else{
		  		Ques.tlist=data.tlist;
		  		Ques.size=data.size;
		  		Ques.lists=data.list;
				nav.img=data.userimg;
				nav.name=data.username;
				Ques.change();
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



//5个渲染  不会用循环。。
function show(){ 
	var box = document.getElementById("c-0"); 
	if(box!=null){
		var text = box.innerHTML; 
		var newBox = document.createElement("div"); 
		var btn = document.createElement("a"); 
		newBox.innerHTML = text.substring(0,200); 
		btn.innerHTML = text.length > 200 ? "...显示全部" : ""; 
		btn.href = "###"; 
		btn.onclick = function(){ 
		if (btn.innerHTML == "...显示全部"){ 
		btn.innerHTML = "收起"; 
		newBox.innerHTML = text; 
		}else{ 
		btn.innerHTML = "...显示全部"; 
		newBox.innerHTML = text.substring(0,200); 
		} 
		} 
		box.innerHTML = ""; 
		box.appendChild(newBox); 
		box.appendChild(btn); 
	}
	
	
	var box2 = document.getElementById("c-1"); 
	if(box2!=null){
		var text2 = box2.innerHTML; 
		var newBox2 = document.createElement("div"); 
		var btn2 = document.createElement("a"); 
		newBox2.innerHTML = text2.substring(0,200); 
		btn2.innerHTML = text2.length > 200 ? "...显示全部" : ""; 
		btn2.href = "###"; 
		btn2.onclick = function(){ 
		if (btn2.innerHTML == "...显示全部"){ 
		btn2.innerHTML = "收起"; 
		newBox2.innerHTML = text2; 
		}else{ 
		btn2.innerHTML = "...显示全部"; 
		newBox2.innerHTML = text2.substring(0,200); 
		} 
		} 
		box2.innerHTML = ""; 
		box2.appendChild(newBox2); 
		box2.appendChild(btn2); 
	}
	
	var box3 = document.getElementById("c-2"); 
	if(box3!=null){
		var text3 = box3.innerHTML; 
		var newBox3 = document.createElement("div"); 
		var btn3 = document.createElement("a"); 
		newBox3.innerHTML = text3.substring(0,200); 
		btn3.innerHTML = text3.length > 200 ? "...显示全部" : ""; 
		btn3.href = "###"; 
		btn3.onclick = function(){ 
		if (btn3.innerHTML == "...显示全部"){ 
		btn3.innerHTML = "收起"; 
		newBox3.innerHTML = text3; 
		}else{ 
		btn3.innerHTML = "...显示全部"; 
		newBox3.innerHTML = text3.substring(0,200); 
		} 
		} 
		box3.innerHTML = ""; 
		box3.appendChild(newBox3); 
		box3.appendChild(btn3); 	
	}
	
	
	var box4 = document.getElementById("c-3"); 
	if(box4!=null){
		var text4 = box4.innerHTML; 
		var newBox4 = document.createElement("div"); 
		var btn4 = document.createElement("a"); 
		newBox4.innerHTML = text4.substring(0,200); 
		btn4.innerHTML = text4.length > 200 ? "...显示全部" : ""; 
		btn4.href = "###"; 
		btn4.onclick = function(){ 
		if (btn4.innerHTML == "...显示全部"){ 
		btn4.innerHTML = "收起"; 
		newBox4.innerHTML = text4; 
		}else{ 
		btn4.innerHTML = "...显示全部"; 
		newBox4.innerHTML = text4.substring(0,200); 
		} 
		} 
		box4.innerHTML = ""; 
		box4.appendChild(newBox4); 
		box4.appendChild(btn4);
	}
	
	
	var box5 = document.getElementById("c-4"); 
	if(box5!=null){
		var text5 = box5.innerHTML; 
		var newBox5 = document.createElement("div"); 
		var btn5 = document.createElement("a"); 
		newBox5.innerHTML = text5.substring(0,200); 
		btn5.innerHTML = text5.length > 200 ? "...显示全部" : ""; 
		btn5.href = "###"; 
		btn5.onclick = function(){ 
		if (btn5.innerHTML == "...显示全部"){ 
		btn5.innerHTML = "收起"; 
		newBox5.innerHTML = text5; 
		}else{ 
		btn5.innerHTML = "...显示全部"; 
		newBox5.innerHTML = text5.substring(0,200); 
		} 
		} 
		box5.innerHTML = ""; 
		box5.appendChild(newBox5); 
		box5.appendChild(btn5);
	}
	
}