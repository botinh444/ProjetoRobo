import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Robo {
    private boolean canetaAtiva;
    private int orientacao;
    private int[] comandos;
    private int x, y;

    public Robo(int xInicial, int yInicial, String nomeArquivo) {
        this.x = xInicial;
        this.y = yInicial;
        this.canetaAtiva = false;
        this.orientacao = 1;
        lerComandosDoArquivo(nomeArquivo);
    }

    private void lerComandosDoArquivo(String nomeArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha = br.readLine();
            if (linha != null) {
                String[] comandosStr = linha.split(",");
                comandos = new int[comandosStr.length];
                for (int i = 0; i < comandosStr.length; i++) {
                    comandos[i] = Integer.parseInt(comandosStr[i].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
            comandos = new int[0];
        }
    }

    public int[] getComandos() {
        return comandos;
    }

    public boolean isCanetaAtiva() {
        return canetaAtiva;
    }

    public void setCanetaAtiva(boolean canetaAtiva) {
        this.canetaAtiva = canetaAtiva;
    }

    public void virarEsquerda() {
        orientacao = (orientacao + 3) % 4;
    }

    public void virarDireita() {
        orientacao = (orientacao + 1) % 4;
    }

    public void mover(int passos, Tabuleiro tabuleiro) {
        int dx = 0, dy = 0;

        switch (orientacao) {
            case 0: // Norte
                dy = -1;
                break;
            case 1: // Leste
                dx = 1;
                break;
            case 2: // Sul
                dy = 1;
                break;
            case 3: // Oeste
                dx = -1;
                break;
        }

        for (int i = 0; i < passos; i++) {
            if (canetaAtiva) {
                tabuleiro.marcarPosicao(x, y);
            }
            x += dx;
            y += dy;
        }
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
}