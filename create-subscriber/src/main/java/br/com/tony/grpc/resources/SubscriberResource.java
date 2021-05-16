package br.com.tony.grpc.resources;

import br.com.tony.grpc.CreateSubscriberServiceGrpc;
import br.com.tony.grpc.EmptyResponse;
import br.com.tony.grpc.PersonRequest;
import io.grpc.stub.StreamObserver;
import io.micronaut.grpc.annotation.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class SubscriberResource extends CreateSubscriberServiceGrpc.CreateSubscriberServiceImplBase {

    private final Logger LOGGER = LoggerFactory.getLogger(SubscriberResource.class);

    @Override
    public void createSubscriber(PersonRequest request, StreamObserver<EmptyResponse> responseObserver) {
        LOGGER.info("==== Create Subscriber ====");
        LOGGER.info("==== Recebendo requisição ====");
        LOGGER.info("Nome: {}", request.getName().getValue());
        LOGGER.info("Email: {}", request.getEmail().getValue());
        LOGGER.info("CPF: {}", request.getCpf().getValue());
        System.out.println(request.getCpf().getValue());

        responseObserver.onNext(EmptyResponse.newBuilder().build());
        responseObserver.onCompleted();
    }
}
