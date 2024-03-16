// 버튼 클릭시 실행되는 함수
function selectcheckbox() {
	// 체크박스 체크된 것만 가져오기(복수선택 가능)
	var checkboxes = document.querySelectorAll('input[type=checkbox]:checked'); 
	var inputMax = document.querySelector('#maxPrice'); 
	var inputMin = document.querySelector('#minPrice'); 
	var lists = [];
	checkboxes.forEach(function(checkbox) {
		lists.push(checkbox.name); // 체크박스의 name 속성 값 가져오기
	});
	var max = inputMax.value;
	var min = inputMin.value;
	var categoryList = JSON.stringify(lists);
	selectProduct(categoryList, max, min);
}
// 통화 표기법을 바꿔주기 위한 함수
let won = Intl.NumberFormat('ko-KR', {
	style: 'currency',
	currency: 'KRW',
});
// 버튼 클릭 시 ajax를 실행시킬 함수
function selectProduct(categoryList, max, min) {
	// console.log(categoryList + "max:" + max + "min:" + min);
	$.ajax({
		type: "POST",
		url: "filter",
		data: {
			"categoryList": categoryList,
			"max": max,
			"min": min
		},
		dataType: "json",
		success: function(productDTO) {
			var product = $('div#store div.row');
			console.log("ajax요청 성공");
			console.log(productDTO);
			var elem = "";
			$.each(productDTO, function(index, product) {
				elem += `
					<div class="col-md-4 col-xs-6" style="margin-top: 30px;">
						        <div class="product">
						            <div class="product-body">
						                <div class="product-label" style="display: flex; justify-content: space-between; align-items: center;">
						                    <span class="new" style="color: #D10024;"><strong>NEW</strong></span>
						                    <div class="product-btns">
						                        <button class="add-to-wishlist" onclick="checkLogin()">
						                            <div class="productID" style="display:none;">${product.productID}</div>
						                            `
				if (product.isWished != 0) {
					elem += '<i class="fa fa-heart" id="heartIcon" onclick="isWished()"></i>'
				} else {
					elem += '<i class="fa fa-heart-o" id="heartIcon" onclick="isWished()" ></i>'
				}
				elem += `
						                            <span class="tooltipp">위시리스트에 추가</span>
						                        </button>
						                    </div>
						                </div>
						            </div>
						            <a href="productDetail.do?productID=${product.productID}&productCategory=${product.productCategory}">
						                <div class="product-img">
						                    <img src="${product.productImg}" alt="${product.productName}" />
						                </div>
						            </a>
						            <div class="product-body">
						                <p class="product-category">${product.productCategory}</p>
						                <h3 class="product-name" style="height: 31px;">
						                    <a href="productDetail.do?productID=${product.productID}&productCategory=${product.productCategory}">
						                       ${product.productName}
						                    </a>
						                </h3>
						                <h4 class="product-price">`
				elem += won.format(product.productPrice);
				elem += 
						                `</h4>
						                <div class="product-rating">
						                </div>
						            </div>
						        </div>
						    </div>`
			});
			// ajax요청 후 해제된 이벤트 리스너 재등록
			elem+=`	
				<script src="js/wishList/isWished.js"></script>
			`
			product.html(elem);
		},
		error: function(err) {
			var product = $('div#store div.row');
			console.log(err.status);
			hiddenPagination();
			var alertMessage = `<h3>검색결과가 없습니다</h3>`;
			product.html(alertMessage);
		}
	});
}
function hiddenPagination(){
	var page = document.querySelector('div.store-filter');
	page.setAttribute('style','display:none;')
}