package com.cap.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason="User Already Exist")
public class USERALREADYEXISTSEXCEPTION extends Exception {

}
