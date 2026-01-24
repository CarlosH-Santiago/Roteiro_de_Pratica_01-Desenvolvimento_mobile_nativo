package br.com.chdevelopent.combustioncarapp.data.local

import android.provider.BaseColumns

object CarrosContract {

    object CarEntry : BaseColumns {
        const val TABLE_NAME = "car"
        const val COLUMN_NAME_CAR_ID = "car_id"
        const val COLUMN_NAME_PRECO = "preco"
        const val COLUMN_NAME_TANQUE = "tanque"
        const val COLUMN_NAME_POTENCIA = "potencia"
        const val COLUMN_NAME_ACELERACAO = "aceleracao"
        const val COLUMN_NAME_URL_PHOTO = "url_photo"
    }

    const val TABLE_CAR =
        "CREATE TABLE ${CarEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${CarEntry.COLUMN_NAME_CAR_ID} TEXT NOT NULL," +
                "${CarEntry.COLUMN_NAME_PRECO} TEXT NOT NULL," +
                "${CarEntry.COLUMN_NAME_TANQUE} TEXT NOT NULL," +
                "${CarEntry.COLUMN_NAME_POTENCIA} TEXT NOT NULL," +
                "${CarEntry.COLUMN_NAME_ACELERACAO} TEXT NOT NULL," +
                "${CarEntry.COLUMN_NAME_URL_PHOTO} TEXT NOT NULL)"

    const val SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS ${CarEntry.TABLE_NAME}"
}