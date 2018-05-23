package br.com.testes.servicos;

import br.com.testes.excecao.KafkaServicoRuntimeException;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.logging.Logger;


@Service
public class ConsumidorServico {

    private static final Logger LOGGER = Logger.getLogger( ConsumidorServico.class.getName() );

    public void consumir(String mensagem) throws ValidationException, JSONException {

        LOGGER.info(String.format("Consumindo a mensagem: %s", mensagem));


        isJsonValido(mensagem);

        boolean isExcecao = mensagem.contains("excecao");

        if (isExcecao) {

            throw new KafkaServicoRuntimeException("Algum erro em tempo de execução");

        }

    }

    private void isJsonValido(String json) throws ValidationException {

        JSONObject jsonSchema = new JSONObject(new JSONTokener(ConsumidorServico.class.getResourceAsStream("/schema.json")));

        JSONObject jsonObject = new JSONObject(json);

        Schema schema = SchemaLoader.load(jsonSchema);

        schema.validate(jsonObject);

    }
}
