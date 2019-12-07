package com.hibuz.blockchain.core;

import com.hibuz.blockchain.proto.Block;
import com.hibuz.blockchain.proto.BlockList;
import com.hibuz.blockchain.proto.BlockList.Builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface MyWallet {
    List<BlockWrapper> chain = Collections.synchronizedList(new ArrayList<>());

    static void init(BlockList blockList) {
        chain.clear();
        List<BlockWrapper> wrapperList = blockList.getBlockList().stream().map(BlockWrapper::of)
                .collect(Collectors.toList());
        chain.addAll(wrapperList);
    }

    static Block getHead() {
        return chain.get(chain.size() - 1).getBlock();
    }

    static void createBlockAndApplyToChain(String msg) {
        Block newBlock = newBlock(msg);
        if (newBlock != null) {
            addBlock(newBlock);
        }
    }

    static boolean addBlock(Block request) {
        Miner.assertion(getHead(), request);
        return chain.add(BlockWrapper.of(request));
    }

    static Block newBlock(String msg) {
        if (chain.isEmpty()) {
            chain.add(BlockWrapper.of(Block.newBuilder().setMsg(msg).build()));
            return null;
        }
        Block head = getHead();
        String hash = Miner.pow(head);
        long height = head.getHeight() + 1;
        return build(hash, height, msg);
    }

    static Block build(String hash, long height, String msg) {
        return Block.newBuilder().setPrevHash(hash).setHeight(height).setMsg(msg).build();
    }

    static String recruitEmail() {
        String hash = Miner.pow(getHead());
        return String.format("%s@test.com", hash.substring(0, 8));
    }

    static BlockList toBlockList() {
        Builder builder = BlockList.newBuilder();
        for (BlockWrapper wrapper : chain) {
            builder.addBlock(wrapper.getBlock());
        }
        return builder.build();
    }
}
