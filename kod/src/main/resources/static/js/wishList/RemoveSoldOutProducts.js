document.getElementById('soldOutDelBtn').addEventListener('click', function() {
    // 품절 상품의 상품 ID 수집
    var soldOutProductIDs = Array.from(document.querySelectorAll('.product.sold-out'))
        .map(product => product.querySelector('.productID').innerText);

    // AJAX 요청을 보내어 품절 상품 삭제
    fetch('removeSoldOutProducts', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ productIDs: soldOutProductIDs }),
    })
    .then(response => response.json())
    .then(data => {
        // 응답에 따라 처리
        console.log(data);

        // 성공적으로 응답 받았을 때 페이지 리로드
        if (data.success) {
            // 페이지 리로드
            window.location.reload();
        }
    })
    .catch(error => console.error('에러:', error));
});

/*
[정현진]
해당 파일에서 실행하는
품절상품 삭제로직은
비동기적으로 처리해줄 필요가 굳이 없지만
배열을 주고받는 비동기처리 로직 학습을 위하여 구현된 기능입니다. 

*/