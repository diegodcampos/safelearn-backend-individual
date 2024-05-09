package setup;

import componentes.MemoriaRam;
import componentes.Sistema;
import componentes.UsoDisco;
import componentes.UsoProcessador;
import dao.LoginDao;
import dao.MaquinaDao;

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
        possuiRegistro = new MaquinaDao().verificarRegistro(processador);
        String nomeUsuario = new Logar().getNomeUsuario();
        Integer fkInstituicao = new LoginDao().getFkInstituicao(nomeUsuario);

        if(!possuiRegistro) {
            new MaquinaDao().inserirDadosMaquina(processador, sistema, fkInstituicao);
            new MaquinaDao().inserirDadosComponente(processador, memoria, disco);

            System.out.println("Registrado com sucesso!");
            String stringHardware = processador.toStringSimplified() + sistema.toString() + memoria.toString() + disco.exibirInformacoesDeDiscos();
            System.out.println("Aqui estão algumas informações básicas sobre sua máquina:");
            System.out.println(stringHardware);
        } else {
            System.out.println("A máquina está sendo monitorada.");
        }
    }


}
