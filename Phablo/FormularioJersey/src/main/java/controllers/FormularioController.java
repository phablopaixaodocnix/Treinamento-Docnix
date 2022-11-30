package controllers;

import dao.Dao;
import model.Formulario;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static model.Formulario.formularioJsonToObject;

@Path("/FormularioJersey")
public class FormularioController {
    Dao dao = new Dao();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/cadastrarFormulario")
    public void cadastrarFormulario(String request){
        JSONObject jsonObject = new JSONObject(request);
        Formulario formulario = formularioJsonToObject(jsonObject);
        dao.cadastrarFormulario(formulario);
    }

    @GET //teste pra ver se a requisição get está funcionando
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello","world");
        return Response.ok().entity(jsonObject.toString()).build();
    }

    public List<Formulario> listarFormularios(){
        return this.dao.listarFormularios();
    }


    public void editarFormulario(Formulario f){
        dao.editarFormulario(f);
    }

    public void deletarFormulario(int id){
        dao.deletarFormulario(id);
    }
}
