import java.util.List;
import java.util.Scanner;

public class Tabuleiro {
    private boolean[][] grade;
    private Robo robo;
    private Scanner scanner;

    public Tabuleiro(int xInicial, int yInicial) {
        this.grade = new boolean[20][20];
        this.robo = new Robo(xInicial, yInicial);
        this.scanner = new Scanner(System.in);
        processarComandos();
    }

    public void marcarPosicao(int x, int y) {
        if (x >= 0 && x < 20 && y >= 0 && y < 20) {
            grade[x][y] = true;
        }
    }

    private void processarComandos() {
        List<Integer> comandos = robo.getComandos();
        int i = 0;

        System.out.println("\n" + "=".repeat(60));
        System.out.println("=== EXECUTANDO COMANDOS DO ROBÔ ===");
        System.out.println("=".repeat(60));
        System.out.println("Posição inicial: (" + robo.getX() + ", " + robo.getY() + ")");
        System.out.println("Orientação inicial: " + robo.getOrientacao());
        System.out.println("Caneta: " + (robo.isCanetaAtiva() ? "ATIVA" : "INATIVA"));
        System.out.println("Total de comandos a executar: " + comandos.size());


        System.out.println("\n--- TABULEIRO INICIAL ---");
        exibirTabuleiro();

        while (i < comandos.size()) {
            int comando = comandos.get(i);

            System.out.println("\n" + "═".repeat(40));
            System.out.println("Comando " + (i+1) + "/" + comandos.size() + ": " + getNomeComando(comando));
            System.out.println("═".repeat(40));

            switch (comando) {
                case 1:
                    robo.setCanetaAtiva(true);
                    System.out.println("✓ CANETA ATIVADA");
                    break;

                case 2:
                    robo.setCanetaAtiva(false);
                    System.out.println("✓ CANETA DESATIVADA");
                    break;

                case 3:
                    robo.virarEsquerda();
                    System.out.println("✓ VIROU À ESQUERDA");
                    System.out.println("  Nova orientação: " + robo.getOrientacao());
                    break;

                case 4:
                    robo.virarDireita();
                    System.out.println("✓ VIROU À DIREITA");
                    System.out.println("  Nova orientação: " + robo.getOrientacao());
                    break;

                case 5:
                    if (i + 1 < comandos.size()) {
                        int passos = comandos.get(i + 1);
                        System.out.println("✓ ANDANDO " + passos + " PASSOS para " + robo.getOrientacao());
                        executarMovimento(passos);
                        i++;
                    }
                    break;

                case 6:
                    System.out.println("✓ MOSTRANDO TABULEIRO ATUAL");
                    exibirTabuleiro();
                    break;

                case 9:
                    System.out.println("✓ PROGRAMAÇÃO FINALIZADA");
                    break;
            }


            if (comando != 6 && comando != 9) {
                System.out.println("\nEstado atual:");
                System.out.println("- Posição: (" + robo.getX() + ", " + robo.getY() + ")");
                System.out.println("- Orientação: " + robo.getOrientacao());
                System.out.println("- Caneta: " + (robo.isCanetaAtiva() ? "ATIVA" : "INATIVA"));
            }

            if (comando != 9) {
                System.out.print("\nPressione Enter para continuar...");
                scanner.nextLine(); // Limpar buffer
                scanner.nextLine(); // Aguardar Enter
            }

            i++;
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("=== EXECUÇÃO CONCLUÍDA ===");
        System.out.println("=".repeat(60));
        exibirTabuleiroFinal();
    }

    private String getNomeComando(int comando) {
        switch (comando) {
            case 1: return "ATIVAR CANETA";
            case 2: return "DESATIVAR CANETA";
            case 3: return "VIRAR À ESQUERDA";
            case 4: return "VIRAR À DIREITA";
            case 5: return "ANDAR";
            case 6: return "MOSTRAR TABULEIRO";
            case 9: return "FINALIZAR";
            default: return "COMANDO DESCONHECIDO";
        }
    }

    private void executarMovimento(int passos) {
        int[] direcao = robo.getDirecao();
        int dx = direcao[0];
        int dy = direcao[1];

        for (int passo = 1; passo <= passos; passo++) {

            if (robo.isCanetaAtiva()) {
                marcarPosicao(robo.getX(), robo.getY());
            }


            robo.mover(dx, dy);

            System.out.println("  Passo " + passo + "/" + passos +
                    " → Posição: (" + robo.getX() + ", " + robo.getY() + ")");

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                // Continua execução
            }
        }
    }

    public void exibirTabuleiro() {
        System.out.println("\n" + "═".repeat(45));
        System.out.println("          TABULEIRO 20x20");
        System.out.println("═".repeat(45));

        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if (x == robo.getX() && y == robo.getY()) {
                    System.out.print("R "); // Posição do robô
                } else if (grade[x][y]) {
                    System.out.print("# "); // Posição marcada
                } else {
                    System.out.print(". "); // Posição vazia
                }
            }
            System.out.println();
        }

        System.out.println("═".repeat(45));
        System.out.println("Posição do robô: (" + robo.getX() + ", " + robo.getY() + ")");
        System.out.println("Orientação: " + robo.getOrientacao());
        System.out.println("Caneta: " + (robo.isCanetaAtiva() ? "ATIVA" : "INATIVA"));
        System.out.println("═".repeat(45));
    }

    private void exibirTabuleiroFinal() {
        System.out.println("\n" + "⭐".repeat(25));
        System.out.println("⭐        RESULTADO FINAL        ⭐");
        System.out.println("⭐".repeat(25));
        exibirTabuleiro();
    }
}
