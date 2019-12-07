package com.hibuz.blockchain.core;

import com.hibuz.blockchain.proto.Block;
import com.hibuz.blockchain.proto.BlockList;
import com.hibuz.blockchain.proto.BlockServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import javax.annotation.PostConstruct;


@GRpcService
@ConditionalOnMissingBean(name = "blockServiceGRpcClient")
public class BlockChainGRpcService extends BlockServiceGrpc.BlockServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BlockChainGRpcService.class);

    @PostConstruct
    private void init() {
        MyWallet.createBlockAndApplyToChain("Welcome blockchain demo");
    }

    @Override
    public void write(Block request, StreamObserver<Block> responseObserver) {
        log.debug("write request: {}", request);
        MyWallet.addBlock(request);
        responseObserver.onNext(request);
        responseObserver.onCompleted();
    }

    @Override
    public void sync(Block request, StreamObserver<BlockList> responseObserver) {
        log.debug("sync request: {}", request.getMsg());
        responseObserver.onNext(MyWallet.toBlockList());
        responseObserver.onCompleted();
    }
}
