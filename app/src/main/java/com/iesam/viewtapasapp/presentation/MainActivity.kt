package com.iesam.viewtapasapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.iesam.viewtapasapp.R
import com.iesam.viewtapasapp.app.ErrorApp
import com.iesam.viewtapasapp.app.extensions.hide
import com.iesam.viewtapasapp.app.extensions.setUrl
import com.iesam.viewtapasapp.app.extensions.visible
import com.iesam.viewtapasapp.app.serialization.GsonSerialization
import com.iesam.viewtapasapp.data.TapaDataRepository
import com.iesam.viewtapasapp.data.local.XmlLocalDataSource
import com.iesam.viewtapasapp.data.remote.ApiMockRemoteDataSource
import com.iesam.viewtapasapp.databinding.ActivityMainBinding
import com.iesam.viewtapasapp.domain.GetTapaUseCase
import com.iesam.viewtapasapp.domain.Tapa

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel : ActivityViewModel by lazy{
        ActivityViewModel(GetTapaUseCase(TapaDataRepository(XmlLocalDataSource(this, GsonSerialization()), ApiMockRemoteDataSource())))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        loadTapa()
    }

    private fun setupBinding(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun loadTapa () {
        setupObserver()
        viewModel.loadTapa()
    }

    private fun setupObserver () {
        val observer = Observer<ActivityViewModel.UiState>{
            if(it.isLoading){
                showLoading()
            }else{
                hideLoading()
                if (it.errorApp != null){
                    showError(it.errorApp)
                } else {
                    if (it.tapa != null) bindData(it.tapa)
                }
            }
        }
        viewModel.uiState.observe(this,observer)
    }

    private fun bindData(tapa : Tapa){
        binding.apply {
            imageTapa.setUrl("https://imag.bonviveur.com/como-hacer-albondigas-en-salsa-de-tomate.jpg")
            labelPosition.text = tapa.id
            labelTitle.text = tapa.title
            labelBar.text = tapa.bar
            labelPtos.text = tapa.points
            labelMedia.text = tapa.media
        }
    }

    private fun showError (error : ErrorApp){
        binding.layoutView.hide()
        binding.viewError.layoutError.visible()

        when(error) {
            ErrorApp.UnknowError -> binding.viewError.labelMesaggeError.text =
                getString(R.string.label_unknow_error)
        }
    }

    private fun hideError (){
        binding.viewError.layoutError.hide()
    }

    private fun showLoading(){
        binding.skeletonLayout.showSkeleton()
    }

    private fun hideLoading(){
        binding.skeletonLayout.showOriginal()
    }


}