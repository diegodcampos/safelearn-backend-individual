package componentes;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
public class MemoriaRam {
    private Looca looca;
    private Double memoriaTotal;
    private Double memoriaEmUso;
    private Double memoriaDisponivel;

    public MemoriaRam() {
        this.looca = new Looca();
        captarInformacoesMemoria();
    }

    public void captarInformacoesMemoria() {
        Memoria memoria = looca.getMemoria();
        this.memoriaTotal = memoria.getTotal() / Math.pow(10, 9);
        this.memoriaEmUso = memoria.getEmUso() / Math.pow(10, 9);
        this.memoriaDisponivel = memoria.getDisponivel() / Math.pow(10, 9);
    }

    @Override
    public String toString() {
        return String.format("\n"
                        + "__________________________________\n"
                        + "|      Informações da Memória    |\n"
                        + "|--------------------------------|\n"
                        + "| Memória Total: %.2f GB           |\n"
                        + "| Memória em Uso: %.2f GB           |\n"
                        + "| Memória Disponível: %.2f GB       |\n"
                        + "__________________________________\n",

                memoriaTotal, memoriaEmUso, memoriaDisponivel);
    }

    public Looca getLooca() {
        return looca;
    }

    public Double getMemoriaTotal() {
        return memoriaTotal;
    }

    public Double getMemoriaEmUso() {
        return memoriaEmUso;
    }

    public Double getMemoriaDisponivel() {
        return memoriaDisponivel;
    }
}
