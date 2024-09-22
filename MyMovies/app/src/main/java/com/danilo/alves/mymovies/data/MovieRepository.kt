package com.danilo.alves.mymovies.data

import com.danilo.alves.mymovies.R
import com.danilo.alves.mymovies.model.Movie

object MovieRepository {

    val movies = listOf (
        Movie(
            title = R.string.title_1_dangal,
            duration = R.string.duration_1_dangal,
            year = R.string.year_1_dangal,
            sinopse = R.string.sinopse_1_dangal,
            image = R.drawable.image_1_dangal
        ),
        Movie(
            title = R.string.title_2_padman,
            duration = R.string.duration_2_padman,
            year = R.string.year_2_padman,
            sinopse = R.string.sinopse_2_padman,
            image = R.drawable.image_2_padman
        ),
        Movie(
            title = R.string.title_3_o_menino_que_descobriu_o_vento,
            duration = R.string.duration_3_o_menino_que_descobriu_o_vento,
            year = R.string.year_3_o_menino_que_descobriu_o_vento,
            sinopse = R.string.sinopse_3_o_menino_que_descobriu_o_vento,
            image = R.drawable.image_3_o_menino_que_descobriu_o_vento
        ),
        Movie(
            title = R.string.title_4_flamin_hot,
            duration = R.string.duration_4_flamin_hot,
            year = R.string.year_4_flamin_hot,
            sinopse = R.string.sinopse_4_flamin_hot,
            image = R.drawable.image_4_flamin_hot
        ),
        Movie(
            title = R.string.title_5_ameliepoulain,
            duration = R.string.duration_5_ameliepoulain,
            year = R.string.year_5_ameliepoulain,
            sinopse = R.string.sinopse_5_ameliepoulain,
            image = R.drawable.image_5_ameliepoulain
        ),
        Movie(
            title = R.string.title_6_a_100_passos_de_um_sonho,
            duration = R.string.duration_6_a_100_passos_de_um_sonho,
            year = R.string.year_6_a_100_passos_de_um_sonho,
            sinopse = R.string.sinopse_6_a_100_passos_de_um_sonho,
            image = R.drawable.image_6_a_100_passos_de_um_sonho
        ),
        Movie(
            title = R.string.title_7_um_amor_para_recordar,
            duration = R.string.duration_7_um_amor_para_recordar,
            year = R.string.year_7_um_amor_para_recordar,
            sinopse = R.string.sinopse_7_um_amor_para_recordar,
            image = R.drawable.image_7_um_amor_para_recordar
        ),
        Movie(
            title = R.string.title_8_oracoes_para_bobby,
            duration = R.string.duration_8_oracoes_para_bobby,
            year = R.string.year_8_oracoes_para_bobby,
            sinopse = R.string.sinopse_8_oracoes_para_bobby,
            image = R.drawable.image_8_oracoes_para_bobby
        )
    )
}