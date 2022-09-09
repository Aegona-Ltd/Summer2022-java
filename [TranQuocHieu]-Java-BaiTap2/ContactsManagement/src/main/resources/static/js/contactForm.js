jQuery(function($){

  $('#btn-submit-ContactUs').click(function (event) {
    checkInputForm()
  });
});

function sendContact() {
    $.ajax({
        url: "http://localhost:8080/api/auth/contact",
        type: "POST",
        contentType: "application/json;",
        data: JSON.stringify({
            fullname: $('#inputFullName').val(),
            email: $('#inputEmail').val(),
            phone: $('#inputPhone').val(),
            subject: $('#inputSubject').val(),
            mess: $('#textareaMessage').val(),
            keyRecapcha: ""
        }),
        dataType: 'json',
        success: function(data) {
            $('#messFullname').html("");
            $('#messEmail').html("");
            $('#messPhone').html("");
            $('#messSubject').html("");
            $('#mess').html("");
            if (data.result==-1) {
                $('#messFullname').html(data.error.fullname);
                $('#messEmail').html(data.error.email);
                $('#messPhone').html(data.error.phone);
                $('#messSubject').html(data.error.subject);
                $('#mess').html(data.error.mess);
            }else {
                if ($("#formFile")[0].files[0]!=undefined) {
                    uploadFileAPI(data.result);
                };
                toastr.success(
                    'Thank you ' + $('#inputFullName').val(),
                    'Send Success',
                    {
                        timeOut: 1000,
                        fadeOut: 1000,
                        onHidden: function () {
                            window.location.href ="http://localhost:8080/login";
                        }
                    }
                );
            }
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
        },
    })
}

function uploadFileAPI(contactId) {
    var data = new FormData();
    data.append('file', $("#formFile")[0].files[0])
    $.ajax({
        url: "http://localhost:8080/api/auth/upload?id="+contactId,
        type: "POST",
        contentType: false,
        cache: false,
        processData: false,
        data: data,
        success: function(data) {
            console.log(data)
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
        },
    })
}

function checkInputForm() {
    $('#messFullname').html("");
    $('#messEmail').html("");
    $('#messPhone').html("");
    $('#messSubject').html("");
    $('#mess').html("");
    let check = false

    let fullname = $('#inputFullName').val()
    let email = $('#inputEmail').val()
    let phone = $('#inputPhone').val()
    let subject = $('#inputSubject').val()
    let mess = $('#textareaMessage').val()

    if (fullname.length==0) {
        $('#messFullname').html("Please enter Fullname");
        document.getElementById("inputFullName").focus();
        check = true
    }
    const validRegex = /^[a-zA-Z0-9.!#$%&*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    if (email.length == 0){
        $('#messEmail').html("Please enter Email");
        document.getElementById("inputEmail").focus();
        check = true
    }else if (!email.match(validRegex)){
        $('#messEmail').html("Invalid email");
        document.getElementById("inputEmail").focus();
        check = true
    }
    if (phone.length==0) {
        $('#messPhone').html("Please enter Phone");
        document.getElementById("inputPhone").focus();
        check = true
    }else if (isNaN(phone)) {
        $('#messPhone').html("Phone is number");
        document.getElementById("inputPhone").focus();
        check = true
    }else if (phone.length != 10) {
        $('#messPhone').html("Phone length is 10");
        document.getElementById("inputPhone").focus();
        check = true
    }
    if (subject.length==0) {
        $('#messSubject').html("Please enter Subject");
        document.getElementById("inputSubject").focus();
        check = true
    }
    if (mess.length==0) {
        $('#mess').html("Please enter Message");
        document.getElementById("textareaMessage").focus();
        check = true
    }
    var response = grecaptcha.getResponse();
    if (response.length==0) {
        $('#messRecapcha').html("Please verify that you are not a robot.")
        check = true
    }else {
        $('#messRecapcha').html("")
    }
    if ($("#formFile")[0].files[0]!=undefined && $("#formFile")[0].files[0].size > 1048576) {
        $('#messFile').html("Accept File < 1MB");
        check = true
    }else {
        $('#messFile').html("");
    }
    if (!check) sendContact();
}

