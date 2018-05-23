package br.com.testes.controladores;

import org.apache.kafka.common.record.RecordBatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "produtor")
public class ProdutorControlador {

    @Autowired
    private MessageChannel testeProdutor;

    @RequestMapping(path = "/produzir-mensagem-com-erro", method = RequestMethod.POST)
    public String produzirMensagemComErro() {

        Message<String> mensagem = new Message<String>() {

            @Override
            public String getPayload() {

                return "teste";

            }

            @Override
            public MessageHeaders getHeaders() {

                Map<String, Object> headers = new HashMap<>();

                headers.put("content-type", "raw");

                return new MessageHeaders(headers);

            }
        };

        testeProdutor.send(mensagem);

        return "";

    }

    @RequestMapping(path = "/produzir-mensagem-sem-erro", method = RequestMethod.POST)
    public String produzirMensagemSemErro() {

        Message<String> mensagem = new Message<String>() {

            @Override
            public String getPayload() {

                return "{\"nome\":\"leonardo\"}";

            }

            @Override
            public MessageHeaders getHeaders() {
                Map<String, Object> headers = new HashMap<>();

                headers.put("content-type", "raw");

                return new MessageHeaders(headers);
            }
        };

        testeProdutor.send(mensagem);

        return "";

    }

    @RequestMapping(path = "/produzir-mensagem-com-excecao", method = RequestMethod.POST)
    public String produzirMensagemComExcecao() {

        Message<String> mensagem = new Message<String>() {

            @Override
            public String getPayload() {

                return "{\"nome\":\"excecao\"}";

            }

            @Override
            public MessageHeaders getHeaders() {
                Map<String, Object> headers = new HashMap<>();

                headers.put("content-type", "raw");

                return new MessageHeaders(headers);
            }
        };

        testeProdutor.send(mensagem);

        return "";

    }

}
