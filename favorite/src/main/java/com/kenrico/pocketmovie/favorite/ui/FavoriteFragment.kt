package com.kenrico.pocketmovie.favorite.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kenrico.pocketmovie.favorite.ViewModelFactory
import com.kenrico.pocketmovie.favorite.databinding.FragmentFavoriteBinding
import com.kenrico.pocketmovie.favorite.di.DaggerFavoriteComponent
import com.kenrico.pocketmovie.presentation.ui.di.FavoriteModule
import com.kenrico.pocketmoviecore.data.Resource
import com.kenrico.pocketmoviecore.ui.MovieAdapter
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    private var _binding: FragmentFavoriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)

        DaggerFavoriteComponent.builder()
            .context(context)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoriteModule::class.java
                )
            )
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null) {

            val rvFavorite = binding.rvFavorite
            rvFavorite.layoutManager = LinearLayoutManager(requireContext())

            favoriteViewModel.favoriteMovies.observe(viewLifecycleOwner) {
                if(it != null) {
                    when(it) {
                        is Resource.Loading -> {
                            // Do nothing
                        }
                        is Resource.Success -> {
                            val adapter = MovieAdapter()
                            rvFavorite.adapter = adapter
                            adapter.submitList(it.data)

                            if(it.data?.isEmpty() == true) {
                                binding.tvFavoriteEmpty.visibility = View.VISIBLE
                                binding.lottieEmptyDb.visibility = View.VISIBLE
                            } else {
                                binding.tvFavoriteEmpty.visibility = View.GONE
                                binding.lottieEmptyDb.visibility = View.GONE
                            }
                        }
                        is Resource.Error -> {
                            Toast.makeText(requireContext(), "Error fetch from local database", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}