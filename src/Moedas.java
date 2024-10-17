public class Conversor {
    public static void main(String[] args) {
        Moedas moedas = new Moedas();
        double usdToBrl = moedas.obterTaxaDeCambio("BRL");

        if (usdToBrl != -1) {
            System.out.println("1 USD = " + usdToBrl + " BRL");
        }
    }
}
