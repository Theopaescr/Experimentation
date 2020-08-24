package br.com.mpc.experimentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.mpc.experimentation.adapters.CharactersAdapter
import br.com.mpc.experimentation.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by stateViewModel<HomeViewModel>()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater).apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.viewModel = homeViewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        subscribe()
    }

    private fun setupListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            homeViewModel.getCharacters()
        }
    }
    private fun subscribe() {
        homeViewModel.message.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        homeViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = if (it) View.VISIBLE
            else View.GONE
        })

        homeViewModel.listOfCharacters.observe(viewLifecycleOwner, Observer {
            binding.recyclerListOfCharacters.apply {
                val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                val adapter = CharactersAdapter(it.data.results)

                this.layoutManager = layoutManager
                this.adapter = adapter
            }
        })
    }
}