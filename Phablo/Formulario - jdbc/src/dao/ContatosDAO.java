package dao;

import model.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContatosDAO {
    private Connection connection;

    public ContatosDAO(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Contato> listar() {
        ArrayList<Contato> contatos = new ArrayList<Contato>();

        String sqlStatment = "SELECT * FROM contatos;";
        try(PreparedStatement pstm = connection.prepareStatement(sqlStatment)) {
            pstm.execute();
            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    int id = rst.getInt(1),
                        idformulario = rst.getInt(2);
                    String nomecontato = rst.getString(3),
                            emailcontato = rst.getString(4),
                            telefonecontato = rst.getString(5);
                    contatos.add(new Contato(id,idformulario,nomecontato,emailcontato,telefonecontato));
                }
            }
            return contatos;
        }
        catch (SQLException e){
            System.out.println("Deu ruim");
            e.printStackTrace();
            return null;
        }
    }

    public int salvarContatoERetornarID(Contato contato, int id){
        String slq = "INSERT INTO contatos(nomecontato,emailcontato,telefonecontato,idFormulario) VALUES(?,?,?,?);";
        int idContato=0;
        try(PreparedStatement pstm = connection.prepareStatement(slq)){
            pstm.setString(1,contato.getNome());
            pstm.setString(2,contato.getEmail());
            pstm.setString(3,contato.getTelefone());
            pstm.setInt(4,id);
            pstm.execute();
            try(ResultSet rst = pstm.getGeneratedKeys()){
                while (rst.next()) idContato = rst.getInt("id");
            }
        }catch (SQLException e){
            System.out.println("Deu ruim");
            e.printStackTrace();
        }
        return idContato;
    }

    public void excluir(int id){
        String slq = "DELETE FROM contatos WHERE id = ?;";
        try(PreparedStatement pstm = connection.prepareStatement(slq)){
            pstm.setInt(1,id);
            pstm.execute();
        }catch (SQLException e){
            System.out.println("Deu ruim");
            e.printStackTrace();
        }
    }

    public void editar(int id, Contato contato){
        String sql = "UPDATE contatos" +
                " SET nomecontato = ?, emailcontato = ?, telefonecontato = ?" +
                " WHERE id = ?;";
        try(PreparedStatement pstm = connection.prepareStatement(sql)){
            pstm.setString(1, contato.getNome());
            pstm.setString(2, contato.getEmail());
            pstm.setString(3, contato.getTelefone());
            pstm.setInt(4,id);
            pstm.execute();
        }catch (SQLException e){
            System.out.println("Deu ruim");
            e.printStackTrace();
        }
    }

}
