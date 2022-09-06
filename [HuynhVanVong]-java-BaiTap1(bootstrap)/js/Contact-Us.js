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
    
    // 1. Fullname
    var fullname = getValue('fullname');
    if (fullname == '' || fullname.length < 5){
        flag = false;
        showError('fullname', 'Vui lòng kiểm tra lại full name');
    }else{
        showError('fullname', '');
    }
    
    // 2. Phone
    var phone = getValue('phone');
    if (phone == '' &&  !/^[0-9]{10}$/.test(phone)){
        flag = false;
        showError('phone', 'Vui lòng kiểm tra lại Phone');
    }else{
        showError('phone', '');
    }
    
    // 3. Email
    var email = getValue('email');
    var mailformat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    if (!mailformat.test(email)){
        flag = false;
        showError('email', 'Vui lòng kiểm tra lại Email');
    }else{
        showError('email', '');
    }
    
    return flag;
}