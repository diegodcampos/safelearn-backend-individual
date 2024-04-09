package componentes;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;

public class UsoProcessador {
    private String nome;
    private String id;
    private String fabricante;
    private Double uso;
    private Long frequencia;
    private String identificador;
    private Integer numeroCpusFisicas;
    private String microarquitetura;
    private Integer numeroCpusLogicas;


    public void capturarInformacoesProcessador(){
        Looca looca = new Looca();
        Processador processadorInfo = looca.getProcessador();
        nome = processadorInfo.getNome();
        id = processadorInfo.getId();
        fabricante = processadorInfo.getFabricante();
        frequencia = processadorInfo.getFrequencia();
        identificador = processadorInfo.getIdentificador();
        numeroCpusFisicas = processadorInfo.getNumeroCpusFisicas();
        microarquitetura = processadorInfo.getMicroarquitetura();
        numeroCpusLogicas = processadorInfo.getNumeroCpusLogicas();
    }


    public void exibirInformacoesProcessador() {
        capturarInformacoesProcessador();
        System.out.println("\n------Informações do Processador------");
        System.out.println("Fabricante: " + fabricante);
        System.out.println("Uso do Processador: " + uso + "%");
        System.out.println("Frequência: " + frequencia + " GHz");
        System.out.println("Identificador: " + identificador);
        System.out.println("Número de CPUs Físicas: " + numeroCpusFisicas);
        System.out.println("Microarquitetura: " + microarquitetura);
        System.out.println("Número de CPUs Lógicas: " + numeroCpusLogicas);
        System.out.println("Nome: " + nome);
        System.out.println("ID: " + id);
    }

    public String getFabricante() {
        return fabricante;
    }

    public Double getUso() {
        return uso;
    }

    public Long getFrequencia() {
        return frequencia;
    }

    public String getIdentificador() {
        return identificador;
    }

    public Integer getNumeroCpusFisicas() {
        return numeroCpusFisicas;
    }

    public String getMicroarquitetura() {
        return microarquitetura;
    }

    public Integer getNumeroCpusLogicas() {
        return numeroCpusLogicas;
    }

    public String getNome() {
        return nome;
    }

    public String getId() {
        return id;
    }
}
