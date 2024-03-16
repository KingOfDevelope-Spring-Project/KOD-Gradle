
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