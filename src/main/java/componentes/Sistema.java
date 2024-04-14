package componentes;

import com.github.britooo.looca.api.core.Looca;

public class Sistema {
    private Looca looca;
    private Long tempoAtividade;
    private String sistemaOperacional;
    private Integer arquitetura;
    private String fabricante;

    public Sistema(){
        this.looca = new Looca();
        inicializarInformacoes();
    }

    private void inicializarInformacoes()  {
        this.tempoAtividade = looca.getSistema().getTempoDeAtividade();
        this.sistemaOperacional = looca.getSistema().getSistemaOperacional();
        this.arquitetura = looca.getSistema().getArquitetura();
        this.fabricante = looca.getSistema().getFabricante();
    }

    @Override
    public String toString() {
        long tempoAtividadeMinutos = tempoAtividade / 60; // minutos

        return String.format("\n"
                        + "______________________________\n"
                        + "|  Informações do Sistema    |\n"
                        + "|----------------------------|\n"
                        + "| Tempo de Atividade: %d minutos |\n"
                        + "| Sistema Operacional: %s    |\n"
                        + "| Arquitetura: %d bits        |\n"
                        + "| Fabricante: %s             |\n"
                        + "______________________________\n",
                tempoAtividadeMinutos, sistemaOperacional, arquitetura, fabricante);
    }

}
