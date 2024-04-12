package dao;

import componentes.Sistema;
import conexao.Conexao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SistemaDao {
    public void inserirDadosSistema(Sistema sistema) {
        String sql = "INSERT INTO dadosMaquina (sistemaOperacional, arquitetura, fabricante) VALUES (?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);

            ps.setString(1, sistema.getSistemaOperacional());
            ps.setInt(2, sistema.getArquitetura());
            ps.setString(3, sistema.getFabricante());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }
}
