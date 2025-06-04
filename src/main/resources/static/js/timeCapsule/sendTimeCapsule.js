document.addEventListener('DOMContentLoaded', () => {
  const submitButton = document.getElementById('sendTimeCapsule');

  const titleInput = document.getElementById("title");
  const contentInput = document.getElementById("content");
  const viewDateInput = document.getElementById("viewDate");
  const emailNotificationEnabledInput = document.getElementById("emailNotificationEnabled");

  submitButton.addEventListener('click', () => {
    const title = titleInput.value;
    const content = contentInput.value;
    const viewDate = viewDateInput.value;
    const isEmailNotificationEnabled = emailNotificationEnabledInput.checked;

    const CreateCapsuleReqDto = {
      title: title,
      content: content,
      viewDate: viewDate,
      isEmailNotificationEnabled: emailNotificationEnabledInput
    };

    fetch('/api/capsule', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(CreateCapsuleReqDto)
    })
    .then(response => {
      if (!response.ok) {
        return response.json().then(error => {
          const customError = new Error(error.message);
          customError.code = error.code;
          throw customError;
        });
      }
      return response.json();
    })
    .then(data => {
      console.log('서버 응답 데이터:', data);
      alert('타임캡슐이 성공적으로 저장되었습니다!');
      window.location.href = '/';
    })
    .catch(error => {
      console.error('타임캡슐 생성 중 오류 발생:', error);
      alert(error.message);
      if (error.code === 401) {
        window.location.href = '/login';
      }
    });
  });
});