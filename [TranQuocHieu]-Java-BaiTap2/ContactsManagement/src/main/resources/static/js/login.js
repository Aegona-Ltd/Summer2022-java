
jQuery(function($){
  $('#btn-login').click(function (event) {
    checkAccount();
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
                $('#messEmail').html(data.errors.email);
                $('#messPassword').html(data.errors.password);
            }else if (data.result==10 || data.result==20){
                $('#messError').html(data.message);
            }else {
                setCookie("USERNAME", data.username, 2)
                setCookie("TOKEN", data.token, 2)
                window.location.href = "http://localhost:8080/dashboard"
            }
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
            document.getElementById('messError').innerText = "Error from server";
        },
    })
}

function setCookie(cname, cvalue, exdays) {
  const d = new Date();
  d.setTime(d.getTime() + (exdays*24*60*60*1000));
  let expires = "expires="+ d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
  let name = cname + "=";
  let decodedCookie = decodeURIComponent(document.cookie);
  let ca = decodedCookie.split(';');
  for(let i = 0; i <ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}