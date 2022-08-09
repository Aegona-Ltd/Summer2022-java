function checkAccount(formLogin) {
    document.getElementById('validation-email').innerText = "";
    document.getElementById('validation-password').innerText = "";
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
    if (!check) {
        window.location="../html/contactList.html";
    }
    
}