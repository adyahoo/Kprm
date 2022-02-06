package id.ac.kprm.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.kprm.data.RemoteDataSource
import id.ac.kprm.ui.detail.DetailViewModel
import id.ac.kprm.ui.login.LoginViewModel

class ViewModelFactory(private val remoteDataSource: RemoteDataSource): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(remoteDataSource) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(remoteDataSource) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(remoteDataSource) as T
            }
            else -> throw Throwable("Undefined ViewModel Class " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(
                    RemoteDataSource(context)
                )
            }
        }
    }
}