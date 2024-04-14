
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

        //        System.out.println("\n"
//                + "  SSSS   AAA   FFFFF  EEEEE     L     EEEEE  AAA  RRRR   N   N\n"
//                + " S      A   A  F      E         L     E     A   A R   R  NN  N\n"
//                + "  SSS   AAAAA  FFF    EEEE      L     EEEE  AAAAA RRRR   N N N\n"
//                + "     S  A   A  F      E         L     E     A   A R  R   N  NN\n"
//                + " SSSS   A   A  F      EEEEE     LLLLL EEEEE A   A R   R  N   N\n"
//                );
        Logar logar = new Logar();
        logar.logar();

    }
}


