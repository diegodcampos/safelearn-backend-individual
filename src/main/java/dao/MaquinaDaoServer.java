package dao;

import com.github.britooo.looca.api.group.discos.Disco;
import componentes.MemoriaRam;
import componentes.Sistema;
import componentes.UsoDisco;
import componentes.UsoProcessador;
import conexao.ConexaoServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaquinaDaoServer extends MaquinaDao{
    @Override
    public Boolean verificarRegistro(UsoProcessador processador) {

        String sql = "SELECT * FROM maquina WHERE idProcessador = (?)";
        ConexaoServer conexaoServer = new ConexaoServer();
        Connection connectionServer = conexaoServer.getConexao();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connectionServer.prepareStatement(sql);

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
            } catch (SQLException e) {
                System.out.println("Erro: " + e);
            }
        }
    }
    @Override
    public void inserirDadosMaquina(UsoProcessador processador, Sistema sistema, Integer fkInstituicao) {

        String sql = "INSERT INTO maquina (idProcessador,nome,sistemaOperacional, fkinstituicao) VALUES (?,?,?,?);";
        ConexaoServer conexaoServer = new ConexaoServer();
        Connection connectionServer = conexaoServer.getConexao();
        PreparedStatement ps = null;

        try {

            ps = connectionServer.prepareStatement(sql);

            ps.setString(1, processador.getId());
            ps.setString(2, processador.getNome());
            ps.setString(3, sistema.getSistemaOperacional());
            ps.setInt(4, fkInstituicao);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }
    @Override
    public void inserirDadosComponente(UsoProcessador processador, MemoriaRam memoria, UsoDisco disco) {

        String sql = "INSERT INTO componente (nomeComponente, especificacaoComponente, unidadeDeMedida, fkMaquina) VALUES (?, ?, ?, ?)";
        ConexaoServer conexaoServer = new ConexaoServer();
        Connection connectionServer = conexaoServer.getConexao();
        PreparedStatement ps = null;

        try {

            ps = connectionServer.prepareStatement(sql);

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
                ps.setString(4, processador.getId());;
                ps.addBatch();
            }

            ps.executeBatch();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }
    @Override
    public List<Integer> getIdsComponentes(UsoProcessador processador) {

        List<Integer> idsComponentes = new ArrayList<>();
        String sql = "SELECT idComponente FROM componente WHERE fkMaquina = ? ORDER BY CASE WHEN nomeComponente = 'processador' THEN 1  WHEN nomeComponente = 'memoria' THEN 2 WHEN nomeComponente = 'disco' THEN 3 END";
        ConexaoServer conexaoServer = new ConexaoServer();
        Connection connectionServer = conexaoServer.getConexao();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = connectionServer.prepareStatement(sql);
            ps.setString(1, processador.getId());
            rs = ps.executeQuery();

            while(rs.next()) {
                idsComponentes.add(rs.getInt("idComponente"));
            }

        } catch(SQLException e) {
            System.out.println("Erro: " + e);
        }
        return idsComponentes;
    }
}
