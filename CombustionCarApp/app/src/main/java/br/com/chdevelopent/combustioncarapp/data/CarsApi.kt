package br.com.chdevelopent.combustioncarapp.data

import br.com.chdevelopent.combustioncarapp.domain.Carro
import retrofit2.Call
import retrofit2.http.GET

interface CarsApi {

    @GET("cars.json")
    fun getAllCars(): Call<List<Carro>>
}