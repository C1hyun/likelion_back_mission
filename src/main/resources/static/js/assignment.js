// Assignment API 객체
const AssignmentAPI = {
    create:        (memberId, data) => httpFetch('POST', `/members/${memberId}/assignments`, data),
    getAll:        ()               => httpFetch('GET',  '/assignments'),
    getByMember:   (memberId)       => httpFetch('GET',  `/members/${memberId}/assignments`),
    getById:       (id)             => httpFetch('GET',  `/assignments/${id}`),
    search:        (keyword)        => httpFetch('GET',  `/assignments/search?keyword=${encodeURIComponent(keyword)}`),
    update:        (id, data)       => httpFetch('PUT',  `/assignments/${id}`, data),
    delete:        (id)             => httpFetch('DELETE', `/assignments/${id}`)
};

async function createAssignment() {
    const memberId = document.getElementById('a-memberId').value;
    const data = {
        title:       document.getElementById('a-title').value,
        description: document.getElementById('a-desc').value
    };
    const res = await AssignmentAPI.create(memberId, data);
    if (res) { showToast('과제 등록 완료!', 'info'); loadAllAssignments(); }
}

async function loadAllAssignments() {
    const data = await AssignmentAPI.getAll();
    if (data) renderAssignments(data);
}

async function loadAssignmentsByMember() {
    const memberId = document.getElementById('a-filterMemberId').value;
    const data = await AssignmentAPI.getByMember(memberId);
    if (data) renderAssignments(data);
}

async function getAssignment() {
    const id = document.getElementById('a-searchId').value;
    const data = await AssignmentAPI.getById(id);
    if (data) renderAssignments([data]);
}

async function searchAssignments() {
    const keyword = document.getElementById('a-keyword').value;
    const data = await AssignmentAPI.search(keyword);
    if (data) renderAssignments(data);
}

async function loadAssignmentForEdit() {
    const id = document.getElementById('a-editId').value;
    const data = await AssignmentAPI.getById(id);
    if (data) {
        document.getElementById('a-editTitle').value = data.title;
        document.getElementById('a-editDesc').value  = data.description;
    }
}

async function updateAssignment() {
    const id   = document.getElementById('a-editId').value;
    const data = {
        title:       document.getElementById('a-editTitle').value,
        description: document.getElementById('a-editDesc').value
    };
    const res = await AssignmentAPI.update(id, data);
    if (res) { showToast('수정 완료', 'info'); loadAllAssignments(); }
}

async function deleteAssignment() {
    const id = document.getElementById('a-editId').value;
    if (!confirm('삭제하시겠습니까?')) return;
    const res = await AssignmentAPI.delete(id);
    if (res !== null) { showToast('삭제 완료', 'info'); loadAllAssignments(); }
}

function renderAssignments(list) {
    const tbody = list.map(a => `
        <tr>
            <td>${a.id}</td>
            <td>${a.title}</td>
            <td>${a.description}</td>
            <td>${a.memberName} (ID: ${a.memberId})</td>
        </tr>`).join('');

    document.getElementById('assignment-list').innerHTML = `
        <table>
            <thead><tr><th>ID</th><th>제목</th><th>설명</th><th>작성자</th></tr></thead>
            <tbody>${tbody || '<tr><td colspan="4">과제가 없습니다.</td></tr>'}</tbody>
        </table>`;
}
