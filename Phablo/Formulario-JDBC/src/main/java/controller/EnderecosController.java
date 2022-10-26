package controller;

import dao.ConnectionFactory;
import dao.EnderecoDAO;
import model.Endereco;

import java.util.ArrayList;

public class EnderecosController {
    private EnderecoDAO enderecoDAO = null;

    public EnderecosController(){
        this.enderecoDAO = new EnderecoDAO(new ConnectionFactory().recuperarConexaoComBancoDeDados());
    }

    public ArrayList<Endereco> listar(){
        return enderecoDAO.listar();
    }

    public int salvar(Endereco endereco, int id){
        return enderecoDAO.salvarEnderecoERetornarID(endereco,id);
    }

    public void excluir(int id){
        enderecoDAO.excluir(id);
    }

    public void editar(int id, Endereco endereco){
        enderecoDAO.editar(id,endereco);
    }


}
