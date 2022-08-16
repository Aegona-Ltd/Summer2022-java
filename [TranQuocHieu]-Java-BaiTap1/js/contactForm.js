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

function checkInputForm(form) {
    let name = document.getElementById('validation-fullname');
    let email = document.getElementById('validation-email');
    let phone = document.getElementById('validation-phone');
    let subject = document.getElementById('validation-subject');
    name.innerText="";
    email.innerText="";
    phone.innerText="";
    subject.innerHTML="";
    let check = false
    if (form.fullName.value.length==0) {
        name.innerText="Fullname is not null";
        document.getElementById("inputFullName").focus();
        check = true
    }
    const validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    if (form.email.value.length == 0){
        email.innerText="Email is not null";
        document.getElementById("inputEmail").focus();
        check = true
    }else if (!form.email.value.match(validRegex)){
        email.innerText="Invalid email";
        document.getElementById("inputEmail").focus();
        check = true
    }
    if (form.phone.value.length==0) {
        phone.innerText="Phone is not null";
        document.getElementById("inputPhone").focus();
        check = true
    }else if (isNaN(form.phone.value)) {
        phone.innerText="Phone is number";
        document.getElementById("inputPhone").focus();
        check = true
    }else if (form.phone.value.length > 11) {
        phone.innerText="Maximum length is 11";
        document.getElementById("inputPhone").focus();
        check = true
    }else if (form.phone.value.length <= 9) {
        phone.innerText="Minimum length is 10";
        document.getElementById("inputPhone").focus();
        check = true
    }
    if (form.subject.value.length==0) {
        subject.innerText="Subject is not null"
        document.getElementById("inputSubject").focus();
        check = true
    }
    if (!check) sendContact()
}