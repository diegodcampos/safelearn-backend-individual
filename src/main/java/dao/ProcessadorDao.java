package dao;

import componentes.UsoProcessador;
import conexao.Conexao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProcessadorDao {
    public void inserirDadosProcessador(UsoProcessador processador) {
        String sql = "INSERT INTO dadosProcessador (id, modelo, fabricante, frequencia, identificador, cpusFisicas, microarquitetura, cpusLogicas) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);

            ps.setString(1, processador.getId());
            ps.setString(2, processador.getNome());
            ps.setString(3, processador.getFabricante());
            ps.setLong(4, processador.getFrequencia());
            ps.setString(5, processador.getIdentificador());
            ps.setInt(6, processador.getNumeroCpusFisicas());
            ps.setString(7, processador.getMicroarquitetura());
            ps.setInt(8, processador.getNumeroCpusLogicas());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
