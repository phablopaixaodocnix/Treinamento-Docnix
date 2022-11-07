package servlets.Endereco;

import controller.EnderecoController;
import model.Endereco;
import org.json.JSONArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/ListarTodosEnderecos")
public class ListarTodosEnderecosServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        EnderecoController enderecoController = new EnderecoController();
        List<Endereco> enderecos = enderecoController.listarEnderecos();
        JSONArray enderecosJSON = new JSONArray(enderecos);
        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().println(enderecosJSON);
        resp.getWriter().close();
    }

}
