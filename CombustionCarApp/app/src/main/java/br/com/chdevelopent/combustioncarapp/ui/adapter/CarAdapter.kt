package br.com.chdevelopent.combustioncarapp.ui.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.chdevelopent.combustioncarapp.R
import br.com.chdevelopent.combustioncarapp.domain.Carro
import com.bumptech.glide.Glide

class CarAdapter(private val carros: List<Carro>, private val isFavoriteScreen : Boolean = false) :
    RecyclerView.Adapter<CarAdapter.ViewHolder>() {

        var carItemLister : (Carro) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carro_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val carro = carros[position]
        holder.preco.text = carro.preco
        holder.tanque.text = carro.tanque
        holder.potencia.text = carro.potencia
        holder.aceleracao.text = carro.aceleracao

        if (isFavoriteScreen) {
            holder.favorite.setImageResource(R.drawable.ic_favorite_selected)
        }

        holder.favorite.setOnClickListener {
            carItemLister(carro)
            setupFavorite(carro, holder)
        }

        // Usando o Glide para carregar a imagem da URL
        Glide.with(holder.itemView.context)
            .load(carro.urlPhoto)
            .into(holder.photo)
    }

    private fun setupFavorite(
        carro: Carro,
        holder: ViewHolder
    ) {
        carro.isFavorite = !carro.isFavorite
        if (carro.isFavorite) {
            holder.favorite.setImageResource(R.drawable.ic_favorite_selected)
        } else {
            holder.favorite.setImageResource(R.drawable.ic_favorite)
        }

    }

    override fun getItemCount(): Int = carros.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val preco: TextView
        val tanque: TextView
        val potencia: TextView
        val aceleracao: TextView
        val photo: ImageView // Referência para o ImageView
        val favorite : ImageView

        init {
            view.apply {
                preco = findViewById(R.id.tv_preco_value)
                tanque = findViewById(R.id.tv_tanque_value)
                potencia = findViewById(R.id.tv_potencia_value)
                aceleracao = findViewById(R.id.tv_aceleracao_value)
                // Assumindo que o ID do seu ImageView é 'iv_car_photo'
                photo = findViewById(R.id.iv_car_photo)
                favorite = findViewById(R.id.iv_favorite)
            }
        }
    }
}
