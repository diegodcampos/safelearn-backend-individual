package componentes;

import com.github.britooo.looca.api.core.Looca;

public class Sistema extends Componente{

    private Long tempoAtividade;
    private String sistemaOperacional;
    private Integer arquitetura;
    private String fabricante;

    public Sistema(){
       super();
        inicializarInformacoes();
    }

    @Override
    public void inicializarInformacoes() {
        this.tempoAtividade = looca.getSistema().getTempoDeAtividade();
        this.sistemaOperacional = looca.getSistema().getSistemaOperacional();
        this.arquitetura = looca.getSistema().getArquitetura();
        this.fabricante = looca.getSistema().getFabricante();
    }

    @Override
    public String toString() {
        long tempoAtividadeMinutos = tempoAtividade / 60;

        return String.format("\n"
                        + "______________________________\n"
                        + "|  Informações do Sistema    |\n"
                        + "|----------------------------|\n"
                        + "| Tempo de Atividade: %d minutos |\n"
                        + "| Sistema Operacional: %s    |\n"
                        + "| Arquitetura: %d bits       |\n"
                        + "| Fabricante: %s             |\n"
                        + "______________________________\n",
                tempoAtividadeMinutos, sistemaOperacional, arquitetura, fabricante);
    }

    public Looca getLooca() {
        return looca;
    }

    public Long getTempoAtividade() {
        return tempoAtividade;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public Integer getArquitetura() {
        return arquitetura;
    }

    public String getFabricante() {
        return fabricante;
    }
}
