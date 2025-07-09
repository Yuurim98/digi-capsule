document.addEventListener('DOMContentLoaded', () => {
  const sendRegisterBtn = document.getElementById('sendRegister');

  const emailInput = document.getElementById('email');
  const verificationCodeInput = document.getElementById('verificationCode');
  const passwordInput = document.getElementById('password');
  const nicknameInput = document.getElementById('nickName');
  const phoneNumberInput = document.getElementById("phoneNumber");

  sendRegisterBtn.addEventListener('click', () => {
    const email = emailInput.value;
    const verificationCode = verificationCodeInput.value;
    const password = passwordInput.value;
    const nickname = nicknameInput.value;
    const phoneNumber = phoneNumberInput.value;

    if (!email) {
      alert('이메일 주소를 입력해주세요.');
      return;
    }

    if (!verificationCode) {
      alert('인증번호를 입력해주세요.');
      return;
    }

    if (!password) {
      alert('비밀번호를 입력해주세요.');
      return;
    }

    if (!nickname) {
      alert('닉네임을 입력해주세요.');
      return;
    }

    if (!phoneNumber) {
      alert('휴대폰 번호를 입력해주세요.');
      return;
    }

    fetch('/api/auth/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        email: email,
        password: password,
        nickName: nickname,
        phoneNumber: phoneNumber,
      })
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
        alert(data.message);
        window.location.href = '/login';
      }
    })
    .catch(error => {
      console.error('회원가입 오류:', error);
      alert(error.message);
    });
  });
});