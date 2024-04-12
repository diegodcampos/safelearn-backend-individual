
import componentes.UsoProcessador;
import setup.Logar;

public class App {
    public static void main(String[] args) {
        UsoProcessador processador = new UsoProcessador();
        processador.exibirInformacoesProcessador();

        Logar logar = new Logar();
        logar.logar();

    }
}


