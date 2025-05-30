document.addEventListener('DOMContentLoaded', () => {
  const passwordInput = document.getElementById('password');
  const confirmPasswordInput = document.getElementById('confirmPassword');
  const passwordMatchResult = document.getElementById('passwordMatchResult');

  function checkPasswordMatch() {
    if (confirmPasswordInput.value === '') {
      passwordMatchResult.textContent = ''; // 둘 다 비어있을 때는 메시지 숨김
      return;
    }

    if (passwordInput.value === confirmPasswordInput.value) {
      passwordMatchResult.textContent = '비밀번호가 일치합니다.';
      passwordMatchResult.style.color = 'green';
    } else {
      passwordMatchResult.textContent = '비밀번호가 일치하지 않습니다.';
      passwordMatchResult.style.color = 'red';
    }
  }

  passwordInput.addEventListener('input', checkPasswordMatch);
  confirmPasswordInput.addEventListener('input', checkPasswordMatch);
});