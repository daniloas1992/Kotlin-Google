package com.danilo.alves.mymovies.model

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danilo.alves.mymovies.R

@Composable
fun MovieScreen (
    movie: Movie,
    modifier: Modifier = Modifier
) {

    var expanded by remember { mutableStateOf(false) }

    Column (modifier = Modifier
        .padding(start = 5.dp, top = 25.dp, end = 5.dp)
        .fillMaxWidth()
        .animateContentSize(animationSpec = spring(dampingRatio= Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessMedium)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = stringResource(id = movie.title),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 5.dp)
        )

        Card {

            Image(painter = painterResource(id = movie.image),
                contentDescription = stringResource(id = movie.title),
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }

        ExpandMovieButton(expanded, onClick = { expanded = !expanded })

        if(expanded) {

            Row (modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = stringResource(id = movie.year), style = MaterialTheme.typography.bodyMedium)
                Text(text = stringResource(id = movie.duration), style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = stringResource(id = movie.sinopse), style = MaterialTheme.typography.bodyMedium)
        }


    }
}

@Composable
fun ExpandMovieButton(expanded: Boolean = false, onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(onClick = onClick, modifier = modifier.fillMaxWidth()) {
        Icon(
            modifier = Modifier.fillMaxWidth(),
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = null,
            tint =MaterialTheme.colorScheme.secondary
        )
        
    }
}

@Preview(showBackground = true)
@Composable
fun MovieScreenPreview() {
    MovieScreen(movie =  Movie(
                            title = R.string.title_1_dangal,
                            duration = R.string.duration_1_dangal,
                            year = R.string.year_1_dangal,
                            sinopse = R.string.sinopse_1_dangal,
                            image = R.drawable.image_1_dangal
                        )
    )
}