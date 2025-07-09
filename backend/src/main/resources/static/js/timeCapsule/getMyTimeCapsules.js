document.addEventListener('DOMContentLoaded', () => {

  const capsulesContainer = document.getElementById('capsulesContainer');
  const loadingMessage = document.getElementById('loadingMessage');
  const paginationControls = document.getElementById('paginationControls');

  let currentPage = 0; // 현재 페이지 번호
  const pageSize = 8;  // 한 페이지에 표시할 항목 수


  // 타임캡슐 목록을 불러오는 함수
  function loadTimeCapsules(page) {
    currentPage = page;

    capsulesContainer.innerHTML = '';
    loadingMessage.style.display = 'block';

    fetch(`/api/capsules/my?page=${currentPage}&size=${pageSize}`)
    .then(response => {
      if (!response.ok) {
        return response.json().then(error => {
          const customError = new Error(error.message || '데이터를 불러오는 데 실패했습니다.');
          customError.code = error.code || response.status;
          throw customError;
        });
      }
      return response.json();
    })
    .then(responsePayload => {
      loadingMessage.style.display = 'none';
      console.log('서버 응답 데이터 (페이징):', responsePayload);

      const actualDataArray = responsePayload.data.content;
      const totalPages = responsePayload.data.totalPages;
      const currentPageNumber = responsePayload.number;

      if (Array.isArray(actualDataArray) && actualDataArray.length > 0) {
        actualDataArray.forEach(capsule => {
          const capsuleCard = document.createElement('div');
          capsuleCard.classList.add('col-md-3', 'mb-4');
          capsuleCard.innerHTML = `
              <div class="card shadow-sm h-100">
                <div class="card-header bg-primary text-white py-3">
                    <h5 class="mb-0">타임캡슐</h5>
                </div>
                <div class="card-body py-4" style="min-height: 80px; display: flex; flex-direction: column; justify-content: space-between;">
                    <h5 class="card-title text-primary">${capsule.title}</h5>
                    <p class="card-text mb-0">
                      <strong>열람일:</strong> ${new Date(capsule.viewDate).toLocaleDateString()}
                    </p>
                </div>
                <div class="card-footer text-end">
                    <a href="/timecapsule/${capsule.id}" class="btn btn-sm btn-outline-primary">자세히 보기</a>
                </div>
              </div>
            `;
          capsulesContainer.appendChild(capsuleCard);
        });
      } else {
        capsulesContainer.innerHTML = '<p class="text-center text-muted">아직 작성된 타임캡슐이 없습니다.</p>';
      }

      renderPaginationControls(totalPages, currentPageNumber);
    })
    .catch(error => {
      loadingMessage.style.display = 'none';
      console.error('타임캡슐 목록 조회 중 오류 발생:', error);
      alert(error.message);
      if (error.code === 401) {
        window.location.href = '/login';
      } else {
        capsulesContainer.innerHTML = `<p class="text-center text-danger">데이터를 불러오는 데 실패했습니다: ${error.message}</p>`;
      }
    });
  }

  // 페이징 버튼을 생성하고 렌더링하는 함수
  function renderPaginationControls(totalPages, currentPageNumber) {
    paginationControls.innerHTML = '';

    const prevItem = document.createElement('li');
    prevItem.classList.add('page-item');
    if (currentPageNumber === 0) {
      prevItem.classList.add('disabled');
    }
    prevItem.innerHTML = `<a class="page-link" href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>`;
    prevItem.addEventListener('click', (e) => {
      e.preventDefault();
      if (currentPageNumber > 0) {
        loadTimeCapsules(currentPageNumber - 1);
      }
    });
    paginationControls.appendChild(prevItem);

    for (let i = 0; i < totalPages; i++) {
      const pageItem = document.createElement('li');
      pageItem.classList.add('page-item');
      if (i === currentPageNumber) {
        pageItem.classList.add('active');
      }
      pageItem.innerHTML = `<a class="page-link" href="#">${i + 1}</a>`;
      pageItem.addEventListener('click', (e) => {
        e.preventDefault();
        loadTimeCapsules(i);
      });
      paginationControls.appendChild(pageItem);
    }

    const nextItem = document.createElement('li');
    nextItem.classList.add('page-item');
    if (currentPageNumber === totalPages - 1) {
      nextItem.classList.add('disabled');
    }
    nextItem.innerHTML = `<a class="page-link" href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>`;
    nextItem.addEventListener('click', (e) => {
      e.preventDefault();
      if (currentPageNumber < totalPages - 1) {
        loadTimeCapsules(currentPageNumber + 1);
      }
    });
    paginationControls.appendChild(nextItem);
  }

  loadTimeCapsules(0);

});