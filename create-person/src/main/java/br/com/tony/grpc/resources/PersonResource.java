package br.com.tony.grpc.resources;

import br.com.tony.grpc.CreatePersonServiceGrpc;
import br.com.tony.grpc.PersonRequest;
import br.com.tony.grpc.PersonResponse;
import br.com.tony.grpc.dto.PersonInputDTO;
import br.com.tony.grpc.dto.PersonOutputDTO;
import br.com.tony.grpc.integration.CreateSubscriberIntegration;
import br.com.tony.grpc.service.PersonService;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import io.micronaut.grpc.annotation.GrpcService;

@GrpcService
public class PersonResource extends CreatePersonServiceGrpc.CreatePersonServiceImplBase {

    private final PersonService personService;
    private final CreateSubscriberIntegration subscriberIntegration;

    public PersonResource(PersonService personService, CreateSubscriberIntegration subscriberIntegration) {
        this.personService = personService;
        this.subscriberIntegration = subscriberIntegration;
    }

    @Override
    public void createPerson(PersonRequest request,
                             StreamObserver<PersonResponse> responseObserver) {

        try {
            PersonOutputDTO outputDTO = personService.create(PersonInputDTO
                    .builder()
                    .setName(request.getName().getValue())
                    .setEmail(request.getEmail().getValue())
                    .setCpf(request.getCpf().getValue()))
                    .build();

            this.subscriberIntegration.sendToSubscriberService(request);

            responseObserver.onNext(PersonResponse
                    .newBuilder()
                    .setId(Int64Value.of(outputDTO.getId()))
                    .setName(StringValue.of(outputDTO.getName()))
                    .setEmail(StringValue.of(outputDTO.getEmail()))
                    .setCpf(StringValue.of(outputDTO.getCpf()))
                    .build());

            responseObserver.onCompleted();
        } catch (StatusRuntimeException e) {
            responseObserver.onError(e.getStatus()
                    .withCause(e.getCause()).withDescription(e.getMessage()).asRuntimeException());
        }

    }
}
