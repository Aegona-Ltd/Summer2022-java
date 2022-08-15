
jQuery(function($){

  $('#btn-submit-ContactUs').click(function (event) {
    sendContact();
  });
});

function sendContact() {
    $.ajax({
        url: "http://localhost:8080/api/contact",
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
            $('#messFullname').html("");
            $('#messEmail').html("");
            $('#messPhone').html("");
            $('#messSubject').html("");
            $('#mess').html("");
            if (data.result==90) {
                $('#messFullname').html(data.errors.fullname);
                $('#messEmail').html(data.errors.email);
                $('#messPhone').html(data.errors.phone);
                $('#messSubject').html(data.errors.subject);
                $('#mess').html(data.errors.mess);
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