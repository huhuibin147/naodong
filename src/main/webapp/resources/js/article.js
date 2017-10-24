
var nav = new Vue({
	el:'#usernav',
		data:{
			img:'',
			name:''
		}
})
function getimg(){
	$.ajax({
		type:"post",
		url:"/nd2/user/nav.do",
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
window.onload = getimg;

var editor = new wangEditor('content');
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

//改变文本框高度
  $("#content").bind('DOMNodeInserted', function(e) {
    var contentHeight = $("#content").scrollTop();
            if(contentHeight > 0){
            	$('#content').attr('style','height:auto;');
            }
});

function push(){
	var html = $("#content").html();
	var title = $(".titl").val();
	$.ajax({
		type:"post",
		url:"/nd2/article/push.do",
		data:{
			title:title,
			content:html
		},
		dataType:"json",
		success:function(data){
			alert("发布成功！")
			$("#content").html('<p>请输入回答内容...</p>');
			$(".titl").val('');
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
