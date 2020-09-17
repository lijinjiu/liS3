 
function post(url,param,callback){
	var xhr;
	try{
		xhr=new XMLHttpRequest();
	}catch(e){
		xhr=new ActiveXObject("XXXXXXXXX");  //低版本的IE方式不同
	}
	
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 && xhr.status==200){
			callback(xhr.responseText);  //js可以在传入的参数后面加上括号，当成函数来执行
		}
	}
	
	xhr.open("POST",url);
	//post必须在open之后设置  
	//如果是文件上传方式 不要设置Content-Type，因为太复杂
	if(!(param instanceof FormData)){
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
	}
	xhr.send(param);
}