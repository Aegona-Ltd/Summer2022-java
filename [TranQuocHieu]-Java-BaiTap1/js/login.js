
function checkAccount() {
    $.ajax({
        url: "https://private-a601d-logincontactform.apiary-mock.com/GET",
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify({
            username: $('#email').val(),
            password: $('#password').val()
        }),
        success: function(data) {
            if(data.success) {
                window.location = "../html/dashboard.html";
            }else {
                document.getElementById('mess-error').innerText = data.message;
            }
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
            document.getElementById('mess-error').innerText = "Error from server";
        },
    })
}


function loginAccount(formLogin) {
    document.getElementById('validation-email').innerText = "";
    document.getElementById('validation-password').innerText = "";
    document.getElementById('mess-error').innerText = "";
    const validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

    let check = false
    if (formLogin.email.value.length==0) {
        document.getElementById('validation-email').innerText = "Email is not null";
        document.getElementById('email').focus();
        check = true
    }else if (!formLogin.email.value.match(validRegex)) {
        document.getElementById('validation-email').innerText = "Invalid email";
        document.getElementById('email').focus();
        check = true
    }
    if (formLogin.password.value.length==0) {
        document.getElementById('validation-password').innerText = "Password is not null";
        document.getElementById('password').focus();
        check = true
    }
    return (check) ? "" : checkAccount();
}   