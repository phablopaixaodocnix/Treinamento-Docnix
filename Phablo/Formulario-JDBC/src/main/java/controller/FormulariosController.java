package controller;

import dao.ConnectionFactory;
import dao.FormularioDAO;
import model.Formulario;

import java.util.ArrayList;

public class FormulariosController {
    private FormularioDAO formularioDAO = null;

    public FormulariosController(){
        this.formularioDAO = new FormularioDAO(new ConnectionFactory().recuperarConexaoComBancoDeDados());
    }

    public ArrayList<Formulario> listar(){
        return formularioDAO.listar();
    }

    public int salvar(Formulario formulario){
        return formularioDAO.salvarFormularioERetornarID(formulario);
    }

    public void excluir(int id){
        formularioDAO.excluir(id);
    }

    public void editar(int id, Formulario formulario){
        formularioDAO.editar(id,formulario);
    }
}
