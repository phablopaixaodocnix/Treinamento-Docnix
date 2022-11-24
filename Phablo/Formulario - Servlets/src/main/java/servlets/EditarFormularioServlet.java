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

@WebServlet(value = "/EditarFormulario")
public class EditarFormularioServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bufferData = getBodyDaRequisicao(request);
        response.setContentType("application/json; charset=UTF-8");

        Formulario formularioParaEditarNoBanco = getFormulario(bufferData,"editar");
        FormularioController formularioController = new FormularioController();
        formularioController.editarFormulario(formularioParaEditarNoBanco);
    }
}