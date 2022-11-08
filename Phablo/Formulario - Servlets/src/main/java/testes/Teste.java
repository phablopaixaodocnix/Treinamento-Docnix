package testes;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.FormularioController;
import model.Formulario;
import org.json.JSONArray;
import org.json.JSONObject;
import servlets.Formulario.*;

import java.util.List;

public class Teste {
    public static void main(String[] args) {
        /*List<Contato> contatoList = new ArrayList<Contato>();
        contatoList.add(new Contato("contato1","62999035665","contato1@contato1"));
        contatoList.add(new Contato("contato2","62999035665","contato2@contato2"));
        Formulario f = new Formulario("phablo","phablo@phablo","70778358127",new Endereco("goiânia","Jardim Maria Inês","Ciprestes",61,01,"74914180",1,89,"GO"),"ensino superior",contatoList);
        f.getEndereco().setFormulario(f);
        for(int i=0;i<f.getContatos().size();i++) f.getContatos().get(i).setFormulario(f);


        List<Contato> contatoList2 = new ArrayList<Contato>();
        contatoList2.add(new Contato("contato3","62999035665","contato3@contato3"));
        contatoList2.add(new Contato("contato4","62999035665","contato4@contato4"));
        Formulario f2 = new Formulario("jennifer","jennifer@jennifer","70778358127",new Endereco("goiânia","Jardim Maria Inês","Ciprestes",61,01,"74914180",1,89,"GO"),"ensino superior",contatoList2);
        f2.getEndereco().setFormulario(f2);
        for(int i=0;i<f2.getContatos().size();i++) f2.getContatos().get(i).setFormulario(f2);

        List<Contato> contatoList3 = new ArrayList<Contato>();
        contatoList3.add(new Contato("contato3","62999035665","contato3@contato3"));
        contatoList3.add(new Contato("contato4","62999035665","contato4@contato4"));
        Formulario f3 = new Formulario("Igor","jennifer@jennifer","70778358127",new Endereco("goiânia","Jardim Maria Inês","Ciprestes",61,01,"74914180",1,89,"GO"),"ensino superior",contatoList3);
        f3.getEndereco().setFormulario(f3);
        for(int i=0;i<f3.getContatos().size();i++) f3.getContatos().get(i).setFormulario(f3);

        FormularioController formularioController = new FormularioController();
        formularioController.cadastrarFormulario(f);
        formularioController.cadastrarFormulario(f2);
        formularioController.cadastrarFormulario(f3);*/

        FormularioController formularioController = new FormularioController();
        formularioController.listarFormularios();


        int i=0;
    }
}
