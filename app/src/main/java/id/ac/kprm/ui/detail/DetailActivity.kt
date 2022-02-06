package id.ac.kprm.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.ac.kprm.databinding.ActivityDetailBinding
import id.ac.kprm.ui.ViewModelFactory
import id.ac.kprm.ui.login.UserSession

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var userSession: UserSession
    private var visi: String? = null
    private var misi: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Kandidat"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val itemId = intent.getIntExtra(ITEM_ID, 0)
        userSession = UserSession(this)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)

        viewModel.getDetailKandidat(itemId).observe(this, {
            visi = it.visi
            misi = it.misi

            binding.progbar.visibility = View.GONE
            binding.tvNama.text = it.nama
            binding.tvDesc.text = it.deskripsi
            binding.tvProdi.text = it.prodi
            binding.tvVimisi.text = it.visi

            Glide.with(this)
                .load("https://himaap.kprmfisipunud.web.id/data_file/" + it.foto)
                .into(binding.civDetail)
        })

        binding.btnVisi.setOnClickListener {
            binding.tvVimisi.text = visi
        }

        binding.btnMisi.setOnClickListener {
            binding.tvVimisi.text = misi
        }

        binding.btnSubmit.setOnClickListener {
            vote(itemId)
        }
    }

    private fun vote(itemId: Int) {
        val pref = this.getSharedPreferences(UserSession.PREF_NAME, 0)
        val pemilih = pref.getInt(UserSession.KEY_ID, 0)
        Log.d("asdsad", pemilih.toString())
        viewModel.userVote(itemId, pemilih)

        viewModel.success.observe(this, {
            if (it) {
                userSession.putVoteStatus(true)
            }
            finish()
        })

        viewModel.msg.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val ITEM_ID = "ID"
    }
}