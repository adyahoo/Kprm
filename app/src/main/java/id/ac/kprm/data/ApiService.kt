package id.ac.kprm.data

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("login/user")
    fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ResponseLogin>

    @GET("user/calon")
    fun getKandidat(): Call<ResponseGetKandidat>

    @GET("user/calon/detail/{id}")
    fun getDetailKandidat(
        @Path("id") id: Int
    ): Call<ResponseGetDetail>

    @FormUrlEncoded
    @POST("user/vote/{id}")
    fun userVote(
        @Path("id") itemId: Int,
        @Field("id") id: Int,
        @Field("pemilih") pemilih: Int
    ): Call<ResponseVote>
}
