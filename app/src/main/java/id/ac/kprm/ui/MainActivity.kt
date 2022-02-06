package id.ac.kprm.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.kprm.R
import id.ac.kprm.databinding.ActivityMainBinding
import id.ac.kprm.ui.detail.DetailActivity
import id.ac.kprm.ui.login.LoginActivity
import id.ac.kprm.ui.login.UserSession

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userSession: UserSession
    private lateinit var viewModel: MainViewModel
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userSession = UserSession(this)
        supportActionBar?.title = "Kandidat"
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        mainAdapter = MainAdapter {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.ITEM_ID, it.id)
            startActivity(intent)
        }
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = mainAdapter

        viewModel.getKandidat().observe(this, {
            mainAdapter.postData(it)
            binding.progbar.visibility = View.GONE
        })
    }

    override fun onStart() {
        super.onStart()
        if (userSession.checkIsVote()) isVoteLayout(true) else isVoteLayout(false)
    }

    private fun isVoteLayout(isVote: Boolean) {
        if (isVote) {
            binding.rv.visibility = View.GONE
            binding.tvIsVote.visibility = View.VISIBLE
        } else {
            binding.rv.visibility = View.VISIBLE
            binding.tvIsVote.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_logout) {
            userSession.logout()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}