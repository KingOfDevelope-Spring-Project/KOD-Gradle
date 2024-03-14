<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <style>
        /* 모달 스타일 */
        .modal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-image: url('uploads/uploads/zeni6.png'); /* 배경 이미지 경로 지정 */
            background-size: contain; /* 배경 이미지를 창에 맞게 조절 */
            background-position: center;
            justify-content: center;
            align-items: center;
        }
        
        .modal-content {
            background-color: rgba(255, 255, 255, 0.9); /* 배경 이미지 위에 레이어 추가 */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            text-align: center;
        }
        
    </style>
</head>
<body>
    <!-- 모달 -->
    <div id="errorModal" class="modal">
        <div class="modal-content">
            <h1>오류 발생!</h1>
            <p>ㅈㅅ</p>
            <p><a href="main.do">홈페이지로 이동</a></p>
        </div>
    </div>

    <!-- 스크립트 -->
    <script>
        // 모달 열기
        function openErrorModal() {
            document.getElementById('errorModal').style.display = 'flex';
        }

        // 모달 닫기
        function closeErrorModal() {
            document.getElementById('errorModal').style.display = 'none';
        }

        // 페이지 로드 시 모달 열기 (원하는 타이밍에 호출)
        window.onload = function() {
            openErrorModal();
        };
    </script>
</body>
</html>