

//인증번호 발송 버튼 체크 함수
var phNumCheckStatus = 0;


// 인증번호를 담는 변수 생성후 초기화
var randNumPhNum = -1;



	



function authentication_Number() {

	var phoneNumberPrefix = $('#phoneNumberPrefix').val();
	var phoneNumberMiddle = $('#phoneNumberMiddle').val();
	var phoneNumberSuffix = $('#phoneNumberSuffix').val();
	console.log(phoneNumberPrefix);
	console.log(phoneNumberMiddle);
	console.log(phoneNumberSuffix);


	var memberPhoneNumber = phoneNumberPrefix + phoneNumberMiddle + phoneNumberSuffix;
	console.log(memberPhoneNumber);

	if(memberPhoneNumber==''){
		alert("핸드폰 번호를 입력해주세요.")
		return false;
	}else if(phoneNumberPrefix  =='' || phoneNumberMiddle =='' || phoneNumberSuffix =='' ){
		alert("핸드폰 번호를 입력해주세요.")
		return false;
	}
	

		console.log("인증번호 발송 들어옴");
		console.log("인증번호 발송횟수 체크 함수"+phNumCheckStatus);
	
	// 인증번호 발송 버튼을 3번 누르면 인증번호버튼을 비활성화 시키는 유효성검사
	if (phNumCheckStatus >= 3) {
		document.getElementById("authenticationNumber").disabled = true;
		// 3분 타이머 설정 (3분 타이머 활성화) 
		setTimeout(function() {
			document.getElementById("authenticationNumber").disabled = false;
			phNumCheckStatus = 0; // 클릭횟수 초기화
			console.log("인증번호 발송횟수 체크 함수"+phNumCheckStatus);
		}, 180000); // 3분 (밀리초 단위)

		alert("인증번호 발송은 3회까지 가능합니다.\n3분후 다시 시도해주세요.")
		return false;
	}
		if(phNumCheckStatus>=3){
		alert("인증번호 발송은 3화까지 가능합니다.\n3분후 다시 시도해주세요.")
		return false;
	}




	// jQuery의 AJAX 함수를 호출 이함수를 사용하여 비동기적으로 서버와 통신할수있음 새로고침 없이 데이터를 받아올 수 있는 기술
	$.ajax({
		type: "GET", // POST메서드를 사용하여 서버로 데이터 전송
		url: "phoneNumberCheck", // 요청을 보낼 서버의 주소(URL)을 지정함 
		data: { 'memberPhoneNumber': memberPhoneNumber }, // 서버로 전송할 데이터를 지정  'memberID'라는 파라미터에  회원가입 페이지에서 입력한 아이디(memberID)값을 전송
		dataType: 'text', // 서버로부터 받은 응답 데이터의 형식을 지정 
		success: function(data) { // AJAX요청이 성공했을때 실행할 콜백함수 지정 
			// 서버로부터 응답이 도착하면 이함수가 실행되며 응답데이터는 "data"매개변수로 전달됩니다.
			console.log("ajax응답" + data);
			randNumPhNum=data;
			console.log("인증번호" + randNumPhNum);

			// 만약 데이터가 true면 메시지 출력 idCheckStatus변수에 1저장  
			if (data != null) {
				phNumCheckStatus++;
				console.log("인증번호 발송횟수 체크 함수"+phNumCheckStatus);
				alert("인증번호가 전송되었습니다.");
				/*	$('#phnumckmsg').css('color', 'blue').text(" 메시지 발송 완료. ");*/

			}
			//else{
			//	phNumCheckStatus = 0;
			///	}
			//console.log(message);
			//console.log(authNumber);
			// 아니라면 메시지 출력 idCheckStatus변수에 0저장
			//	else{
			//		$('#phnumckmsg').css('color', '#FF0000').text(" 인증번호 요청 버튼을 눌러주세요. ");
			//		phNumCheckStatus = 0; // 아이디 중복검사 함수에 0을저장
			//		
			//	}
		},
		// AJAX 요청이 실패했을경우 함수실행 콘솔에 로그찍기
		error: function(error) {
			console.log('에러발생!');
			console.log('에러종류 : ' + error);
		}

	});
}