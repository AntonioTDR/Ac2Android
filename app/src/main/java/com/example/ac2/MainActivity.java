package com.example.ac2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText editTextRa, editTextNome, editTextCep, editTextLogradouro, editTextComplemento, editTextBairro, editTextCidade, editTextUf;
    private Button buttonBuscarCep, buttonSalvar;

    private ViaCepService viaCepService;
    private MockApiService mockApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextRa = findViewById(R.id.editTextRa);
        editTextNome = findViewById(R.id.editTextNome);
        editTextCep = findViewById(R.id.editTextCep);
        editTextLogradouro = findViewById(R.id.editTextLogradouro);
        editTextComplemento = findViewById(R.id.editTextComplemento);
        editTextBairro = findViewById(R.id.editTextBairro);
        editTextCidade = findViewById(R.id.editTextCidade);
        editTextUf = findViewById(R.id.editTextUf);
        buttonBuscarCep = findViewById(R.id.buttonBuscarCep);
        buttonSalvar = findViewById(R.id.buttonSalvar);

        Retrofit retrofitViaCep = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Retrofit retrofitMockApi = new Retrofit.Builder()
                .baseUrl("https://mockapi.io/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        viaCepService = retrofitViaCep.create(ViaCepService.class);
        mockApiService = retrofitMockApi.create(MockApiService.class);

        buttonBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarCep();
            }
        });

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarAluno();
            }
        });
    }

    private void buscarCep() {
        String cep = editTextCep.getText().toString().trim();
        Call<Endereco> call = viaCepService.buscarEndereco(cep);
        call.enqueue(new Callback<Endereco>() {
            @Override
            public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                if (response.isSuccessful()) {
                    Endereco endereco = response.body();
                    if (endereco != null) {
                        editTextLogradouro.setText(endereco.getLogradouro());
                        editTextComplemento.setText(endereco.getComplemento());
                        editTextBairro.setText(endereco.getBairro());
                        editTextCidade.setText(endereco.getLocalidade());
                        editTextUf.setText(endereco.getUf());
                    }
                } else {
                    Toast.makeText(MainActivity.this, "CEP não encontrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Endereco> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro na requisição", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void salvarAluno() {
        int ra = Integer.parseInt(editTextRa.getText().toString().trim());
        String nome = editTextNome.getText().toString().trim();
        String cep = editTextCep.getText().toString().trim();
        String logradouro = editTextLogradouro.getText().toString().trim();
        String complemento = editTextComplemento.getText().toString().trim();
        String bairro = editTextBairro.getText().toString().trim();
        String cidade = editTextCidade.getText().toString().trim();
        String uf = editTextUf.getText().toString().trim();

        Aluno aluno = new Aluno(ra, nome, cep, logradouro, complemento, bairro, cidade, uf);
        Call<Aluno> call = mockApiService.criarAluno(aluno);
        call.enqueue(new Callback<Aluno>() {
            @Override
            public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Aluno salvo com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao salvar aluno", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Aluno> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro na requisição", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
