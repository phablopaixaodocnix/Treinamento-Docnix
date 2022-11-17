package testes;


import controller.ContatosController;
import controller.EnderecoController;
import controller.FormularioController;
import dao.Dao;
import model.Contato;
import model.Endereco;
import model.Formulario;

import java.util.ArrayList;
import java.util.List;

public class Teste {
    public static void main(String[] args) {
        FormularioController formularioController = new FormularioController();
        EnderecoController enderecoController = new EnderecoController();
        ContatosController contatosController = new ContatosController();
        List<Contato> contatoList = new ArrayList<Contato>();
        /*contatoList.add(new Contato("contato1phablo","62999035665","contato1@contato1"));
        contatoList.add(new Contato("contato2phablo","62999035665","contato2@contato2"));
        Formulario f = new Formulario("phablo","phablo@phablo","70778358127",new Endereco("goiânia","Bairro Phablo","Ciprestes",61,01,"74914180",1,89,"GO"),"ensino superior",contatoList);
        f.getContatos().get(0).setIdFormularioNotFk(f.getIdFormulario());
        f.getContatos().get(1).setIdFormularioNotFk(f.getIdFormulario());
        f.getEndereco().setFormulario(f);
        for(int i=0;i<f.getContatos().size();i++) f.getContatos().get(i).setFormulario(f);


        List<Contato> contatoList2 = new ArrayList<Contato>();
        contatoList2.add(new Contato("contato1jennifer","62999035665","contato3@contato3"));
        contatoList2.add(new Contato("contato2jennifer","62999035665","contato4@contato4"));
        Formulario f2 = new Formulario("jennifer","jennifer@jennifer","00596089198",new Endereco("goiânia","Bairro Jennifer","Ciprestes",61,01,"74914180",1,89,"GO"),"ensino superior",contatoList2);
        f2.getContatos().get(0).setIdFormularioNotFk(f2.getIdFormulario());
        f2.getContatos().get(1).setIdFormularioNotFk(f2.getIdFormulario());
        f2.getEndereco().setFormulario(f2);
        for(int i=0;i<f2.getContatos().size();i++) f2.getContatos().get(i).setFormulario(f2);

        List<Contato> contatoList3 = new ArrayList<Contato>();
        contatoList3.add(new Contato("contato1Igor","62999035665","contato3@contato3"));
        contatoList3.add(new Contato("contato2Igor","62999035665","contato4@contato4"));
        Formulario f3 = new Formulario("Igor","Igor@Igor","70778358127",new Endereco("goiânia","Bairro Igor","Ciprestes",61,01,"74914180",1,89,"GO"),"ensino superior",contatoList3);
        f3.getContatos().get(0).setIdFormularioNotFk(f3.getIdFormulario());
        f3.getContatos().get(1).setIdFormularioNotFk(f3.getIdFormulario());
        f3.getEndereco().setFormulario(f3);
        for(int i=0;i<f3.getContatos().size();i++) f3.getContatos().get(i).setFormulario(f3);

        formularioController.cadastrarFormulario(f);
        formularioController.cadastrarFormulario(f2);
        formularioController.cadastrarFormulario(f3);*/

        formularioController.deletarFormulario(38);

        int i=0;
    }
}
