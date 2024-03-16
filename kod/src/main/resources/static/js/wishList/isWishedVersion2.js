  $(".add-to-wishlist2").on("click", function(){ /*[정현진](찜기능 심화버전)하나의 함수에서 2가지 기능을 실행*/
    console.log("[로그:정현진] 위시리스트 버튼 클릭됨");
    
    var productID = $(this).find(".productID").text();
    var heartIcon = $(this).find("#heartIcon");
    console.log("productID", productID);
    
    $.ajax({ /*찜기능*/
      type: "POST",
      url: "isWishedServlet",
      data: {"productID": productID},
      success: function(data){
        console.log(data);
        heartIcon.toggleClass('fa-heart-o fa-heart');
        
        var updatedWishListCnt = parseInt(data);
        $(".wishListCnt").text(updatedWishListCnt);
        console.log("[로그:정현진] updatedWishListCnt >> " + updatedWishListCnt);
        
	    $.ajax({ /*상품의 찜 합계수량 비동기 반응*/
		      type: "POST",
		      url: "wishTotalCntServlet",
		      data: {"productID": productID},
		      success: function(data){
		        console.log(data);

		        var updatedwishTotalCnt = parseInt(data);
		        $(".wishTotalCnt").text(updatedwishTotalCnt); // 상품의 찜 합계수량
		        console.log("[로그:정현진] updatedwishTotalCnt >> " + updatedwishTotalCnt);

		      },
		      error: function(error){
		        console.log("에러: " + error);
		      } 
		    });
      },
      error: function(error){
        console.log("에러: " + error);
      } 
    });
  });




