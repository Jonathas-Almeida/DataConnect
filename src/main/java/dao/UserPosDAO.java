package dao;

import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.UserPosJava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO (Data Access Object) responsável por gerenciar operações
 * de acesso aos dados das tabelas userposjava e telefoneuser
 */
public class UserPosDAO {

    private Connection connection;

    /**
     * Construtor que inicializa a conexão com o banco de dados
     */
    public UserPosDAO() {
        connection = SingleConnection.getConnection();
    }

    /**
     * Salva um novo usuário no banco de dados
     * @param userPosJava objeto contendo os dados do usuário a ser salvo
     */
    public void salvar(UserPosJava userPosJava) {
        try {
            // SQL para inserir um novo usuário na tabela
            String sql = "insert into userposjava (nome, email ) values (?,?)";
            PreparedStatement insert = connection.prepareStatement(sql);

            // Define os parâmetros da query
            insert.setString(1, userPosJava.getNome());
            insert.setString(2, userPosJava.getEmail());

            // Executa a inserção
            insert.execute();
            connection.commit(); // Confirma a transação no banco

        } catch (Exception e) {
            try {
                connection.rollback(); // Desfaz a operação em caso de erro
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    /**
     * Lista todos os usuários cadastrados no banco de dados
     * @return Lista contendo todos os usuários
     * @throws Exception em caso de erro na consulta
     */
    public List<UserPosJava> listar() throws Exception {
        List<UserPosJava> list = new ArrayList<UserPosJava>();

        // SQL para buscar todos os usuários
        String sql = "select * from userposjava";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado = statement.executeQuery();

        // Percorre todos os resultados da consulta
        while (resultado.next()) {
            UserPosJava userPosJava = new UserPosJava();

            // Mapeia os dados do ResultSet para o objeto
            userPosJava.setId(resultado.getLong("id"));
            userPosJava.setNome(resultado.getString("nome"));
            userPosJava.setEmail(resultado.getString("email"));

            list.add(userPosJava);
        }

        return list;
    }

    /**
     * Busca um usuário específico pelo ID
     * @param id ID do usuário a ser buscado
     * @return Objeto UserPosJava com os dados do usuário encontrado
     * @throws Exception em caso de erro na consulta
     */
    public UserPosJava buscar(Long id) throws Exception {
        UserPosJava retorno = new UserPosJava();

        // SQL para buscar usuário por ID (concatenação direta - vulnerável a SQL Injection)
        String sql = "select * from userposjava where id = " + id;

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado = statement.executeQuery();

        // Processa o resultado (deve retornar apenas um registro)
        while (resultado.next()) {
            UserPosJava userPosJava = new UserPosJava();
            retorno.setId(resultado.getLong("id"));
            retorno.setNome(resultado.getString("nome"));
            retorno.setEmail(resultado.getString("email"));
        }

        return retorno;
    }

    /**
     * Atualiza os dados de um usuário existente
     * @param userPosJava objeto contendo os novos dados do usuário
     */
    public void atualizar(UserPosJava userPosJava) {
        try {
            // SQL para atualizar apenas o nome do usuário (não atualiza email)
            String sql = "update userposjava set nome = ? WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userPosJava.getNome());
            statement.setLong(2, userPosJava.getId());

            statement.execute();
            connection.commit(); // Confirma a transação

        } catch (Exception e) {
            try {
                connection.rollback(); // Desfaz a operação em caso de erro
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }

    /**
     * Remove um usuário do banco de dados
     * @param id ID do usuário a ser removido
     */
    public void deletar(Long id) {
        try {
            // SQL para deletar usuário (concatenação direta - vulnerável a SQL Injection)
            String sql = "delete from userposjava where id = " + id;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            connection.commit();

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }

    /**
     * Salva um telefone associado a um usuário
     * @param telefone objeto contendo os dados do telefone
     */
    public void salvarTelefone(Telefone telefone) {
        try {
            // SQL para inserir um novo telefone na tabela telefoneuser
            String sql = "INSERT INTO telefoneuser (numero,tipo,usuariopessoa) VALUES (?,?,?);";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Define os parâmetros da query
            statement.setString(1, telefone.getNumero());
            statement.setString(2, telefone.getTipo());
            statement.setLong(3, telefone.getUsuario());

            statement.execute();
            connection.commit();

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * Lista todos os telefones de um usuário específico com seus dados
     * @param idUser ID do usuário
     * @return Lista de objetos BeanUserFone contendo dados do usuário e telefones
     */
    public List<BeanUserFone> listaUserFone(Long idUser) {
        List<BeanUserFone> beanUserFones = new ArrayList<BeanUserFone>();

        // Construção da query SQL com JOIN entre as tabelas
        String sql = " select nome,numero, email from telefoneuser as fone ";
        sql += " inner join userposjava as userp";
        sql += " on fone.usuariopessoa = userp.id ";
        sql += "where userp.id = " + idUser; // Concatenação direta - vulnerável a SQL Injection

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            // Percorre todos os resultados da consulta
            while (resultSet.next()) {
                BeanUserFone userFone = new BeanUserFone();
                userFone.setEmail(resultSet.getString("email"));
                userFone.setNumero(resultSet.getString("nome")); // BUG: setando nome como numero
                userFone.setNumero(resultSet.getString("numero")); // Sobrescreve o valor anterior
                beanUserFones.add(userFone);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return beanUserFones;
    }

    /**
     * Remove todos os telefones de um usuário e depois remove o próprio usuário
     * @param idUser ID do usuário a ser removido completamente
     */
    public void deleteFonesPorUser(Long idUser) {
        try {
            // SQL para deletar telefones do usuário (concatenação direta - vulnerável a SQL Injection)
            String sqlFone = "delete from telefoneuser where usuariopessoa=" + idUser;
            // SQL para deletar o usuário (concatenação direta - vulnerável a SQL Injection)
            String sqlUser = "delete from userposjava where id=" + idUser;

            // Remove primeiro os telefones (devido à chave estrangeira)
            PreparedStatement preparedStatement = connection.prepareStatement(sqlFone);
            preparedStatement.executeUpdate();
            connection.commit();

            // Depois remove o usuário
            preparedStatement = connection.prepareStatement(sqlUser);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}