document.addEventListener('DOMContentLoaded', () => {

  const capsulesContainer = document.getElementById('capsulesContainer');
  const loadingMessage = document.getElementById('loadingMessage');

  capsulesContainer.innerHTML = '';
  loadingMessage.style.display = 'block';

  fetch('/api/capsules/my')
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
  .then(responsePayload => {
    loadingMessage.style.display = 'none';
    console.log('서버 응답 데이터:', responsePayload);
    const actualDataArray = responsePayload.data;

    if (actualDataArray && actualDataArray.length > 0) {
      actualDataArray.forEach(capsule => {
        const capsuleCard = document.createElement('div');
        capsuleCard.classList.add('col-md-3', 'mb-4');
        capsuleCard.innerHTML = `
            <div class="card shadow-sm h-100">
              <div class="card-header bg-primary text-white py-3">
                  <h5 class="mb-0">타임캡슐</h5>
              </div>
               <div class="card-body py-4" style="min-height: 80px; display: flex; flex-direction: column; justify-content: space-between;">
                  <h5 class="card-title">${capsule.title}</h5>
                  <p class="card-text mb-0">
                    <strong>열람일:</strong> ${new Date(
            capsule.viewDate).toLocaleDateString()}
                  </p>
              </div>
              <div class="card-footer text-end">
                  <a href="/timecapsule/${capsule.id}" class="btn btn-sm btn-outline-primary">자세히 보기</a>
              </div>
            </div>
          `;
        capsulesContainer.appendChild(capsuleCard);
      })
    }
  })
  .catch(error => {
    loadingMessage.style.display = 'none';
    console.error('타임캡슐 목록 조회 중 오류 발생:', error);
    alert(error.message);
    if (error.code === 401) {
      window.location.href = '/login';
    }
  });
});