package dao;

import model.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnderecoDAO {
    private Connection connection;

    public EnderecoDAO(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Endereco> listar() {
        ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
        String sqlStatment = "SELECT * FROM enderecos;";
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
                    enderecos.add(new Endereco(idFormulario,cidade, bairro, rua, quadra, casa, cep, lote, numero, uf));
                }
            }
            return enderecos;
        }
        catch (SQLException e){
            System.out.println("Deu ruim");
            e.printStackTrace();
            return null;
        }
    }

    public int salvarEnderecoERetornarID(Endereco endereco, int id){
        String slq = "INSERT INTO enderecos(uf,cidade,bairro,rua,quadra,casa,lote,numero,cep,idFormulario) VALUES(?,?,?,?,?,?,?,?,?,?);";
        int idEndereco=0;
        try(PreparedStatement pstm = connection.prepareStatement(slq)){
            pstm.setString(1,endereco.getUf());
            pstm.setString(2,endereco.getCidade());
            pstm.setString(3,endereco.getBairro());
            pstm.setString(4,endereco.getRua());
            pstm.setInt(5,endereco.getQuadra());
            pstm.setInt(6,endereco.getCasa());
            pstm.setInt(7,endereco.getLote());
            pstm.setInt(8,endereco.getNumero());
            pstm.setString(9,endereco.getCep());
            pstm.setInt(10,id);
            pstm.execute();
          try(ResultSet rst = pstm.getGeneratedKeys()){
               while (rst.next()) idEndereco = rst.getInt("id");
           }
        }catch (SQLException e){
            System.out.println("Deu ruim");
            e.printStackTrace();
        }
        return idEndereco;
    }

    public void excluir(int id){
        String slq = "DELETE FROM enderecos WHERE id = ?;";
        try(PreparedStatement pstm = connection.prepareStatement(slq)){
            pstm.setInt(1,id);
            pstm.execute();
        }catch (SQLException e){
            System.out.println("Deu ruim");
            e.printStackTrace();
        }
    }

    public void editar(int id, Endereco endereco){
        String sql = "UPDATE enderecos" +
                     " SET uf = ? ,cidade = ?  ,bairro = ? ,rua = ? ,quadra = ? ,casa = ? ,lote = ? ,numero = ? ,cep = ?" +
                     " WHERE idformulario = ?;";
        try(PreparedStatement pstm = connection.prepareStatement(sql)){
            pstm.setString(1,endereco.getUf());
            pstm.setString(2,endereco.getCidade());
            pstm.setString(3,endereco.getBairro());
            pstm.setString(4,endereco.getRua());
            pstm.setInt(5,endereco.getQuadra());
            pstm.setInt(6,endereco.getCasa());
            pstm.setInt(7,endereco.getLote());
            pstm.setInt(8,endereco.getNumero());
            pstm.setString(9,endereco.getCep());
            pstm.setInt(10,id);
            pstm.execute();
        }catch (SQLException e){
            System.out.println("Deu ruim");
            e.printStackTrace();
        }
    }

}
