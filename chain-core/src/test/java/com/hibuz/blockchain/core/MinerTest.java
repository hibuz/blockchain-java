package com.hibuz.blockchain.core;

import org.junit.Assert;
import org.junit.Test;

import com.hibuz.blockchain.proto.Block;

public class MinerTest {

	@Test
	public void testPow() {
		Block block = Block.newBuilder().setMsg("Welcome Super Yggdrashers").build();
		String hash = Miner.pow(block);
		Assert.assertEquals("381b89e9ee66943f9511847cded6b45c4ffdf239852e7be09ab9d8e74eaf01be", hash);
	}

}
