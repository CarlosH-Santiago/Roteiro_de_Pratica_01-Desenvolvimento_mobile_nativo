package br.com.chdevelopent.combustioncarapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.chdevelopent.combustioncarapp.R
import androidx.recyclerview.widget.RecyclerView
import br.com.chdevelopent.combustioncarapp.domain.Carro

class CarAdapter(private val carros: List<Carro>) :
    RecyclerView.Adapter<CarAdapter.ViewHolder>()  {
    // Cria uma nova view
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carro_item, parent, false)
        return ViewHolder(view)
    }
 // Pega o conteudo de uma view e troca pela informação de uma lista
 override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.preco.text = carros[position].preco
        holder.tanque.text = carros[position].tanque
        holder.potencia.text = carros[position].potencia
        holder.aceleracao.text = carros[position].aceleracao
    }

    // Pega a quantidadede carros da lista
    override fun getItemCount(): Int = carros.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val preco: TextView
        val tanque: TextView
        val potencia: TextView
        val aceleracao: TextView

        init {
            view.apply {
                preco = findViewById(R.id.tv_preco_value)
                tanque = findViewById(R.id.tv_tanque_value)
                potencia = findViewById(R.id.tv_potencia_value)
                aceleracao = findViewById(R.id.tv_aceleracao_value)
            }

        }
    }
}

