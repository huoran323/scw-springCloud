package com.hr.scw.user.exp;

import com.hr.scw.enums.UserExceptionEnum;

public class UserException extends RuntimeException {

	public UserException() {}
	
	public UserException(UserExceptionEnum enums) {
		super(enums.getMsg());
	}
}
