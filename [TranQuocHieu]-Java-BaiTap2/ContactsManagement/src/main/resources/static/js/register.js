
jQuery(function($){

  $('#btn-resgister').click(function (event) {
    checkAccount();
  });
});



function addUserAPI() {
    $.ajax({
        url: "http://localhost:8080/api/auth/register",
        type: "POST",
        contentType: "application/json;",
        dataType: "json",
        data: JSON.stringify({
            name: $('#inputname').val(),
            email: $('#email').val(),
            password: $('#password').val(),
            comfirm: $('#passwordConfirm').val()
        }),
        success: function(data) {
            $('#messError').html("");
            $('#messName').html("");
            $('#messEmail').html("");
            $('#messPassword').html("");
            $('#messPasswordConfirm').html("");
            $('#messCheckBox').html("");
            if (data.result==90) {
                $('#messName').html(data.error.name);
                $('#messEmail').html(data.error.email);
                $('#messPassword').html(data.error.password);
                $('#messPasswordConfirm').html(data.error.comfirm);
            }else if (data.result==10 || data.result==20){
                $('#messError').html(data.message);
            }else {
                window.location.href = "http://localhost:8080/login";
            }
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
            document.getElementById('messError').innerText = "Error from server";
        },
    })
}

function checkAccount() {
    if( $("#savePassword").is(':checked')==false){
        $('#messCheckBox').html("Vui long dong y dieu khoan");
    }else {
        addUserAPI();
    }
}

