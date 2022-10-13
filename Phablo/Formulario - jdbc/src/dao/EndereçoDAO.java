package dao;

import model.Endereço;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EndereçoDAO {
    private Connection connection;

    public EndereçoDAO(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Endereço> listar() {
        ArrayList<Endereço> endereços = new ArrayList<Endereço>();
        String sqlStatment = "SELECT * FROM endereços;";
        try(PreparedStatement pstm = connection.prepareStatement(sqlStatment)) {
            pstm.execute();
            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    String uf = rst.getString("uf"),
                            cidade = rst.getString("cidade"),
                            bairro = rst.getString("bairro"),
                            rua = rst.getString("rua"),
                            cep = rst.getString("cep");
                    int quadra = rst.getInt("quadra"),
                            casa = rst.getInt("casa"),
                            lote = rst.getInt("lote"),
                            numero = rst.getInt("numero"),
                            idFormulario = rst.getInt("idformulario");
                    endereços.add(new Endereço(idFormulario,cidade, bairro, rua, quadra, casa, cep, lote, numero, uf));
                }
            }
            return endereços;
        }
        catch (SQLException e){
            System.out.println("Deu ruim");
            e.printStackTrace();
            return null;
        }
    }

    public int salvarEndereçoERetornarID(Endereço endereço, int id){
        String slq = "INSERT INTO endereços(uf,cidade,bairro,rua,quadra,casa,lote,numero,cep,idFormulario) VALUES(?,?,?,?,?,?,?,?,?,?);";
        int idEndereço=0;
        try(PreparedStatement pstm = connection.prepareStatement(slq)){
            pstm.setString(1,endereço.getUf());
            pstm.setString(2,endereço.getCidade());
            pstm.setString(3,endereço.getBairro());
            pstm.setString(4,endereço.getRua());
            pstm.setInt(5,endereço.getQuadra());
            pstm.setInt(6,endereço.getCasa());
            pstm.setInt(7,endereço.getLote());
            pstm.setInt(8,endereço.getNumero());
            pstm.setString(9,endereço.getCep());
            pstm.setInt(10,id);
            pstm.execute();
          try(ResultSet rst = pstm.getGeneratedKeys()){
               while (rst.next()) idEndereço = rst.getInt("id");
           }
        }catch (SQLException e){
            System.out.println("Deu ruim");
            e.printStackTrace();
        }
        return idEndereço;
    }

    public void excluir(int id){
        String slq = "DELETE FROM endereços WHERE id = ?;";
        try(PreparedStatement pstm = connection.prepareStatement(slq)){
            pstm.setInt(1,id);
            pstm.execute();
        }catch (SQLException e){
            System.out.println("Deu ruim");
            e.printStackTrace();
        }
    }

    public void editar(int id, Endereço endereço){
        String sql = "UPDATE endereços" +
                     " SET uf = ? ,cidade = ?  ,bairro = ? ,rua = ? ,quadra = ? ,casa = ? ,lote = ? ,numero = ? ,cep = ?" +
                     " WHERE idformulario = ?;";
        try(PreparedStatement pstm = connection.prepareStatement(sql)){
            pstm.setString(1,endereço.getUf());
            pstm.setString(2,endereço.getCidade());
            pstm.setString(3,endereço.getBairro());
            pstm.setString(4,endereço.getRua());
            pstm.setInt(5,endereço.getQuadra());
            pstm.setInt(6,endereço.getCasa());
            pstm.setInt(7,endereço.getLote());
            pstm.setInt(8,endereço.getNumero());
            pstm.setString(9,endereço.getCep());
            pstm.setInt(10,id);
            pstm.execute();
        }catch (SQLException e){
            System.out.println("Deu ruim");
            e.printStackTrace();
        }
    }

}
