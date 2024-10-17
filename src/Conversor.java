import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Set;

public class Moedas {
    private static final String API_KEY = "4da70373bd7d81457f51905e"; // Sua chave de API
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/USD?access_key=" + API_KEY;

    public JsonObject obterTodasAsTaxas() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(API_URL).build();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String jsonData = response.body().string();
                JsonObject jsonObject = JsonParser.parseString(jsonData).getAsJsonObject();
                return jsonObject.getAsJsonObject("rates");
            } else {
                System.out.println("Erro na requisição da API");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public double obterTaxaDeCambio(String moedaDestino) {
        JsonObject rates = obterTodasAsTaxas();
        if (rates != null && rates.has(moedaDestino)) {
            return rates.get(moedaDestino).getAsDouble();
        } else {
            System.out.println("Moeda não encontrada.");
            return -1;
        }
    }

    public void listarMoedas() {
        JsonObject rates = obterTodasAsTaxas();
        if (rates != null) {
            Set<String> moedas = rates.keySet();
            System.out.println("Moedas disponíveis para conversão:");
            for (String moeda : moedas) {
                System.out.println(moeda);
            }
        }
    }
}
