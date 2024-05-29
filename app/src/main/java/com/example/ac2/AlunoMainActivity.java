package com.example.ac2;

public class AlunoMainActivity {
    import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

    public class AlunoMain extends AppCompatActivity {

        private RecyclerView recyclerView;
        private AlunoAdapter alunoAdapter;
        private Button buttonAddAluno;
        private Retrofit retrofit;
        private AlunoApi alunoApi;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_aluno_main);

            recyclerView = findViewById(R.id.recyclerView);
            buttonAddAluno = findViewById(R.id.buttonAddAluno);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://<your-mockapi-url>/")  // Substitua pela URL correta do seu MockAPI
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            alunoApi = retrofit.create(AlunoApi.class);

            buttonAddAluno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AlunoMain.this, AlunoCadastro.class);
                    startActivity(intent);
                }
            });

            loadAlunos();
        }

        private void loadAlunos() {
            Call<List<Aluno>> call = alunoApi.getAlunos();
            call.enqueue(new Callback<List<Aluno>>() {
                @Override
                public void onResponse(Call<List<Aluno>> call, Response<List<Aluno>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Aluno> alunos = response.body();
                        alunoAdapter = new AlunoAdapter(alunos);
                        recyclerView.setAdapter(alunoAdapter);
                    } else {
                        Toast.makeText(AlunoMain.this, "Failed to load alunos", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Aluno>> call, Throwable t) {
                    Toast.makeText(AlunoMain.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
