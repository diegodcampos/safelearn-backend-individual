package dao;

import conexao.Conexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    public boolean autenticarUsuario(String nomeUsuario, String senha) {
        String sql = "SELECT * FROM teste WHERE nomeUsuario = ? AND senha = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);

            ps.setString(1, nomeUsuario);
            ps.setString(2, senha);

            rs = ps.executeQuery();
            //
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
