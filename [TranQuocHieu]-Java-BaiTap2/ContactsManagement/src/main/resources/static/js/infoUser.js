$(document).ready(function () {
    loadData();
    accountName1();
    accountName();
});

let userU = {}

function accountName1() {
    const username = getCookie("USERNAME");
    document.getElementById('username').innerHTML = username.toUpperCase();
}

function loadData() {
    $.ajax({
        url: "http://localhost:8080/api/users/info",
        type: "GET",
        headers: {
            'Authorization':'Bearer ' + getCookie("TOKEN")
        },
        success: function (rs) {
            const user = rs.data;
            userU = user;
            $("#email").html(user.email);
            let rolesUser = "";
            $.each(rs.data.roles, function(i, role) {
                rolesUser += role.name + " - ";
            })
            $("#roles").html(rolesUser.substring(0, rolesUser.length-3));
            let buttonEdit = '<button type="button" onclick="viewUpdate()" class="btn btn-success w-25 col-auto">Đổi thông tin</button>' +
                             '<button type="button" onclick="viewUpPassword()" class="btn btn-secondary w-25 col-auto">Đổi mật khẩu</button>';
            $('#btn-handle').html(buttonEdit);
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
            if (jqXHR.status==401) {
                let refreshToken = getCookie("REFRESHTOKEN");
                if (refreshToken!="") {
                    refreshToke(refreshToken);
                    loadData();
                }else {
                    window.location.href="http://localhost:8080/login"
                }
            }
        },
    })
}

function viewUpdate(user) {
    const htmlView = '<div class="card border-2 border-primary">' +
                        '<div class="card-header bg-primary">' +
                        '   <div class="row py-2"> ' +
                        '        <h3 class="fw-bold col text-white">Thông tin</h3> ' +
                        '    </div> ' +
                        '</div> ' +
                        '<div class="card-body"> ' +
                        '   <form class="row gy-3 px-2 gx-5"> ' +
                        '        <div class="col-6"> ' +
                        '            <div class="row"> ' +
                        '                <label for="inputName" class="form-label col-4">Name: <span class="text-danger">*</span></label> ' +
                        '                <input type="text" class="form-control col-8" name="name" id="inputName" ' +
                        '                       placeholder="Enter your Name"> ' +
                        '            </div> ' +
                        '            <p class="text-danger ps-3 m-0 fw-bold" id="messName"></p> ' +
                        '        </div>' +
                        '        <div class="col-6">' +
                        '            <div class="row">' +
                        '                <p class="form-label">Email: <span class="text-danger">*</span></p>' +
                        '                <p id="inputEmail">Hello</p>' +
                        '            </div>' +
                        '        </div>' +
                        '        <div><img th:src="@{/img/update.png}"></div>' +
                        '        <div class="col">' +
                        '            <div class="row justify-content-evenly">' +
                        '                <button type="button" onclick="handleUpdate()" id="btn-update-user" class="btn btn-success w-25 col-auto">Save</button>' +
                        '                <button type="button" onclick="handleCancel()" id="btn-cancel-user" class="btn btn-secondary w-25 col-auto">CANCEL</button>' +
                        '            </div>' +
                        '        </div>' +
                        '    </form>' +
                        '</div>' +
                    '</div>';
        $("#view-user").html(htmlView);
        $('#inputName').val(userU.name);
        $('#inputEmail').html(userU.email);
}

function viewUpPassword() {
    const htmlView = '<div class="card border-2 border-primary">' +
                            '<div class="card-header bg-primary">' +
                            '   <div class="row py-2"> ' +
                            '        <h3 class="fw-bold col text-white">Thông tin</h3> ' +
                            '    </div> ' +
                            '</div> ' +
                            '<div class="card-body"> ' +
                            '   <form class="row row-cols-1 gy-3 px-2 gx-5"> ' +
                            '        <div class="col"> ' +
                            '            <div class="row"> ' +
                            '                <label for="inputPassOld" class="form-label col-4">Password:</label> ' +
                            '                <input type="password" class="form-control col-8" name="inputPassOld" id="inputPassOld" ' +
                            '                       placeholder="Enter your Password"> ' +
                            '            </div> ' +
                            '            <p class="text-danger ps-3 m-0 fw-bold" id="messPass"></p> ' +
                            '        </div>' +
                            '        <div class="col"> ' +
                             '            <div class="row"> ' +
                             '                <label for="inputNewPass" class="form-label col-4">New Password:</label> ' +
                             '                <input type="password" class="form-control col-8" name="inputNewPass" id="inputNewPass" ' +
                             '                       placeholder="Enter your new password"> ' +
                             '            </div> ' +
                             '            <p class="text-danger ps-3 m-0 fw-bold" id="messNewPassword"></p> ' +
                             '        </div>' +
                             '         <div class="col"> ' +
                             '            <div class="row"> ' +
                             '                <label for="inputPassConfirm" class="form-label col-4">Password comfirm:</label> ' +
                             '                <input type="password" class="form-control col-8" name="name" id="inputPassConfirm" ' +
                             '                       placeholder="Enter your password confirm"> ' +
                             '            </div> ' +
                             '            <p class="text-danger ps-3 m-0 fw-bold" id="messPassConfirm"></p> ' +
                             '        </div>' +
                            '        <div class="col">' +
                            '            <div class="row justify-content-evenly">' +
                            '                <button type="button" onclick="handleSavePass()" class="btn btn-success w-25 col-auto">Save</button>' +
                            '                <button type="button" onclick="handleCancel()" class="btn btn-secondary w-25 col-auto">CANCEL</button>' +
                            '            </div>' +
                            '        </div>' +
                            '    </form>' +
                            '</div>' +
                        '</div>';
            $("#view-user").html(htmlView);
}


function handleCancel(){
    $("#view-user").html("");
}

function handleUpdate() {
    let checkERROR = false;
    if ($("#inputName").val().length==0) {
            $("#messName").html("Vui long nhap name!!");
            checkERROR = true;
    }else {
       $("#messName").html("");
    }
    (checkERROR) ? "": updateApiUser();
}

function updateApiUser() {
    $.ajax({
        url: "http://localhost:8080/api/users",
        type: "PUT",
        headers: {
            'Authorization':'Bearer ' + getCookie("TOKEN")
        },
        contentType: "application/json;",
        dataType: "json",
        data: JSON.stringify({
            id: userU.id,
            name: $('#inputName').val(),
            roles: userU.roles
        }),
        success: function(rs) {
            setCookie("USERNAME", $('#inputName').val(), 2)
            $("#view-user").html("");
            toastr.success(
                'UPDATE',
                'Success',
                {
                    timeOut: 1000,
                    fadeOut: 1000
                }
            );
            window.location.href="/info";

        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception)
            if (jqXHR.status==401) {
                let refreshToken = getCookie("REFRESHTOKEN");
                if (refreshToken!="") {
                    refreshToke(refreshToken);
                    updateApiUser();
                }else {
                    window.location.href="http://localhost:8080/login"
                }
            }
        },
    })
}

function setCookie(cname, cvalue, exdays) {
  const d = new Date();
  d.setTime(d.getTime() + (exdays*24*60*60*1000));
  let expires = "expires="+ d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function handleSavePass() {
    let errorInput = false;
    $('#messPass').html("")
    $('#messNewPassword').html("")
    $('#messPassConfirm').html("")
    if ($("#inputPassOld").val().length==0) {
       $('#messPass').html("Vui long nhap password!!")
       errorInput = true;
    }
    if ($("#inputNewPass").val().length==0) {
       $('#messNewPassword').html("Vui long nhap new password!!")
       errorInput = true;
    }
    if ($("#inputPassConfirm").val().length==0) {
       $('#messPassConfirm').html("Vui long nhap password comfirm!!")
       errorInput = true;
    }
    if ($("#inputNewPass").val() != $("#inputPassConfirm").val()) {
        $('#messPassConfirm').html("Password confirm khong dung!!!")
        errorInput = true;
    }

    (errorInput) ? "": updatePassAPI();
}

function updatePassAPI() {
    $.ajax({
        url: "http://localhost:8080/api/users/upPass",
        type: "PUT",
        headers: {
            'Authorization':'Bearer ' + getCookie("TOKEN")
        },
        contentType: "application/json;",
        dataType: "json",
        data: JSON.stringify({
            name: userU.name,
            email: userU.email,
            password: $('#inputPassOld').val(),
            comfirm: $('#inputNewPass').val()
        }),
        success: function (rs) {
            if (rs.result==10) {
                $('#messPass').html(rs.message)
            }else if (rs.result==90) {
                $('#messPass').html(rs.error.password)
                $('#messNewPassword').html(rs.error.confirm)
            }else {
                handleCancel();
                toastr.success(
                    'UPDATE',
                    'Success',
                    {
                        timeOut: 1000,
                        fadeOut: 1000
                    }
                );
            }
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
            if (jqXHR.status==401) {
                let refreshToken = getCookie("REFRESHTOKEN");
                if (refreshToken!="") {
                    refreshToke(refreshToken);
                    updatePassAPI();
                }else {
                    window.location.href="http://localhost:8080/login"
                }
            }
        },
    })
}