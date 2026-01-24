package br.com.chdevelopent.combustioncarapp.ui


import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.chdevelopent.combustioncarapp.R
import br.com.chdevelopent.combustioncarapp.data.CarsApi
import br.com.chdevelopent.combustioncarapp.data.local.CarRepository
import br.com.chdevelopent.combustioncarapp.domain.Carro
import br.com.chdevelopent.combustioncarapp.ui.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CarFragment : Fragment() {
    lateinit var btnCalcularRedirect: FloatingActionButton
    lateinit var listaCarros: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var noSignalImg: ImageView
    lateinit var noSignalTxt: TextView
    lateinit var carsApi: CarsApi
    var carrosArray: ArrayList<Carro> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRetrofit()
        setupView(view)
        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        if (checkForInternet(context)) {
            callService()
        } else {
            emptyState()
        }
    }

    fun setupRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://carlosh-santiago.github.io/cars-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        carsApi = retrofit.create(CarsApi::class.java)
    }

    fun getAllCars() {
        progressBar.visibility = View.VISIBLE
        carsApi.getAllCars().enqueue(object : Callback<List<Carro>> {
            override fun onResponse(call: Call<List<Carro>>, response: Response<List<Carro>>) {
                if (response.isSuccessful) {
                    progressBar.visibility = View.GONE
                    noSignalImg.visibility = View.GONE
                    noSignalTxt.visibility = View.GONE

                    response.body()?.let {
                        carrosArray.clear()
                        carrosArray.addAll(it)
                        setupList(carrosArray)
                    }
                } else {
                    progressBar.visibility = View.GONE
                    emptyState()
                    Log.e("Retrofit Error", "Response code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Carro>>, t: Throwable) {
                progressBar.visibility = View.GONE
                emptyState()
                Log.e("Retrofit Failure", t.message.toString())
            }
        })
    }

    fun emptyState() {
        progressBar.visibility = View.GONE
        listaCarros.visibility = View.GONE
        noSignalImg.visibility = View.VISIBLE
        noSignalTxt.visibility = View.VISIBLE
    }

    fun setupView(view: View) {
        view.apply {
            btnCalcularRedirect = findViewById(R.id.fab_calcular)
            listaCarros = findViewById(R.id.rv_lista_carros)
            progressBar = findViewById(R.id.pb_loader)
            noSignalImg = findViewById(R.id.iv_empty_state)
            noSignalTxt = findViewById(R.id.tv_no_signal_txt)
        }
    }

    fun setupList(lista: List<Carro>) {
        val carAdapter = CarAdapter(lista)
        listaCarros.apply {
            visibility = View.VISIBLE
            adapter = carAdapter
        }

        carAdapter.carItemLister = { carro ->
            val isSaved = CarRepository(requireContext()).saveIfNotExists(carro)
        }
    }

    fun setupListeners() {
        btnCalcularRedirect.setOnClickListener {
            startActivity(Intent(context, CalcularAutonomiaActivity::class.java))
        }
    }

    fun callService() {
        // Agora usamos o Retrofit para buscar os dados
        getAllCars()
    }

    fun checkForInternet(context: Context?): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}
