import java.io.IOException;

public class SystemCommandExecutor {
    public static void main(String[] args) {
        try{
            ProcessBuilder processBuilder = new ProcessBuilder("shutdown", "-s");
            Process process = processBuilder.start();
            Integer exitCode = process.waitFor();

            if (exitCode == 0){
                System.out.println("O sistema foi desligado com sucesso.");
            }else {
                System.out.println("Falha ao desligar o sistema.\nCódigo de saída: "+exitCode);
            }
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}