(function(){
	let loginFlag = sessionStorage.getItem("user");
	console.log(loginFlag);
	if(loginFlag){
		$("#userNameShow").html(loginFlag+"<button id='quitBtn'>退出</button>");
	}else{
		window.location.href="../html/login.jsp"
	}
})();

function getUrlParam(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r!=null) return unescape(r[2]); return null;
}

$("#quitBtn").on("click",function(){
	console.log(123);
	sessionStorage.removeItem("user");
	window.location.href="../html/login.jsp"
});
