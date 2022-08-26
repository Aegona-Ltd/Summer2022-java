$(document).ready(function () {
    loadData();
    accountName();
});

function loadData(page = 1) {
    $.ajax({
        url: "http://localhost:8080/api/contact?page="+page,
        type: "GET",
        headers: {
            'Authorization':'Bearer ' + getCookie("TOKEN")
        },
        success: function (rs) {
            var tableList = "";
            var data = rs.data;
            $.each(data, function (i, item) {
                tableList += '<tr>' +
                                '<th>'+ (i+1) +'</th>' +
                                '<td>'+ item.datatime +'</td>' +
                                '<td>'+ item.fullname +'</td>'+
                                '<td>'+ item.email +'</td>' +
                                '<td>'+ item.phone +'</td>' +
                                '<td>'+ item.subject +'</td>' +
                                '<td class="text-center">' +
                                    '<button class="btn btn-outline-success" data-bs-toggle="modal" data-bs-target="#view" onclick="viewContact('+item.id+')">View <i class="bi bi-card-list"></i></button> ' +
                                    '<button class="btn btn-outline-danger" onclick="deleteContact('+item.id+')">Delete <i class="bi bi-trash3-fill"></i></i></button>' +
                                '</td>' +
                             '</tr>';
            })
            $('#contact-list').html(tableList);
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

function deleteContact(id) {
    $.ajax({
        url: "http://localhost:8080/api/contact/" + id,
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
            }
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
            if (jqXHR.status==401) {
                let refreshToken = getCookie("REFRESHTOKEN");
                if (refreshToken!="") {
                    refreshToke(refreshToken);
                    deleteContact();
                }else {
                    window.location.href="http://localhost:8080/login"
                }
            }
        },
    })
}

function viewContact(id) {
    $.ajax({
        url: "http://localhost:8080/api/contact/" + id,
        type: "GET",
        headers: {
            'Authorization':'Bearer ' + getCookie("TOKEN")
        },
        dataType: 'json',
        success: function(rs) {
            var data = rs.contact;
            const dateTime = data.datatime.split("T");
            $('#date').html(dateTime[0])
            $('#time').html(dateTime[1])
            $('#labelModel').html(data.fullname)
            $('#email').html(data.email)
            $('#phone').html(data.phone)
            $('#subject').html(data.subject)
            $('#message').html(data.message)
            var elementLinkDownloadFile = document.getElementById('down-file');
            if (data.fileName!=null) {
                $('#down-file').html('File <i class="bi bi-file-earmark-arrow-down"></i>')
                elementLinkDownloadFile.setAttribute('href', 'http://localhost:8080/api/auth/down-file/' + data.id);
                elementLinkDownloadFile.classList.add("btn");
                elementLinkDownloadFile.classList.add("btn-primary");
            }else {
                $('#down-file').html("")
                elementLinkDownloadFile.classList.remove("btn");
                elementLinkDownloadFile.classList.remove("btn-primary");
            }
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception)
            if (jqXHR.status==401) {
                let refreshToken = getCookie("REFRESHTOKEN");
                if (refreshToken!="") {
                    refreshToke(refreshToken);
                    viewContact();
                }else {
                    window.location.href="http://localhost:8080/login"
                }
            }
        },
    })
}
