package componentes;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
public class MemoriaRam {
    private Long memoriaTotal;
    private Long memoriaEmUso;
    private Long memoriaDisponivel;
    public void captarInformacoesMemoria() {
        Looca looca = new Looca();
        Memoria memoria = looca.getMemoria();

        memoriaTotal = memoria.getTotal();
        memoriaEmUso = memoria.getEmUso();
        memoriaDisponivel = memoria.getDisponivel();
    }

    public void exibirInformacoesMemoria() {
        captarInformacoesMemoria();
        System.out.println("\n------Informações da Memória RAM------");
        System.out.println("Memória Total: " + memoriaTotal + " GB");
        System.out.println("Memória em Uso: " + memoriaEmUso + " GB");
        System.out.println("Memória Disponível: " + memoriaDisponivel + " GB");
    }

}


