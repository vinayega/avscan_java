package com.function;

public class ClamAVSizeLimitException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public ClamAVSizeLimitException(String msg) {
		super(msg);
	}
}
