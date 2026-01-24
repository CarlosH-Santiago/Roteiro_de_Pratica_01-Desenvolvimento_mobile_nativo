package br.com.chdevelopent.combustioncarapp.data.local

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import br.com.chdevelopent.combustioncarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_CAR_ID
import br.com.chdevelopent.combustioncarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_PRECO
import br.com.chdevelopent.combustioncarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_POTENCIA
import br.com.chdevelopent.combustioncarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_ACELERACAO
import br.com.chdevelopent.combustioncarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_TANQUE
import br.com.chdevelopent.combustioncarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_URL_PHOTO
import br.com.chdevelopent.combustioncarapp.data.local.CarrosContract.CarEntry.TABLE_NAME
import br.com.chdevelopent.combustioncarapp.domain.Carro

class CarRepository(private val context: Context) {

    fun save(carro: Carro) : Boolean {
        var inSaved = false

        try {
        val dbHelper = CarsDBHelper( context)
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(COLUMN_NAME_CAR_ID, carro.id)
            put(COLUMN_NAME_PRECO, carro.preco)
            put(COLUMN_NAME_TANQUE, carro.tanque)
            put(COLUMN_NAME_POTENCIA, carro.potencia)
            put(COLUMN_NAME_ACELERACAO, carro.aceleracao)
            put(COLUMN_NAME_URL_PHOTO, carro.urlPhoto)
        }

        val inserted = db?.insert(TABLE_NAME, null, values)
        if (inserted != null) {
            inSaved = true
            }

        } catch (ex : Exception) {
            ex.message?.let {
                Log.e("Erro ao inserir daods -> ", it)
            }
        }
        return inSaved
    }

    fun findCarById(id: Int) : Carro {
        val dbHelper = CarsDBHelper( context)
        val db = dbHelper.readableDatabase
        val columns = arrayOf(
            BaseColumns._ID,
            COLUMN_NAME_CAR_ID,
            COLUMN_NAME_PRECO,
            COLUMN_NAME_TANQUE,
            COLUMN_NAME_POTENCIA,
            COLUMN_NAME_ACELERACAO,
            COLUMN_NAME_URL_PHOTO)

        val filter = "$COLUMN_NAME_CAR_ID = ?"
        val filterValues = arrayOf(id.toString())

        val cursor = db.query(
            TABLE_NAME,
            columns,
            filter,
            filterValues,
            null,
            null,
            null
        )

            var itemId : Long = 0
            var preco =  ""
            var tanque = ""
            var potencia = ""
            var aceleracao = ""
            var urlPhoto = ""

        with(cursor) {

            while(moveToNext()) {
                itemId = getLong(getColumnIndexOrThrow(COLUMN_NAME_CAR_ID))
                Log.d("ID -> ",itemId.toString())
                preco = getString(getColumnIndexOrThrow(COLUMN_NAME_PRECO))
                Log.d("Preço -> ",preco.toString())
                tanque = getString(getColumnIndexOrThrow(COLUMN_NAME_TANQUE))
                Log.d("Tanque -> ",tanque.toString())
                potencia = getString(getColumnIndexOrThrow(COLUMN_NAME_POTENCIA))
                Log.d("Potência -> ",potencia.toString())
                aceleracao = getString(getColumnIndexOrThrow(COLUMN_NAME_ACELERACAO))
                Log.d("Aceleração -> ",aceleracao.toString())
                urlPhoto = getString(getColumnIndexOrThrow(COLUMN_NAME_URL_PHOTO))
                Log.d("URL da Foto -> ",urlPhoto.toString())
            }
        }
        cursor.close()
        return Carro(
            id = itemId.toInt(),
            preco = preco,
            tanque = tanque,
            potencia = potencia,
            aceleracao = aceleracao,
            urlPhoto = urlPhoto,
            isFavorite = true
        )
    }

    fun saveIfNotExists(carro: Carro) {

        val car = findCarById(carro.id)
        if (car.id == ID_WHEN_NO_CAR ) {
            save(carro)
        }
    }

    fun getAll() : List<Carro> {
        val dbHelper = CarsDBHelper( context)
        val db = dbHelper.readableDatabase
        val columns = arrayOf(
            BaseColumns._ID,
            COLUMN_NAME_CAR_ID,
            COLUMN_NAME_PRECO,
            COLUMN_NAME_TANQUE,
            COLUMN_NAME_POTENCIA,
            COLUMN_NAME_ACELERACAO,
            COLUMN_NAME_URL_PHOTO)

        val cursor = db.query(
            TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        )

        val carros = mutableListOf<Carro>()


        with(cursor) {

            while(moveToNext()) {
                val itemId = getLong(getColumnIndexOrThrow(COLUMN_NAME_CAR_ID))
                Log.d("ID -> ",itemId.toString())
                val preco = getString(getColumnIndexOrThrow(COLUMN_NAME_PRECO))
                Log.d("Preço -> ",preco.toString())
                val tanque = getString(getColumnIndexOrThrow(COLUMN_NAME_TANQUE))
                Log.d("Tanque -> ",tanque.toString())
                val potencia = getString(getColumnIndexOrThrow(COLUMN_NAME_POTENCIA))
                Log.d("Potência -> ",potencia.toString())
                val aceleracao = getString(getColumnIndexOrThrow(COLUMN_NAME_ACELERACAO))
                Log.d("Aceleração -> ",aceleracao.toString())
                val urlPhoto = getString(getColumnIndexOrThrow(COLUMN_NAME_URL_PHOTO))
                Log.d("URL da Foto -> ",urlPhoto.toString())

                carros.add(
                    Carro(
                        id = itemId.toInt(),
                        preco = preco,
                        tanque = tanque,
                        potencia = potencia,
                        aceleracao = aceleracao,
                        urlPhoto = urlPhoto,
                        isFavorite = true
                    )
                )
            }
        }
        cursor.close()
        return carros
    }

    companion object {
        const val ID_WHEN_NO_CAR = 0
    }
}