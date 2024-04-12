package dao;

import componentes.Sistema;
import componentes.UsoDisco;
import componentes.UsoProcessador;
import conexao.Conexao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MaquinaDao {
    public void inserirDadosMaquina(UsoProcessador processador, Sistema sistema, UsoDisco disco) {
        String sql = "INSERT INTO maquina (idProcessador, modeloProcessador, fabricanteProcessador, identificador, microarquitetura, frequencia, cpusFisicas, cpusLogicas, so, arquitetura, fabricanteSo, modeloDisco, tamanhoDisco, modeloDisco2, tamanhoDisco2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            ps.setString(9, sistema.getSistemaOperacional());
            ps.setInt(10, sistema.getArquitetura());
            ps.setString(11, sistema.getFabricante());

            List<String> modelosDisco = disco.getModelosDisco();
            List<Long> tamanhosDisco = disco.getTamanhosDisco();

            if (modelosDisco.size() != tamanhosDisco.size()) {
                throw new IllegalArgumentException("O número de modelos de disco não corresponde ao número de tamanhos.");
            }

            Integer parameterIndex = 12;
            Integer parameterIndex2 = 13;
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
