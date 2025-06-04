import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import model.BeanUserFone;
import model.Telefone;
import model.UserPosJava;
import org.junit.Test;

import java.util.List;

public class TesteBanco {

    @Test
    public void testSalvarUsuario() {
        UserPosDAO userPosDAO = new UserPosDAO();
        UserPosJava userPosJava = new UserPosJava();
        userPosJava.setNome("EmilleGabrielle");
        userPosJava.setEmail("Emily@email.com");
        userPosDAO.salvar(userPosJava);
    }

    @Test
    public void testListarUsuarios() {
        UserPosDAO dao = new UserPosDAO();
        try {
            List<UserPosJava> usuarios = dao.listar();
            for (UserPosJava user : usuarios) {
                System.out.println(user);
                System.out.println("-------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBuscarUsuarioPorId() {
        UserPosDAO dao = new UserPosDAO();
        try {
            UserPosJava user = dao.buscar(3L);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAtualizarUsuario() {
        try {
            UserPosDAO dao = new UserPosDAO();
            UserPosJava user = dao.buscar(6L);
            user.setNome("Jonathas Gabriel");
            dao.atualizar(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeletarUsuario() {
        try {
            UserPosDAO dao = new UserPosDAO();
            dao.deletar(11L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSalvarTelefone() {
        Telefone telefone = new Telefone();
        telefone.setNumero("75 - 9214-6614");
        telefone.setTipo("cel");
        telefone.setUsuario(6L);

        UserPosDAO dao = new UserPosDAO();
        dao.salvarTelefone(telefone);
    }

    @Test
    public void testListarTelefonesPorUsuario() {
        UserPosDAO dao = new UserPosDAO();
        List<BeanUserFone> telefones = dao.listaUserFone(10L);

        for (BeanUserFone bean : telefones) {
            System.out.println(bean);
            System.out.println("----------------------");
        }
    }

    @Test
    public void testDeletarTelefonesPorUsuario() {
        UserPosDAO dao = new UserPosDAO();
        dao.deleteFonesPorUser(10L);
    }
}