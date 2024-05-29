import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoHolder> {

    private List<Aluno> alunos;

    public AlunoAdapter(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @NonNull
    @Override
    public AlunoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_aluno, parent, false);
        return new AlunoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoHolder holder, int position) {
        Aluno aluno = alunos.get(position);
        holder.textViewNome.setText(aluno.getNome());
        holder.textViewRa.setText(String.valueOf(aluno.getRa()));
        String endereco = aluno.getLogradouro() + ", " + aluno.getComplemento() + ", " + aluno.getBairro() + ", " + aluno.getCidade() + ", " + aluno.getUf();
        holder.textViewEndereco.setText(endereco);
    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }
}
