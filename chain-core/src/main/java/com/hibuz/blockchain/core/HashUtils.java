package com.hibuz.blockchain.core;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {
	private HashUtils() {
		throw new IllegalStateException("Utility class");
	}
	// hash algorithm is fixed
	private static final String HASH_ALGORITHM = "SHA-256";

	public static byte[] sha256(byte[] input) {
		try {
			return MessageDigest.getInstance(HashUtils.HASH_ALGORITHM).digest(input);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeExceptionWrapper(e);
		}
	}

	public static String hashString(String input) {
		return Hex.encodeHexString(HashUtils.sha256(StringUtils.getBytesUtf8(input)));
	}

	public static class RuntimeExceptionWrapper extends RuntimeException {
		RuntimeExceptionWrapper(Throwable cause) {
			super(cause);
		}
	}
}
