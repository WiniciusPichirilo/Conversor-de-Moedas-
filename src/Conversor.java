import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Scanner;

public class Conversor {
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/USD";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Conversor de Moedas!");
        System.out.println("Escolha uma conversão:");
        System.out.println("1. USD para BRL");
        System.out.println("2. BRL para USD");
        System.out.println("3. USD para EUR");
        System.out.println("4. EUR para USD");
        System.out.println("5. JPY para BRL");
        System.out.println("6. BRL para JPY");

        int choice = scanner.nextInt();
        String fromCurrency = "", toCurrency = "";

        switch (choice) {
            case 1: fromCurrency = "USD"; toCurrency = "BRL"; break;
            case 2: fromCurrency = "BRL"; toCurrency = "USD"; break;
            case 3: fromCurrency = "USD"; toCurrency = "EUR"; break;
            case 4: fromCurrency = "EUR"; toCurrency = "USD"; break;
            case 5: fromCurrency = "JPY"; toCurrency = "BRL"; break;
            case 6: fromCurrency = "BRL"; toCurrency = "JPY"; break;
            default: System.out.println("Escolha inválida!"); return;
        }

        double rate = getExchangeRate(fromCurrency, toCurrency);
        if (rate != -1) {
            System.out.println("Digite o valor que deseja converter:");
            double amount = scanner.nextDouble();
            double convertedAmount = amount * rate;
            System.out.printf("O valor convertido de %s para %s é: %.2f %s\n", amount, fromCurrency, convertedAmount, toCurrency);
        } else {
            System.out.println("Erro ao obter taxas.");
        }

        scanner.close();
    }

    public static double getExchangeRate(String fromCurrency, String toCurrency) {
        try {
            String jsonResponse = getRates();
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            JsonObject rates = jsonObject.getAsJsonObject("rates");

            double fromRate = rates.get(fromCurrency).getAsDouble();
            double toRate = rates.get(toCurrency).getAsDouble();

            return toRate / fromRate;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String getRates() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
