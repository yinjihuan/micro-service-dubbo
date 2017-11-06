package com.cxytiandi.common.exception;

import com.cxytiandi.common.base.ResponseCode;

public class LimitException extends GlobalException {

	private static final long serialVersionUID = 4066485840242028945L;

	public LimitException(String message) {
		super(message, ResponseCode.LIMIT_ERROR_CODE.getCode());
	}

}
