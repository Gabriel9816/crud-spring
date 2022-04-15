function editStudent(id, name, email, password) {
    document.getElementById("editModalForm").action = "/student/update/" + id;
    document.getElementById("editName").value = name;
    document.getElementById("editEmail").value = email;
    document.getElementById("editPassword").value = password;
}

// function editTeacher(id, name, turma, email, password) {
function editTeacher(id, name, email, password) {
    document.getElementById("editModalTeacherForm").action = "/teacher/update/" + id;
    document.getElementById("editNameT").value = name;
	// document.getElementById("editturmaT").value = turma;
    document.getElementById("editEmailT").value = email;
    document.getElementById("editPasswordT").value = password;
}

function editTurma(id, name) {
    document.getElementById("editModalTurmaForm").action = "/turma/update/" + id;
    document.getElementById("editNameTurma").value = name;
}