package com.example.ac2.api;

public class Api {
    import retrofit2.Call;
    import retrofit2.http.GET;
    import retrofit2.http.Path;

    private static final String BASE_URL = " https://66567e619f970b3b36c59651.mockapi.io/Usuario\n ";
    public interface ViaCepService {
        @GET("{cep}/json/")
        Call<Endereco> buscarEndereco(@Path("cep") String cep);
    }
}
