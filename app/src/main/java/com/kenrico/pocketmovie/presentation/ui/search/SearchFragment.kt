package com.kenrico.pocketmovie.presentation.ui.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kenrico.pocketmoviecore.data.Resource
import com.kenrico.pocketmoviecore.domain.model.Movie
import com.kenrico.pocketmoviecore.ui.MovieAdapter
import com.kenrico.pocketmovie.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null) {

            val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
            val searchView = binding.svMovie

            searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        searchViewModel.insertQuery(query)
                        searchView.clearFocus()
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })

            searchViewModel.searchedMovies.observe(viewLifecycleOwner) {
                if(it != null) {
                    when(it) {
                        is Resource.Loading -> {
                            binding.pbLoadingSearch.visibility = View.VISIBLE
                            binding.lottieInitialSearch.visibility = View.GONE
                        }
                        is Resource.Success -> {
                            binding.pbLoadingSearch.visibility = View.GONE
                            binding.lottieInitialSearch.visibility = View.GONE

                            it.data?.let { it1 -> setupSearchResult(it1) }

                            if(it.data?.isEmpty() == true) {
                                binding.tvSearchEmpty.visibility = View.VISIBLE
                                binding.lottieEmptySearch.visibility = View.VISIBLE
                            } else {
                                binding.tvSearchEmpty.visibility = View.GONE
                                binding.lottieEmptySearch.visibility = View.GONE
                            }
                        }
                        is Resource.Error -> {
                            binding.pbLoadingSearch.visibility = View.GONE
                            binding.lottieInitialSearch.visibility = View.GONE
                            Toast.makeText(requireContext(), "Error searching movies", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun setupSearchResult(searchedMovies: List<Movie>) {
        val rvSearch = binding.rvSearch
        rvSearch.layoutManager = LinearLayoutManager(requireContext())

        val adapter = MovieAdapter()
        rvSearch.adapter = adapter
        adapter.submitList(searchedMovies)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}