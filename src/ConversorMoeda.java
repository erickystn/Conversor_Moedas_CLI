import com.google.gson.Gson;
import DTO.CambioDTO;
import io.github.cdimascio.dotenv.Dotenv;
import modelo.TipoMoeda;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorMoeda {
    private TipoMoeda moedaBase, moedaAlvo;

    public ConversorMoeda(TipoMoeda moedaBase, TipoMoeda moedaAlvo) {
        this.moedaBase = moedaBase;
        this.moedaAlvo = moedaAlvo;
    }

    public double converterMoeda(double valorAConverter) {
        return valorAConverter * obterCambio();
    }

    private double obterCambio() {
        Dotenv dotenv = Dotenv.load();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/"+dotenv.get("API_KEY")+"/pair/" + moedaBase.getCodigo() + "/" + moedaAlvo.getCodigo()))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return new Gson().fromJson(response.body(), CambioDTO.class).conversion_rate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public TipoMoeda getMoedaBase() {
        return moedaBase;
    }

    public TipoMoeda getMoedaAlvo() {
        return moedaAlvo;
    }
}
