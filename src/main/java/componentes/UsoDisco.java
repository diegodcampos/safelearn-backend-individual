package componentes;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.Volume;

import java.util.ArrayList;
import java.util.List;

public class UsoDisco extends Componente {
    private List<Disco> discos;
    private Integer qtdDiscos;
    private List<Volume> listaVolumes;
    private Integer qtdVolumes;
    private List<String> modelosDisco;
    private List<Long> tamanhosDisco;

    public UsoDisco() {
        super();
        capturarInformacoesDiscos();
    }

    @Override
    public void inicializarInformacoes() {
        this.discos = looca.getGrupoDeDiscos().getDiscos();
        this.qtdDiscos = looca.getGrupoDeDiscos().getQuantidadeDeDiscos();
        this.listaVolumes = looca.getGrupoDeDiscos().getVolumes();
        this.qtdVolumes = looca.getGrupoDeDiscos().getQuantidadeDeVolumes();
    }

    public void capturarInformacoesDiscos() {
        inicializarInformacoes();
        this.modelosDisco = new ArrayList<>();
        this.tamanhosDisco = new ArrayList<>();

        for (Disco disco : discos) {
            this.modelosDisco.add(disco.getModelo());
            this.tamanhosDisco.add(disco.getTamanho());
        }
    }

    public String exibirInformacoesDeDiscos() {
        StringBuilder string = new StringBuilder("__________________________________\n");
        string.append("|      Informações de Disco     |\n");
        string.append("|--------------------------------|\n");
        string.append("| Quantidade de Discos: ").append(getQtdDiscos()).append(" |\n");

        for (int i = 0; i < discos.size(); i++) {
            Disco disco = discos.get(i);
            string.append("| Tamanho do Disco ").append(i + 1).append(": ").append(disco.getTamanho() / (1024 * 1024 * 1024)).append(" GB |\n");
        }
        string.append("__________________________________\n");
        return string.toString();
    }

    public List<Disco> getDiscos() {
        return discos;
    }

    public Integer getQtdDiscos() {
        return qtdDiscos;
    }

    public List<Volume> getListaVolumes() {
        return listaVolumes;
    }

    public List<Long> getTamanhosDisco() {
        return tamanhosDisco;
    }

    public Integer getQtdVolumes() {
        return qtdVolumes;
    }

    @Override
    public String toString() {
        return "UsoDisco{" +
                "discos=" + discos +
                ", qtdDiscos=" + qtdDiscos +
                ", listaVolumes=" + listaVolumes +
                ", qtdVolumes=" + qtdVolumes +
                ", modelosDisco=" + modelosDisco +
                ", tamanhosDisco=" + tamanhosDisco +
                '}';
    }
}
