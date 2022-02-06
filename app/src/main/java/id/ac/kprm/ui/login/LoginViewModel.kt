package id.ac.kprm.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.kprm.data.RemoteDataSource

class LoginViewModel(private val remoteDataSource: RemoteDataSource) : ViewModel() {
    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun login(username: String, password: String) {
        remoteDataSource.login(username, password, object : RemoteDataSource.ResponseCallback {
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