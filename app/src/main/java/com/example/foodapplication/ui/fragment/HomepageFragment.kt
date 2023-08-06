package com.example.foodapplication.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.foodapplication.R
import com.example.foodapplication.databinding.FragmentHomepageBinding
import com.example.foodapplication.ui.adapter.YemeklerAdapter
import com.example.foodapplication.ui.viewmodel.HomepageViewModel
import com.example.foodapplication.utils.navPages
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomepageFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentHomepageBinding
    private lateinit var viewModel: HomepageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_homepage, container, false)
        binding.homepageFragment = this
        binding.homepageToolbarTitle = "Hoşgeldiniz"

        //menu search vs
        (activity as AppCompatActivity).setSupportActionBar(
            binding.toolbarHomepage
        )

        viewModel.yemeklerList.observe(viewLifecycleOwner) {
            val adapter = YemeklerAdapter(requireContext(), it, viewModel)
            binding.yemeklerAdapter = adapter
        }

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu, menu)

                val item = menu.findItem(R.id.action_search)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@HomepageFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    //navigate to card
    fun goToCard(it: View) {
        Navigation.navPages(it, R.id.action_homepage_to_card)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: HomepageViewModel by viewModels()
        viewModel = tempViewModel
    }

    //search
    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.search(query)
        return true
    }

    //search
    override fun onQueryTextChange(newText: String): Boolean {
        viewModel.search(newText)
        return true
    }



}

