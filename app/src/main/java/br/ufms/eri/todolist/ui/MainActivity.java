package br.ufms.eri.todolist.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import br.ufms.eri.todolist.R;
import br.ufms.eri.todolist.model.Tarefa;

public class MainActivity extends AppCompatActivity {

    TextView textviewListaVazia;
    ListView listViewDeTarefas;
    ArrayAdapter<Tarefa> tarefaArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textviewListaVazia = (TextView) findViewById(R.id.texto_lista_vazia);
        listViewDeTarefas = (ListView) findViewById(R.id.lista_de_tarefas);


    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Tarefa> listaDeTarefas = buscarListaDeTarefas();
        if (listaDeTarefas.size() > 0) {
            //TODO Nao mostrar o texto de lista vazia
            textviewListaVazia.setVisibility(View.GONE);
            listViewDeTarefas.setVisibility(View.VISIBLE);

            tarefaArrayAdapter =
                    new ArrayAdapter<Tarefa>(this, android.R.layout.simple_list_item_1, listaDeTarefas);

            listViewDeTarefas.setAdapter(tarefaArrayAdapter);

            listViewDeTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Tarefa tarefa = (Tarefa) parent.getAdapter().getItem(position);
                    Intent intent = new Intent(MainActivity.this, AdicionarTarefaActivity.class);
                    intent.putExtra("OBJETO_TAREFA", tarefa);
                    startActivity(intent);
                }
            });

            listViewDeTarefas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    final Tarefa tarefa = (Tarefa) parent.getAdapter().getItem(position);
                    mostraAlerta(tarefa);
                    return true;
                }
            });
        } else {
            //TODO Mostrar o texto de lista vazia
            textviewListaVazia.setVisibility(View.VISIBLE);
            listViewDeTarefas.setVisibility(View.GONE);
        }
    }

    private void mostraAlerta(final Tarefa tarefa) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
        alerta.setTitle("Excluir");
        alerta.setMessage("Tem certeza que você deseja deletar esse item");
        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tarefa.delete();
                tarefaArrayAdapter.remove(tarefa);
                tarefaArrayAdapter.notifyDataSetChanged();
            }
        });
        alerta.setNegativeButton("CANCELAR", null);
        alerta.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.acao_salvar) {
            Intent intent = new Intent(MainActivity.this, AdicionarTarefaActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public List<Tarefa> buscarListaDeTarefas() {
        //"SELECT * FROM tarefa;"
        //List<Tarefa> listaDeTarefas = new Select().all().from(Tarefa.class).queryList();
        //return  listaDeTarefas;
        return new Select().all().from(Tarefa.class).queryList();
    }


}
