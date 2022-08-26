$(document).ready(function () {
    loadData();
    accountName();
    loadheader('users');
});

var dataUser = {};

function loadData(page = 1) {
    $.ajax({
        url: "http://localhost:8080/api/users?page="+page,
        type: "GET",
        headers: {
            'Authorization':'Bearer ' + getCookie("TOKEN")
        },
        success: function (rs) {

            var tableList = "";
            var data = rs.data;

            $.each(data, function (i, item) {
                let roles = "";
                $.each(item.roles, function(i, role){
                    roles += role.name + " - ";
                })
                roles = roles.substring(0, roles.length-3)
                tableList += '<tr>' +
                                '<th>'+ (i+1) +'</th>' +
                                '<td>'+ item.name +'</td>' +
                                '<td>'+ item.email +'</td>' +
                                '<td class="text-center">'+ roles +'</td>' +
                                '<td class="text-center">' +
                                    '<button class="btn btn-outline-success" onclick="callApiUserId('+ item.id +')">View <i class="bi bi-card-list"></i></button> ' +
                                    '<button class="btn btn-outline-danger" onclick="deleteApiUser('+item.id+')">Delete <i class="bi bi-trash3-fill"></i></i></button>' +
                                '</td>' +
                             '</tr>';
            })
            $('#user-list').html(tableList);
            let pagi = "";
            pagi += '<li class="page-item">' +
                        '<button class="page-link" onclick="loadData('+((page==1) ? 1: page-1)+')">Previous</button>' +
                    '</li>'
            for (let i = 1; i <= rs.totalPages; i++) {
                pagi += '<li class="page-item" id="pagi-'+i+'">' +
                         '<button class="page-link" onclick="loadData('+i+')">'+i+'</button>' +
                         '</li>'
            }
            pagi += '<li class="page-item">' +
                    '<button class="page-link" onclick="loadData('+((page==rs.totalPages) ? rs.totalPages: page+1)+')">Next</button>'+
                    '</li>';
             $('#pagination').html(pagi);
             document.getElementById("pagi-" + rs.page).classList.add('active');
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

function deleteApiUser(id) {
    $.ajax({
        url: "http://localhost:8080/api/users/" + id,
        type: "DELETE",
        headers: {
            'Authorization':'Bearer ' + getCookie("TOKEN")
        },
        success: function (rs) {
            if (rs.result===0) {

                toastr.success(
                    'Delete',
                    'Success',
                    {
                        timeOut: 1000,
                        fadeOut: 1000
                    }
                );
                loadData();
                handleCancel();

            }
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
            if (jqXHR.status==401) {
                let refreshToken = getCookie("REFRESHTOKEN");
                if (refreshToken!="") {
                    refreshToke(refreshToken);
                    deleteApiUser();
                }else {
                    window.location.href="http://localhost:8080/login"
                }
            }
        },
    })
}

function callApiUserId(id) {
    $.ajax({
        url: "http://localhost:8080/api/users/" + id,
        type: "GET",
        headers: {
            'Authorization':'Bearer ' + getCookie("TOKEN")
        },
        dataType: 'json',
        success: function(rs) {
            dataUser = rs;
            viewUser(rs.roles, rs.data.id);
            getDataInUserById(rs.data, rs.roles);
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception)
            if (jqXHR.status==401) {
                let refreshToken = getCookie("REFRESHTOKEN");
                if (refreshToken!="") {
                    refreshToke(refreshToken);
                    callApiUserId();
                }else {
                    window.location.href="http://localhost:8080/login"
                }
            }
        },
    })
}

function updateApiUser(idUser, rolesUser) {
    $.ajax({
        url: "http://localhost:8080/api/users",
        type: "PUT",
        headers: {
            'Authorization':'Bearer ' + getCookie("TOKEN")
        },
        contentType: "application/json;",
        dataType: "json",
        data: JSON.stringify({
            id: idUser,
            name: $('#inputName').val(),
            roles: rolesUser
        }),
        success: function(rs) {
            loadData();
            $("#view-user").html("");
            toastr.success(
                'UPDATE',
                'Success',
                {
                    timeOut: 1000,
                    fadeOut: 1000
                }
            );
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

function viewUser(roles, id) {
    const htmlView = '<div class="card border-2 border-primary" id="show-view-user">' +
                    '   <div class="card-header bg-primary">' +
                    '       <div class="row py-2"> ' +
                    '           <h3 class="fw-bold col text-white">Thông tin</h3> ' +
                    '       </div> ' +
                    '   </div> ' +
                    '   <div class="card-body"> ' +
                    '       <form class="row gy-3 px-2 gx-5"> ' +
                    '           <div class="col-6"> ' +
                    '               <div class="row"> ' +
                    '                   <label for="inputName" class="form-label col-4">Name: <span class="text-danger">*</span></label> ' +
                    '                   <input type="text" class="form-control col-8" name="name" id="inputName" ' +
                    '                       placeholder="Enter your Name"> ' +
                    '               </div> ' +
                    '            <p class="text-danger ps-3 m-0 fw-bold" id="messName"></p> ' +
                    '        </div>' +
                    '        <div class="col-6">' +
                    '            <div class="row">' +
                    '                <p class="form-label">Email: <span class="text-danger">*</span></p>' +
                    '                <p id="inputEmail"></p>' +
                    '            </div>' +
                    '        </div>' +
                    '        <div class="col-6">' +
                    '            <p>Role: </p>' +
                                setCheckBox(roles) +
                    '        <p class="text-danger ps-3 m-0 fw-bold" id="messRole"></p>' +
                    '        <div class="col-6"><img th:src="@{/img/update.png}"/></div>' +
                    '        <div class="col">' +
                    '            <div class="row justify-content-evenly">' +
                    '                <button type="button" onclick="handleUpdate('+id+')" id="btn-update-user" class="btn btn-success w-25 col-auto">UPDATE</button>' +
                    '                <button type="button" onclick="handleCancel()" id="btn-cancel-user" class="btn btn-secondary w-25 col-auto">CANCEL</button>' +
                    '            </div>' +
                    '        </div>' +
                    '    </form>' +
                    '</div>' +
                    '</div>';
    $("#view-user").html(htmlView);
}

function getDataInUserById(dataUser, roles) {
    $("#inputName").val(dataUser.name);
    $("#inputEmail").html(dataUser.email);
    $.each(dataUser.roles, function(i, role) {
        let idCheck = "#check-" + role.id;
        $(idCheck).prop('checked', true);
    })
}

function setCheckBox(roles) {
    let htmlCheckBox = "";
    $.each(roles, function(i, role) {
        htmlCheckBox += '<div class="form-check">' +
             '                <input class="form-check-input" type="checkbox" value="" id="check-'+role.id+'">' +
             '                <label class="form-check-label" for="check-'+role.id+'">' + role.name +
             '                </label>' +
             '            </div>';
    })
    return htmlCheckBox;
}

function rolesCheck(roles) {
    let rolesCheckUser = [];
    $.each(roles, function(i, role) {
            let idCheck = "#check-" + role.id;
            if( $(idCheck).is(':checked') ){
                rolesCheckUser.push(role);
            }
        })
    return rolesCheckUser;
}

function handleUpdate(id) {
    let checkERROR = false;
    let rolesList = rolesCheck(dataUser.roles);
    if ($("#inputName").val().length==0) {
        $("#messName").html("Vui long nhap name!!");
        checkERROR = true;
    }else {
        $("#messName").html("");
    }
    if (rolesList.length==0) {
        $("#messRole").html("Vui long chon 1 role");
        checkERROR = true;
    }
    (checkERROR) ? "": updateApiUser(id, rolesList);
}

function handleCancel() {
    $("#view-user").html("");
}