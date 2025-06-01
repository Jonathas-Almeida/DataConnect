import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import model.UserPosJava;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class TesteBanco {
    @Test
    public void initBanco(){
        UserPosDAO userPosDAO = new UserPosDAO();
        UserPosJava userPosJava = new UserPosJava();

        userPosJava.setId(6L);
        userPosJava.setNome("Jonatha");
        userPosJava.setEmail("Jonatha@email.com");


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
}