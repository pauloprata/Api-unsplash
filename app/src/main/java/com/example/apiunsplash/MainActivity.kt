package com.example.apiunsplash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.apiunsplash.Api.PhotosAPI
import com.example.apiunsplash.Api.RetrofitService
import com.example.apiunsplash.adapter.PhotoAdapter
import com.example.apiunsplash.databinding.ActivityMainBinding
import com.example.apiunsplash.model.PhotosResponse
import com.example.apiunsplash.model.SearchResponse
import kotlinx.coroutines.*
import retrofit2.Response

//Acccess keyy Ug8NHwzqRIUQAZE-R48KJ8sqpONXrXXBbV50sRCJ4Po
class MainActivity : AppCompatActivity() {
    private var job: Job? = null
    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val photoAPI by lazy{
        RetrofitService.getAPI(PhotosAPI::class.java)
    }
    private val TAG = "info_filme"
    private var photoadapter: PhotoAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

         photoadapter = PhotoAdapter()
         binding.rvPhotos.adapter = photoadapter
         binding.rvPhotos.layoutManager = GridLayoutManager(this,2)

        binding.btnPesquisar.setOnClickListener {
            pesquisarPhotos()
            binding.editPesq.setText("")
        }

    }
    private fun pesquisarPhotos(){
        CoroutineScope(Dispatchers.IO).launch {
            val campoPesquisa = binding.editPesq.text.toString()
            if(campoPesquisa.isNotEmpty()){
                var resposta: Response<SearchResponse>? = null
                try {
                    resposta = photoAPI.pesquisarPhotos(campoPesquisa)
                }catch (e:Exception){
                    e.printStackTrace()
                    Log.i(TAG, "Erro aos recuperar photos")
                }
                if ( resposta != null ){
                    if( resposta.isSuccessful ){
                        val searchPhotos = resposta.body()?.results
                        Log.i("paulo", "$searchPhotos ")
                        if ( searchPhotos != null ){
                            withContext(Dispatchers.Main){
                                photoadapter?.adicionarLista(searchPhotos)
                            }
                        }
                        /*listaFilmes?.forEach { filme ->
                            Log.i(TAG, "${filme.id} - ${filme.title}")
                        }*/
                    }else{
                        Log.i(TAG, "Erro na requisição-codigo erro: ${resposta.code()}")
                    }
                }
            }

        }
    }
    private fun recuperarPhotos(){
        // A cada 20 segundos, atualiza os dados
        job = CoroutineScope(Dispatchers.IO).launch {
            var resposta: Response<PhotosResponse>? = null
            try {
                resposta = photoAPI.recuperarPhotos(  )
            }catch (e:Exception){
                e.printStackTrace()
                Log.i(TAG, "Erro aos recuperar filmes populares")
            }
            if ( resposta != null ){
                if( resposta.isSuccessful ){

                    val listaPhotos = resposta.body()
                    if ( listaPhotos != null ){
                        withContext(Dispatchers.Main){
                            photoadapter?.adicionarLista( listaPhotos )
                        }
                    }
                    /*listaFilmes?.forEach { filme ->
                        Log.i(TAG, "${filme.id} - ${filme.title}")
                    }*/
                }else{
                    Log.i(TAG, "Erro na requisição-codigo erro: ${resposta.code()}")
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        recuperarPhotos()
        Log.i("info_ciclo_vida", "onStart: CHAMADO")
    }
}