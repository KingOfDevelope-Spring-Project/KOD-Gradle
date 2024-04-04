    function previewImage(event) {
        var reader = new FileReader();
        var output = document.getElementById('imagePreview');
        var cancelButton = document.getElementById('cancelImageButton');

        reader.onload = function() {
            output.src = reader.result;
            cancelButton.style.display = "inline-block";

            // 이미지를 업로드한 경우 크기를 50%로 조절
            if (input.files.length !== 0) {
                output.style.width = "50%";
                output.style.height = "50%";
            }
        };

        var input = event.target;
        if (input.files.length === 0) {
            // 이미지를 업로드하지 않은 경우 크기를 40px로 조절
            output.src = "img/imagePreview.png";
            cancelButton.style.display = "none";
            output.style.width = "40px";
            output.style.height = "40px";
        } else {
            // 추가: 파일 크기 체크
            var fileSize = input.files[0].size; // 파일 크기 (바이트)
            var maxFileSize = 5 * 1024 * 1024; // 5MB

            if (fileSize > maxFileSize) {
                alert('파일 크기가 5MB를 초과합니다. 더 작은 파일을 선택해주세요.');
                // 선택한 파일 초기화
                input.value = '';
                return;
            }

            reader.readAsDataURL(input.files[0]);
        }
    }

	function setThumbnail(event) {
        for (var image of event.target.files) {
          var reader = new FileReader();

          reader.onload = function(event) {
            var img = document.createElement("img");
            img.setAttribute("src", event.target.result);
            document.querySelector("div#image_container").appendChild(img);
          };

          console.log(image);
          reader.readAsDataURL(image);
        }
      }

    function cancelImageUpload() {
        var input = document.getElementById('image');
        input.value = ''; // 파일 선택 취소

        var output = document.getElementById('image_container');
        output.innerHTML = '';

        var cancelButton = document.getElementById('cancelImageButton');
        cancelButton.style.display = "none";
    }

