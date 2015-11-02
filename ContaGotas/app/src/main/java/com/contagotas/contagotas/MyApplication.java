package com.contagotas.contagotas;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.contagotas.contagotas.data.DAO.DAOContaGotasHelper;
import com.contagotas.contagotas.data.DAO.DaoMaster;
import com.contagotas.contagotas.data.DAO.DaoSession;

/**
 * Created by admin on 10/26/15.
 */
public class MyApplication extends Application {

    public static DaoMaster mDaoMaster;
    public static DaoSession mDaoSession;
    public static SQLiteDatabase mdb;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDatabase();
    }

    private void setupDatabase() {
        DAOContaGotasHelper helper= new DAOContaGotasHelper(this,"contagotas",null);
        mdb= helper.getWritableDatabase();
        mDaoMaster= new DaoMaster(mdb);
        mDaoSession= mDaoMaster.newSession();

    }

    public static void setDaoSession(com.contagotas.contagotas.data.DAO.DaoSession session){
        mDaoSession = session;
    }

}
