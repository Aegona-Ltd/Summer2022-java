function sendContact() {
    $.ajax({
        url: "https://private-a601d-logincontactform.apiary-mock.com/contact",
        type: "POST",
        data: JSON.stringify({
            fullname: $('#inputFullName').val(),
            email: $('#inputEmail').val(),
            phonenumber: $('#inputPhone').val(),
            subject: $('#inputSubject').val(),
            message: $('#textareaMessage').val()
        }),
        dataType: 'json',
        success: function(data) {
            toastr.success(
                'Thank you ' + $('#inputFullName').val(),
                'Send Success',
                {
                    timeOut: 1000,
                    fadeOut: 1000,
                    onHidden: function () {
                        window.location="../html/contactList.html";
                    }
                }
              );
            
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception)
            document.getElementById('mess-error').innerText = "Error from server";
        },
    })
}