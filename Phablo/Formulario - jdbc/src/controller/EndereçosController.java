package controller;

import dao.ConnectionFactory;
import dao.EndereçoDAO;
import model.Endereço;

import java.util.ArrayList;

public class EndereçosController {
    private EndereçoDAO endereçoDAO = null;

    public EndereçosController(){
        this.endereçoDAO = new EndereçoDAO(new ConnectionFactory().recuperarConexãoComBancoDeDados());
    }

    public ArrayList<Endereço> listar(){
        return endereçoDAO.listar();
    }

    public int salvar(Endereço endereço, int id){
        return endereçoDAO.salvarEndereçoERetornarID(endereço,id);
    }

    public void excluir(int id){
        endereçoDAO.excluir(id);
    }

    public void editar(int id, Endereço endereço){
        endereçoDAO.editar(id,endereço);
    }


}
