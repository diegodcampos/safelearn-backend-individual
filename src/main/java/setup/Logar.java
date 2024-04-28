package setup;

import dao.LoginDao;

import java.util.Scanner;

public class Logar {
    private String nomeUsuario;
    private String senha;

    public Logar() {
        logar();
    }

    public void logar() {
        Scanner scanner = new Scanner(System.in);

        Boolean autenticado = false;

        do {
            System.out.println("Nome de usuário: ");
            nomeUsuario = scanner.nextLine();

            System.out.println("Senha: ");
            senha = scanner.nextLine();

            autenticado = new LoginDao().autenticarUsuario(nomeUsuario, senha);

            if(!autenticado) {
                System.out.println("Credenciais inválidas. Tente novamente.");
            }
        } while (!autenticado);
        System.out.println("Login bem-sucedido!");
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }
}
