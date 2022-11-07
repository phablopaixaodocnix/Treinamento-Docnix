package servlets.Contato;

import controller.ContatosController;
import model.Contato;
import org.json.JSONArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/ListarTodosContatos")
public class ListarTodosContatosServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ContatosController contatosController = new ContatosController();
        List<Contato> contatos = contatosController.listarContatos();
        JSONArray contatosJSON = new JSONArray(contatos);
        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().println(contatosJSON);
        resp.getWriter().close();
    }


}
