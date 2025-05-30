document.addEventListener('DOMContentLoaded', () => {
  const sendVerificationBtn = document.getElementById('sendVerification');
  const emailInput = document.getElementById('email');
  const verificationCodeInput = document.getElementById('verificationCode');

  sendVerificationBtn.addEventListener('click', () => {
    const email = emailInput.value;
    const verificationCode = verificationCodeInput.value;

    if (!email) {
      alert('이메일 주소를 입력해주세요.');
      return;
    }

    if (!verificationCode) {
      alert('인증번호를 입력해주세요.');
      return;
    }

    fetch('/api/auth/verification', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ email: email, verificationCode: verificationCode })
    })
    .then(response => { // response를 받음
      if (!response.ok) {
        return response.json().then(error => {
          throw new Error(error.message);
        });
      }
      return response.json(); // response body를 JSON 형태로 파싱하는 Promise를 반환
    })
    .then(data => { // 파싱된 JSON 데이터를 받음
      console.log('서버 응답 데이터:', data);
      if (data.status === 'success' && data.code === 200) {
        alert('인증되었습니다.');
      }
    })
    .catch(error => {
      console.error('인증번호 검증 오류:', error);
      alert(error.message);
    });
  });
});