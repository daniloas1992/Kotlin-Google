package com.danilo.alves.whereismybook.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.danilo.alves.whereismybook.R
import com.danilo.alves.whereismybook.ui.screens.BookDetailsScreen
import com.danilo.alves.whereismybook.ui.screens.BookcaseScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhereIsMyBookApp() {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { TopBarApp(scrollBehavior = scrollBehavior)}
    ) {
        Surface (
            modifier = Modifier.fillMaxSize()
        ) {
            val booksViewModel: BooksViewModel = viewModel(factory = BooksViewModel.Factory)

            if(booksViewModel.isShowingBookcasePage) {
                BookcaseScreen(
                    booksViewModel= booksViewModel,
                    retryAction = booksViewModel::getBookList,
                    contentPadding = it
                )
            } else {
                BookDetailsScreen(booksViewModel= booksViewModel, retryAction = {}, contentPadding = it)
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarApp(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.headlineSmall)
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun WhereIsMyBookAppPreview() {
    WhereIsMyBookApp()
}
