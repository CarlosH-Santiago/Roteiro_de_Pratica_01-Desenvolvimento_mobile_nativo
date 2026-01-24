package br.com.chdevelopent.combustioncarapp.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.chdevelopent.combustioncarapp.data.local.CarrosContract.SQL_DELETE_ENTRIES
import br.com.chdevelopent.combustioncarapp.data.local.CarrosContract.TABLE_CAR


class CarsDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(TABLE_CAR)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }

    companion object {
        const val DATABASE_VERSION = 2 // Versão incrementada para forçar a atualização
        const val DATABASE_NAME = "DbCarros.db"
    }
}