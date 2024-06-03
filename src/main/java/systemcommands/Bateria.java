package systemcommands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bateria {
    //Essas são as variaveis principais
    public int cargaRestante;
    public int statusBateria;

    public void verificarBateria() {
        try {
            Process powerShellProcess = Runtime.getRuntime().exec(
                    "powershell.exe Get-WmiObject Win32_Battery | Select-Object EstimatedChargeRemaining, Status, BatteryStatus | Format-Table -Property @{Label='Carga Restante %';Expression={$_.EstimatedChargeRemaining}}, @{Label='Status';Expression={$_.Status}}, @{Label='Status da Bateria';Expression={$_.BatteryStatus}} -AutoSize -Wrap"
            );

            BufferedReader reader = new BufferedReader(new InputStreamReader(powerShellProcess.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.contains("Carga Restante %")) {
                    reader.readLine();
                } else if (line.trim().length() > 0) {
                    String[] parts = line.trim().split("\\s+");
                    if (parts.length >= 3) {
                        try {
                            cargaRestante = Integer.parseInt(parts[0]);
                            statusBateria = Integer.parseInt(parts[2]);
                        } catch (NumberFormatException e) {
                            System.out.println("Erro ao analisar os valores: " + e.getMessage());
                        }
                    }
                }
            }
            reader.close();

            powerShellProcess.waitFor();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro de IO: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("O processo foi interrompido: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Status da bateria '1': desconectada da tomada\nStatus da bateria '2': conectada na tomada");
        }
    }
}
