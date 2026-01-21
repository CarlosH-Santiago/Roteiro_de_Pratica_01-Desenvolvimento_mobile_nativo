package br.com.chdevelopent.combustioncarapp.data

import br.com.chdevelopent.combustioncarapp.domain.Carro

object CarFactory {

    val list = listOf(
        Carro(
            id = 1,
            preco = "R$ 300.000,00",
            tanque = "55L",
            potencia = "220cv",
            aceleracao = "0-100: 3,5s",
            urlPhoto = "www.google.com.br",
        )
    )
}