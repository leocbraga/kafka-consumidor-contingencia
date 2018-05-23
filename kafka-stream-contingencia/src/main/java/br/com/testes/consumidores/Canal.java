package br.com.testes.consumidores;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;


public interface Canal extends Processor {

    String TESTE_REPLAY = "testeReplay";

    @Input(TESTE_REPLAY)
    SubscribableChannel testeReplay();

}
