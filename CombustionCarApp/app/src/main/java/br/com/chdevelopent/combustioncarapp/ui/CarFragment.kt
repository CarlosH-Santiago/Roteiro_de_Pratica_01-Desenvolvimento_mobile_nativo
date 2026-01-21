package br.com.chdevelopent.combustioncarapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.chdevelopent.combustioncarapp.R
import br.com.chdevelopent.combustioncarapp.data.CarFactory
import br.com.chdevelopent.combustioncarapp.ui.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CarFragment : Fragment(){
    lateinit var btnCalcularRedirect: FloatingActionButton
    lateinit var listaCarros: RecyclerView



    override fun onCreateView(
        infalter: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return infalter.inflate(R.layout.car_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupList()
        setupListeners()
    }

    fun setupView(view: View) {
        view.apply {
            btnCalcularRedirect = findViewById(R.id.fab_calcular)
            listaCarros = findViewById(R.id.rv_lista_carros)
        }
    }
    fun setupList() {
        val dados = CarFactory.list
        val adapter = CarAdapter(dados)
        listaCarros.adapter = adapter
    }

    fun setupListeners() {
        btnCalcularRedirect.setOnClickListener {
             startActivity(Intent(context, CalcularAutonomiaActivity::class.java))
        }
    }
}