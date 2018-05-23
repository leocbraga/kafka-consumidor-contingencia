package br.com.testes.excecao;

public class KafkaServicoRuntimeException extends RuntimeException {


    public KafkaServicoRuntimeException(String mensagem, RuntimeException e){

        super(mensagem, e);

    }

    public KafkaServicoRuntimeException(String mensagem){

        super(mensagem);

    }

    public KafkaServicoRuntimeException(RuntimeException e){

        super(e);

    }
}
