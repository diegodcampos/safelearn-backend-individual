import componentes.MemoriaRam;
import componentes.Sistema;
public class App {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        MemoriaRam memoriaram = new MemoriaRam();
        sistema.exibirInformacoesSistema();
        memoriaram.exibirInformacoesMemoria();

    }
}


