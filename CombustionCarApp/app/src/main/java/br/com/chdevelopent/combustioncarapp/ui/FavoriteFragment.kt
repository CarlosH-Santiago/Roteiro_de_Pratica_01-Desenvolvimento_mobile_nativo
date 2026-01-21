package br.com.chdevelopent.combustioncarapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.chdevelopent.combustioncarapp.R

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        infalter: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return infalter.inflate(R.layout.favorite_fragment, container, false)
    }
}