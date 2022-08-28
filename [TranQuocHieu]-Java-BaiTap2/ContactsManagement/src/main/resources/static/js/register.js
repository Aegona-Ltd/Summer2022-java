
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
    $('#messError').html("");
    $('#messName').html("");
    $('#messEmail').html("");
    $('#messPassword').html("");
    $('#messPasswordConfirm').html("");
    $('#messCheckBox').html("");
    let isError = false;
    const validRegex = /^[a-zA-Z0-9.!#$%&*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

    if ($('#inputname').val().length==0) {
        $('#messName').html('Please enter your name!!');
        isError=true;
    }
    if ($('#email').val().length==0) {
        $('#messEmail').html('Please enter your email!!');
        isError=true;
    }else if($('#email').val().match(validRegex)==false){
        $('#messEmail').html('Invalid email!!');
        isError=true;
    }
    if ($('#password').val().length==0) {
        $('#messPassword').html('Please enter your password!!');
        isError=true;
    }
    if (!$('#passwordConfirm').val().match($('#password').val())) {
        $('#messPasswordConfirm').html('Wrong password comfirm');
        isError=true;
    }

    if( $("#savePassword").is(':checked')==false){
        $('#messCheckBox').html("Please accept the terms");
        isError = true;
    }

    var response = grecaptcha.getResponse();
    if (response.length==0) {
        $('#messRecapcha').html("Please verify that you are not a robot.")
        isError=true;
    }else {
        $('#messRecapcha').html("")
    }

    (!isError) ? addUserAPI():''
}

