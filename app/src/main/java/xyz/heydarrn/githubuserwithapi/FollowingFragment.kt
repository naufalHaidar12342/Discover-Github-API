package xyz.heydarrn.githubuserwithapi

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import xyz.heydarrn.githubuserwithapi.databinding.FragmentFollowingBinding

class FollowingFragment : Fragment() {
    private var _bindingFollowing:FragmentFollowingBinding?=null
    private val bindingFollowing get() = _bindingFollowing!!
    private val viewModel by viewModels<FollowingViewModel>()
    private val adapterFollowing by lazy { FollowingListAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _bindingFollowing= FragmentFollowingBinding.inflate(inflater,container,false)
        return bindingFollowing.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username=arguments?.getString(UserDetailActivity.EXTRA_USERNAME).toString()
        bindingFollowing.apply {
            recyclerViewFollowing.layoutManager=LinearLayoutManager(context)
            recyclerViewFollowing.adapter=adapterFollowing

            viewModel.getFollower(username)
            viewModel.setFollowing().observe(viewLifecycleOwner){
                if (it!=null){
                    adapterFollowing.submitList(it)
                    loadingAnimationFollowing.visibility=View.INVISIBLE
                }
            }
            viewModel.viewModelScope.launch {
                delay(1000)
                loadingAnimationFollowing.visibility=View.INVISIBLE
            }

        }
    }
}