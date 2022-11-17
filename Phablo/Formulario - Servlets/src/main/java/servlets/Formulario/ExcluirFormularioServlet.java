package servlets.Formulario;

import controller.FormularioController;
import model.Formulario;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(value = "/ExcluirFormulario")
public class ExcluirFormularioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;

        while ((line = reader.readLine())!= null){
            buffer.append(line);
        }
        int id = Integer.valueOf(String.valueOf(buffer));

        FormularioController formularioController = new FormularioController();
        formularioController.deletarFormulario(id);
    }

}
