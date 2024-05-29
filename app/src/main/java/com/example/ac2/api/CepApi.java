package com.example.ac2.api;

public class CepApi {
    import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

    public interface ViaCepApi {
        @GET("{cep}/json/")
        Call<Endereco> getEndereco(@Path("cep") String cep);
    }

}
