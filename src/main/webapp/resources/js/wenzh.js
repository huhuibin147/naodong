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
	el:'#Art',
	data:{
		todo:'',
		u:''
	}
})
function ques(){
	$.ajax({
		type:"post",
		url:"/nd2/article/art.do",
		data:{
			id:id
		},
		dataType:"json",
		success:function(data){
			Ques.todo=data.art;
			Ques.u=data.artuser;
			nav.img=data.userimg;
			nav.name=data.username;
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