function checkLogin() {
    // 여기서 로그인 여부를 확인하고 모달창을 띄울지 결정합니다.
    var memberID = document.getElementById('memberID').getAttribute('memberID');
	console.log('['+memberID+']');
	console.log(memberID==''? 0:1); /*★getAttribute는 해당 값이 없으면 null이 아닌 공백''으로 반환해준다.*/
    if (memberID == '') {
        var modal = document.getElementById('checkLoginModal');
        modal.style.display = 'block';

        // 확인 버튼에 이벤트 리스너 추가
        var confirmButton = document.getElementById('confirmButton');
        confirmButton.addEventListener('click', function () {
            // 확인 버튼을 눌렀을 때 login.do로 이동
            window.location.href = 'login.jsp';
        });

        // 취소 버튼에 이벤트 리스너 추가
        var cancelButton = document.getElementById('cancelButton');
        cancelButton.addEventListener('click', function () {
            // 취소 버튼을 눌렀을 때 모달창 닫기
            closeModal();
        });
    }
}

function closeModal() {
    var modal = document.getElementById('checkLoginModal');
    modal.style.display = 'none';
}

// 모달창 외부를 클릭하면 모달창이 닫히도록 설정
window.onclick = function (event) {
    var modal = document.getElementById('checkLoginModal');
    if (event.target == modal) {
        modal.style.display = 'none';
    }
}