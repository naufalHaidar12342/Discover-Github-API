package xyz.heydarrn.githubuserwithapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import xyz.heydarrn.githubuserwithapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bindingMain:ActivityMainBinding
    private val viewModel by viewModels<SearchViewModel>()
    private val adapterSearch by lazy { SearchListAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain= ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        
        bindingMain.apply {
            //mendapatkan teks dari searchview
            searchviewUser.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    //ketika user tekan enter/klik kaca pembesar
                    viewModel.searchUser(p0!!)
                    searchviewUser.clearFocus()
                    loadingAnimationSearch.visibility=View.VISIBLE
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    //ketika user sedang mengetik
                    loadingAnimationSearch.visibility=View.VISIBLE
                    return false
                }

            })

            recyclerViewSearch.apply {
                this.layoutManager=LinearLayoutManager(this@MainActivity)
                this.adapter=adapterSearch
            }
            adapterSearch.openDetailWithUsername(object : SearchListAdapter.OnUserClicked {
                override fun chooseUser(userChosen: String) {
                    startActivity(
                        Intent(this@MainActivity,UserDetailActivity::class.java).putExtra(UserDetailActivity.EXTRA_USERNAME,userChosen)
                    )
                }

            })
            viewModel.setSearchUser().observe(this@MainActivity){
                if (it!=null){
                    adapterSearch.submitList(it)
                    loadingAnimationSearch.visibility=View.INVISIBLE
                }
            }
        }

    }

}