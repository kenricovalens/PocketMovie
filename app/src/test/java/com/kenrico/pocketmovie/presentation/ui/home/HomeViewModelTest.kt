package com.kenrico.pocketmovie.presentation.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.kenrico.pocketmovie.FakeApiService
import com.kenrico.pocketmoviecore.data.MovieRepository
import com.kenrico.pocketmoviecore.data.Resource
import com.kenrico.pocketmoviecore.data.source.local.room.MovieDao
import com.kenrico.pocketmoviecore.data.source.local.room.MovieDatabase
import com.kenrico.pocketmoviecore.data.source.remote.network.ApiService
import com.kenrico.pocketmoviecore.domain.model.Movie
import com.kenrico.pocketmoviecore.domain.usecase.MovieInteractor
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var homeViewModel: HomeViewModel

    @Mock
    private lateinit var movieInteractor: MovieInteractor

    private lateinit var movieRepository: MovieRepository
    private lateinit var fakeApiService: FakeApiService

    @Mock
    private lateinit var movieDao: MovieDao

    private var dummyResponse = arrayListOf<Movie>()

    @Before
    fun setUp() {
        for(i in 1 until 5) {
            dummyResponse.add(
                Movie(
                    movieId = i,
                    movieTitle = "Avatar $i",
                    movieOverview = "Overview $i",
                    moviePosterUrl = "Poster Url $i",
                    movieReleaseDate = "$i Jan 2022",
                    movieScore = i.toDouble(),
                    movieVoteCount = i
                )
            )
        }

        fakeApiService = FakeApiService()
        movieRepository = MovieRepository(fakeApiService, movieDao)
        movieInteractor = MovieInteractor(movieRepository)

        homeViewModel = mock(HomeViewModel::class.java)
    }

    @Test
    fun `get list of now playing movies`() {
        val expectedSuccessOutput = MutableLiveData<Resource<List<Movie>>>()
        expectedSuccessOutput.value = Resource.Success(dummyResponse)

        `when`(homeViewModel.getListMovies()).thenReturn(expectedSuccessOutput)

        val actualSuccessOutput = homeViewModel.getListMovies().value

        verify(homeViewModel).getListMovies()

        assertNotNull(actualSuccessOutput)
        assertTrue(actualSuccessOutput is Resource.Success<*>)
    }

}