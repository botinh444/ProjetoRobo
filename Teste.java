import java.io.FileWriter;
import java.io.IOException;

public class Teste {
    public static void main(String[] args) {

        criarArquivoComandos();
        Tabuleiro tabuleiro = new Tabuleiro(10, 10, "comandos.txt");
    }

    private static void criarArquivoComandos() {
        try (FileWriter writer = new FileWriter("comandos.txt")) {
            String comandos = "2, 5, 8, 3, 5, 8, 3, 5, 8, 3, 5, 3, 1, 5, 2, 2, 5, 3, 1, 6, 9";
            writer.write(comandos);
            System.out.println("Arquivo 'comandos.txt' criado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo: " + e.getMessage());
        }
    }
}