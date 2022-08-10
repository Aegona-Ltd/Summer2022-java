$(document).ready(function () {
    loadData();
});

function loadData() {
    $.ajax({
        url: "https://private-58a9a5-contactformapi.apiary-mock.com/contacts",
        type: "GET",
        success: function (rs) {
            var tableList = "";
            console.log(rs)
            $.each(rs, function (i, item) {
                tableList += '<tr>' +
                                '<th>'+ (i+1) +'</th>' +
                                '<td>'+ item.data_time +'</td>' +
                                '<td>'+ item.fullname +'</td>'+
                                '<td>'+ item.email +'</td>' +
                                '<td>'+ item.phonenumber +'</td>' +
                                '<td>'+ item.subject +'</td>' +
                                '<td class="text-center">' +
                                    '<button class="btn btn-outline-success" onclick="'+ getContactId(item.id) +'" data-bs-toggle="modal" data-bs-target="#view">View <i class="bi bi-card-list"></i></button> ' +
                                    '<button class="btn btn-outline-danger">Delete <i class="bi bi-trash3-fill"></i></i></button>' +
                                '</td>' +
                             '</tr>';
            })
            $('#contact-list').html(tableList);
        }
    })
}

function addContact() {
    window.location="../html/contactForm.html";
}

function signout() {
    window.location="../html/login.html";
}

function getContactId(id) {
    $.ajax({
        url: "https://private-58a9a5-contactformapi.apiary-mock.com/contacts/?id=" + id,
        type: "GET",
        dataType: 'json',
        success: function(data) {
            const dataTime = data.data_time.split(" ");
            $('#date').html(dataTime[0])
            $('#time').html(dataTime[1])
            $('#labelModel').html(data.fullname)
            $('#name').html(data.fullname)
            $('#email').html(data.email)
            $('#phone').html(data.phonenumber)
            $('#subject').html(data.subject)
            $('#message').html(data.message)
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception)
        },
    })
}