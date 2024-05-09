package dao;

import conexao.Conexao;
import setup.Logar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    public boolean autenticarUsuario(String nomeUsuario, String senha) {
        String sql = "SELECT * FROM usuario WHERE userName = ? AND senhaUsuario = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);

            ps.setString(1, nomeUsuario);
            ps.setString(2, senha);

            rs = ps.executeQuery();

            while (rs.next()) {
                String nomeUsuarioBanco = rs.getString("userName");
                String senhaBanco = rs.getString("senhaUsuario");

                if (nomeUsuario.equals(nomeUsuarioBanco) && senha.equals(senhaBanco)) {
                    return true;
                }
            }

            return false;
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

    public Integer getFkInstituicao(String nomeUsuario) {
        String sql = "SELECT fkInstituicao FROM usuario WHERE userName = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nomeUsuario);
            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt("fkInstituicao");
            }

            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
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

