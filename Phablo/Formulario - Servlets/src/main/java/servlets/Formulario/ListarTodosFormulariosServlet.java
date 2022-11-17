package servlets.Formulario;

import controller.FormularioController;
import model.Formulario;
import org.json.JSONArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/ListarTodosFormularios")
public class ListarTodosFormulariosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        FormularioController formularioController = new FormularioController();
        List<Formulario> formularios = formularioController.listarFormularios();
        JSONArray jsonArray = new JSONArray(formularios);
        resp.setContentType("application/json; charset=UTF-8");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.getWriter().println(jsonArray);
        resp.getWriter().close();
    }

}
