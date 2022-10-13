package controller;

import dao.ConnectionFactory;
import dao.ContatosDAO;
import model.Contato;

import java.util.ArrayList;

public class ContatosController {
    private ContatosDAO contatosDAO = null;

    public ContatosController(){
        this.contatosDAO = new ContatosDAO( new ConnectionFactory().recuperarConexãoComBancoDeDados());
    }

    public ArrayList<Contato> listar(){
        return contatosDAO.listar();
    }

    public int salvar(Contato contato,int id){
        return contatosDAO.salvarContatoERetornarID(contato,id);
    }

    public void excluir(int id){
        contatosDAO.excluir(id);
    }

    public void editar(int id, Contato contato){
        contatosDAO.editar(id,contato);
    }
}
