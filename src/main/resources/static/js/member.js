// Member API 객체
const MemberAPI = {
    getAll:       (part) => httpFetch('GET', part ? `/members?part=${encodeURIComponent(part)}` : '/members'),
    getById:      (id)   => httpFetch('GET', `/members/${id}`),
    createLion:   (data) => httpFetch('POST', '/members/lions',  data),
    createStaff:  (data) => httpFetch('POST', '/members/staffs', data),
    updateLion:   (id, data) => httpFetch('PUT', `/members/lions/${id}`,  data),
    updateStaff:  (id, data) => httpFetch('PUT', `/members/staffs/${id}`, data),
    delete:       (id)   => httpFetch('DELETE', `/members/${id}`)
};

async function createMember() {
    const role = document.getElementById('role-type').value;
    const base = {
        name:       document.getElementById('m-name').value,
        major:      document.getElementById('m-major').value,
        generation: parseInt(document.getElementById('m-generation').value),
        part:       document.getElementById('m-part').value
    };

    let res;
    if (role === 'lion') {
        res = await MemberAPI.createLion({ ...base, studentId: document.getElementById('m-studentId').value });
    } else {
        res = await MemberAPI.createStaff({ ...base, position: document.getElementById('m-position').value });
    }
    if (res) { showToast(`${res.name} 등록 완료!`, 'info'); loadMembers(); }
}

async function loadMembers() {
    const part = document.getElementById('m-filter-part')?.value;
    const data = await MemberAPI.getAll(part);
    if (!data) return;

    const tbody = data.map(m => `
        <tr>
            <td>${m.id}</td>
            <td>${m.name}</td>
            <td>${m.roleName}</td>
            <td>${m.major}</td>
            <td>${m.generation}기</td>
            <td>${m.part}</td>
            <td>${m.studentId || m.position || '-'}</td>
            <td>
                <button class="btn-edit" onclick="editMember(${m.id}, '${m.roleName}')">수정</button>
                <button class="btn-del"  onclick="deleteMember(${m.id})">삭제</button>
            </td>
        </tr>`).join('');

    document.getElementById('member-list').innerHTML = `
        <table>
            <thead><tr><th>ID</th><th>이름</th><th>역할</th><th>전공</th><th>기수</th><th>파트</th><th>학번/직책</th><th>관리</th></tr></thead>
            <tbody>${tbody || '<tr><td colspan="8">등록된 멤버가 없습니다.</td></tr>'}</tbody>
        </table>`;
}

async function deleteMember(id) {
    if (!confirm('삭제하시겠습니까?')) return;
    const res = await MemberAPI.delete(id);
    if (res !== null) { showToast('삭제 완료', 'info'); loadMembers(); }
}

async function editMember(id, roleName) {
    const m = await MemberAPI.getById(id);
    if (!m) return;
    const major = prompt('전공', m.major);
    const gen   = parseInt(prompt('기수', m.generation));
    const part  = prompt('파트', m.part);

    let res;
    if (roleName === '아기사자') {
        const studentId = prompt('학번', m.studentId);
        res = await MemberAPI.updateLion(id, { major, generation: gen, part, studentId });
    } else {
        const position = prompt('직책', m.position);
        res = await MemberAPI.updateStaff(id, { major, generation: gen, part, position });
    }
    if (res) { showToast('수정 완료', 'info'); loadMembers(); }
}
