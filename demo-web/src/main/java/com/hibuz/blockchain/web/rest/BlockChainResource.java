package com.hibuz.blockchain.web.rest;

import com.hibuz.blockchain.client.BlockServiceGRpcClient;
import com.hibuz.blockchain.core.BlockWrapper;
import com.hibuz.blockchain.core.MyWallet;
import com.hibuz.blockchain.proto.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlockChainResource {

	private BlockServiceGRpcClient client;

	@Autowired
	BlockChainResource(BlockServiceGRpcClient client) {
		this.client = client;
	}

	@GetMapping("/")
	public List<BlockWrapper> getBlockList() {
		return MyWallet.chain;
	}

	@PostMapping("/")
	public List<BlockWrapper> sendBlock(@RequestParam String msg) {
		Block newBlock = MyWallet.newBlock(msg);
		Block response = client.write(newBlock);
		MyWallet.addBlock(response);
		return MyWallet.chain;
	}

	@PostMapping("/recruit")
	public List<BlockWrapper> sendRecruitBlock() {
		String msg = MyWallet.recruitEmail();
		return sendBlock(msg);
	}
}
