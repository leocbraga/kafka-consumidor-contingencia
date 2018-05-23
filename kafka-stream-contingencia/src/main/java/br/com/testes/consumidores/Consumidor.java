package br.com.testes.consumidores;

import br.com.testes.servicos.ConsumidorServico;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.BinderHeaders;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

import javax.xml.bind.ValidationException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

@EnableAutoConfiguration
@EnableBinding(Canal.class)
public class Consumidor {

    private static final Logger LOGGER = Logger.getLogger( Consumidor.class.getName() );

    private static final String X_RETRIES_HEADER = "x-retries";

    @Autowired
    private ConsumidorServico consumidorServico;

    private final AtomicInteger processado = new AtomicInteger();

    @StreamListener(Canal.TESTE_REPLAY)
    public void processar(Message<String> mensagem){

        try {

            consumidorServico.consumir(mensagem.getPayload());

        }catch(ValidationException | JSONException e){

            LOGGER.log(Level.SEVERE, "Erro ao extrair o JSON", e);

            throw new RuntimeException(e);

        }

    }

}
