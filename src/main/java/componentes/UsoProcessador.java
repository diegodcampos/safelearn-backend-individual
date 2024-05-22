package componentes;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;

public class UsoProcessador extends Componente {
    private String nome;
    private String id;
    private Double uso;
    private Long frequencia;


    public UsoProcessador() {
        super();
        inicializarInformacoes();
    }

    @Override
    public void inicializarInformacoes() {
        Processador processador = looca.getProcessador();

        this.nome = processador.getNome();
        this.id = processador.getId();
        this.uso = processador.getUso();
        this.frequencia = processador.getFrequencia();

    }

    @Override
    public String toString() {
        return String.format("\n"
                        + "______________________________\n"
                        + "|      Uso do Processador     |\n"
                        + "|-----------------------------|\n"
                        + "| Nome: %s                    |\n"
                        + "| ID: %s                      |\n"
                        + "| Total (frequencia): %.2f GHz|\n"
                        + "| Uso: %.2f%%                 |\n"
                        + "______________________________\n",
                nome, id, frequencia / 1e9, uso);
    }


    public String getNome() {
        return nome;
    }

    public String getId() {
        return id;
    }


    public Double getUso() {
        return uso;
    }

    public Long getFrequencia() {
        return frequencia;
    }


}
