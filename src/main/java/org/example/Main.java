package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        Looca looca = new Looca();

        System.out.println(looca.getMemoria());

        System.out.println(looca.getProcessador());
        System.out.println(looca.getGrupoDeDiscos());
    }
}
