// 페이지가 로드된 후 실행될 스크립트
$(document).ready(function() {
    // 페이지 버튼을 눌렀을 때 페이지로 이동
    $('ul.reviews-pagination a').on('click', function(event) {
        event.preventDefault(); // 기본 동작(링크 이동)을 막음

        // 이동할 페이지의 주소를 가져옴
        var pageUrl = $(this).attr('href');

        // 페이지로 바로 이동
        window.location.href = pageUrl;
    });

    // 페이지 이동이 완료된 후에 스크롤 실행
    var pageParameter = getUrlParameter('page');
    if (pageParameter) {
        var target = $('ul.tab-nav');
        console.log("[로그:정현진] 스크롤 실행!");
        $('html, body').animate({
            scrollTop: target.offset().top
        }, 500); // 500은 스크롤 속도(ms)를 나타냄
    }
});

// URL에서 파라미터 값을 가져오는 함수
function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    var results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
};
