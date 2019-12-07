package com.hibuz.blockchain.client;

import java.net.InetAddress;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hibuz.blockchain.core.MyWallet;
import com.hibuz.blockchain.proto.Block;
import com.hibuz.blockchain.proto.BlockList;
import com.hibuz.blockchain.proto.BlockServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class BlockServiceGrpcClient {

	private static final Logger log = LoggerFactory.getLogger(BlockServiceGrpcClient.class);
	private BlockServiceGrpc.BlockServiceBlockingStub blockServiceBlockingStub;

	@Value("${rpc.target}")
	private String target;

	public Block write(Block block) {
		Block response = blockServiceBlockingStub.write(block);
		log.debug("response: {}", response);
		return response;
	}

	@PostConstruct
	private void init() {
		log.info("RPC target=" + target);
		ManagedChannel managedChannel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();
		String remoteHost = "unknown";
		try {
			remoteHost = InetAddress.getLocalHost().getHostName();
		} catch (Exception e) {
			log.warn(e.getMessage());
		}
		blockServiceBlockingStub = BlockServiceGrpc.newBlockingStub(managedChannel);
		Block requestBlock = Block.newBuilder().setMsg(remoteHost).build();
		BlockList response = blockServiceBlockingStub.sync(requestBlock);
		log.debug("sync response: {}", response);
		MyWallet.init(response);
	}

}
