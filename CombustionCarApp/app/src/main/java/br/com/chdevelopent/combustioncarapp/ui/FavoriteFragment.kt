package br.com.chdevelopent.combustioncarapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.chdevelopent.combustioncarapp.R
import br.com.chdevelopent.combustioncarapp.data.local.CarRepository
import br.com.chdevelopent.combustioncarapp.domain.Carro
import br.com.chdevelopent.combustioncarapp.ui.adapter.CarAdapter

class FavoriteFragment : Fragment() {

    lateinit var listaCarrosFavoritos: RecyclerView

    override fun onCreateView(
        infalter: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return infalter.inflate(R.layout.favorite_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        callService()
    }

    override fun onResume() {
        super.onResume()
        callService()
    }

    private fun getCarsOnLocalDb(): List<Carro> {
        val repository = CarRepository(requireContext())
        val carList = repository.getAll()
        return carList
    }

    fun setupList(lista : List<Carro>) {
        val carAdapter = CarAdapter(lista, isFavoriteScreen = true)
        listaCarrosFavoritos.apply {
            visibility = View.VISIBLE
            adapter = carAdapter
        }

        carAdapter.carItemLister = { carro ->
            // @TODO IMPLEMENTAR O DELETE NO BANCO DE DADOS
            // @TODO IMPLEMENTAR FIXAÇÃO DE ICON FAV NO CARRO APRESENTADO (ICONE PERMANECER AMARELO QUANDO FAVORITO)

        }
    }

    fun setupView(view: View) {
        view.apply {
            listaCarrosFavoritos = findViewById(R.id.rv_lista_carros_favoritos)

        }
    }

    fun callService() {
        val car = getCarsOnLocalDb()
        setupList(car)
    }

}