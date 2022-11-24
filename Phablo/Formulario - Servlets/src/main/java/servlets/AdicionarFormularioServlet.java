package servlets;

import controller.FormularioController;
import model.Formulario;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.FuncoesAuxiliaresServlets.getBodyDaRequisicao;
import static servlets.FuncoesAuxiliaresServlets.getFormulario;

@WebServlet(value = "/AdicionarFormulario")
public class AdicionarFormularioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bufferData = getBodyDaRequisicao(request);
        response.setContentType("application/json; charset=UTF-8");

        Formulario formularioParaAdicionarNoBanco = getFormulario(bufferData,"adicionar");
        FormularioController formularioController = new FormularioController();
        formularioController.cadastrarFormulario(formularioParaAdicionarNoBanco);
    }

}
