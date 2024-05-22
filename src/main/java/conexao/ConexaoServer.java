package conexao;

public class ConexaoServer extends Conexao{
    @Override
    protected String getUser() {
        return "sa";
    }

    @Override
    protected String getUrl() {
        return "jdbc:sqlserver://52.73.82.233:1433;databaseName=safelearn;encrypt=true;trustServerCertificate=true";
    }
}
