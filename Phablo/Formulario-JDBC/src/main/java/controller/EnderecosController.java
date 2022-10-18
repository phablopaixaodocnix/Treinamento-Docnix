package controller;

import dao.ConnectionFactory;
import dao.EnderecoDAO;
import model.Endereço;

import java.util.ArrayList;

public class EnderecosController {
    private EnderecoDAO enderecoDAO = null;

    public EnderecosController(){
        this.enderecoDAO = new EnderecoDAO(new ConnectionFactory().recuperarConexãoComBancoDeDados());
    }

    public ArrayList<Endereço> listar(){
        return enderecoDAO.listar();
    }

    public int salvar(Endereço endereço, int id){
        return enderecoDAO.salvarEndereçoERetornarID(endereço,id);
    }

    public void excluir(int id){
        enderecoDAO.excluir(id);
    }

    public void editar(int id, Endereço endereço){
        enderecoDAO.editar(id,endereço);
    }


}
