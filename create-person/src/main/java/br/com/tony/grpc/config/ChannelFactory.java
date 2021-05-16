package br.com.tony.grpc.config;

import br.com.tony.grpc.CreateSubscriberServiceGrpc;
import io.grpc.ManagedChannel;
import io.micronaut.context.annotation.Factory;
import io.micronaut.grpc.annotation.GrpcChannel;

import javax.inject.Inject;
import javax.inject.Singleton;

@Factory
public class ChannelFactory {

    @Inject
    @GrpcChannel("create-subscriber")
    private ManagedChannel createSubscriberChannel;

    @Singleton
    public CreateSubscriberServiceGrpc.CreateSubscriberServiceBlockingStub createSubscriberStub() {
        return CreateSubscriberServiceGrpc.newBlockingStub(createSubscriberChannel);
    }
}
