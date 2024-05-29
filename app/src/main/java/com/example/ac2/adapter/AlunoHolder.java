package com.example.ac2.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlunoHolder extends RecyclerView.ViewHolder {
    TextView textViewNome;
    TextView textViewRa;
    TextView textViewEndereco;

    public AlunoHolder(@NonNull View itemView) {
        super(itemView);
        textViewNome = itemView.findViewById(R.id.textViewNome);
        textViewRa = itemView.findViewById(R.id.textViewRa);
        textViewEndereco = itemView.findViewById(R.id.textViewEndereco);
    }
}
