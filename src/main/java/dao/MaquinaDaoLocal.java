package dao;

import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.janelas.Janela;
import componentes.*;
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
        String sql = "INSERT INTO maquina (idProcessador, nome, sistemaOperacional,tempoAtividade, fkinstituicao) VALUES (?,?,?,?,?);";
        ConexaoLocal conexaoLocal = new ConexaoLocal();
        Connection connectionLocal = conexaoLocal.getConexao();
        PreparedStatement ps = null;

        try {
            connectionLocal.setAutoCommit(false);

            ps = connectionLocal.prepareStatement(sql);
            ps.setString(1, processador.getId());
            ps.setString(2, processador.getNome());
            ps.setString(3, sistema.getSistemaOperacional());
            ps.setLong(4, sistema.getTempoAtividade());
            ps.setInt(5, fkInstituicao);
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
        try (Connection connectionLocal = conexaoLocal.getConexao();
             PreparedStatement ps = connectionLocal.prepareStatement(sql)) {

            ps.setString(1, processador.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    idsComponentes.add(rs.getInt("idComponente"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
        return idsComponentes;
    }

    @Override
    public void monitoramento(UsoProcessador processador, MemoriaRam memoria, UsoDisco disco, List<Integer> idsComponentes) {
        if (idsComponentes == null || idsComponentes.isEmpty() || idsComponentes.size() < 2) {
            throw new IllegalStateException("idsComponentes is null, empty, or does not contain enough elements");
        }

        String sql = "INSERT INTO registro (valorCaptura, fkComponente, fkMaquina) VALUES (?, ?, ?)";
        ConexaoLocal conexaoLocal = new ConexaoLocal();
        Connection connectionLocal = conexaoLocal.getConexao();
        PreparedStatement ps = null;

        try {
            connectionLocal.setAutoCommit(false);

            ps = connectionLocal.prepareStatement(sql);

            // Processador
            ps.setDouble(1, processador.getUso());
            ps.setInt(2, idsComponentes.get(0));
            ps.setString(3, processador.getId());
            ps.addBatch();

            // Memória
            ps.setDouble(1, memoria.getMemoriaEmUso());
            ps.setInt(2, idsComponentes.get(1));
            ps.setString(3, processador.getId());
            ps.addBatch();

            // Discos
            List<Long> volumes = disco.getEspacoDisponivel();
            Integer j = 2;
            for (int i = 0; i < volumes.size(); i++) {
                if (j >= idsComponentes.size()) {
                    throw new IndexOutOfBoundsException("Not enough idsComponentes for all disks");
                }
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
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Erro: " + e);
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
    public void inserirDadosProcessso(UsoProcessador processador, GrupoJanelas janelas) {
        String sql = "INSERT INTO processo (nomeProcesso, fkMaquina) VALUES (?,?)";
        ConexaoLocal conexaoLocal = new ConexaoLocal();
        Connection connectionLocal = null;
        PreparedStatement ps = null;

        try {
            connectionLocal = conexaoLocal.getConexao();
            connectionLocal.setAutoCommit(false);
            ps = connectionLocal.prepareStatement(sql);

            for (Janela janela : janelas.getJanelas()) {
                ps.setString(1, janela.getTitulo());
                ps.setString(2, processador.getId());
                ps.addBatch();
            }

            ps.executeBatch();
            connectionLocal.commit();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            if (connectionLocal != null) {
                try {
                    connectionLocal.rollback();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fazer rollback: " + ex);
                }
            }
        } finally {
            try {
                if (ps != null) ps.close();
                if (connectionLocal != null) {
                    connectionLocal.setAutoCommit(true);
                    connectionLocal.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro: " + e);
            }
        }
    }

    @Override
    public void inserirDadosBateria(UsoProcessador processador, Bateria bateria) {
        String sql = "INSERT INTO bateria (porcentagemBateria, statusEnergia, fkMaquina) VALUES (?,?,?)";
        ConexaoLocal conexaoLocal = new ConexaoLocal();
        Connection connectionLocal = null;
        PreparedStatement ps = null;

        try {
            connectionLocal = conexaoLocal.getConexao();
            connectionLocal.setAutoCommit(false);
            ps = connectionLocal.prepareStatement(sql);

            ps.setDouble(1, bateria.getCapacidadeEmPorcentagem());
            ps.setBoolean(2, bateria.isConectadoNaTomada());
            ps.setString(3, processador.getId());

            ps.executeUpdate();

            connectionLocal.commit();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            if (connectionLocal != null) {
                try {
                    connectionLocal.rollback();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fazer rollback: " + ex);
                }
            }
        } finally {
            try {
                if (ps != null) ps.close();
                if (connectionLocal != null) {
                    connectionLocal.setAutoCommit(true);
                    connectionLocal.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro: " + e);
            }
        }
    }
}
