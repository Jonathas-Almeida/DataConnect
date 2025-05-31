import conexaojdbc.SingleConnection;

import java.sql.Connection;

public class TesteBanco {
    public static void main(String[] args) {
        Connection conn = SingleConnection.getConnection();
        if (conn != null) {
            System.out.println("Conexão obtida com sucesso!");
        } else {
            System.out.println("Falha na conexão!");
        }


        SingleConnection.closeConnection();
    }
}