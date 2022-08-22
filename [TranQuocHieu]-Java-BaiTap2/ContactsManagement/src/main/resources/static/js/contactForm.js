
jQuery(function($){

  $('#btn-submit-ContactUs').click(function (event) {
    sendContact();
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
            mess: $('#textareaMessage').val()
        }),
        dataType: 'json',
        success: function(data) {
            console.log(data)
            $('#messFullname').html("");
            $('#messEmail').html("");
            $('#messPhone').html("");
            $('#messSubject').html("");
            $('#mess').html("");
            if (data.result==90) {
                $('#messFullname').html(data.error.fullname);
                $('#messEmail').html(data.error.email);
                $('#messPhone').html(data.error.phone);
                $('#messSubject').html(data.error.subject);
                $('#mess').html(data.error.mess);
            }else {
                toastr.success(
                    'Thank you ' + $('#inputFullName').val(),
                    'Send Success',
                    {
                        timeOut: 1000,
                        fadeOut: 1000,
                        onHidden: function () {
                            window.location.href ="http://localhost:8080/contacts";
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