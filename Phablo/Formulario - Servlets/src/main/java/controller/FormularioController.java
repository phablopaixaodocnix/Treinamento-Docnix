package controller;

import dao.Dao;
import model.Formulario;

import java.util.List;

public class FormularioController {
    Dao dao = new Dao();

    public List<Formulario> listarFormularios(){
        List<Formulario> formularios = this.dao.listarFormularios();
        return formularios;
    }

    public void cadastrarFormulario(Formulario f){
        dao.cadastrarFormulario(f);
    }

    public void atualizarFormulario(Formulario f){
        dao.atualizarFormulario(f);
    }

    public void deletarFormulario(Formulario f){
        dao.deletarFormulario(f);
    }
}
