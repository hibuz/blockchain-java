package com.hibuz.blockchain.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hibuz.blockchain.proto.Block;

@JsonPropertyOrder({ "prev_hash", "height", "msg" })
public class BlockWrapper {

	@JsonIgnore
	Block block;

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	@JsonProperty("prev_hash")
	public String getPrevHash() {
		return block.getPrevHash();
	}

	public long getHeight() {
		return block.getHeight();
	}

	public String getMsg() {
		return block.getMsg();
	}

	public static BlockWrapper of(Block block) {
		BlockWrapper wrapper = new BlockWrapper();
		wrapper.setBlock(block);
		return wrapper;
	}
}
