let users = [];
getUserManager();
var _limitPage;
function getUserManager(pageChoose = 1, limitPage =3 ) {
    _limitPage = limitPage;
    $.ajax({
        url: "http://localhost:8080/account/pageable?page="+pageChoose+"&limit="+limitPage,
        type: "GET",
        contentType: "application/json;",
        success: function(data) {
            let response = "";
            let newData = data.listResults;
            users = newData;
            newData.forEach(el => {
                const user = el;
                response += `<tr>
                <td>${el.idUser}</td>
                <td>${el.userName}</td>
                <td>${el.fullName}</td>
                <td>${el.email}</td>
                <td>${el.password}</td>
                 <td>
                    <button class="btn btn-primary ml-2" onclick="deleteUser(${el.idUser})">Delete</button>
                    <button class="btn btn-primary ml-2" onclick="handleEditUser(${el.idUser})">Edit</button>
                 </td>
            </tr>`
            })
            document.getElementById("tbodyUsers").innerHTML = response;
            renderPaginationButton(data.totalPages);
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
        },
    })
}

// render pagination button
function renderPaginationButton(totalPages) {
    document.getElementById("pagination").innerHTML ="";
    for (let i = 0; i <= totalPages; i++) {
        let pageNumber = i + 1;
        document.getElementById("pagination").innerHTML +='<li  class=\"page-item\"><button onclick="getUserManager('+pageNumber+','+_limitPage+')"  class=\"page-link\" href=\"#\" >'+pageNumber+'</button></li>';
    }
}

// choose pagination page number

// delete user function
function deleteUser(id) {
    $.ajax({
        url: "http://localhost:8080/account/delete/"+id,
        type: "DELETE",
        contentType: "application/json;",
        success: function(data) {
            getUserManager();
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
        },
    })
}

// edit button
function handleEditUser(idUser) {
    const index = users.findIndex(el => el.idUser === idUser)
    // console.log(users[index]);
    document.getElementById("idEdit").value = users[index].idUser;
    document.getElementById("usernameEdit").value =  users[index].userName;
    document.getElementById("emailEdit").value =  users[index].email;
    document.getElementById("fullnamelEdit").value =  users[index].fullName;
    document.getElementById("passwordEdit").value =  users[index].password;

}

// edit infor user
function editInfoUser() {
    console.log('edit function');
    $.ajax({
        url: "http://localhost:8080/account",
        type: "PUT",
        contentType: "application/json;",
        data: JSON.stringify({
            idUser:$('#idEdit').val(),
            userName:$('#usernameEdit').val(),
            fullName: $('#fullnamelEdit').val(),
            email: $('#emailEdit').val(),
            password: $('#passwordEdit').val()
        }),
        dataType: 'json',
        success: function(data) {
            console.log(data);
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
        },
    })
}