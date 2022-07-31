package com.kenrico.pocketmovie.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kenrico.pocketmoviecore.data.Resource
import com.kenrico.pocketmoviecore.domain.model.Movie
import com.kenrico.pocketmoviecore.ui.MovieAdapter
import com.kenrico.pocketmovie.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null) {

            homeViewModel.listMovies.observe(viewLifecycleOwner) {
                if(it != null) {
                    when(it) {
                        is Resource.Loading -> {
                            binding.pbLoadingHome.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding.pbLoadingHome.visibility = View.GONE

                            it.data?.let { it1 -> setupUi(it1) }

                            if(it.data?.isEmpty() == true) {
                                binding.tvEmptyNowPlaying.visibility = View.VISIBLE
                            } else {
                                binding.tvEmptyNowPlaying.visibility = View.GONE
                            }
                        }
                        is Resource.Error -> {
                            binding.pbLoadingHome.visibility = View.GONE
                            Toast.makeText(requireContext(), "Error fetch now playing movies", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun setupUi(listMovies: List<Movie>) {
        val rvMovie = binding.rvHomeMovie
        rvMovie.layoutManager = LinearLayoutManager(requireContext())

        val adapter = MovieAdapter()
        rvMovie.adapter = adapter

        adapter.submitList(listMovies)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}