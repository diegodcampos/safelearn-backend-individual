package setup;

import componentes.MemoriaRam;
import componentes.Sistema;
import componentes.UsoDisco;
import componentes.UsoProcessador;
import dao.LoginDao;
import dao.MaquinaDaoLocal;
import dao.MaquinaDaoServer;

public class InserirRegistros {
    public InserirRegistros() {
        inserirRegistros();
    }

    public void inserirRegistros() {
        UsoProcessador processador = new UsoProcessador();
        Sistema sistema = new Sistema();
        MemoriaRam memoria = new MemoriaRam();
        UsoDisco disco = new UsoDisco();


        Boolean possuiRegistro;
        possuiRegistro = new MaquinaDaoServer().verificarRegistro(processador);
        String nomeUsuario = new Logar().getNomeUsuario();
        Integer fkInstituicao = new LoginDao().getFkInstituicao(nomeUsuario);

        if(!possuiRegistro) {
            new MaquinaDaoServer().inserirDadosMaquina(processador, sistema, fkInstituicao);
            new MaquinaDaoServer().inserirDadosComponente(processador, memoria, disco);
            new MaquinaDaoLocal().inserirDadosMaquina(processador, sistema, fkInstituicao);
            new MaquinaDaoLocal().inserirDadosComponente(processador, memoria, disco);

            System.out.println("Registrado com sucesso!");
            String toString = processador.toString() + sistema.toString() + memoria.toString() + disco.toString();
            System.out.println("Aqui estão algumas informações básicas sobre sua máquina:");
            System.out.println(toString);
        } else {
            System.out.println("A máquina está sendo monitorada.");
        }
    }


}
