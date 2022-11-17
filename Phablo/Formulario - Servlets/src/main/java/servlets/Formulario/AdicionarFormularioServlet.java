package servlets.Formulario;

import controller.FormularioController;
import model.Contato;
import model.Endereco;
import model.Formulario;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/AdicionarFormulario")
public class AdicionarFormularioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;

        while ((line = reader.readLine())!= null){
            buffer.append(line);
            buffer.append(System.lineSeparator());
        }

        response.setContentType("application/json; charset=UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        String bufferData = buffer.toString();
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
        Endereco endereco = new Endereco(cidade,bairro,rua,quadra,casa,cep,lote,numero,uf);

        List<Contato> contatos = new ArrayList<>();
        for(int i=0 ; i<contatosJson.length();i++){
            JSONObject contato = contatosJson.getJSONObject(i);
            String nomeContato = (String) contato.get("nome");
            String emailContato = (String) contato.get("telefone");
            String telefoneContato = (String) contato.get("email");
            contatos.add(new Contato(nomeContato,telefoneContato,emailContato));
        }

        Formulario formulario = new Formulario(nome,email,cpf,endereco,escolaridade,contatos);
        FormularioController formularioController = new FormularioController();
        formularioController.cadastrarFormulario(formulario);
    }

    private Integer converterParaIntegerConsiderandoCasosDeStringNula(String str) {
        try{
            return Integer.valueOf(str);
        } catch (NumberFormatException exception){
            return 0;
        }
    }
}
