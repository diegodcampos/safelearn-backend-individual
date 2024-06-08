package dao;

import componentes.*;

import java.util.List;

public abstract class MaquinaDao {
    public abstract Boolean verificarRegistro(UsoProcessador processador);

    public abstract void inserirDadosMaquina(UsoProcessador processador, Sistema sistema, Integer fkInstituicao);

    public abstract void inserirDadosComponente(UsoProcessador processador, MemoriaRam memoria, UsoDisco disco);

    public abstract List<Integer> getIdsComponentes(UsoProcessador processador);

    public abstract void monitoramento(UsoProcessador processador, MemoriaRam memoria, UsoDisco disco, List<Integer> idsComponentes);

    public abstract void inserirDadosProcessso(UsoProcessador processador, GrupoJanelas janelas);

    public abstract void inserirDadosBateria(UsoProcessador processador, Bateria bateria);
}


