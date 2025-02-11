package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

public class BancoDados {

    private static Connection conn = null;

    // Método para conectar ao banco
    public static Connection conectar() throws IOException, SQLException {
    	if (conn == null) {
			Properties props = carregarPropriedades();
			String url = props.getProperty("dburl");
			conn = DriverManager.getConnection(url, props);
		}
		return conn;
    }

    // Método para desconectar
    public static void desconectar() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage(),
                        "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para carregar propriedades do arquivo
    private static Properties carregarPropriedades() throws IOException {
        Properties props = new Properties();
        try (FileInputStream propriedadesBanco = new FileInputStream("database.properties")) {
            props.load(propriedadesBanco);
        }
        return props;
    }

    // Método para fechar Statement
    public static void finalizarStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar Statement: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para fechar ResultSet
    public static void finalizarResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar ResultSet: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
