import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SlackIntegracao {
    private static final String WEBHOOK_URL = "https://hooks.slack.com/services/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";

    //chamar sendAlert na logica ao verificar se o uso esta acima do esperado
    public static void sendAlert(String message) {
        try {
            URL url = new URL(WEBHOOK_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            String jsonPayload = "{\"text\":\"" + message + "\"}";
            try (OutputStream os = connection.getOutputStream()) {
                os.write(jsonPayload.getBytes());
                os.flush();
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Mensagem enviada com sucesso.");
            } else {
                System.out.println("Erro ao enviar a mensagem. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        sendAlert("⚠️ Alerta de Desempenho: O sistema está apresentando alta utilização de recursos. Verifique o status de CPU, RAM, disco ou outros componentes críticos para prevenir possíveis falhas.");
    }

}
