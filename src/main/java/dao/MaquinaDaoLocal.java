package dao;

import com.github.britooo.looca.api.group.discos.Disco;
import componentes.MemoriaRam;
import componentes.Sistema;
import componentes.UsoDisco;
import componentes.UsoProcessador;
import conexao.ConexaoLocal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaquinaDaoLocal extends MaquinaDao {
    @Override
    public Boolean verificarRegistro(UsoProcessador processador) {
        String sql = "SELECT * FROM maquina WHERE idProcessador = (?)";
        ConexaoLocal conexaoLocal = new ConexaoLocal();
        Connection connectionLocal = conexaoLocal.getConexao();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connectionLocal.prepareStatement(sql);
            ps.setString(1, processador.getId());
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                connectionLocal.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e);
            }
        }
    }

    @Override
    public void inserirDadosMaquina(UsoProcessador processador, Sistema sistema, Integer fkInstituicao) {
        String sql = "INSERT INTO maquina (idProcessador, nome, sistemaOperacional, fkinstituicao) VALUES (?,?,?,?);";
        ConexaoLocal conexaoLocal = new ConexaoLocal();
        Connection connectionLocal = conexaoLocal.getConexao();
        PreparedStatement ps = null;

        try {
            connectionLocal.setAutoCommit(false);

            ps = connectionLocal.prepareStatement(sql);
            ps.setString(1, processador.getId());
            ps.setString(2, processador.getNome());
            ps.setString(3, sistema.getSistemaOperacional());
            ps.setInt(4, fkInstituicao);
            ps.executeUpdate();

            connectionLocal.commit();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            try {
                connectionLocal.rollback();
            } catch (SQLException ex) {
                System.out.println("Erro ao fazer rollback: " + ex);
            }
        } finally {
            try {
                if (ps != null) ps.close();
                connectionLocal.setAutoCommit(true);
                connectionLocal.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e);
            }
        }
    }

    @Override
    public void inserirDadosComponente(UsoProcessador processador, MemoriaRam memoria, UsoDisco disco) {
        String sql = "INSERT INTO componente (nomeComponente, especificacaoComponente, unidadeDeMedida, fkMaquina) VALUES (?, ?, ?, ?)";
        ConexaoLocal conexaoLocal = new ConexaoLocal();
        Connection connectionLocal = conexaoLocal.getConexao();
        PreparedStatement ps = null;

        try {
            connectionLocal.setAutoCommit(false);

            ps = connectionLocal.prepareStatement(sql);

            ps.setString(1, "processador");
            ps.setDouble(2, processador.getFrequencia() / 1e9);
            ps.setString(3, "GHz");
            ps.setString(4, processador.getId());
            ps.addBatch();

            ps.setString(1, "memoria");
            ps.setDouble(2, memoria.getMemoriaTotal());
            ps.setString(3, "GB");
            ps.setString(4, processador.getId());
            ps.addBatch();

            List<Disco> discos = disco.getDiscos();
            for (Disco discoDaVez : discos) {
                ps.setString(1, "disco");
                ps.setDouble(2, discoDaVez.getTamanho() / (1024 * 1024 * 1024));
                ps.setString(3, "GB");
                ps.setString(4, processador.getId());
                ps.addBatch();
            }

            ps.executeBatch();
            connectionLocal.commit();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            try {
                connectionLocal.rollback();
            } catch (SQLException ex) {
                System.out.println("Erro ao fazer rollback: " + ex);
            }
        } finally {
            try {
                if (ps != null) ps.close();
                connectionLocal.setAutoCommit(true);
                connectionLocal.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e);
            }
        }
    }

    @Override
    public List<Integer> getIdsComponentes(UsoProcessador processador) {
        List<Integer> idsComponentes = new ArrayList<>();
        String sql = "SELECT idComponente FROM componente WHERE fkMaquina = ? ORDER BY CASE WHEN nomeComponente = 'processador' THEN 1 WHEN nomeComponente = 'memoria' THEN 2 WHEN nomeComponente = 'disco' THEN 3 END";
        ConexaoLocal conexaoLocal = new ConexaoLocal();
        Connection connectionLocal = conexaoLocal.getConexao();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connectionLocal.prepareStatement(sql);
            ps.setString(1, processador.getId());
            rs = ps.executeQuery();

            while (rs.next()) {
                idsComponentes.add(rs.getInt("idComponente"));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                connectionLocal.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e);
            }
        }
        return idsComponentes;
    }

    @Override
    public void monitoramento(UsoProcessador processador, MemoriaRam memoria, UsoDisco disco, List<Integer> idsComponentes) {
        String sql = "INSERT INTO registro (valorCaptura, fkComponente, fkMaquina) VALUES (?, ?, ?)";
        ConexaoLocal conexaoLocal = new ConexaoLocal();
        Connection connectionLocal = conexaoLocal.getConexao();
        PreparedStatement ps = null;

        try {
            connectionLocal.setAutoCommit(false);

            ps = connectionLocal.prepareStatement(sql);

            ps.setDouble(1, processador.getUso());
            ps.setInt(2, idsComponentes.get(0));
            ps.setString(3, processador.getId());
            ps.addBatch();

            ps.setDouble(1, memoria.getMemoriaEmUso());
            ps.setInt(2, idsComponentes.get(1));
            ps.setString(3, processador.getId());
            ps.addBatch();

            List<Long> volumes = disco.getEspacoDisponivel();
            Integer j = 2;
            for (int i = 0; i < volumes.size(); i++) {
                ps.setDouble(1, volumes.get(i));
                ps.setInt(2, idsComponentes.get(j));
                ps.setString(3, processador.getId());
                ps.addBatch();
                j++;
            }

            ps.executeBatch();
            connectionLocal.commit();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            try {
                connectionLocal.rollback();
            } catch (SQLException ex) {
                System.out.println("Erro ao fazer rollback: " + ex);
            }
        } finally {
            try {
                if (ps != null) ps.close();
                connectionLocal.setAutoCommit(true);
                connectionLocal.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e);
            }
        }
    }
}
