package br.com.testes.consumidores;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;


public interface Canal extends Processor {

    String TESTE = "teste";

    String TESTE_REPLAY = "testeReplay";

    String TESTE_PRODUTOR = "testeProdutor";

    @Input(TESTE)
    SubscribableChannel teste();

    @Output(TESTE_PRODUTOR)
    MessageChannel testeProdutor();

    @Output(TESTE_REPLAY)
    MessageChannel testeReplay();

}
