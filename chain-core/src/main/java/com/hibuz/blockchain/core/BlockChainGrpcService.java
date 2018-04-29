package com.hibuz.blockchain.core;

import javax.annotation.PostConstruct;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import com.hibuz.blockchain.proto.Block;
import com.hibuz.blockchain.proto.BlockList;
import com.hibuz.blockchain.proto.BlockServiceGrpc;

import io.grpc.stub.StreamObserver;

@GRpcService
@ConditionalOnMissingBean(name = "blockServiceGrpcClient")
public class BlockChainGrpcService extends BlockServiceGrpc.BlockServiceImplBase {

	private static final Logger log = LoggerFactory.getLogger(BlockChainGrpcService.class);

	@PostConstruct
	private void init() {
		MyWallet.createBlockAndApplyToChain("Welcome Super Yggdrashers");
		MyWallet.createBlockAndApplyToChain(
				"Send To your resume, This Block hash first 8digit @yggdrash.io, example 46992daf@yggdrash.io");

	}

	@Override
	public void write(Block request, StreamObserver<Block> responseObserver) {
		log.debug("write request: " + request);
		MyWallet.addBlock(request);
		responseObserver.onNext(request);
		responseObserver.onCompleted();
	}

	@Override
	public void sync(Block request, StreamObserver<BlockList> responseObserver) {
		log.debug("sync request: " + request.getMsg());
		responseObserver.onNext(MyWallet.toBlockList());
		responseObserver.onCompleted();
	}
}
