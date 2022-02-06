package id.ac.kprm.data

import android.content.Context
import android.util.Log
import id.ac.kprm.ui.login.UserSession
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val context: Context) {
    interface ResponseCallback {
        fun onSuccess(message: String?)
        fun onFail(message: String?)
    }

    interface ResponseKandidatCallback {
        fun onGetKandidat(calon: List<CalonItem>?, message: String?)
    }

    interface ResponseDetailKandidatCallback {
        fun onGetDetailKandidat(calon: Detail?)
    }

    fun login(username: String, password: String, responseCallback: ResponseCallback) {
        ApiConfig.getApi().loginUser(username, password).enqueue(object :
            Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if (response.body()?.status == 200) {
                    UserSession(context).login(
                        username,
                        response.body()?.token,
                        response.body()?.user?.id,
                        response.body()?.isVote!!
                    )
                    responseCallback.onSuccess(response.body()?.message)
                } else {
                    responseCallback.onFail(response.body()?.message)
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                responseCallback.onFail(t.message.toString())
            }
        })
    }

    fun getKandidat(responseCallback: ResponseKandidatCallback) {
        ApiConfig.getApi().getKandidat().enqueue(object : Callback<ResponseGetKandidat> {
            override fun onResponse(
                call: Call<ResponseGetKandidat>,
                response: Response<ResponseGetKandidat>
            ) {
                if (response.body()?.status == 200) {
                    responseCallback.onGetKandidat(
                        response.body()?.calon,
                        response.body()?.message
                    )
                }
            }

            override fun onFailure(call: Call<ResponseGetKandidat>, t: Throwable) {
                Log.e("onFailure", t.message.toString())
            }
        })
    }

    fun getDetailKandidat(id: Int, responseCallback: ResponseDetailKandidatCallback) {
        ApiConfig.getApi().getDetailKandidat(id).enqueue(object : Callback<ResponseGetDetail> {
            override fun onResponse(
                call: Call<ResponseGetDetail>,
                response: Response<ResponseGetDetail>
            ) {
                if (response.body()?.status == 200) {
                    responseCallback.onGetDetailKandidat(response.body()?.detail)
                }
            }

            override fun onFailure(call: Call<ResponseGetDetail>, t: Throwable) {
                Log.e("onFailure", t.message.toString())
            }
        })
    }

    fun postVote(id: Int, pemilih: Int, responseCallback: ResponseCallback) {
        ApiConfig.getApi().userVote(id, id, pemilih).enqueue(object : Callback<ResponseVote> {
            override fun onResponse(call: Call<ResponseVote>, response: Response<ResponseVote>) {
                if (response.body()?.status == 200) {
                    responseCallback.onSuccess(response.body()?.message)
                } else {
                    responseCallback.onFail(response.body()?.message)
                }
            }

            override fun onFailure(call: Call<ResponseVote>, t: Throwable) {
                responseCallback.onFail(t.message.toString())
            }
        })
    }
}