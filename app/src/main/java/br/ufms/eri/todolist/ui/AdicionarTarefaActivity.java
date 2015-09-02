package br.ufms.eri.todolist.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import br.ufms.eri.todolist.R;
import br.ufms.eri.todolist.model.Tarefa;

public class AdicionarTarefaActivity extends AppCompatActivity {

    EditText titulo;
    EditText descricao;
    EditText dataInicio;
    EditText dataFim;
    CheckBox concluida;

    Tarefa tarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        titulo = (EditText) findViewById(R.id.editTextTitulo);
        descricao = (EditText) findViewById(R.id.editTextDescricao);
        dataInicio = (EditText) findViewById(R.id.editTextDataInicio);
        dataFim = (EditText) findViewById(R.id.editTextDataFim);
        concluida = (CheckBox) findViewById(R.id.checkBoxConcluida);

        if (getIntent().hasExtra("OBJETO_TAREFA")){
            tarefa = (Tarefa) getIntent().getExtras().getSerializable("OBJETO_TAREFA");
            titulo.setText(tarefa.titulo);
            descricao.setText(tarefa.descricao);
            dataInicio.setText(tarefa.dataInicio);
            dataFim.setText(tarefa.dataFim);
            concluida.setChecked(tarefa.concluida);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.acao_salvar) {
            if (tarefa == null) {
                tarefa = new Tarefa();
            }
            salvar();


            /*String mensagem = "Titulo:"+titulo.getText().toString()+ "\n"+
                    "Descrição:"+descricao.getText().toString()+ "\n"+
                    "Data Inicio:"+dataInicio.getText().toString()+ "\n"+
                    "Data Fim:"+dataFim.getText().toString()+
                    "Concluida:"+concluida.isChecked();
            Toast.makeText(AdicionarTarefaActivity.this, mensagem, Toast.LENGTH_SHORT).show();*/
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void salvar() {
        tarefa.titulo = titulo.getText().toString();
        tarefa.descricao = descricao.getText().toString();
        tarefa.dataInicio = dataInicio.getText().toString();
        tarefa.dataFim = dataFim.getText().toString();
        tarefa.concluida = concluida.isChecked();
        tarefa.save();
        finish();
    }


















}
