package xyz.heydarrn.githubuserwithapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import xyz.heydarrn.githubuserwithapi.databinding.ActivityUserDetailBinding

class UserDetailActivity : AppCompatActivity() {
    private lateinit var bindingDetail:ActivityUserDetailBinding
    private val viewModel by viewModels<DetailViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDetail= ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(bindingDetail.root)

        val bundle=Bundle()
        bundle.putString(EXTRA_USERNAME,intent.getStringExtra(EXTRA_USERNAME))
        bindingDetail.apply {
            val adapterTabs=TabLayoutAdapter(this@UserDetailActivity,bundle)
            val viewPager=viewpager2

            viewPager.adapter=adapterTabs
            val tabs=tabLayout
            TabLayoutMediator(tabs, viewPager){tab, position ->
                tab.text=resources.getString(TAB_TITLES[position])
            }.attach()

            viewModel.getUserDetail(intent.getStringExtra(EXTRA_USERNAME).toString())
            viewModel.setUserDetail().observe(this@UserDetailActivity){
                if (it!=null){
                    Glide.with(this@UserDetailActivity)
                        .load(it.avatarUrl)
                        .into(detailUserAvatar)
                    detailUsername.text=resources.getString(R.string.username_template,it.login)
                    if (it.name!=null){
                        detailFullname.text=it.name
                    }else{
                        detailFullname.text="Nama lengkap tidak di-set"
                    }

                    if(it.company!=null){
                        detailCompany.text=resources.getString(R.string.company_template,it.company)
                    }else{
                        detailCompany.text="Perusahaan tidak di-set"
                    }

                    if (it.publicRepos!=null){
                        detailRepository.text=resources.getString(R.string.repository_template,it.publicRepos.toString())
                    }else{
                        detailRepository.text="User tidak punya public repo"
                    }
                }
            }
        }
    }
    companion object{
        const val EXTRA_USERNAME="@johndoe"
        val TAB_TITLES= intArrayOf(
            R.string.follower,
            R.string.following
        )
    }
}