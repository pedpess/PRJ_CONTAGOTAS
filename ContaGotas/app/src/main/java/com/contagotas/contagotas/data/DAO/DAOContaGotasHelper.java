package com.contagotas.contagotas.data.DAO;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.contagotas.contagotas.MyApplication;
import com.contagotas.contagotas.data.DAO.DaoMaster;

import static com.contagotas.contagotas.data.DAO.DaoMaster.createAllTables;
import static com.contagotas.contagotas.data.DAO.DaoMaster.dropAllTables;

/**
 * Created by admin on 10/26/15.
 */
public class DAOContaGotasHelper extends DaoMaster.OpenHelper {

    Context mcontext;


    public DAOContaGotasHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
        this.mcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
        try {
            createAllTables(db, true);
            MyApplication.mDaoMaster = new DaoMaster(db);
            MyApplication.setDaoSession(MyApplication.mDaoMaster.newSession());
        }catch(Exception e){

        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //   if(oldVersion!=newVersion){
        dropAllTables(db, true);
        onCreate(db);

        // }


    }
}
