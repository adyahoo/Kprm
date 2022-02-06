package id.ac.kprm.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ac.kprm.data.CalonItem
import id.ac.kprm.databinding.ItemMainBinding

class MainAdapter(
    private val onClick: (CalonItem) -> Unit
): RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private var listKandidat: ArrayList<CalonItem> = ArrayList()

    fun postData(items: List<CalonItem>) {
        listKandidat.clear()
        listKandidat.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(calon: CalonItem) {
            binding.tvNama.text = calon.nama
            binding.tvProdi.text = calon.prodi
            Glide.with(itemView.context)
                .load("https://himaap.kprmfisipunud.web.id/data_file/" + calon.foto)
                .into(binding.civKandidat)

            itemView.setOnClickListener {
                onClick(calon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = ItemMainBinding.inflate(view, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listKandidat[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return listKandidat.size
    }
}