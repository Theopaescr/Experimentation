package br.com.mpc.experimentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.mpc.experimentation.databinding.FragmentHomeBinding
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
}