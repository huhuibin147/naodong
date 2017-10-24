
//获取url参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
    }
//这样调用：
//alert(getQueryString("id"));
var list = new Vue({
	el:'#userlist',
	data:{
		items:[]
	}
})
var content = new Vue({
	el:'#consolecontainer',
	data:{
		todos:[]
	},
	methods:{
			c:function(){
		    
	var console = document.getElementById('console');
	console.scrollTop = console.scrollHeight-50;
	  }
    }
})

var roomid = getQueryString("roomid");
if(roomid == null){
	roomid="1";
}

var Chat = {};

Chat.socket = null;

Chat.connect = (function(host) {
	if ('WebSocket' in window) {
		Chat.socket = new WebSocket(host);
	} else if ('MozWebSocket' in window) {
		Chat.socket = new MozWebSocket(host);
	} else {
		Console.log('Error: WebSocket is not supported by this browser.');
		return;
	}

	Chat.socket.onopen = function() {
		Console.log('Info: 已连上服务器.');
		document.getElementById('chat').onkeydown = function(event) {
			if (event.keyCode == 13) {
				Chat.sendMessage();
			}
		};
	};

	Chat.socket.onclose = function() {
		document.getElementById('chat').onkeydown = null;
		Console.log('Info: 与服务器失去连接.');
	};

	Chat.socket.onmessage = function(message) {
		if(message.data!="请先登录!!!"){
			var data = JSON.parse(message.data);
			if(data.status==1){
				if(!contains(list.items,data.img)){
					list.items.push(data);
					Console.log(data.msg);
				}
			}else if(data.status==0){
				var ind = index(list.items,data.img)
				if(ind!=-1){
					list.items.splice(ind,1);
					Console.log(data.msg);
				}
			}else{
				if(!contains(list.items,data.img)){
					list.items.push(data);
				}
				content.todos.push(data);
				content.c();
			}
		}else{
			Console.log(message.data);
		}
	};
});

Chat.initialize = function() {
	if (window.location.protocol == 'http:') {
		Chat.connect('ws://localhost:8080/nd2/chat/'+roomid);
	} else {
		Chat.connect('ws://localhost:8080/nd2/chat/'+roomid);
	}
};

Chat.sendMessage = (function() {
	var message = document.getElementById('chat').value;
	if (message != '') {
		Chat.socket.send(message);
		document.getElementById('chat').value = '';
	}
});

var Console = {};

Console.log = (function(message) {
	var console = document.getElementById('console');
	var p = document.createElement('p');
	p.style.wordWrap = 'break-word';
	p.innerHTML = message;
	console.appendChild(p);
	while (console.childNodes.length > 100) {
		console.removeChild(console.firstChild);
	}
	console.scrollTop = console.scrollHeight;
});

Chat.initialize();

document.addEventListener("DOMContentLoaded", function() {
			// Remove elements with "noscript" class - <noscript> is not allowed in XHTML  
			var noscripts = document.getElementsByClassName("noscript");
			for (var i = 0; i < noscripts.length; i++) {
				noscripts[i].parentNode.removeChild(noscripts[i]);
			}
		}, false);

//arrays contains
function contains(a, obj) {
    var i = a.length;
    while (i--) {
       if (a[i].img === obj) {
           return true;
       }
    }
    return false;
}
//arrays index
function index(a, obj) {
    var i = a.length;
    while (i--) {
       if (a[i].img === obj) {
           return i;
       }
    }
    return -1;
}