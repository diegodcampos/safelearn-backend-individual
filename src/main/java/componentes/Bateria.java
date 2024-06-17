package componentes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Bateria extends Componente {

    private boolean conectadoNaTomada;
    private double capacidadeEmPorcentagem;

    public Bateria() {
        inicializarInformacoes();
    }

    @Override
    public void inicializarInformacoes() {
        if (isWindows()) {
            obterStatusBateriaWindows();
            obterCapacidadeBateriaWindows();
        } else if (isLinux()) {
            obterStatusBateriaLinux();
            obterCapacidadeBateriaLinux();
        } else {
            System.out.println("Sistema operacional não suportado");
        }
    }

    private void obterStatusBateriaWindows() {
        try {
            Process process = Runtime.getRuntime().exec("wmic path Win32_Battery get BatteryStatus");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().length() > 0 && !line.contains("BatteryStatus")) {
                        String[] parts = line.trim().split("\\s+");
                        if (parts.length == 1) {
                            try {
                                int status = Integer.parseInt(parts[0]);
                                conectadoNaTomada = (status != 1);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void obterCapacidadeBateriaWindows() {
        try {
            Process process = Runtime.getRuntime().exec("wmic path Win32_Battery get EstimatedChargeRemaining");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().length() > 0 && !line.contains("EstimatedChargeRemaining")) {
                        String[] parts = line.trim().split("\\s+");
                        if (parts.length == 1) {
                            try {
                                capacidadeEmPorcentagem = Double.parseDouble(parts[0]);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void obterStatusBateriaLinux() {
        try {
            Process process = Runtime.getRuntime().exec("upower -i $(upower -e | grep BAT) | grep state");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("state:")) {
                        String state = line.split(":")[1].trim();
                        conectadoNaTomada = !state.equals("discharging");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void obterCapacidadeBateriaLinux() {
        try {
            Process process = Runtime.getRuntime().exec("upower -i $(upower -e | grep BAT) | grep percentage");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("percentage:")) {
                        String percentage = line.split(":")[1].trim().replace("%", "");
                        try {
                            capacidadeEmPorcentagem = Double.parseDouble(percentage);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    private boolean isLinux() {
        return System.getProperty("os.name").toLowerCase().contains("nix") ||
                System.getProperty("os.name").toLowerCase().contains("nux") ||
                System.getProperty("os.name").toLowerCase().contains("aix");
    }

    public boolean isConectadoNaTomada() {
        return conectadoNaTomada;
    }

    public double getCapacidadeEmPorcentagem() {
        return capacidadeEmPorcentagem;
    }

    @Override
    public String toString() {
        return String.format("\n"
                        + "______________________________\n"
                        + "|     Status da Bateria      |\n"
                        + "|----------------------------|\n"
                        + "| Conectado na Tomada: %s    |\n"
                        + "| Capacidade da Bateria: %.2f%% |\n"
                        + "______________________________\n",
                conectadoNaTomada ? "Sim" : "Não", capacidadeEmPorcentagem);
    }
}
