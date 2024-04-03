

	
/*
	if(authStatus == 0){
		alert(" 인증번호를 확인해주세요. ")
	}

	if (phNumCK.value == '') {
		alert("인증번호를 입력해주세요.");
		return false;
	}
*/

var authStatus = 0; 

// 인증번호 확인란 
var phoneNumberCK;

function authentication_Number_Check(){
		
		
	phoneNumberCK = document.getElementById("phoneNumberCK").value; // 사용자가 입력한 번호
	console.log(phoneNumberCK);

	
	if(phoneNumberCK==''){
		alert("인증번호를 입력해주세요.");
		return false;
	}
	
	console.log("인증번호 확인함수 실행");
	console.log("인증번호"+randNumPhNum);
	
	// el로 받아야되나? 안오네
	//ajax로 받은 데이터를 받아오는 방법??
	
	//var authNumber = $('authNumber').val(); // 정답
	//var randNumPhNum = $('randNumPhNum').val(); // 정답
	//console.log(authNumber);
	



	// 대문자 소문자로 변경해주세요!~~
	console.log("사용자가 입력한 인증번호"+phoneNumberCK);
	
	
	if(randNumPhNum == phoneNumberCK){
	console.log(randNumPhNum);
	console.log(phoneNumberCK);
	
	alert(" 인증번호가 확인되었습니다. ");
	console.log("인증번호가 확인되었습니다");
	
	authStatus = 1; 
	console.log(authStatus);
}  else{
	alert(" 인증번호가 옳바르지 않습니다. ");
	console.log(" 인증번호가 옳바르지 않습니다. ");
	console.log(authStatus);
	document.getElementById("phoneNumberCK").focus();
	//return false;
}
	
}
