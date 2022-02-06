package id.ac.kprm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.kprm.data.CalonItem
import id.ac.kprm.data.RemoteDataSource

class MainViewModel(private val remoteDataSource: RemoteDataSource) : ViewModel() {
    fun getKandidat(): LiveData<List<CalonItem>> {
        val listCalon = MutableLiveData<List<CalonItem>>()

        remoteDataSource.getKandidat(object : RemoteDataSource.ResponseKandidatCallback {
            override fun onGetKandidat(calon: List<CalonItem>?, message: String?) {
                listCalon.value = calon
            }
        })

        return listCalon
    }
}