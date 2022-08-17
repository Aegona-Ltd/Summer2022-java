$(document).ready(function () {
    loadData();
    accountName();
});

function loadData(page = 1) {
    //if (page==undefined) page = 1;
    $.ajax({
        url: "http://localhost:8080/api/contact?page="+page,
        type: "GET",
        success: function (rs) {
            var tableList = "";
            var data = rs.data.content;
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
            for (let i = 1; i <= rs.data.totalPages; i++) {
                pagi += '<li class="page-item" id="pagi-'+i+'">' +
                         '<button class="page-link" onclick="loadData('+i+')">'+i+'</button>' +
                         '</li>'
            }
            pagi += '<li class="page-item">' +
                    '<button class="page-link" onclick="loadData('+((page==rs.data.totalPages) ? rs.data.totalPages: page+1)+')">Next</button>'+
                    '</li>';
             $('#pagination').html(pagi);
             document.getElementById("pagi-" + rs.page).classList.add('active');
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
        },
    })
}

function accountName() {
    $.ajax({
        url: "http://localhost:8080/api/login/account",
        type: "GET",
        success: function (rs) {
            $('#account-name').html("Name: " + rs.message);
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
        },
    })
}

function deleteContact(id) {
    $.ajax({
        url: "http://localhost:8080/api/contact/" + id,
        type: "DELETE",
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
        },
    })
}

function viewContact(id) {
    $.ajax({
        url: "http://localhost:8080/api/contact/" + id,
        type: "GET",
        dataType: 'json',
        success: function(rs) {
            var data = rs.data[0];
            const dataTime = data.datatime.split("T");
            $('#date').html(dataTime[0])
            $('#time').html(dataTime[1])
            $('#labelModel').html(data.fullname)
            $('#name').html(data.fullname)
            $('#email').html(data.email)
            $('#phone').html(data.phone)
            $('#subject').html(data.subject)
            $('#message').html(data.message)
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception)
        },
    })
}