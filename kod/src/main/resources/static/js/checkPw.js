$(document).ready(function(){
    let pw = $("#mPw") ;
    let ckPw = $("#mPwCheck") ;
		
    ckPw.on('input', () => {
		if(pw==ckPw){
			console.log("비밀번호가 일치합니다");
		}
	})
})