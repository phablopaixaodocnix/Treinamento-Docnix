package controller;

import dao.Dao;
import model.Contato;
import model.Formulario;

import java.util.List;

public class ContatosController {
    Dao dao = new Dao();

    public List<Contato> listarContatosDeUmFormulario(Formulario f){//Read
        return dao.listarContatosDeUmFormulario(f);
    }
}
