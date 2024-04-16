
import componentes.GrupoJanelas;
import componentes.MemoriaRam;
import componentes.UsoDisco;
import componentes.UsoProcessador;
import setup.Logar;

public class App {
    public static void main(String[] args) {
        UsoProcessador processador = new UsoProcessador();
        MemoriaRam memoriaram = new MemoriaRam();
        UsoDisco usoDisco = new UsoDisco();
        GrupoJanelas grupoJanelas = new GrupoJanelas();

        System.out.println(grupoJanelas.toString());
        System.out.println(processador.toStringSimplified());
        System.out.println(memoriaram.toString());
        usoDisco.exibirInformacoesDeDiscos();

        Logar logar = new Logar();
        logar.logar();

    }
}


