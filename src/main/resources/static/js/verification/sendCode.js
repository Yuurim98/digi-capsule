document.addEventListener('DOMContentLoaded', () => {
  const sendVerificationCodeBtn = document.getElementById('sendVerificationCode');
  const emailInput = document.getElementById('email');

  sendVerificationCodeBtn.addEventListener('click', () => {
    const email = emailInput.value;

    if (!email) {
      alert('이메일 주소를 입력해주세요.');
      return;
    }

    fetch('/api/auth/verification-code', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ email: email })
    })
    .then(response => { // response를 받음
      if (!response.ok) {
        return response.json().then(error => {
          throw new Error(error.message || '인증번호 발송에 실패했습니다.');
        });
      }
      return response.json(); // response body를 JSON 형태로 파싱하는 Promise를 반환
    })
    .then(data => { // 파싱된 JSON 데이터를 받음
      console.log('서버 응답 데이터:', data);
      if (data.status === 'success' && data.code === 200) {
        alert('인증번호가 발송되었습니다. 이메일을 확인해주세요.');
      }
    })
  });
});