package id.ac.kprm.data

import com.google.gson.annotations.SerializedName

data class ResponseGetDetail(

	@field:SerializedName("detail")
	val detail: Detail,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)

data class ResponseVote(
	@field:SerializedName("status")
	val status: Int,

	@field:SerializedName("message")
	val message: String
)

data class Detail(

	@field:SerializedName("misi")
	val misi: String,

	@field:SerializedName("nim")
	val nim: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("foto")
	val foto: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("angkatan")
	val angkatan: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("prodi")
	val prodi: String,

	@field:SerializedName("visi")
	val visi: String
)
