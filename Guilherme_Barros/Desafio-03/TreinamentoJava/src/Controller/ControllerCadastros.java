package Controller;

import Cadastro.Cadastro;
import DAO.DaoCadastros;
import Cadastro.Contato;

import java.util.ArrayList;

public class ControllerCadastros {
    DaoCadastros daoCadastros = new DaoCadastros();
    public void incluir(Cadastro cadastro, ArrayList<Cadastro> arrayList){
        daoCadastros.incluir(cadastro, arrayList);
    }
    public Cadastro editar(long ID, ArrayList<Cadastro> arrayList, Cadastro cadastro){
        return daoCadastros.editar(ID, arrayList, cadastro);
    }
    public void listar(ArrayList<Cadastro> arrayList){
        daoCadastros.listar(arrayList);
    }
    public void excluir(long ID, ArrayList<Cadastro> arrayList){daoCadastros.excluir(ID,arrayList);}
}
