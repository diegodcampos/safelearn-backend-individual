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
        Integer opcao;

        do {
            System.out.println("Selecione uma opção:");
            System.out.println("1. Informações sobre componentes de um computador");
            System.out.println("2. História do Disco Rígido");
            System.out.println("3. Fazer login");
            System.out.println("4. Sair");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    exibirInformacoesComponentes();
                    break;
                case 2:
                    exibirHistoriaDiscoRigido();
                    break;
                case 3:
                    fazerLogin(scanner);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 3);
    }

    private void exibirInformacoesComponentes() {
        String informacoes = """
        \n-----------------------------------------------------------------------------------------------------------
        Aqui estão algumas informações sobre os componentes de um computador:
        1. Memória RAM (Random Access Memory):
           A memória RAM é um tipo de memória volátil que armazena temporariamente dados e programas em uso pelo computador. Quanto mais RAM um computador possui, mais programas ele pode executar simultaneamente sem diminuir o desempenho.
           - Capacidade: Determina quantos programas e processos podem ser executados simultaneamente.
           - Velocidade: Medida em MHz ou MT/s (megatransferências por segundo), afeta a rapidez com que os dados podem ser acessados e manipulados.
        \n2. Disco:
           O disco, também conhecido como armazenamento, refere-se aos dispositivos de armazenamento permanente, como discos rígidos (HDDs) e unidades de estado sólido (SSDs). Eles armazenam dados permanentemente, mesmo quando o computador é desligado.
           - Tipo: Os HDDs tradicionais são mais lentos em comparação com os SSDs, que oferecem tempos de acesso muito mais rápidos.
           - Capacidade: Determina quanto espaço está disponível para programas e arquivos.
           - Velocidade de Transferência: Medida em MB/s (megabytes por segundo), é mais alta em SSDs do que em HDDs, resultando em tempos de inicialização mais rápidos e maior responsividade geral do sistema.
        \n3. CPU (Central Processing Unit):
           A CPU é o cérebro do computador, responsável por executar instruções e processar dados. Quanto mais rápida e poderosa for a CPU, mais rapidamente o computador pode realizar tarefas complexas.
           - Velocidade de Clock: Medida em GHz (gigahertz), indica quantas operações o processador pode executar por segundo.
           - Núcleos e Threads: O número de núcleos e threads afeta a capacidade de multitarefa e o desempenho em aplicações que podem utilizar vários núcleos simultaneamente.
           - Arquitetura: A arquitetura do processador é importante. Por exemplo, os processadores baseados na arquitetura x86 são amplamente utilizados em PCs, enquanto os processadores ARM são comuns em dispositivos móveis e embarcados.
        -----------------------------------------------------------------------------------------------------------
        """;

        System.out.println(informacoes);
    }

    private void exibirHistoriaDiscoRigido() {
        String historia = """
        \n-----------------------------------------------------------------------------------------------------------
        História do Disco Rígido:
        O disco rígido, ou HDD (Hard Disk Drive), é um dispositivo de armazenamento de dados introduzido pela primeira vez pela IBM em 1956. O primeiro modelo, chamado IBM 350, fazia parte do sistema IBM 305 RAMAC (Random Access Method of Accounting and Control). Ele tinha uma capacidade de cerca de 5 MB, usando cinquenta discos de 24 polegadas, cada um dos quais armazenava 100.000 bytes.
        
        Nos anos 1980, a tecnologia de discos rígidos começou a se tornar mais acessível e compacta, com empresas como Seagate e Western Digital lançando unidades para PCs. O tamanho padrão dos discos passou de 8 polegadas para 5,25 e finalmente 3,5 polegadas para desktops e 2,5 polegadas para laptops.
        
        Na década de 1990, os HDDs continuaram a evoluir, com aumentos significativos na capacidade e na velocidade. A introdução de interfaces como IDE (Integrated Drive Electronics) e posteriormente SATA (Serial ATA) melhorou a taxa de transferência de dados e a facilidade de conexão.
        
        Com o advento dos SSDs (Solid State Drives) no século 21, os HDDs começaram a enfrentar concorrência séria. No entanto, devido ao seu custo por gigabyte mais baixo, os discos rígidos ainda são amplamente utilizados para armazenamento de dados em massa.
        
        Os HDDs modernos podem armazenar vários terabytes de dados e possuem tecnologias avançadas como cache grande, correção de erros e algoritmos de otimização de leitura/escrita. Eles continuam a ser uma escolha popular para armazenamento de grandes volumes de dados, como backups, sistemas de vigilância e servidores.
        -----------------------------------------------------------------------------------------------------------
        """;

        System.out.println(historia);
    }

    private void fazerLogin(Scanner scanner) {
        Boolean autenticado = false;

        do {
            System.out.println("Nome de usuário: ");
            nomeUsuario = scanner.nextLine();

            System.out.println("Senha: ");
            senha = scanner.nextLine();

            autenticado = new LoginDao().autenticarUsuario(nomeUsuario, senha);

            if (!autenticado) {
                System.out.println("Credenciais inválidas. Tente novamente.");
            }
        } while (!autenticado);

        System.out.println("Login bem-sucedido!");
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }
}
