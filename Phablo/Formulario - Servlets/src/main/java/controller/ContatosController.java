package controller;

import dao.Dao;
import model.Contato;

import java.util.List;

public class ContatosController {
    Dao dao = new Dao();

    public List<Contato> listarContatos() {
        return dao.listarContatos();
    }
}
