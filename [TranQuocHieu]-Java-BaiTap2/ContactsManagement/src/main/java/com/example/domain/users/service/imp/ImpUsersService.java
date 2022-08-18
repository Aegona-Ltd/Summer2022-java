package com.example.domain.users.service.imp;

import com.example.domain.users.model.User;
import com.example.domain.users.service.UsersService;
import com.example.domain.restResult.RestResult;
import com.example.domain.users.model.UserDTO;
import com.example.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImpUsersService implements UsersService {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UsersRepository repository;

    /*
    * Method check account on database
    * Method return RestResult:
    *   result 0: login success
    *   result 10: Wrong Email
    *   result 20: Wrong Password
    *   result 90: Wrong input value
    * */
    @Override
    public RestResult loginAccount(@Valid UserDTO account, BindingResult bindingResult) {
        RestResult result = new RestResult();
        if(bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            for (FieldError error: bindingResult.getFieldErrors()) {
                String message = messageSource.getMessage(error, null);
                errors.put(error.getField(), message);
            }
            result.setResult(90);
            result.setMessage("Wrong input value");
            result.setErrors(errors);
            return result;
        }

//        Get account in database
        User user = repository.findByEmail(account.getEmail()).orElse(null);

        if (user==null) {
            result.setResult(10);
            result.setMessage("Wrong email!!");
        }else if (!user.getPassword().equals(account.getPassword())) {
            result.setResult(20);
            result.setMessage("Wrong password!!");
        }else {
            result.setResult(0);
            result.setMessage("Success");
        }
        return result;
    }

    public RestResult getAccountName(HttpServletRequest request) {
        RestResult result = new RestResult();
        result.setResult(0);
        Cookie[] cookies = request.getCookies();
        if (cookies==null) result.setMessage("");
        else {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("username")) {
                    result.setMessage(cookies[i].getValue());
                }
            }
        }
        return result;
    }
}
