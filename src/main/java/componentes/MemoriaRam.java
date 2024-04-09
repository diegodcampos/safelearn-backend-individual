package componentes;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
public class MemoriaRam {
    private Double memoriaTotal;
    private Double memoriaEmUso;
    private Double memoriaDisponivel;
    public void captarMemoria() {
        Looca looca = new Looca();
        Memoria memoria = looca.getMemoria();

    }
}


