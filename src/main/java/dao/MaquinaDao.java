package dao;

import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.memoria.Memoria;
import componentes.MemoriaRam;
import componentes.Sistema;
import componentes.UsoDisco;
import componentes.UsoProcessador;
import conexao.Conexao;
import oshi.util.Memoizer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaquinaDao {
    public Boolean verificarRegistro(UsoProcessador processador) {
        String sql = "SELECT * FROM maquina WHERE idProcessador = (?)";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);

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



    public void inserirDadosMaquina(UsoProcessador processador, Sistema sistema) {

        String sql = "INSERT INTO maquina (idProcessador,nome,sistemaOperacional) VALUES (?,?,?);";
        PreparedStatement ps = null;

        try {

            ps = Conexao.getConexao().prepareStatement(sql);

            ps.setString(1, processador.getId());
            ps.setString(2, processador.getNome());
            ps.setString(3, sistema.getSistemaOperacional());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }

    }

    public void inserirDadosComponente(UsoProcessador processador, MemoriaRam memoria, UsoDisco disco) {


        String sql = "INSERT INTO componente (nomeComponente, valorComponente, tipoCaptura, fkProcessador) VALUES (?, ?, ?, ?);";
        PreparedStatement ps = null;

        try {

            ps = Conexao.getConexao().prepareStatement(sql);

            ps.setString(1, "processador");
            ps.setDouble(2, processador.getFrequencia());
            ps.setString(3, "Gz");
            ps.setString(4, processador.getId());
            ps.executeUpdate();

            ps.setString(5, "memoria");
            ps.setDouble(6, memoria.getMemoriaTotal());
            ps.setString(7, "Gb");
            ps.setString(8, processador.getId());
            ps.executeUpdate();

            List<Long> tamanhosDisco = disco.getTamanhosDisco();
            for (Long tamanho : tamanhosDisco) {
                ps.setString(9, "disco");
                ps.setDouble(10, tamanho);
                ps.setString(11, "Gb");
                ps.setString(12, processador.getId());
                ps.executeUpdate();
            }


            ps.close();

        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }
}

