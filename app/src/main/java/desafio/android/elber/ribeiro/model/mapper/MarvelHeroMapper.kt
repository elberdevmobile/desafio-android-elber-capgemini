package com.costular.marvelheroes.data.model.mapper

import desafio.android.elber.ribeiro.model.response.marvel.hero.MarvelHero


class MarvelHeroMapper : Mapper<MarvelHero, MarvelHero> {

    override fun transform(input: MarvelHero): MarvelHero =
        MarvelHero(input.id,
                    input.name,
                    input.description,
                    input.thumbnail)

    override fun transformList(inputList: List<MarvelHero>): List<MarvelHero> =
            inputList.map { transform(it) }


}