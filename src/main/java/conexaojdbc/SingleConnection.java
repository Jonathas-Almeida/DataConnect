package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

    private static String url = "jdbc:postgresql://localhost:5433/posjava";
    private static String password = "admin";
    private static String user = "postgres";
    private static Connection connection = null;

    static {
        conectar();
    }

    public SingleConnection() {
        conectar();
    }

    private static void conectar() {
        try {
            if (connection == null) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
                System.out.println("Conexão estabelecida com sucesso!");
            }
        } catch (Exception e) {
            System.err.println("Erro ao conectar com o banco de dados:");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
                System.out.println("Conexão fechada com sucesso!");
            }
        } catch (Exception e) {
            System.err.println("Erro ao fechar a conexão:");
            e.printStackTrace();
        }
    }
}