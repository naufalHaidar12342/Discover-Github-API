package xyz.heydarrn.githubuserwithapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabLayoutAdapter(activity:AppCompatActivity, bundle: Bundle):FragmentStateAdapter(activity) {
    var fragmentBundle=bundle

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment:Fragment?=null
        when(position){
            0 -> fragment=FollowerFragment()
            1 -> fragment=FollowingFragment()
        }
        fragment?.arguments=this.fragmentBundle
        return fragment as Fragment
    }
}