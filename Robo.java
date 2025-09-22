import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Robo {
    private boolean canetaAtiva;
    private int orientacao;
    private List<Integer> comandos;
    private int x, y;
    private Scanner scanner;

    public Robo(int xInicial, int yInicial) {
        this.x = xInicial;
        this.y = yInicial;
        this.canetaAtiva = false;
        this.orientacao = 1;
        this.comandos = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        solicitarComandosDoUsuario();
    }

    private void solicitarComandosDoUsuario() {
        System.out.println("=== SISTEMA DE COMANDOS DO ROBÔ ===");
        System.out.println("Comandos disponíveis:");
        System.out.println("1 = Ativar caneta");
        System.out.println("2 = Desativar caneta");
        System.out.println("3 = Virar à esquerda");
        System.out.println("4 = Virar à direita");
        System.out.println("5 = Andar (será solicitado número de passos)");
        System.out.println("6 = Mostrar tabuleiro");
        System.out.println("9 = Finalizar programação");
        System.out.println("\nDigite os comandos um por um (digite 9 para finalizar):");

        int comando;
        do {
            System.out.print("Digite o comando: ");
            comando = scanner.nextInt();

            if (comando == 5) {
                System.out.print("Digite o número de passos: ");
                int passos = scanner.nextInt();
                comandos.add(comando);
                comandos.add(passos);
            } else if (comando >= 1 && comando <= 9) {
                comandos.add(comando);

                if (comando == 9) {
                    System.out.println("Programação finalizada!");
                }
            } else {
                System.out.println("Comando inválido! Digite um número entre 1-9.");
            }
        } while (comando != 9);
    }

    public List<Integer> getComandos() {
        return comandos;
    }

    public boolean isCanetaAtiva() {
        return canetaAtiva;
    }

    public void setCanetaAtiva(boolean canetaAtiva) {
        this.canetaAtiva = canetaAtiva;
    }

    public void virarEsquerda() {
        orientacao = (orientacao + 3) % 4; // -1 mod 4
    }

    public void virarDireita() {
        orientacao = (orientacao + 1) % 4;
    }

    public void mover(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getOrientacao() {
        switch (orientacao) {
            case 0: return "Norte";
            case 1: return "Leste";
            case 2: return "Sul";
            case 3: return "Oeste";
            default: return "Desconhecida";
        }
    }

    public int[] getDirecao() {
        int dx = 0, dy = 0;
        switch (orientacao) {
            case 0: dy = -1; break;
            case 1: dx = 1; break;
            case 2: dy = 1; break;
            case 3: dx = -1; break;
        }
        return new int[]{dx, dy};
    }
}
