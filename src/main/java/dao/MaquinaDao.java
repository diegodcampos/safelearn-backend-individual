package dao;

import componentes.MemoriaRam;
import componentes.Sistema;
import componentes.UsoDisco;
import componentes.UsoProcessador;
import java.util.List;

public abstract class MaquinaDao {
    public abstract Boolean verificarRegistro(UsoProcessador processador);

    public abstract void inserirDadosMaquina(UsoProcessador processador, Sistema sistema, Integer fkInstituicao);

    public abstract void inserirDadosComponente(UsoProcessador processador, MemoriaRam memoria, UsoDisco disco);

    public abstract List<Integer> getIdsComponentes(UsoProcessador processador);
}

