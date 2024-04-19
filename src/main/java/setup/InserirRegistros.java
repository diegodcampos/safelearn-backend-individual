package setup;

import componentes.MemoriaRam;
import componentes.Sistema;
import componentes.UsoDisco;
import componentes.UsoProcessador;
import dao.MaquinaDao;

public class InserirRegistros {
    public InserirRegistros() {
        inserirRegistros();
    }

    public void inserirRegistros() {
        UsoProcessador processador = new UsoProcessador();
        Sistema sistema = new Sistema();
        UsoDisco usoDisco = new UsoDisco();
        MemoriaRam memoriaram = new MemoriaRam();

        Boolean possuiRegistro;
        possuiRegistro = new MaquinaDao().verificarRegistro(processador);

        if(!possuiRegistro) {
            new MaquinaDao().inserirDadosMaquina(processador, sistema, usoDisco, memoriaram);
            System.out.println("Registrado com sucesso!");
            String stringHardware = processador.toStringSimplified() + sistema.toString() + memoriaram.toString();
            System.out.println("Aqui estão algumas informações básicas sobre sua máquina:");
            System.out.println(stringHardware);
        } else {
            System.out.println("A máquina está sendo monitorada.");
        }
    }
}
