$(document).ready(function () {
    loadData();
    accountName();
    loadheader('contacts');
});

function loadData(page = 1, size = 5) {
    $.ajax({
        url: "http://localhost:8080/api/contact?page="+page+"&size="+size,
        type: "GET",
        headers: {
            'Authorization':'Bearer ' + getCookie("TOKEN")
        },
        success: function (rs) {
            var tableList = "";
            var data = rs.data;
            let stt = (page-1) * rs.size;
            $.each(data, function (i, item) {
                tableList += '<tr>' +
                                '<th>'+ (i+stt+1) +'</th>' +
                                '<td>'+ item.dateTime +'</td>' +
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
                        '<button class="page-link" onclick="loadData('+((page==1) ? 1: page-1)+','+ selectedValue +')">Previous</button>' +
                    '</li>'
            for (let i = 1; i <= rs.totalPages; i++) {
                pagi += '<li class="page-item" id="pagi-'+i+'">' +
                         '<button class="page-link" onclick="loadData('+i+','+ selectedValue +')">'+i+'</button>' +
                         '</li>'
            }
            pagi += '<li class="page-item">' +
                    '<button class="page-link" onclick="loadData('+((page==rs.totalPages) ? rs.totalPages: page+1)+','+ selectedValue +')">Next</button>'+
                    '</li>';
             $('#pagination').html(pagi);
             document.getElementById("pagi-" + rs.page).classList.add('active');
             $('#download-excel').html('<i class="bi bi-cloud-arrow-down-fill"></i> Download to Excel')
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
            if (jqXHR.status==403) {
                window.location.href="http://localhost:8080/dashboard"
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
            console.log(rs)
            var data = rs.contact;
            const dateTime = data.dateTime.split(" ");
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
        }
    })
}
var selectedValue = 5;
function selectSize() {
    var selectBox = document.getElementById("selectSize");
    selectedValue = selectBox.options[selectBox.selectedIndex].value;
    loadData(1, selectedValue)
}
