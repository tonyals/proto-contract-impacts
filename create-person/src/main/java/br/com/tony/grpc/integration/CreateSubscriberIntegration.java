package br.com.tony.grpc.integration;

import br.com.tony.grpc.PersonRequest;
import br.com.tony.grpc.config.ChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class CreateSubscriberIntegration {

    private final ChannelFactory channelFactory;
    private final Logger LOGGER = LoggerFactory.getLogger(CreateSubscriberIntegration.class);

    public CreateSubscriberIntegration(ChannelFactory channelFactory) {
        this.channelFactory = channelFactory;
    }

    public void sendToSubscriberService(PersonRequest request) {
        LOGGER.info("==== Enviando requisição ====");
        LOGGER.info("Nome: {}", request.getName().getValue());
//        LOGGER.info("Email: {}", request.getEmail().getValue());
        LOGGER.info("CPF: {}", request.getCpf().getValue());
        this.channelFactory.createSubscriberStub().createSubscriber(request);
    }
}
