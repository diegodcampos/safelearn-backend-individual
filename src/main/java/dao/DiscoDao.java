package dao;
import componentes.UsoDisco;
import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DiscoDao {
    public void inserirDadosDisco(UsoDisco disco) {
        String sql = "INSERT INTO dadosDisco (modelo, tamanho_total) VALUES (?, ?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);

            List<String> modelosDisco = disco.getModelosDisco();
            List<Long> tamanhosDisco = disco.getTamanhosDisco();

            if (modelosDisco.size() != tamanhosDisco.size()) {
                throw new IllegalArgumentException("O número de modelos de disco não corresponde ao número de tamanhos.");
            }

            for (int i = 0; i < modelosDisco.size(); i++) {
                ps.setString(1, modelosDisco.get(i));
                ps.setLong(2, tamanhosDisco.get(i));
                ps.executeUpdate();
            }

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}