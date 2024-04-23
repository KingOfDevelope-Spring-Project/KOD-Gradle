//인증번호 확인 버튼 체크 함수 
var authStatus = 0; 

// 인증번호 확인란 
var phoneNumberCK;

function authentication_Number_Check(){
	console.log("인증번호 확인함수 실행");
		
		
	phoneNumberCK = document.getElementById("phoneNumberCK").value; // 사용자가 입력한 번호
	console.log(phoneNumberCK);

	
	if(phoneNumberCK==''){
		alert("인증번호를 입력해주세요.");
		return false;
	}
	
	console.log("인증번호"+randNumPhNum);
	console.log("사용자가 입력한 인증번호"+phoneNumberCK);
	
	
	if(randNumPhNum == phoneNumberCK){
	console.log(randNumPhNum);
	console.log(phoneNumberCK);
	
	alert(" 인증번호가 확인되었습니다. ");
	console.log("인증번호가 확인되었습니다");
	//인증번호 확인 버튼 체크 함수 
	authStatus = 1; 
	console.log(authStatus);
}  else{
	alert(" 인증번호가 옳바르지 않습니다. ");
	console.log(" 인증번호가 옳바르지 않습니다. ");
	console.log(authStatus);
	document.getElementById("phoneNumberCK").focus();
}
	
}
