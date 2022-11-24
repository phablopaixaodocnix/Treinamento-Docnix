package servlets;

import controller.FormularioController;
import model.Formulario;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

import static servlets.FuncoesAuxiliaresServlets.getBodyDaRequisicao;

@WebServlet(value = "/ExcluirFormulario")
public class ExcluirFormularioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bodyDaRequisicao = getBodyDaRequisicao(request).replaceAll("(\\r|\\n)", "");
        int id = Integer.parseInt(bodyDaRequisicao);

        FormularioController formularioController = new FormularioController();
        formularioController.deletarFormulario(id);
    }

}
