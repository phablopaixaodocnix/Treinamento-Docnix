package classesTeste;

import dao.ConnectionFactory;
import dao.EndereçoDAO;
import model.Endereço;

import java.sql.Connection;
import java.util.List;

public class testesEndereçoDAO {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexãoComBancoDeDados();

        //testando listagem dos endereços
        EndereçoDAO endereçoDAO = new EndereçoDAO(connection);
        /*List<Endereço> list = endereçoDAO.listar();
        for (int i = 0 ; i <4 ; i++) {
            System.out.println(list.get(i).getUf() + " "
                    + list.get(i).getCidade() + " "
                    + list.get(i).getBairro() + " "
                    + list.get(i).getRua() + " "
                    + list.get(i).getQuadra() + " "
                    + list.get(i).getCasa() + " "
                    + list.get(i).getLote() + " "
                    + list.get(i).getNumero() + " "
                    + list.get(i).getCep());
        }*/

        //testando Inserção dos endereços
        endereçoDAO.salvarEndereçoERetornarID(new Endereço("New York","Brookling","3rd Avenue",10,15,"12301",10,65,"NY"),90);
        List<Endereço> list = endereçoDAO.listar();
        for (int i = 0 ; i <list.size() ; i++) {
            System.out.println(list.get(i).getUf() + " "
                    + list.get(i).getCidade() + " "
                    + list.get(i).getBairro() + " "
                    + list.get(i).getRua() + " "
                    + list.get(i).getQuadra() + " "
                    + list.get(i).getCasa() + " "
                    + list.get(i).getLote() + " "
                    + list.get(i).getNumero() + " "
                    + list.get(i).getCep());
        }
    }
}
