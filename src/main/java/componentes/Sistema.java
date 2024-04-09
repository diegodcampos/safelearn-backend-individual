package componentes;

import com.github.britooo.looca.api.core.Looca;

public class Sistema {
    private Looca looca;
    private Long tempoAtividade;

    private String sistemaOperacional;

    private Integer arquitetura;

    private String fabricante;

    public Sistema() {
        this.looca = new Looca();
        this.tempoAtividade = looca.getSistema().getTempoDeAtividade();
        this.sistemaOperacional = looca.getSistema().getSistemaOperacional();
        this.arquitetura = looca.getSistema().getArquitetura();
        this.fabricante = looca.getSistema().getFabricante();
    }

    public Long getTempoAtividade() {
        return tempoAtividade;
    }

    public void exibirInformacoesSistema() {
        Long tempoAtividade = looca.getSistema().getTempoDeAtividade();
        String sistemaOperacional = looca.getSistema().getSistemaOperacional();
        Integer arquitetura = looca.getSistema().getArquitetura();
        String fabricante = looca.getSistema().getFabricante();

        System.out.println("Informações do Sistema:");
        System.out.println("Tempo de Atividade: " + tempoAtividade + " segundos");
        System.out.println("Sistema Operacional: " + sistemaOperacional);
        System.out.println("Arquitetura: " + arquitetura + " bits");
        System.out.println("Fabricante: " + fabricante);
    }

    public void setTempoAtividade(Long tempoAtividade) {
        this.tempoAtividade = tempoAtividade;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public Integer getArquitetura() {
        return arquitetura;
    }

    public void setArquitetura(Integer arquitetura) {
        this.arquitetura = arquitetura;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }


}
