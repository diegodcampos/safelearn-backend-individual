import componentes.MemoriaRam;
import componentes.UsoProcessador;
import componentes.Sistema;
import conexao.Conexao;
import dao.LoginDao;
import dao.ProcessadorDao;
import dao.SistemaDao;
import setup.Logar;

import java.io.Console;
import java.util.Scanner;

import systemcommands.Bateria;
import systemcommands.SystemCommandExecutor;


public class App {
    public static void main(String[] args) {
        Bateria bateria = new Bateria();
        bateria.verificarBateria();

        //teste comandos remotos
//        SystemCommandExecutor.shutdown();
//        SystemCommandExecutor.restart();
//        SystemCommandExecutor.suspend();

        Sistema sistema = new Sistema();
        UsoProcessador processador = new UsoProcessador();
        Logar logar = new Logar();

        logar.logar();

        System.out.println("""
                Olá, seja bem-vindo!!!
                Aqui temos algumas informações básicas sobre seu sistema e processador.
                
                Sistema Operacional: %s
                Arquitetura: %s bits 
                Fabricante: %s""".formatted(sistema.getSistemaOperacional(), sistema.getArquitetura(), sistema.getFabricante()));

        processador.exibirInformacoesProcessador();

    }
}


