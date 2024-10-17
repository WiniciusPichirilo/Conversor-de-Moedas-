import java.util.Scanner;

public class Conversor {
    public static void main(String[] args) {
        Moedas moedas = new Moedas();
        Scanner scanner = new Scanner(System.in);

        // Listar todas as moedas disponíveis
        moedas.listarMoedas();

        // Solicitar ao usuário para escolher moedas para conversão
        System.out.println("\nDigite o código da moeda de origem (ex: USD): ");
        String moedaOrigem = scanner.nextLine().toUpperCase();

        System.out.println("Digite o código da moeda de destino (ex: BRL): ");
        String moedaDestino = scanner.nextLine().toUpperCase();

        // Calcular e exibir a taxa de conversão
        if (!moedaOrigem.equals("USD")) {
            System.out.println("Atualmente, só é possível converter do USD para outras moedas.");
            return;
        }

        double taxa = moedas.obterTaxaDeCambio(moedaDestino);
        if (taxa != -1) {
            System.out.println("1 " + moedaOrigem + " = " + taxa + " " + moedaDestino);
        }
    }
}
