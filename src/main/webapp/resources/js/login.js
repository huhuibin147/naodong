var log = new Vue({
	el:'#login-form',
	data:{
		email:'',
		psw:''
	}
})

var registInfo = new Vue({
	el:'#regist-form',
	data:{
		rname:'',
		remail:'',
		rpassword:'',
		rcode:''
	}
})

function checkEmail(){
	var inputEmail = document.getElementById('inputEmail').value;
	var Reg=/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
	var result=Reg.test(inputEmail);
	if(result === true){
		$.ajax({
			type:"post",
			url:"/nd2/user/checkEmail.do",
			data:{
				email:registInfo.remail
			},
			dataType:"json",
			success:function(data){
				if(data.msg=="yes"){
					alert("该邮箱已被注册");
					 $("#sendEmail").attr("disabled", true);
				}else{
					$("#sendEmail").removeAttr("disabled");
				}
			}
		})
	}else{
		
	}
	
}

function login(){
	$.ajax({
		type:"post",
		url:"/nd2/user/login.do",
		data:{
			email:log.email,
			psw:log.psw
		},
		dataType:"json",
		success:function(data){
			if(data.msg=="密码正确"){
				window.location.href="index.html";
			}else{
				alert(data.msg);
			}
		},
		error:function(msg){
			alert("服务器异常");
		}
	});
}

function sendIdentifyCode(){
	$.ajax({
		type:"post",
		url:"/nd2/user/sendIdentifyCode.do",
		data:{
			email:registInfo.remail
		},
		dataType:"json",
		success:daojishi($("#sendEmail")),//添加cookie记录,有效时间60s
		error:alert("服务器异常")
	})
}

function daojishi(obj){
	  addCookie("secondsremained",60,60);//添加cookie记录,有效时间60s
      v = getCookieValue("secondsremained")?getCookieValue("secondsremained"):0 ;
      if(v>0){
    	  settime($("#sendEmail"));
      }
}



//开始倒计时
var countdown;
function settime(obj) { 
    countdown=getCookieValue("secondsremained");
    if (countdown.toString() == 0) {
    	$("#sendEmail").removeAttr("disabled");
    	$("#sendEmail").val("获取验证码");
        return;
    } else { 
        obj.attr("disabled", true); 
//        var button1 = document.getElementById('#sendEmail');
//        button1.innerHTML = "重新发送"+countdown;
//        obj.value = "重新发送"+countdown;
        $("#sendEmail").val("重新发送"+countdown);
        countdown--;
        editCookie("secondsremained",countdown,countdown+1);
    } 
    setTimeout(function() { settime(obj) },1000) //每1000毫秒执行一次
} 

//发送验证码时添加cookie
function addCookie(name,value,expiresHours){ 
    var cookieString=name+"="+escape(value); 
    //判断是否设置过期时间,0代表关闭浏览器时失效
    if(expiresHours>0){ 
        var date=new Date(); 
        date.setTime(date.getTime()+expiresHours*1000); 
        cookieString=cookieString+";expires=" + date.toUTCString(); 
    } 
        document.cookie=cookieString; 
} 

//修改cookie的值
function editCookie(name,value,expiresHours){ 
    var cookieString=name+"="+escape(value); 
    if(expiresHours>0){ 
      var date=new Date(); 
      date.setTime(date.getTime()+expiresHours*1000); //单位是毫秒
      cookieString=cookieString+";expires=" + date.toGMTString(); 
    } 
      document.cookie=cookieString; 
} 

$(document).ready(function(){
	 var v = getCookieValue("secondsremained")>0?getCookieValue("secondsremained"):0 ;
     if(v>0){
   	  settime($("#sendEmail"));
     }
})

//根据名字获取cookie的值
function getCookieValue(name){ 
      var strCookie=document.cookie; 
      var arrCookie=strCookie.split("; "); 
      for(var i=0;i<arrCookie.length;i++){ 
        var arr=arrCookie[i].split("="); 
        if(arr[0]==name){
          return unescape(arr[1]);
          break;
        }else{
             return ""; 
             break;
         } 
      } 
       
}


function regist(){
	$.ajax({
		type:"post",
		url:"/nd2/user/regist.do",
		data:{
			email:registInfo.remail,
			name:registInfo.rname,
			password:registInfo.rpassword,
			identifyCode:registInfo.rcode
		},
		dataType:"json",
		success:function(data){
			if(data.msg=="注册成功"){
				location.reload();
			}else{
				alert(data.msg);
			}
		},
		error:function(msg){
			alert("服务器异常");
		}
	});
		
}
