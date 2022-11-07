package controller;

import dao.Dao;
import model.Contato;
import model.Endereco;
import model.Formulario;

import java.util.List;

public class ContatosController {
    Dao dao = new Dao();

    public List<Contato> listarContatosDeUmFormulario(Formulario f){//Read
        return dao.listarContatosDeUmFormulario(f);
    }

    public List<Contato> listarContatos() {
        return dao.listarContatos();
    }
}
