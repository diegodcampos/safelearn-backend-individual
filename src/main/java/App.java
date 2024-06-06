import componentes.GrupoJanelas;
import componentes.MemoriaRam;
import componentes.UsoDisco;
import componentes.UsoProcessador;
import dao.MaquinaDaoLocal;
import dao.MaquinaDaoServer;
import setup.InserirRegistros;
import systemcommands.Bateria;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App  {
    public static void main(String[] args) {
        InserirRegistros inserirRegistros = new InserirRegistros();

        MaquinaDaoServer maquinaDaoServer = new MaquinaDaoServer();
        MaquinaDaoLocal maquinaDaoLocal = new MaquinaDaoLocal();
        UsoProcessador processador = new UsoProcessador();
        MemoriaRam memoriaRam = new MemoriaRam();
        UsoDisco disco = new UsoDisco();
        GrupoJanelas janelas = new GrupoJanelas();

        maquinaDaoServer.setStatus(processador);

        List<Integer> idsComponentesServer = maquinaDaoServer.getIdsComponentes(processador);
        List<Integer> idsComponentesLocal = maquinaDaoLocal.getIdsComponentes(processador);

        if (idsComponentesServer == null || idsComponentesServer.isEmpty()) {
            System.err.println("idsComponentesServer is null or empty");
        }
        if (idsComponentesLocal == null || idsComponentesLocal.isEmpty()) {
            System.err.println("idsComponentesLocal is null or empty");
        }

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable tarefaMonitoramento = new Runnable() {
            @Override
            public void run() {
                try {
                    maquinaDaoServer.executarComandoDeMaquina(processador);
                    maquinaDaoLocal.monitoramento(processador, memoriaRam, disco, idsComponentesLocal);
                    maquinaDaoServer.monitoramento(processador, memoriaRam, disco, idsComponentesServer);
                    maquinaDaoLocal.inserirDadosProcessso(processador, janelas);
                    maquinaDaoServer.inserirDadosProcessso(processador, janelas);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("Erro no monitoramento: " + e.getMessage());
                }
            }
        };
        scheduler.scheduleWithFixedDelay(tarefaMonitoramento, 0, 7, TimeUnit.SECONDS);
    }
}
