
jQuery(function($){

  $('#btn-login').click(function (event) {
    checkAccount();
  });
});



function checkAccount() {
    $.ajax({
        url: "http://localhost:8080/api/login",
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
                $('#messEmail').html(data.errors.email);
                $('#messPassword').html(data.errors.password);
            }else if (data.result==10 || data.result==20){
                $('#messError').html(data.message);
            }else {
                window.location.href = "http://localhost:8080/dashboard"
            }
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
            document.getElementById('messError').innerText = "Error from server";
        },
    })
}