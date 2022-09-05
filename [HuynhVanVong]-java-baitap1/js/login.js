// get input value
function getValue(id){
    return document.getElementById(id).value.trim();
}

// show err
function showError(key, mess){
    document.getElementById(key + '_error').innerHTML = mess;
}

// validate function
function validate()
{
    var flag = true;
    
    // 1. Email
    var email = getValue('email');
    var mailformat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    if (!mailformat.test(email)){
        flag = false;
        showError('email', 'Vui lòng kiểm tra lại Email');
    }else{
        showError('email', '');
    }

    //2. Password
    var password = getValue('password');
    if (password == '' || password.length < 6){
        flag = false;
        showError('password', 'Vui lòng kiểm tra lại password');
    }else{
        showError('password', '');
    }
    
    return flag;
}