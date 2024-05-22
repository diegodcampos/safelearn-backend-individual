package conexao;

public class ConexaoLocal extends Conexao{
    @Override
    protected String getUser() {
        return "root";
    }

    @Override
    protected String getUrl() {
        return "jdbc:mysql://localhost:3306/safelearn";
    }
}
