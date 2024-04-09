import componentes.MemoriaRam;
import componentes.UsoProcessador;
import componentes.Sistema;
public class App {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        MemoriaRam memoriaram = new MemoriaRam();
        UsoProcessador processador = new UsoProcessador();

        sistema.exibirInformacoesSistema();
        memoriaram.exibirInformacoesMemoria();
        processador.exibirInformacoesProcessador();


    }
}


