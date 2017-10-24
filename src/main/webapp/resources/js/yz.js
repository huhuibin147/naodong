
var nav = new Vue({
	el:'#usernav',
		data:{
			img:'',
			name:''
		}
})
function ques(){
	$.ajax({
		type:"post",
		url:"/nd2/question/yz.do",
		data:{
		},
		dataType:"json",
		success:function(data){
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