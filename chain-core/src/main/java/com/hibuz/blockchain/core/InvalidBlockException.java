package com.hibuz.blockchain.core;

public class InvalidBlockException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public InvalidBlockException() {
		super();
	}

	public InvalidBlockException(String msg) {
		super(msg);
	}
}
