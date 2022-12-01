package controllers;

import dao.Dao;
import model.Formulario;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static model.Formulario.formularioJsonToObject;
import static model.Formulario.formularioListToJsonArray;

@Path("/")
public class FormularioController {
    Dao dao = new Dao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response listarFormularios(){
        JSONArray jsonArray = formularioListToJsonArray(this.dao.listarFormularios());
        Response response = Response.ok().entity(jsonArray.toString()).build();
        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public void cadastrarFormulario(String request){
        JSONObject jsonObject = new JSONObject(request);
        Formulario formulario = formularioJsonToObject(jsonObject,true);
        dao.cadastrarFormulario(formulario);
    }


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path(("/"))
    public void editarFormulario(String request){
        JSONObject jsonObject = new JSONObject(request);
        Formulario formulario = formularioJsonToObject(jsonObject,false);
        dao.editarFormulario(formulario);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public void deletarFormulario(@PathParam("id") int id){
        dao.deletarFormulario(id);
    }
}
