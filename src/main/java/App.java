import componentes.MemoriaRam;
import componentes.UsoProcessador;
import componentes.Sistema;
import conexao.Conexao;
import dao.LoginDao;
import dao.ProcessadorDao;
import dao.SistemaDao;

import java.io.Console;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sistema sistema = new Sistema();
        UsoProcessador processador = new UsoProcessador();

        String nomeUsuario;
        String senha;
        Boolean autenticado;

        do {
            System.out.println("Nome de usuário: ");
            nomeUsuario = scanner.nextLine();

            System.out.println("Senha: ");
            senha = scanner.nextLine();

            autenticado = new LoginDao().autenticarUsuario(nomeUsuario, senha);

            if(!autenticado) {
                System.out.println("Credenciais inválidas. Tente novamente.");
            }
        } while (nomeUsuario.equals(null) || senha.equals(null) || !autenticado);


        // Implementar nossa regra de negócio aqui
        System.out.println("""
                Olá %s, seja bem-vindo!!!
                Aqui temos algumas informações básicas sobre seu sistema e processador.
                
                Sistema Operacional: %s
                Arquitetura: %s bits 
                Fabricante: %s""".formatted(nomeUsuario, sistema.getSistemaOperacional(), sistema.getArquitetura(), sistema.getFabricante()));

        processador.exibirInformacoesProcessador();
    }
}


