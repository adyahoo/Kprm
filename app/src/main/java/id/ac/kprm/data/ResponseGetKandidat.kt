package id.ac.kprm.data

import com.google.gson.annotations.SerializedName

data class ResponseGetKandidat(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("calon")
	val calon: List<CalonItem>,

	@field:SerializedName("status")
	val status: Int
)

data class CalonItem(

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("foto")
	val foto: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("prodi")
	val prodi: String
)
