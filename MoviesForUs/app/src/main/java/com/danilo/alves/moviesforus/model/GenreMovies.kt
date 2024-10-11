package com.danilo.alves.moviesforus.model

import com.danilo.alves.moviesforus.R

enum class GenreMovies(val genre: String, val genreImageResourceId: Int) {

    Acao("Ação", R.drawable.capa_genero_acao),
    Biografia("Biografia", R.drawable.capa_genero_biografia),
    Comedia("Comédia", R.drawable.capa_genero_comedia),
    Drama("Drama", R.drawable.capa_genero_drama),
    Esporte("Esporte", R.drawable.capa_genero_esportes),
    Fantasia("Fantasia", R.drawable.capa_genero_fantasia),
    Ficcao("Ficção Científica", R.drawable.capa_genero_ficcao),
    Guerra("Guerra", R.drawable.capa_genero_guerra),
    Historia("História", R.drawable.capa_genero_historia),
    Misterio("Mistério", R.drawable.capa_genero_misterio),
    Musical("Musical", R.drawable.capa_genero_musical),
    Policial("Policial", R.drawable.capa_genero_policial),
    Romance("Romance", R.drawable.capa_genero_romance),
    Suspense("Suspense", R.drawable.capa_genero_suspense),
    Terror("Terror", R.drawable.capa_genero_terror),
    Thriller("Thriller", R.drawable.capa_genero_thriller),;

    companion object {
        fun getDefaultGenre(): GenreMovies {
            return Acao
        }
    }

    /*companion object {
        fun getList(): List<String> {
            return values().map {
                it.name  //it.toString()
            }
        }
    }*/
}