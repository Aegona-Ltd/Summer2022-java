function accountName() {
    document.getElementById('account-name').innerHTML = "Name: " + getCookie("USERNAME");
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

function setCookie(cname, cvalue, exdays) {
  const d = new Date();
  d.setTime(d.getTime() + (exdays*24*60*60*1000));
  let expires = "expires="+ d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function refreshToke(token) {
    $.ajax({
        url: "http://localhost:8080/api/auth/refreshtoken",
        type: "POST",
        contentType: "application/json;",
        data: JSON.stringify({
            refreshToken: token
        }),
        dataType: 'json',
        success: function(data) {
            setCookie("TOKEN", data.accessToken, 7)
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
        },
    })
}

function logout() {
    $.ajax({
        url: "http://localhost:8080/api/auth/logout",
        type: "GET",
        success: function(data) {
            window.location.href="http://localhost:8080/login";
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
        },
    })
}
function loadheader(page) {
    document.getElementById('link-home-header').classList.remove('active');
    document.getElementById('link-contacts-header').classList.remove('active');
    document.getElementById('link-users-header').classList.remove('active');
    if (page!="") {
        document.getElementById('link-' + page + '-header').classList.add("active");
    }
}

