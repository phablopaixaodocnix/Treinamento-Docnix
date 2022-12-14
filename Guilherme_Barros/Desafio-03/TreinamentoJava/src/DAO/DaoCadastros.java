package DAO;

import Cadastro.Cadastro;

import java.util.ArrayList;

public class DaoCadastros {

    public void incluir(Cadastro cadastro, ArrayList<Cadastro> arrayList) {
        arrayList.add(cadastro);
    }

    public Cadastro editar(String ID, ArrayList<Cadastro> arrayList, Cadastro cadastro) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (ID.equals(String.valueOf(i))) {
                arrayList.set(i, cadastro);
                return arrayList.get(i);
            }
        }
        return null;
    }

    public void listar(ArrayList<Cadastro> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i).toString());
        }
    }

    public void excluir(String ID, ArrayList<Cadastro> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (ID.equals(String.valueOf(i))) {
                arrayList.remove(i);
            }
            if (!arrayList.isEmpty()) {
                arrayList.get(i).setID(String.valueOf(i));
            }
        }
    }
}
