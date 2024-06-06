package componentes;

import com.github.britooo.looca.api.group.janelas.Janela;

import java.util.ArrayList;
import java.util.List;

public class GrupoJanelas extends Componente {

    private List<Janela> janelas;

    public GrupoJanelas() {
        super();
        inicializarInformacoes();
    }

    @Override
    public void inicializarInformacoes() {
        janelas = looca.getGrupoDeJanelas().getJanelasVisiveis();
    }

    @Override
    public String toString() {
        return String.format("\n"
                        + "__________________________________\n"
                        + "|      Janelas do sistema        |\n"
                        + "|--------------------------------|\n"
                        + "|      Título : %s               |\n"
                        + "__________________________________\n",
                janelas
                );
    }

    public List<Janela> getJanelas() {
        List<Janela> janelasVisiveis = new ArrayList<>();
        for(Janela janela : janelas) {
            if(!janela.getTitulo().equals("")) {
                janelasVisiveis.add(janela);
            }
        }
        return janelasVisiveis;
    }
}



