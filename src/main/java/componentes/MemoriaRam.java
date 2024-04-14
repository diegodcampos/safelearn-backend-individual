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
        Memoria memoria = looca.getMemoria();
        this.memoriaTotal = memoria.getTotal() / (1024 * 1024 * 1024);
        this.memoriaEmUso = memoria.getEmUso() / (1024 * 1024 * 1024);
        this.memoriaDisponivel = memoria.getDisponivel() / (1024 * 1024 * 1024);
    }

    @Override
    public String toString() {
        return String.format("\n"
                        + "__________________________________\n"
                        + "|      Informações da Memória    |\n"
                        + "|--------------------------------|\n"
                        + "| Memória Total: %d GB           |\n"
                        + "| Memória em Uso: %d GB           |\n"
                        + "| Memória Disponível: %d GB       |\n"
                        + "__________________________________\n",

                memoriaTotal, memoriaEmUso, memoriaDisponivel);
    }
}
