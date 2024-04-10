package systemcommands;

import java.io.IOException;


public class SystemCommandExecutor {
    public static void shutdown(){
        executeCommand("shutdown", "-s");
    }
    public static void restart(){
        executeCommand("shutdown", "-r");
    }
    public static void suspend() {
        executeCommand("shutdown", "-h");
    }

    private static void executeCommand(String... command) {
        try{
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();
            Integer exitCode = process.waitFor();
            if (exitCode == 0){
                System.out.println("Comando executado com sucesso");
            }else {
                System.out.println("Falha ao executar o comando.\nCódigo de saída: "+exitCode);
            }
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}