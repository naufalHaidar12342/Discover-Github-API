package xyz.heydarrn.githubuserwithapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import xyz.heydarrn.githubuserwithapi.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {
    private var _bindingFollower:FragmentFollowerBinding?=null
    private val bindingFollower get() = _bindingFollower!!
    private val viewModel by viewModels<FollowerViewModel>()
    private val adapterFollower by lazy { FollowerListAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _bindingFollower= FragmentFollowerBinding.inflate(inflater,container,false)
        return bindingFollower.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username=arguments?.getString(UserDetailActivity.EXTRA_USERNAME).toString()

        bindingFollower.apply {
            recyclerViewFollower.layoutManager=LinearLayoutManager(context)
            recyclerViewFollower.adapter=adapterFollower
            viewModel.getFollower(username)
            viewModel.setFollower().observe(viewLifecycleOwner){
                if (it!=null){
                    adapterFollower.submitList(it)
                    progressBarFollower.visibility=View.INVISIBLE
                }
            }
            viewModel.viewModelScope.launch {
                delay(1000)
                progressBarFollower.visibility=View.INVISIBLE
            }
        }
    }
}