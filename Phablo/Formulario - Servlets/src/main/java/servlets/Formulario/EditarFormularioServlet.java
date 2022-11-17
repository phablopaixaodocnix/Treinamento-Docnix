package servlets.Formulario;

import controller.FormularioController;
import model.Contato;
import model.Endereco;
import model.Formulario;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/EditarFormulario")
public class EditarFormularioServlet extends HttpServlet {
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
        String bufferData = buffer.toString();
        JSONObject formularioJson = new JSONObject(bufferData);
        JSONObject enderecoJson = formularioJson.getJSONObject("endereco");
        JSONArray contatosJson = formularioJson.getJSONArray("contatos");

        Integer idFormulario = (Integer) formularioJson.get("idFormulario");
        String nome = (String) formularioJson.get("nome");
        String email = (String) formularioJson.get("email");
        String cpf = (String) formularioJson.get("cpf");
        String escolaridade = (String) formularioJson.get("escolaridade");

        String cidade = (String) enderecoJson.get("cidade");
        String bairro = (String) enderecoJson.get("bairro");
        String rua = (String) enderecoJson.get("rua");
        Integer quadra = (Integer) enderecoJson.get("quadra");
        Integer casa = (Integer) enderecoJson.get("casa");
        String cep = (String) enderecoJson.get("cep");
        Integer lote = (Integer) enderecoJson.get("lote");
        Integer numero = (Integer) enderecoJson.get("numero");
        String uf = (String) enderecoJson.get("uf");
        Endereco endereco = new Endereco(idFormulario,cidade,bairro,rua,quadra,casa,cep,lote,numero,uf);

        List<Contato> contatos = new ArrayList<>();
        for(int i=0 ; i<contatosJson.length();i++){
            JSONObject contato = contatosJson.getJSONObject(i);
            String nomeContato = (String) contato.get("nome");
            String emailContato = (String) contato.get("email");
            String telefoneContato = (String) contato.get("telefone");
            contatos.add(new Contato(nomeContato,telefoneContato,emailContato));
        }

        Formulario formulario = new Formulario(idFormulario,nome,email,cpf,endereco,escolaridade,contatos);
        FormularioController formularioController = new FormularioController();
        formularioController.editarFormulario(formulario);
    }
}
