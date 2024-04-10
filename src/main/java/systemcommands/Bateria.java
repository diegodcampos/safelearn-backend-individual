package systemcommands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bateria {
    public static void main(String[] args) {
        try {
            Process powerShellProcess = Runtime.getRuntime().exec("powershell.exe Get-WmiObject Win32_Battery | Select-Object EstimatedChargeRemaining, Status, BatteryStatus | Format-Table -Property @{Label='Carga Restante %';Expression={$_.EstimatedChargeRemaining}}, @{Label='Status';Expression={$_.Status}}, @{Label='Status da Bateria';Expression={$_.BatteryStatus}} -AutoSize -Wrap");

            BufferedReader reader = new BufferedReader(new InputStreamReader(powerShellProcess.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();

            powerShellProcess.waitFor();
            System.out.println("status da bateria 1: desconectada da tomada\nstatus bateria 2: conectada na tomada");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
