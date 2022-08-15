$(document).ready(function () {
    loadData();
    accountName();
});

function loadData() {
    $.ajax({
        url: "http://localhost:8080/api/contact",
        type: "GET",
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
                                    '<button class="btn btn-outline-success" data-bs-toggle="modal" data-bs-target="#view">View <i class="bi bi-card-list"></i></button> ' +
                                    '<button class="btn btn-outline-danger">Delete <i class="bi bi-trash3-fill"></i></i></button>' +
                                '</td>' +
                             '</tr>';
            })
            $('#contact-list').html(tableList);
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
            console.log(rs)
                $('#account-name').html("Name: " + rs.message);
            },
            error: function (jqXHR, exception) {
                console.log(jqXHR, exception);
            },
        })
}