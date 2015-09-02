package br.ufms.eri.todolist.model;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by rodrigo on 26/08/15.
 */
@Database(name = TODOBancoDeDados.NAME, version = TODOBancoDeDados.VERSION)
public class TODOBancoDeDados {
    public static final String NAME = "TarefasDB";

    public static final int VERSION = 1;
}
