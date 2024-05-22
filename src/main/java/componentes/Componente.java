package componentes;

import com.github.britooo.looca.api.core.Looca;

public abstract class Componente {
    protected Looca looca;

    public Componente() {
        looca = new Looca();
    }

    public abstract void inicializarInformacoes();

    public Looca getLooca() {
        return looca;
    }


}
