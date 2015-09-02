package br.ufms.eri.todolist.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

/**
 * Created by rodrigo on 26/08/15.
 */
@Table(databaseName = TODOBancoDeDados.NAME, tableName = "tarefa")
public class Tarefa extends BaseModel implements Serializable{

    @Column(name = "id")
    @PrimaryKey(autoincrement = true)
    public long id;

    @Column(name = "titulo")
    public String titulo;

    @Column(name = "descricao")
    public String descricao;

    @Column(name = "data_inicio")
    public String dataInicio;

    @Column(name = "data_fim")
    public String dataFim;

    @Column(name = "concluida")
    public boolean concluida;


    @Override
    public String toString() {
        return titulo;
    }
}
