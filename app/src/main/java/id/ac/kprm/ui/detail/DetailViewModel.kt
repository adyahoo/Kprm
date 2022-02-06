package id.ac.kprm.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.kprm.data.Detail
import id.ac.kprm.data.RemoteDataSource

class DetailViewModel(private val remoteDataSource: RemoteDataSource) : ViewModel() {
    fun getDetailKandidat(id: Int): LiveData<Detail> {
        val detail = MutableLiveData<Detail>()

        remoteDataSource.getDetailKandidat(id,
            object : RemoteDataSource.ResponseDetailKandidatCallback {
                override fun onGetDetailKandidat(calon: Detail?) {
                    detail.value = calon
                }
            })

        return detail
    }

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun userVote(id: Int, pemilih: Int) {
        remoteDataSource.postVote(id, pemilih, object : RemoteDataSource.ResponseCallback {
            override fun onSuccess(message: String?) {
                _success.value = true
                _msg.value = message
            }

            override fun onFail(message: String?) {
                _success.value = false
                _msg.value = message
            }
        })
    }
}