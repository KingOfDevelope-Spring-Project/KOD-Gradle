function handleAddressManage() { //[조형련] 해당 회원의 배송지 정보를 보여주는 비동기처리
	console.log('배송지관리'); 
	$.ajax({
		type: "POST", 
		url: 'addressManageActionServlet', 
		dataType: "json", 
		success: function(data) { //[조형련] 비동기 처리가 정상적으로 끝났다면 Json타입으로 받아옴
			var contentHtml = ""; // 해당 데이터를 contentHtml에 배열횟수만큼 저장함
			var header= 
               `<header class="n-section-title">
                    <h1 class="tit">
                        배송지 관리 
                    </h1>`;
            // 배송지 개수가 5개를 초과하는 경우에만 추가 버튼 렌더링
            if (data.length < 5) {
                header += `<button id="insertButton_" onclick="openInsertModal()" class="n-btn w100 btn-sm btn-default cert-hidden" id="refund-account-btn" style="color: blue; float: right;">배송지 추가하기</button>`;
            }
            header += `</header>
                <table id="addressContainer" class="n-table table-row">
                </table>`;
				contentHtml += 
				`<colgroup>
						<col style="width: 20%">
						<col style="width: 1%">
						<col style="width: 50%">
				</colgroup>`;
			$.each(data, function(index, addressDTO) {
				contentHtml += 
					`<input type="hidden" name="adrsID" value="${addressDTO.adrsID}">
					<tbody>
						<tr>
							<th scope="row">${addressDTO.adrsName}</th>
							<td> </td>
							<td>
							<p>우편번호 : ${addressDTO.adrsZipcode}</p>
							<p>도로명주소 : ${addressDTO.adrsStreet} </p>
							<p>상세주소 : ${addressDTO.adrsDetail}</p>
							</td>
							<td><button id="modifyButton_" onclick="openModifyModal(${addressDTO.adrsID})" class="n-btn w100 btn-sm btn-default cert-hidden" id="refund-account-btn">수정</button>`
							if(data.length>1){ // 배송지 숫자가 1개인 경우에는 삭제버튼을 노출 시키지 않기 위한 조건
								contentHtml+= `<button id="deleteButton_" onclick="openDeleteModal(${addressDTO.adrsID})" class="n-btn w100 btn-sm btn-default cert-hidden" id="refund-account-btn" style="color:red;">삭제</button>`
							}
					contentHtml +=	
							`</td>
						</tr>
					</tbody>
				
					'<button id="modifyButton_" onclick="openModifyModal(' + addressDTO.adrsID + ')" style="background-color: #0fbcf9; color: white; border: none; margin-right:10px;">수정</button>`
				if(data.length>1){ // 배송지 숫자가 1개인 경우에는 삭제버튼을 노출 시키지 않기 위한 조건
					contentHtml +=`<button id="deleteButton_" onclick="openDeleteModal(${addressDTO.adrsID})" style="background-color: #00d8d6; color: white; border: none;">삭제</button>`
				}
					contentHtml +=
						`</div>
					</div>`
			});
			$('#address').html(header);
			$('#addressContainer').html(contentHtml); // 배송지 숫자가 5개보다 적은 경우에만 "배송지 추가하기 버튼 활성화"
			/*if (data.length < 5) {
				$('#addressContainer').append('<button id="insertButton_" onclick="openInsertModal()" class="n-btn w100 btn-sm btn-default cert-hidden" id="refund-account-btn" style="color : blue;">배송지 추가하기</button>');    
			} */		
		},
		error: function(error) {
			console.error(error);
		}
	});
	deleteCookie("addressManageClicked"); // 배송지 수정 및 추가를 진행하면, 쿠키가 생성되는데 생성된 쿠키를 자동으로 비워주는 함수
}
window.onload = function() { // 마이페이지에 들어왔을 때, 쿠키가 존재한다면 배송지관리 화면을 보여줌
	var addressManageCookie = getCookie("addressManageClicked");
	// 쿠키가 존재하는 경우에만 해당 태그의 클릭 이벤트를 실행
	if (addressManageCookie) { 
		handleAddressManage();
	}
	else{
		
	}
}
// 쿠키를 가져오는 함수
function getCookie(name) {
	var nameEQ = name + "=";
	var cookies = document.cookie.split(';');
	for (var i = 0; i < cookies.length; i++) {
		var cookie = cookies[i];
		while (cookie.charAt(0) === ' ') {
			cookie = cookie.substring(1, cookie.length);
		}
		if (cookie.indexOf(nameEQ) === 0) {
			return cookie.substring(nameEQ.length, cookie.length);
		}
	}
	return null;
}
// 쿠키를 생성하는 함수
function setCookie(name, value) {
					    var expires = "";
					    var date = new Date();
					    date.setTime(date.getTime() + (60 * 1000)); 
					    expires = "; expires=" + date.toUTCString();
					    document.cookie = name + "=" + value + expires + "; path=/";
					}
// 쿠키를 지우는 함수
function deleteCookie(name) {
	this.setCookie(name, "", -1);
}
//수정 버튼이 클릭됐을 때 실행되는 이벤트리스너
document.getElementById("modifyButton_").addEventListener("click", function() {
	openModal(addressDTO.adrsID);
});
//삭제 버튼이 클릭됐을 때 실행되는 이벤트 리스너
document.getElementById("deleteButton_").addEventListener("click", function() {
	openDeleteModal(addressDTO.adrsID);
});
// 배송지 추가 버튼이 클릭됐을 때 실행되는 이벤트 리스너
document.getElementById("insertButton_").addEventListener("click", function() {
	openInsertModal();
});		
			  //수정 함수가 호출되는 경우, 해당 모달창을 호출함
				function openModifyModal(adrsID) {
				    document.querySelector(".modal").classList.remove("hidden");
				  //console.log('[adrsID로그] ['+adrsID+']');
				    document.getElementById('ADRSID').value=adrsID;
				}
			  //삭제 함수가 호출되는 경우, 해당 모달창을 호출함	
				function openDeleteModal(adrsID) {
				    document.querySelector(".modalDelete").classList.remove("hidden");
				  //console.log('[adrsID로그] ['+adrsID+']');
				  //console.log('삭제 들어옴'); 
				  //adrsID 값을 ADRSID 필드에 설정
				    document.getElementById('ADRSID2').value=adrsID;
				}
				//추가 함수가 호출되는 경우, 해당 모달창을 호출함
				function openInsertModal(){
					document.querySelector(".modalInsert").classList.remove("hidden");
				  //console.log('[배송지 추가 들어옴]');
				}
				// 모달창을 닫기 위한 함수 : 각 모달창에서 x버튼이나 배경을 클릭하면 모달창을 닫아주는 기능
				function closeModal() {
					document.querySelector(".modal").classList.add("hidden");
					document.querySelector(".modalDelete").classList.add("hidden");
					document.querySelector(".modalInsert").classList.add("hidden");

					event.preventDefault();
				}
					document.querySelector(".bg").addEventListener("click", closeModal);
					document.querySelector(".bg2").addEventListener("click", closeModal);
				    document.querySelector(".bg3").addEventListener("click", closeModal);
				    
				//※ 쿠키를 사용하는 이유 : 사용자가 수정,삭제,추가 등을 진행했을 때 해당 화면에서 변경된 결과값을 자연스럽게 보여주기 위해서 사용함
				function addressUpdate(){ //배송지 수정 form을 submit하는 함수, 해당 함수가 실행되면 쿠키 생성함수가 같이 실행되면서 페이지에 쿠키 생성
					setCookie("addressManageClicked", true);
					document.getElementById('form1').submit();
			    }
				function addressDelete(){ //배송지 삭제 form을 submit하는 함수, 해당 함수가 실행되면 쿠키 생성함수가 같이 실행되면서 페이지에 쿠키 생성
					setCookie("addressManageClicked", true);
					document.getElementById('form2').submit();
				}
				function addressInsert(){ //배송지 추가 form을 submit하는 함수, 해당 함수가 실행되면 쿠키 생성함수가 같이 실행되면서 페이지에 쿠키 생성
					setCookie("addressManageClicked", true);
					document.getElementById('form3').submit();
				}
				
	function validateForm(event) {
    console.log('validateForm() 함수 호출됨');
    var form = document.getElementById('form3');
    var adrsNameValue = form.adrsName.value;
    var adrsDetailValue = form.adrsDetail.value;
 
    // 특수 문자 검사
    var regex = /^[a-zA-Z0-9가-힣]*$/;

    if (!regex.test(adrsNameValue) || !regex.test(adrsDetailValue)) {
        alert('주소지 이름 또는 상세주소에 공백이나 특수 문자를 사용할 수 없습니다.');
        event.preventDefault(); // 폼 제출 방지
    } else {
        addressInsert(); // 유효성 검사를 통과한 경우 addressInsert() 함수 호출
    }
}

	function validateForm2(event) {
    console.log('validateForm2() 함수 호출됨');
    var form = document.getElementById('form1');
    var adrsNameValue = form.adrsName.value;
    var adrsDetailValue = form.adrsDetail.value;

    // 특수 문자 검사
    var regex = /^[a-zA-Z0-9가-힣]*$/;

    if (!regex.test(adrsNameValue) || !regex.test(adrsDetailValue)) {
        alert('주소지 이름 또는 상세주소에 공백이나 특수 문자를 사용할 수 없습니다.');
        event.preventDefault(); // 폼 제출 방지
    } else {
        addressUpdate(); // 유효성 검사를 통과한 경우 addressInsert() 함수 호출
    }
}