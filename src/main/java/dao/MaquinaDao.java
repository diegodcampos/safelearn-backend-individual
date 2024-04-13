package dao;

import componentes.MemoriaRam;
import componentes.Sistema;
import componentes.UsoDisco;
import componentes.UsoProcessador;
import conexao.Conexao;
import oshi.util.Memoizer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MaquinaDao {
    public void inserirDadosMaquina(UsoProcessador processador, Sistema sistema, UsoDisco disco, MemoriaRam memoriaRam) {
        String sql = "INSERT INTO maquina (idProcessador, modeloProcessador, fabricanteProcessador, identificador, microarquitetura, frequencia, cpusFisicas, cpusLogicas, usoProcessador, so, arquitetura, fabricanteSo, memoriaRamTotal, memoriaRamEmUso, memoriaRamDisponivel, modeloDisco, tamanhoDisco, modeloDisco2, tamanhoDisco2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);

            ps.setString(1, processador.getId());
            ps.setString(2, processador.getNome());
            ps.setString(3, processador.getFabricante());
            ps.setString(4, processador.getIdentificador());
            ps.setString(5, processador.getMicroarquitetura());
            ps.setLong(6, processador.getFrequencia());
            ps.setInt(7, processador.getNumeroCpusFisicas());
            ps.setInt(8, processador.getNumeroCpusLogicas());
            ps.setDouble(9, processador.getUso());
            ps.setString(10, sistema.getSistemaOperacional());
            ps.setInt(11, sistema.getArquitetura());
            ps.setString(12, sistema.getFabricante());
            ps.setDouble(13, memoriaRam.getMemoriaTotal());
            ps.setDouble(14, memoriaRam.getMemoriaEmUso());
            ps.setDouble(15, memoriaRam.getMemoriaDisponivel());

            List<String> modelosDisco = disco.getModelosDisco();
            List<Long> tamanhosDisco = disco.getTamanhosDisco();

            if (modelosDisco.size() != tamanhosDisco.size()) {
                throw new IllegalArgumentException("O número de modelos de disco não corresponde ao número de tamanhos.");
            }

            Integer parameterIndex = 16;
            Integer parameterIndex2 = 17;
            for (int i = 0; i < modelosDisco.size(); i++) {
                ps.setString(parameterIndex, modelosDisco.get(i));
                ps.setLong(parameterIndex2, tamanhosDisco.get(i));
                parameterIndex += 2;
                parameterIndex2 += 2;
            }
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }
}
