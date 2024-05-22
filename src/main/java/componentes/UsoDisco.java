package componentes;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.Volume;

import java.util.ArrayList;
import java.util.List;

public class UsoDisco extends Componente {
    private List<Disco> discos;
    private Integer qtdDiscos;

    public UsoDisco() {
        super();
       inicializarInformacoes();
    }

    @Override
    public void inicializarInformacoes() {
        this.discos = looca.getGrupoDeDiscos().getDiscos();
        this.qtdDiscos = looca.getGrupoDeDiscos().getQuantidadeDeDiscos();
    }


    public List<Disco> getDiscos() {
        return discos;
    }

    public Integer getQtdDiscos() {
        return qtdDiscos;
    }


    @Override
    public String toString() {
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
}


