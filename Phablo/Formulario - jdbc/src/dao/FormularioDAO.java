package dao;

import model.Formulario;

import java.sql.*;
import java.util.ArrayList;

public class FormularioDAO {
    private Connection connection;

    public FormularioDAO(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Formulario> listar() {
        ArrayList<Formulario> formularios = new ArrayList<Formulario>();

        String sqlStatment = "SELECT * FROM formularios;";
        try(PreparedStatement pstm = connection.prepareStatement(sqlStatment)) {
            pstm.execute();
            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    int id = rst.getInt(1);
                    String nome = rst.getString(2),
                            email = rst.getString(3),
                            cpf = rst.getString(4),
                            escolaridade = rst.getString(5);
                    formularios.add(new Formulario(id,nome,email,cpf,null,escolaridade,null));
                }
            }
            return formularios;
        }
        catch (SQLException e){
            System.out.println("Deu ruim");
            e.printStackTrace();
            return null;
        }
    }

    public int salvarFormularioERetornarID(Formulario formulario){
        String slq = "INSERT INTO formularios(nome,email,cpf,escolaridade) VALUES(?,?,?,?);";
        int id=0;
        try(PreparedStatement pstm = connection.prepareStatement(slq, Statement.RETURN_GENERATED_KEYS)){
            pstm.setString(1,formulario.getNome());
            pstm.setString(2,formulario.getEmail());
            pstm.setString(3,formulario.getCpf());
            pstm.setString(4,formulario.getEscolaridade());
            pstm.execute();
            try(ResultSet rst = pstm.getGeneratedKeys()){
                while (rst.next()) id = rst.getInt("id");
            }
        }catch (SQLException e){
            System.out.println("Deu ruim");
            e.printStackTrace();
        }
        return id;
    }

    public void excluir(int id){
        String slq = "DELETE FROM formularios WHERE id = ?;";
        try(PreparedStatement pstm = connection.prepareStatement(slq)){
            pstm.setInt(1,id);
            pstm.execute();
        }catch (SQLException e){
            System.out.println("Deu ruim");
            e.printStackTrace();
        }
    }

    public void editar(int id, Formulario formulario){
        String sql = "UPDATE formularios" +
                " SET nome = ?, email = ?, cpf = ?, escolaridade = ?" +
                " WHERE id = ?;";
        try(PreparedStatement pstm = connection.prepareStatement(sql)){
            pstm.setString(1, formulario.getNome());
            pstm.setString(2, formulario.getEmail());
            pstm.setString(3, formulario.getCpf());
            pstm.setString(4, formulario.getEscolaridade());
            pstm.setInt(5,id);
            pstm.execute();
        }catch (SQLException e){
            System.out.println("Deu ruim");
            e.printStackTrace();
        }
    }

}
