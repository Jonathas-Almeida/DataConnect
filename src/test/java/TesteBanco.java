import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import model.UserPosJava;
import org.junit.Test;

import java.sql.Connection;

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
}