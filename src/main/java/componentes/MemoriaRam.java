package componentes;

import com.github.britooo.looca.api.core.Looca;

public class MemoriaRam extends Componente {
    private Double memoriaTotal;
    private Double memoriaEmUso;
    private Double memoriaDisponivel;


    public MemoriaRam() {
        super();
        inicializarInformacoes();
    }

    @Override
    public void inicializarInformacoes() {
        this.memoriaTotal = looca.getMemoria().getTotal() / Math.pow(10, 9);;
        this.memoriaEmUso = looca.getMemoria().getEmUso() / Math.pow(10, 9);
        this.memoriaDisponivel = looca.getMemoria().getDisponivel() / Math.pow(10, 9);
    }

    @Override
    public String toString() {
        return String.format("\n"
                        + "__________________________________\n"
                        + "|      Informações da Memória    |\n"
                        + "|--------------------------------|\n"
                        + "| Memória Total: %.2f GB         |\n"
                        + "| Memória em Uso: %.2f GB        |\n"
                        + "| Memória Disponível: %.2f GB    |\n"
                        + "__________________________________\n",
                memoriaTotal, memoriaEmUso, memoriaDisponivel);
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
