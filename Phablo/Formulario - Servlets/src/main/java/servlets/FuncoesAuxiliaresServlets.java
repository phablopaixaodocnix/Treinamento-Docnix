package servlets;

import model.Contato;
import model.Endereco;
import model.Formulario;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

abstract public class FuncoesAuxiliaresServlets {

    public static Integer converterParaIntegerConsiderandoCasosDeStringNula(String str) {
        try{
            return Integer.valueOf(str);
        } catch (NumberFormatException exception){
            return 0;
        }
    }

    public static String getBodyDaRequisicao(HttpServletRequest request) throws IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;

        while ((line = reader.readLine())!= null){
            buffer.append(line);
            buffer.append(System.lineSeparator());
        }
        String bufferData = buffer.toString();
        return bufferData;
    }

    public static Formulario getFormulario(String bufferData, String adicioarOuEditar){
        JSONObject formularioJson = new JSONObject(bufferData);
        JSONObject enderecoJson = formularioJson.getJSONObject("endereco");
        JSONArray contatosJson = formularioJson.getJSONArray("contatos");

        String nome = (String) formularioJson.get("nome");
        String email = (String) formularioJson.get("email");
        String cpf = (String) formularioJson.get("cpf");
        String escolaridade = (String) formularioJson.get("escolaridade");

        String cidade = (String) enderecoJson.get("cidade");
        String bairro = (String) enderecoJson.get("bairro");
        String rua = (String) enderecoJson.get("rua");
        Integer quadra = converterParaIntegerConsiderandoCasosDeStringNula((String) enderecoJson.get("quadra"));
        Integer casa = converterParaIntegerConsiderandoCasosDeStringNula((String) enderecoJson.get("casa"));
        String cep = (String) enderecoJson.get("cep");
        Integer lote = converterParaIntegerConsiderandoCasosDeStringNula((String) enderecoJson.get("lote"));
        Integer numero = converterParaIntegerConsiderandoCasosDeStringNula((String) enderecoJson.get("numero"));
        String uf = (String) enderecoJson.get("uf");
        Endereco endereco = null;

        List<Contato> contatos = new ArrayList<>();
        for(int i=0 ; i<contatosJson.length();i++){
            JSONObject contato = contatosJson.getJSONObject(i);
            String nomeContato = (String) contato.get("nome");
            String emailContato = (String) contato.get("telefone");
            String telefoneContato = (String) contato.get("email");
            contatos.add(new Contato(nomeContato,telefoneContato,emailContato));
        }

        if(adicioarOuEditar == "adicionar") {
            endereco = new Endereco(cidade, bairro, rua, quadra, casa, cep, lote, numero, uf);
            return new Formulario(nome,email,cpf,endereco,escolaridade,contatos);
        }
        if (adicioarOuEditar == "editar") {
            Integer idFormulario = (Integer) formularioJson.get("idFormulario");
            endereco = new Endereco(idFormulario, cidade, bairro, rua, quadra, casa, cep, lote, numero, uf);
            return new Formulario(idFormulario,nome,email,cpf,endereco,escolaridade,contatos);
        }

        return null;
    }

}
