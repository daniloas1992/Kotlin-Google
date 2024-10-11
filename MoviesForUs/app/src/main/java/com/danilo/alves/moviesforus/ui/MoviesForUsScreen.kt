package com.danilo.alves.moviesforus.ui


import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.danilo.alves.moviesforus.R
import com.danilo.alves.moviesforus.utils.MovieForUsContentType


@Composable
fun MoviesForUsApp(
    windowSize: WindowWidthSizeClass,
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val viewModel: MoviesForUsViewModel = viewModel()

    val uiState by viewModel.uiState.collectAsState()

    val backStateEntry by navController.currentBackStackEntryAsState()

    val currentScreen = MoviesForUsScreensId.valueOf(
        backStateEntry?.destination?.route ?: MoviesForUsScreensId.Genre.name
    )

    val contentType = when (windowSize) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> MovieForUsContentType.ListOnly

        WindowWidthSizeClass.Expanded -> MovieForUsContentType.ListAndDetails
        else -> MovieForUsContentType.ListOnly
    }

    Scaffold(
        topBar = {
            MoviesForUsTopBar(
                viewModel = viewModel,
                navController = navController,
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = MoviesForUsScreensId.Genre.name,
            modifier = modifier
        ) {
            composable(route = MoviesForUsScreensId.Genre.name) {
                GenreScreen(
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                    onItemClick = {
                        viewModel.updateSelectedGenre(it)
                        navController.navigate(MoviesForUsScreensId.Movies.name)
                    },
                    contentPadding = innerPadding
                )
            }

            composable(route = MoviesForUsScreensId.Movies.name) {
                MoviesListScreen(
                    genre = uiState.currentGenre,
                    onItemClick = {
                        viewModel.updateSelectedMovie(it)
                        navController.navigate(MoviesForUsScreensId.Details.name)
                    },
                    contentPadding = innerPadding
                )
            }

            composable(route = MoviesForUsScreensId.Details.name) {
                MovieDetailScreen(
                    movie = uiState.currentMovie,
                    contentPadding = innerPadding
                )
            }
        }
    }
}

private fun navigateToGenres(navController: NavHostController) {
    navController.popBackStack(MoviesForUsScreensId.Genre.name, inclusive = false)
}

private fun navigateToListMovies(navController: NavHostController) {
    navController.popBackStack(MoviesForUsScreensId.Movies.name, inclusive = false)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesForUsTopBar(
    viewModel: MoviesForUsViewModel,
    navController: NavHostController,
    currentScreen: MoviesForUsScreensId,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = stringResource(id = currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(
                    onClick = {
                        viewModel.updateScreenBackUpPressed(currentScreen.name)

                        when(currentScreen.name) {
                            MoviesForUsScreensId.Movies.name -> navigateToGenres(navController)
                            MoviesForUsScreensId.Details.name -> navigateToListMovies(navController)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            }
        }
    )
}

