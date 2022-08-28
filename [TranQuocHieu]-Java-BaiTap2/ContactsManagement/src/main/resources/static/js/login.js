jQuery(function($){
  $('#btn-login').click(function (event) {
    loginAccount();
  });
});

function checkAccount() {
    $.ajax({
        url: "http://localhost:8080/api/auth/login",
        type: "POST",
        contentType: "application/json;",
        dataType: "json",
        data: JSON.stringify({
            email: $('#email').val(),
            password: $('#password').val()
        }),
        success: function(data) {
            $('#messEmail').html("");
            $('#messPassword').html("");
            $('#messError').html("");
            if (data.result==90) {
                $('#messEmail').html(data.error.email);
                $('#messPassword').html(data.error.password);
            }else if (data.result==10 || data.result==20){
                $('#messError').html(data.message);
            }else {
                setCookie("USERNAME", data.username, 2)
                setCookie("TOKEN", data.token, 2)
                setCookie("REFRESHTOKEN", data.refreshToken, 7)
                window.location.href = "http://localhost:8080/dashboard"
            }
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
            document.getElementById('messError').innerText = "Error from server";
        },
    })
}

function loginAccount() {
    document.getElementById('messEmail').innerText = "";
    document.getElementById('messPassword').innerText = "";
    document.getElementById('messError').innerText = "";
    const validRegex = /^[a-zA-Z0-9.!#$%&*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    let check = false
    if (document.getElementById('email').value.length==0) {
        document.getElementById('messEmail').innerText = "Please enter your Email!!";
        document.getElementById('email').focus();
        check = true
    }else if (!document.getElementById('email').value.match(validRegex)) {
        document.getElementById('messEmail').innerText = "Invalid email";
        document.getElementById('email').focus();
        check = true
    }
    if (document.getElementById('password').value.length==0) {
        document.getElementById('messPassword').innerText = "Please enter your Password!!";
        document.getElementById('password').focus();
        check = true
    }
    return (check) ? "" : checkAccount();
}