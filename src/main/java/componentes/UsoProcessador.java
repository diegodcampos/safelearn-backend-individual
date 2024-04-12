package componentes;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;

public class UsoProcessador {
    private Looca looca;
    private String nome;
    private String id;
    private String fabricante;
    private Double uso;
    private Long frequencia;
    private String identificador;
    private Integer numeroCpusFisicas;
    private String microarquitetura;
    private Integer numeroCpusLogicas;

    public UsoProcessador() {
        this.looca = new Looca();
        capturarInformacoesProcessador();
    }

    public void capturarInformacoesProcessador(){
        this.nome = looca.getProcessador().getNome();
        this.id = looca.getProcessador().getId();
        this.fabricante = looca.getProcessador().getFabricante();
        this.uso = looca.getProcessador().getUso();
        this.frequencia = looca.getProcessador().getFrequencia();
        this.identificador = looca.getProcessador().getIdentificador();
        this.numeroCpusFisicas = looca.getProcessador().getNumeroCpusFisicas();
        this.numeroCpusLogicas = looca.getProcessador().getNumeroCpusLogicas();
        this.microarquitetura = looca.getProcessador().getMicroarquitetura();
    }

    public void exibirInformacoesProcessador() {
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
