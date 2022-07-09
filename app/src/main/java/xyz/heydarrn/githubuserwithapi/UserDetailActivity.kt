package xyz.heydarrn.githubuserwithapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import xyz.heydarrn.githubuserwithapi.databinding.ActivityUserDetailBinding

class UserDetailActivity : AppCompatActivity() {
    private lateinit var bindingDetail:ActivityUserDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDetail= ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(bindingDetail.root)
    }
}