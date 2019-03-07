package com.consultoraestrategia.ss_crmeducativo.lib;


import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by irvinmarin on 24/04/2017.
 */

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION, insertConflict = ConflictAction.REPLACE)
public class AppDatabase {
    public static final String NAME = "CRMEfinal";
    public static final int VERSION = 3;
}
