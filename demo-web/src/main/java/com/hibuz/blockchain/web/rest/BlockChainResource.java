package com.hibuz.blockchain.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hibuz.blockchain.client.BlockServiceGrpcClient;
import com.hibuz.blockchain.core.BlockWapper;
import com.hibuz.blockchain.core.MyWallet;
import com.hibuz.blockchain.proto.Block;

@RestController
@RequestMapping("/api")
public class BlockChainResource {

	private BlockServiceGrpcClient client;

	@Autowired
	BlockChainResource(BlockServiceGrpcClient client) {
		this.client = client;
	}

	@GetMapping("/")
	public List<BlockWapper> getBlockList() {
		return MyWallet.chain;
	}

	@PostMapping("/")
	public List<BlockWapper> sendBlock(@RequestParam String msg) {
		Block newBlock = MyWallet.newBlock(msg);
		Block response = client.write(newBlock);
		MyWallet.addBlock(response);
		return MyWallet.chain;
	}

	@PostMapping("/recruit")
	public List<BlockWapper> sendRecruitBlock() {
		String msg = MyWallet.recruitEmail();
		return sendBlock(msg);
	}
}
