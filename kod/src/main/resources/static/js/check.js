// idCheckStatus 선언후 0으로 초기화

// 아이가 중복인지 확인하는 변수 생성후 초기화
var idCheckStatus = 0;

// regId 변수 선언후 정규식 저장
//  아이디 입력 형식 정규식을 변수에 담아줍니다
var regId = /^[0-9a-z]{6,13}$/;


function check(){
	// 회원가입페이지에있는(join.jsp) 아이디입력값(memberID)를 가져와 memberID변수에 저장함
	var memberID=$('#memberID').val();
	// 만약 memberID값이 없다면 메시지 출력 함수 종료
  if (memberID.trim() === '') {
        $('#msg').css('color', '#FF0000').text("아이디를 입력해주세요.");
        return; // 함수 종료
    }	

    // 입력한 아이디가 정규식 패턴에 맞지 않으면 
    if (!regId.test(memberID)) {                       // 메시지를 출력하고 함수 종료
        $('#msg').css('color', '#FF0000').text("아이디는 6~13자의 영문 소문자, 숫자로 입력해주세요.");
        idCheckStatus = 0; // 아이디 중복검사 함수에 0을 저장후
        return; // 함수 종료
    }
	
	// jQuery의 AJAX 함수를 호출 이함수를 사용하여 비동기적으로 서버와 통신할수있음 새로고침 없이 데이터를 받아올 수 있는 기술
	$.ajax({
		type:"POST", // POST메서드를 사용하여 서버로 데이터 전송
		url : "check", // 요청을 보낼 서버의 주소(URL)을 지정함 
		data : {'memberID' : memberID }, // 서버로 전송할 데이터를 지정  'memberID'라는 파라미터에  회원가입 페이지에서 입력한 아이디(memberID)값을 전송
		dataType :  'text' , // 서버로부터 받은 응답 데이터의 형식을 지정 
		success : function(data){ // AJAX요청이 성공했을때 실행할 콜백함수 지정 
								  // 서버로부터 응답이 도착하면 이함수가 실행되며 응답데이터는 "data"매개변수로 전달됩니다.
			
		
		// 만약 데이터가 true면 메시지 출력 idCheckStatus변수에 1저장  
			if(data=='true'){
				$('#msg').css('color', '#00FF00').text("사용가능한 아이디 입니다. ");
				idCheckStatus = 1; // 아이디 중복검사 함수에 1을저장
			}
		// 아니라면 메시지 출력 idCheckStatus변수에 0저장
			else{
				$('#msg').css('color', '#FF0000').text("중복 아이디 입니다. ");
				idCheckStatus = 0; // 아이디 중복검사 함수에 0을저장
				
			}
		},
		// AJAX 요청이 실패했을경우 함수실행 콘솔에 로그찍기
		error : function(error){
			console.log('에러발생!');
			console.log('에러종류 : '+error);
		}
		
	});
}
