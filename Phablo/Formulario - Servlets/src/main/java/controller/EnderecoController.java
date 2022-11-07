package controller;

import dao.Dao;
import model.Endereco;
import model.Formulario;

import java.util.List;

public class EnderecoController {
    Dao dao = new Dao();

    public Endereco listarOEnderecoDeUmFormulario(Formulario f){//Read
        return dao.listarOEnderecoDeUmFormulario(f);
    }

    public List<Endereco> listarEnderecos() {
        return dao.listarEnderecos();
    }
}
