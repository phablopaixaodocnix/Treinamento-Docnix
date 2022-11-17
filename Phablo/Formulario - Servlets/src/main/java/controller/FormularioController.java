package controller;

import dao.Dao;
import model.Formulario;

import java.util.List;

public class FormularioController {
    Dao dao = new Dao();

    public List<Formulario> listarFormularios(){
        return this.dao.listarFormularios();
    }

    public void cadastrarFormulario(Formulario f){
        dao.cadastrarFormulario(f);
    }

    public void editarFormulario(Formulario f){
        dao.editarFormulario(f);
    }

    public void deletarFormulario(int id){
        dao.deletarFormulario(id);
    }
}
