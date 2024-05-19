package componentes;

import com.github.britooo.looca.api.core.Looca;

public abstract class Componente {

    private Looca looca;

    public Componente(Looca looca) {
        this.looca = new Looca();
    }


    public abstract void inicializarInformacoes();

    public Looca getLooca() {
        return looca;
    }

    public Componente() {
    }

    @Override
    public String toString() {
        return "Componente{" +
                "looca=" + looca +
                '}';
    }
}
