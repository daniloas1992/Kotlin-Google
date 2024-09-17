package com.danilo.alves.superheroes.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danilo.alves.superheroes.R

@Composable
fun HeroesScreen (hero: Hero, modifier: Modifier = Modifier
) {
    ElevatedCard (
        elevation = CardDefaults.cardElevation (defaultElevation = 2.dp),
        modifier = Modifier
            .padding(
                top = 16.dp,
                bottom = 16.dp,
                start = 16.dp,
                end = 8.dp
            )) {

        Row (horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .sizeIn(minHeight = 72.dp)
        ) {
            Column (modifier = Modifier.weight(1f)) {
                Text(text = stringResource(id = hero. nameRes),
                    style = MaterialTheme.typography.displaySmall)
                Text(text = stringResource(id = hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Image(painter = painterResource(id = hero.imageRes),
                contentDescription = stringResource(id = hero.descriptionRes),
                modifier = modifier
                    .height(72.dp)
                    .clip(MaterialTheme.shapes.small)
                    .widthIn(min = 72.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HeroesScreenPreview(modifier: Modifier = Modifier) {
    HeroesScreen(hero = Hero(R.string.hero1, R.string.description1, R.drawable.android_superhero1))
}