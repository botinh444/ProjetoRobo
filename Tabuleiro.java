public class Tabuleiro {
    private boolean[][] grade;
    private Robo robo;

    public Tabuleiro(int xInicial, int yInicial, String nomeArquivo) {
        this.grade = new boolean[20][20];
        this.robo = new Robo(xInicial, yInicial, nomeArquivo);
        processarComandos();
    }

    public void marcarPosicao(int x, int y) {
        if (x >= 0 && x < 20 && y >= 0 && y < 20) {
            grade[x][y] = true;
        }
    }

    private void processarComandos() {
        int[] comandos = robo.getComandos();
        int i = 0;

        while (i < comandos.length) {
            int comando = comandos[i];

            switch (comando) {
                case 1:
                    robo.setCanetaAtiva(true);
                    break;
                case 2:
                    robo.setCanetaAtiva(false);
                    break;
                case 3:
                    robo.virarEsquerda();
                    break;
                case 4:
                    robo.virarDireita();
                    break;
                case 5:
                    if (i + 1 < comandos.length) {
                        int passos = comandos[i + 1];
                        robo.mover(passos, this);
                        i++;
                    }
                    break;
                case 6:
                    exibirTabuleiro();
                    break;
                case 9:
                    System.out.println("Processamento concluído.");
                    break;
            }
            i++;
        }
    }

    public void exibirTabuleiro() {
        System.out.println("\n TABULEIRO ");
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if (x == robo.getX() && y == robo.getY()) {
                    System.out.print("R ");
                } else if (grade[x][y]) {
                    System.out.print("# ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println("Robô: (" + robo.getX() + ", " + robo.getY() + ") - " + robo.getOrientacao());
        System.out.println("Caneta: " + (robo.isCanetaAtiva() ? "ATIVA" : "INATIVA"));
        System.out.println("=================\n");
    }
}