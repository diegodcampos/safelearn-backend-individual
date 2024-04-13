package componentes;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
public class MemoriaRam {
    private Looca looca;
    private Long memoriaTotal;
    private Long memoriaEmUso;
    private Long memoriaDisponivel;

    public MemoriaRam() {
        this.looca = new Looca();
        captarInformacoesMemoria();
    }

    public void captarInformacoesMemoria() {
        this.memoriaTotal = looca.getMemoria().getTotal();
        this.memoriaEmUso = looca.getMemoria().getEmUso();
        this.memoriaDisponivel = looca.getMemoria().getDisponivel();
    }

    public void exibirInformacoesMemoria() {
        captarInformacoesMemoria();
        System.out.println("\n------Informações da Memória RAM------");
        System.out.println("Memória Total: " + memoriaTotal + " GB");
        System.out.println("Memória em Uso: " + memoriaEmUso + " GB");
        System.out.println("Memória Disponível: " + memoriaDisponivel + " GB");
    }

    public Long getMemoriaTotal() {
        return memoriaTotal;
    }

    public Long getMemoriaEmUso() {
        return memoriaEmUso;
    }

    public Long getMemoriaDisponivel() {
        return memoriaDisponivel;
    }
}


