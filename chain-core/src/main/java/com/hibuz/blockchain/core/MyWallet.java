package com.hibuz.blockchain.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.hibuz.blockchain.proto.Block;
import com.hibuz.blockchain.proto.BlockList;
import com.hibuz.blockchain.proto.BlockList.Builder;

public interface MyWallet {
	static final List<BlockWapper> chain = Collections.synchronizedList(new ArrayList<>());

	public static void init(BlockList blockList) {
		chain.clear();
		List<BlockWapper> wapperList = blockList.getBlockList().stream().map(x -> BlockWapper.of(x))
				.collect(Collectors.toList());
		chain.addAll(wapperList);
	}

	public static Block getHead() {
		return chain.get(chain.size() - 1).getBlock();
	}

	public static void createBlockAndApplyToChain(String msg) {
		Block newBlock = newBlock(msg);
		if (newBlock != null) {
			addBlock(newBlock);
		}
	}

	public static boolean addBlock(Block request) {
		Miner.assertion(getHead(), request);
		return chain.add(BlockWapper.of(request));
	}

	public static Block newBlock(String msg) {
		if (chain.isEmpty()) {
			chain.add(BlockWapper.of(Block.newBuilder().setMsg(msg).build()));
			return null;
		}
		Block head = getHead();
		String hash = Miner.pow(head);
		long height = head.getHeight() + 1;
		return build(hash, height, msg);
	}

	public static Block build(String hash, long height, String msg) {
		return Block.newBuilder().setPrevHash(hash).setHeight(height).setMsg(msg).build();
	}

	public static String recruitEmail() {
		String hash = Miner.pow(getHead());
		return String.format("%s@yggdrash.io", hash.substring(0, 8));
	}

	public static List<BlockWapper> getChain() {
		return chain;
	}

	public static BlockList toBlockList() {
		Builder builder = BlockList.newBuilder();
		for (BlockWapper wapper : chain) {
			builder.addBlock(wapper.getBlock());
		}
		return builder.build();
	}
}
