import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import model.Telefone;
import model.UserPosJava;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class TesteBanco {
    @Test
    public void initBanco(){
        UserPosDAO userPosDAO = new UserPosDAO();
        UserPosJava userPosJava = new UserPosJava();


        userPosJava.setNome("EmilleGabrielle");
        userPosJava.setEmail("Emily@email.com");


        userPosDAO.salvar(userPosJava);
    }
    @Test
    public void initListar () {
        UserPosDAO dao = new UserPosDAO();
        try {
            List<UserPosJava> list = dao.listar();

            for (UserPosJava userPosJava : list) {
                System.out.println(userPosJava);
                System.out.println("-------------------------------");
                
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void initBuscar () {
        UserPosDAO dao = new UserPosDAO();
        try {
            UserPosJava userPosJava = dao.buscar(3L);
            System.out.println(userPosJava);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void initAtualizar(){
        try {
            UserPosDAO dao = new UserPosDAO();
            UserPosJava objetoBanco = dao.buscar(6L);
            objetoBanco.setNome("Jonathas Gabriel ");
            dao.atualizar(objetoBanco);
        } catch (Exception e) {
           e.printStackTrace();
        }



    }
    @Test
    public void initDeletar() {
       try {
           UserPosDAO dao = new UserPosDAO();
           dao.deletar(11L);

       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    @Test
    public void testeInsertTelefone(){
        Telefone telefone = new Telefone();
        telefone.setNumero("75 - 9214-6614");
        telefone.setTipo("cel");
        telefone.setUsuario(3L);

        UserPosDAO dao = new UserPosDAO();
        dao.salvarTelefone(telefone);
    }
}