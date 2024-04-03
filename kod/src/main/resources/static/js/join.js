/*-------------------------------------------------------------------------*/
// 이메일입력란 select 요소를 가져옴
const domainListEl = document.querySelector('#domain-list')
// 이메일입력란 input 요소를 가져옴
const domainInputEl = document.querySelector('#domain-txt')
// select 옵션 변경 시
domainListEl.addEventListener('change', (event) => {
  
  // option에 있는 도메인 선택 시
  if(event.target.value !== "type") {
    // 선택한 도메인을 input에 입력하고 입력을 비활성화
    domainInputEl.value = event.target.value
    domainInputEl.disabled = true // 입력을 비활성화하여 수정 불가능하도록 설정
    console.log(domainInputEl);
    
  } else { // 직접 입력 시
    // input 내용 초기화 & 입력 가능하도록 변경
    domainInputEl.value = ""
    domainInputEl.disabled = false // 입력을 활성화하여 수정 가능하도록 설정
  }
}) 
/*-------------------------------------------------------------------------*/
//JavaScript 함수: 모든 체크박스를 선택 상태로 변경하는 기능을 수행함
function checkAllCheckboxes() {
    // 모든 체크박스에 대한 NodeList(체크박스 목록) 가져오기 
    var checkboxes = document.querySelectorAll('input[type="checkbox"]');

    // "약관 전체 동의하기" 체크박스의 상태 가져오기
    var checkAllCheckbox = document.getElementById('checkAll');

    // "약관 전체 동의하기" 체크박스의 상태에 따라 모든 체크박스의 상태를 설정
    // 모든 체크박스를 순회하면서 각 체크박스의 상태를 "약관 전체 동의하기" 체크박스의 상태로 설정
    checkboxes.forEach(function(checkbox) {
        checkbox.checked = checkAllCheckbox.checked;
    });
}
/*-------------------------------------------------------------------------*/
// 개인정보 처리방침 자세히 버튼을 누르면 실행되는 함수
function openWin1() {
	// window.open이라는 메서드를 사용하여 개인정보 처리방침 안내문이 적혀있는 페이지 열기 
	// 페이지의 이름은 "약관동의"로 설정되며 크기는 600*800 , 툴바, 메뉴바, 스크롤바 표시되지 않음
	// 페이지의 사이즈는 조절가능
	window.open("privacyAgree.jsp", "약관동의", "width=600, height=800, toolbar=no, menubar=no, scrollbars=no, resizable=yes")
    // 폼 제출을 막기위해 false반환
	return false;
}
// KOD 이용약관 동의 자세히 버튼을 누르면 실행되는 함수
function openWin2() {
    window.open("kodAgree.jsp", "약관동의", "width=600, height=800, toolbar=no, menubar=no, scrollbars=no, resizable=yes")
    return false;
}
// 마케팅 정보 수신동의 자세히 버튼을 누르면 실행되는 함수
function openWin3() {
    window.open("marketingAgree.jsp", "약관동의", "width=600, height=800, toolbar=no, menubar=no, scrollbars=no, resizable=yes")
    return false;
}
/*-------------------------------------------------------------------------*/
// 유효성검사 함수
function validateForm(form) {
         
    // 문서에 개인정보처리방침 동의 박스와 KOD이용약관 동의 박스체크박스를 변수에 저장함
    var privacyCheckbox = document.getElementById('privacyAgreeCheckbox');
    var KODAgreeCheckbox = document.getElementById('KODAgreeCheckbox');
    
    // 필수 동의 약관 중 하나라도 체크되지 않으면 폼 제출을 막음
    // 개인정보처리방침 동의 박스가 체크되지 않았거나 KOD이용약관 동의 박스가 체크되지않았으면
    // 안내문구출력후 false를 반환해 함수를 종료하고 폼제출을 막음
    if (!privacyCheckbox.checked || !KODAgreeCheckbox.checked) {
        alert('필수 이용약관을 확인해주세요.');
        return false;
    }
    
    // 위에 조건문에 해당하지않는다면 안내문구 출력
    alert('필수 이용약관 동의 확인.\n회원가입페이지로 이동합니다.');

    // 모든 조건이 충족되면 폼을 제출
    form.submit();
}

// 이 함수는 사용자가 입력란에 공백을 입력할 때 호출됩니다.
function removeWhitespace(input) {
	// 입력된 값에서 모든 공백을 제거합니다.
	input.value = input.value.replace(/\s/g, '');
}

// 'memberPWCK' 요소의 input 이벤트에 대한 리스너를 등록
document.getElementById('memberPWCK').addEventListener('input', function () {

	// 현재문서(input)에서 비밀번호와 비밀번호 확인 값을 가져옴
	var password = document.getElementById('memberPW').value; // 비밀번호 입력란의 값
	var confirmPassword = this.value; // 'memberPWCK' 입력란의 값

	// 비밀번호 불일치 시 메시지를 표시할 변수(요소)생성
	var mismatchSpan = document.getElementById('passwordMismatch');

	// 비밀번호와 비밀번호 확인 값이 일치하는지 확인
	if (password === confirmPassword) {
		// 일치하면 불일치 메시지를 숨김
		mismatchSpan.style.display = 'none';
	} else {
		// 불일치하면 불일치 메시지를 표시
		mismatchSpan.style.display = 'block';
	}
});
/*-------------------------------------------------------------------------*/
// 선택 박스에 1부터 12까지의 월 옵션 추가
var selectElement = document.getElementById("month");
// 1부터 12까지의 월에 대한 옵션을 동적으로 생성하여 추가
for (var i = 1; i <= 12; i++) {
	// option 엘리먼트를 생성
	var option = document.createElement("option");
	// option의 value와 text 속성에 1자리 숫자 앞에 0을 붙여 두 자리로 만든 값을 할당
	option.value = option.text = ("0" + i).slice(-2);
	// 생성한 option을 select 요소에 추가  
	selectElement.add(option);
}
/*-------------------------------------------------------------------------*/
// HTML 폼에서 사용자가 입력한 값들을 가져와서 유효성을 체크하는 JavaScript 함수
function formCheck(form) {
	console.log(" formCheck 들어옴");

	var memberID = document.getElementById("memberID"); // 회원아이디를 입력하는 입력란(input text) 의 값을 memberID 저장 
	var memberPW = document.getElementById("memberPW");
	var memberPWCK = document.getElementById("memberPWCK");
	var memberName = document.getElementById("memberName");
	var memberGender = document.getElementById("memberGender");
	var year = document.getElementById("year");
	var month = document.getElementById("month");
	var day = document.getElementById("day");
	var phoneNumberPrefix = document.getElementById("phoneNumberPrefix");
	var phoneNumberMiddle = document.getElementById("phoneNumberMiddle");
	var phoneNumberSuffix = document.getElementById("phoneNumberSuffix");
	var phoneNumberCK = document.getElementById("phoneNumberCK");
	var emailField = document.getElementById("emailUsername");
	var emailType = document.getElementById("domain-list").value;
	var emailTxt = document.getElementById("domain-txt").value;

	var email1 = emailField.value;
	var email2 = (emailType === "type") ? emailTxt : document.getElementById("domain-list").value;
	var fullEmail = email1 + "@" + email2;


	var adrsName = document.getElementById("adrsName");
	var sample4_postcode = document.getElementById("sample4_postcode");
	var sample4_roadAddress = document.getElementById("sample4_roadAddress");
	var sample4_jibunAddress = document.getElementById("sample4_jibunAddress");
	var sample4_detailAddress = document.getElementById("sample4_detailAddress");

	// 이름입력란 유효성검사를 위한 정규식
	var regName = /^[가-힣]{2,}$/;      // 한글만입력가능 , 2글자이상 입력	
	// 아이디입력 유효성검사를 위한 정규식
	var regId = /^[0-9a-z]{6,13}$/;    // 숫자 , 소문자만 입력가능 6글자이상 13글자 이하

	// 비밀번호 입력 유효성검사를위한 정규식
	// 숫자, 대소문자, 특수문자 입력가능(숫자, 소문자, 특수문자 1개이상 반드시 포함시켜야함) 6글자이상 13글자 이하
	var regPw = /^(?=.*[a-z])(?=.*\d)(?=.*[!@#$%^&*])[a-z\d!@#$%^&*]{6,13}$/;

	// 이메일 유효성검사를 하기위한 정규식
	var emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

	// memberID 값이 없으면 알림창 출력후 memberID 입력란에 포커스  false를 반환해 form제출을 못하게 막는코드 
	if (memberID.value == '') {
		console.log("아이디 입력");
		alert("아이디를 입력해주세요.");
		memberID.focus();
		return false;
	}
	// 만약 memberID의 값이 위에서 정의한 정규식패턴과 맞지않는다면 알람창출력 memberID 입력란에 포커스후 false를 반환해 form제출을 못하게 막음
	else if (!regId.test(memberID.value)) {
		console.log("아이디 형식");
		alert("아이디는 6~13자의 영문 소문자, 숫자로 입력해주세요.");
		memberID.focus();
		return false;
	}

	// 만약 idCheckStatus값이 0 이라면 안내문출력후 아이디 입력란에 포커스 false반환 form제출을 막음
	if (idCheckStatus == 0) {
		alert("ID중복검사를 확인해주세요\n아이디를 확인해주세요.");
		memberID.focus();
		return false;
	}

	//만약 memberPW값이 없다면 알람창 출력후 비밀번호 입력란에 포커스 false반환 form제출을 막음
	if (memberPW.value == '') {
		console.log("비밀번호 입력");
		alert('비밀번호를 입력해주세요.');
		memberPW.focus();
		return false;
	}
	// 만약 비밀번호입력값이 위에정의해둔 정규식값이 아니라면 알람창 출력후 비밀번호입력창에 포커스후 false반환 form제출을 막음
	else if (!regPw.test(memberPW.value)) {
		console.log("비밀번호 형식");
		alert("비밀번호는 6~13자의 영문 소문자, 숫자, 특수문자가 각각 최소 \n1개 이상 포함되어야 합니다.");
		memberPW.focus();
		return false;
	}



	// 만약 비밀번호확인 값이 없다면 알람창실행후 비밀번호확인 입력창에 포커스후 false반환 form제출막음    
	if (memberPWCK.value == '') {
		console.log("비밀번호 확인입력");
		alert('비밀번호 확인을 입력해주세요.');
		memberPWCK.focus();
		return false;
	}
	// 만약 비밀번호값과 , 비밀번호확인값이 같지않다면 알람창출력후 비밀번호확인 입력란에 포커스후 false반환 form제출을 막음    
	else if (memberPW.value != form.memberPWCK.value) {
		console.log("비번1 비번2 비교");
		alert('비밀번호, 비밀번호 확인이 동일하지 않습니다. \n다시입력해주세요.');
		//setTimeout(function() {
		memberPWCK.focus();
		//  }, 0);
		return false;
	}

	// 만약 이름입력란에 값이 없다면 알람창 출력후 이름입력란에 포커스후 false반환후 form제출을 막음    
	if (memberName.value == '') {
		console.log("이름 입력");
		alert('이름을 입력해주세요.');
		memberName.focus();
		return false;
	}
	// 만약 이름입력값이 위에서정의한 정규식코드와 같지않다면 알람창출력후 이름입력란에 포커스후 false반환 form제출을 막음
	else if (!regName.test(memberName.value)) {
		console.log("이름 형식");
		alert("2글자 이상 한글만 입력 가능 합니다. ");
		memberName.focus();
		return false;
	}


	// 성별 유효성 검사
	// 변수 gendetFlag 선언후 false값으로 초기화
	var genderFlag = false;
	for (var i = 0; i < form.memberGender.length; i++) { // for문을 사용하여 form객체 내의 memberGender 라는 이름을 가진 라디오버튼 그룹 각요소를 확인합니다
		if (form.memberGender[i].checked) { //만약 현재 라디오버튼이 선택되어있다면 genderFlag변수에 true값 저장후 반복문을 탈출합니다.
			genderFlag = true;
			break;
		}
	}

	// 만약 genderFlag 값이 여전히 false라면(라디오버튼이 선택되어있지 않을경우) 알람창 출력후
	if (!genderFlag) {
		console.log("성별 체크입력");
		alert("성별을 체크해주세요.");
		//setTimeout 함수를 사용하여 비동기적으로 첫 번째 라디오 버튼에 포커스를 맞춥니다.
		setTimeout(function () {
			memberGender[0].focus();
		}, 0);
		return false;
	}


	// 생년월일 year 유효성검사
	// 생년월일(year)입력란에 값이 없다면 안내문출력후 생년월일 입력란(year)에 포커스 false반환 form에 제출을 못하게 막음
	if (year.value == '') {
		console.log("생년월일 년도입력");
		alert('생년월일을 입력해주세요.');
		year.focus();
		return false;
	}
	// 만약 숫자가 아니거나 , 1900보다작거나 , 2024보다크면 안내문 출력후 생년월일(year)입력란에 포커스 false반환 form 제출 막음
	else if (isNaN(year.value) || year.value < 1900 || year.value > 2024) {
		console.log("올바른 년도입력");
		alert("올바른 연도를 입력해주세요.");
		year.focus();
		return false;
	}



	// 생년월일 day 유효성검사
	// 생년월일(day)입력란에 값이 없다면 안내문출력후 생년월일 입력란(day)에 포커스 후 false반환 form에 제출을 못하게 막음
	if (day.value == '') {
		console.log("생년월일 일입력");
		alert('생년월일을 입력해주세요.');
		day.focus();
		return false;
	}
	// 만약 숫자가 아니거나 , 1보다작거나 , 31보다크면 안내문 출력후 생년월일(day)입력란에 포커스 이동후 false반환 form 제출 막음
	else if (isNaN(day.value) || day.value < 1 || day.value > 31 || (day.value.length === 1 && day.value < 10)) {
		console.log("생년월일 올바른입력이 아닙니다");
		alert("올바른 일을 입력해주세요.");

		day.focus();
		return false;
	}



	// 휴대폰 PhNum1 유효성검사
	// 휴대폰 (PhNum1)입력란에 값이 없다면 안내문출력후 휴대폰 입력란(PhNum1)에 포커스 후 false반환 form에 제출을 못하게 막음
	if (phoneNumberPrefix.value == '') {
		console.log("휴대폰번호 입력");
		alert('휴대폰 번호를 입력해주세요.');
		phoneNumberPrefix.focus();
		return false;
	}
	// 만약 휴대폰입력(PhNum1)값이 /^01[016789]$/(정규식)조건이 아니라면 안내문구 출력후 휴대폰 입력란(PhNum1)에 포커스 후 false반환 form에 제출을 못하게 막음
	// /^01[016789]$/ 다음 정규식은 3자리중 0,1로 2자리고정후 마지막자리에 0,1,6,7,8,9 만입력가능하다는 정규식이다
	else if (!/^01[016789]$/.test(phoneNumberPrefix.value)) {
		console.log("휴대폰 앞번호 검사");
		alert("휴대폰 앞 번호는 [ 010 , 011 , 016 , 017 , 018 , 019 ] 중 에서만 입력 가능 합니다. ");
		phoneNumberPrefix.focus();
		return false;
	}


	// 휴대폰 PhNum2 유효성검사
	// 휴대폰 (PhNum2)입력란에 값이 없다면 안내문출력후 휴대폰 입력란(PhNum2)에 포커스 후 false반환 form에 제출을 못하게 막음
	if (phoneNumberMiddle.value == '') {
		console.log("휴대폰번호 중간 입력");
		alert('휴대폰 번호를 입력해주세요.');
		phoneNumberMiddle.focus();
		return false;
	}
	// 만약 휴대폰입력(PhNum2)값이 /^([0-9]{4})$/(정규식)조건이 아니라면 안내문구 출력후 휴대폰 입력란(PhNum2)에 포커스 후 false반환 form에 제출을 못하게 막음
	// /^([0-9]{4})$/ 다음 정규식은 0~9까지숫자만사용가능하고 4자리까지입력가능하다는 정규식이다
	else if (!/^([0-9]{4})$/.test(phoneNumberMiddle.value)) {
		console.log("휴대폰 번호 중간자리 숫자만 입력");
		alert("휴대폰 번호 숫자 4자리를 입력해주세요. ");
		phoneNumberMiddle.focus();
		return false;
	}


	// 휴대폰 PhNum3 유효성검사
	// 휴대폰 (PhNum3)입력란에 값이 없다면 안내문출력후 휴대폰 입력란(PhNum3)에 포커스 후 false반환 form에 제출을 못하게 막음
	if (phoneNumberSuffix.value == '') {
		console.log("휴대폰 번호 끝자리 입력");
		alert('휴대폰 번호를 입력해주세요.');
		phoneNumberSuffix.focus();
		return false;
	}

	// 만약 휴대폰입력(PhNum3)값이 /^([0-9]{4})$/(정규식)값이아니라면 안내문구 출력후 휴대폰 입력란(PhNum3)에 포커스 후 false반환 form에 제출을 못하게 막음
	// /^([0-9]{4})$/ 다음 정규식은 0~9까지숫자만사용가능하고 4자리까지입력가능하다는 정규식이다
	else if (!/^([0-9]{4})$/.test(phoneNumberSuffix.value)) {
		console.log("휴대폰 번호 끝자리 숫자만 입력");
		
		//인증번호 발송 상태 변수
		console.log(phNumCheckStatus);
		
		alert("휴대폰 번호 숫자 4자리를 입력해주세요. ");
		phoneNumberSuffix.focus();
		return false;
	}
	
	
	
	if(phNumCheckStatus == 0){
						alert("인증번호 발송 버튼을 눌러주세요.");
						console.log("인증번호 발송 버튼을 눌러주세요."+phNumCheckStatus)
						return false;					
					}
					
			
					
					
					if(phoneNumberCK.value ==''){
						alert("인증번호를 입력해주세요.");
						phoneNumberCK.focus();
						 console.log("인증번호를 입력해주세요"+authStatus);
						return false;
					}
					
					
						//authStatus = 0; 
				
					
				var phoneNumberCK = document.getElementById("phoneNumberCK").value;
				 	if(randNumPhNum != phoneNumberCK){
						console.log(" 인증번호 체크 유효성 실행 ");
						console.log(" randNumPhNum "+randNumPhNum);
						console.log(" phoneNumberCK "+phoneNumberCK);
						authStatus = 0; // 인증번호 확인버튼 초기화
						alert(" 인증번호가 옳바르지 않습니다. ");
						
						console.log(" 인증번호가 옳바르지 않습니다. ");
						console.log(authStatus);
						phNumCK.focus();
						
						return false;
					} 
					
					if(authStatus == 0){
				 		alert("인증번호를 확인해주세요.");
				 		console.log("인증번호를 확인해주세요");
						console.log(authStatus);
				 		return false;
				 	} 
					

	// 만약 이메일1과 이메일2 가 입력되어있지 않다면 안내문출력후 이메일 이메일 필드에 포커스
	if (!email1 || !email2) {
		console.log("이메일 1 , 2 입력");
		alert("이메일을 입력해주세요");
		emailField.focus();
		// 만약 이메일 1이 입력되지않았다면 이메일1에 포커스
		if (!email1) {
			emailField.focus();
		}
		// 아니라면 도메인 텍스트에 포커스
		else {
			emailTxt.focus();
		}
		// false반환후 폼제출 막음

		return false;
	}

	// 만약 fullEmail의 값이 위에서 정의한 정규식패턴과 맞지않는다면 알람창출력후 emailField 입력란에 포커스후 false를 반환해 form제출을 못하게 막음
	if (!emailRule.test(fullEmail)) {
		console.log("이메일 형식에 맞게 입력");
		alert("이메일을 형식에 맞게 입력해주세요.");
		emailField.focus();
		return false;
	}

	// 사용자가 입력한값이 .com 이나 .net가 아니라면 알림창 출력후 emailTxt에 포커스후 false반환해 form제출을 못하게 막음
	// 메서드 문자열에 특정문자, 숫자를 찾고 해당 위치를 반환함 만약 찾는 문자열이 없다면 -1반환
	if (emailTxt.indexOf('.com') === -1 && emailTxt.indexOf('.net') === -1) {
		console.log("도메인에 반드시 .com입력,net 입력");
		alert("도메인은 반드시 .com 또는 .net을 포함해야 합니다.");
		document.getElementById("domain-txt").focus();
		//emailTxt.focus();
		return false;
	}

	/* 2024-03-26 15:45 addressDAO작성되기 전까지 임시로 막아두는거임
	// adrsName(배송지 이름)입력되지 않았거나 배송지가 [ 집 , 회사 , 친구 ]가 아니라면 알림창 출력후 배송지(adrsName.focus)에 포커스 false반환해 form제출을 못하게 막음
	if (adrsName.value == '' || !['집', '회사', '친구'].includes(adrsName.value)) {
		console.log("배송지를 입력해주세요.\n[ 집, 회사, 친구 ] 중 입력해주세요.");
		alert('배송지를 입력해주세요.\n[ 집, 회사, 친구 ] 중 입력해주세요.');
		adrsName.focus();
		return false;
	}
	// 만약 우편번호가 입력되지않았다면 알림창출력후 우편번호 입력란에 포커스 false반환해 form제출을 못하게 막음
	if (sample4_postcode.value == '') {
		console.log("우편번호를 입력해주세요.");
		alert('우편번호를 입력해주세요.');
		sample4_postcode.focus();
		return false;
	}
	// 만약 도로명주소가 입력되지않았다면 알림창출력후 도로명주소 입력란에 포커스 false반환해 form제출을 못하게 막음
	if (sample4_roadAddress.value == '') {
		console.log('도로명주소를 입력해주세요.');
		alert('도로명주소를 입력해주세요.');
		sample4_roadAddress.focus();
		return false;
	}
	// 만약 지번주소가 입력되지않았다면 알림창출력후 지번주소 입력란에 포커스 false반환해 form제출을 못하게 막음
	if (sample4_jibunAddress.value == '') {
		alert('지번주소를 입력해주세요.');
		sample4_jibunAddress.focus();
		return false;
	}
	// 만약 상세주소가 입력되지않았다면 알림창출력후 상세주소 입력란에 포커스 false반환해 form제출을 못하게 막음
	if (sample4_detailAddress.value == '') {
		alert('상세주소를 입력해주세요.');
		sample4_detailAddress.focus();
		return false;
	}*/

	// 모든 조건이 충족되면 폼을 제출
	joinform.submit();

}

// 본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
function sample4_execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function (data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var roadAddr = data.roadAddress; // 도로명 주소 변수
			var extraRoadAddr = ''; // 참고 항목 변수

			// 법정동명이 있을 경우 추가한다. (법정리는 제외)
			// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
			if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
				extraRoadAddr += data.bname;
			}
			// 건물명이 있고, 공동주택일 경우 추가한다.
			if (data.buildingName !== '' && data.apartment === 'Y') {
				extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
			}
			// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
			if (extraRoadAddr !== '') {
				extraRoadAddr = ' (' + extraRoadAddr + ')';
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('sample4_postcode').value = data.zonecode;
			document.getElementById("sample4_roadAddress").value = roadAddr;
			document.getElementById("sample4_jibunAddress").value = data.jibunAddress;

			// 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
			if (roadAddr !== '') {
				document.getElementById("sample4_detailAddress").value = extraRoadAddr;
			} else {
				document.getElementById("sample4_detailAddress").value = '';
			}

			var guideTextBox = document.getElementById("guide");
			// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
			if (data.autoRoadAddress) {
				var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
				guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expAddr + ')';
				guideTextBox.style.display = 'block';

			} else if (data.autoJibunAddress) {
				var expJibunAddr = data.autoJibunAddress;
				guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
				guideTextBox.style.display = 'block';
			} else {
				guideTextBox.innerHTML = '';
				guideTextBox.style.display = 'none';
			}
		}
	}).open();
}