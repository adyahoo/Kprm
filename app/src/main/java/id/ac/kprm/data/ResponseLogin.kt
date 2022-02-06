package id.ac.kprm.data

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

	@field:SerializedName("user")
	val user: User,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int,

	@field:SerializedName("is_vote")
	val isVote: Boolean,

	@field:SerializedName("token")
	val token: String
)

data class User(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("nim")
	val nim: String,

	@field:SerializedName("flag")
	val flag: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("angkatan")
	val angkatan: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("screenshot")
	val screenshot: String,

	@field:SerializedName("prodi")
	val prodi: String,

	@field:SerializedName("email")
	val email: String
)
