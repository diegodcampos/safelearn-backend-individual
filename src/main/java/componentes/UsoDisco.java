package componentes;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.Volume;

import java.util.ArrayList;
import java.util.List;

public class UsoDisco {

    private List<Disco> discos;
    private Integer qtdDiscos;
    private List<Volume> listaVolumes;
    private Integer qtdVolumes;
    private List<String> modelosDisco;
    private List<Long> tamanhosDisco;

    public  void capturarInformacoesDiscos(){
        Looca looca = new Looca();

       discos = looca.getGrupoDeDiscos().getDiscos();
       qtdDiscos = looca.getGrupoDeDiscos().getQuantidadeDeDiscos();
       listaVolumes = looca.getGrupoDeDiscos().getVolumes();
       tamanhosDisco = new ArrayList<>();
       qtdVolumes = looca.getGrupoDeDiscos().getQuantidadeDeVolumes();
       modelosDisco = new ArrayList<>();

       for (Disco disco : discos){
         modelosDisco.add(disco.getModelo());
         tamanhosDisco.add(disco.getTamanho());
       }

    }

    public void exibirInformacoesDeDiscos(){

        capturarInformacoesDiscos();
        for (Disco disco : discos) {
            System.out.println(disco);

        }

        System.out.println(modelosDisco);
        System.out.println(tamanhosDisco);

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

    public List<String> getModelosDisco() {
        return modelosDisco;
    }
}
