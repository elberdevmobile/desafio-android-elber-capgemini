package desafio.android.elber.ribeiro

import desafio.android.elber.ribeiro.model.response.marvel.common.MarvelThumbnail
import desafio.android.elber.ribeiro.model.response.marvel.hero.MarvelHero
import com.costular.marvelheroes.data.model.mapper.MarvelHeroMapper
import org.assertj.core.api.Assertions
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by costular on 18/03/2018.
 */
class TesteHero {
    private lateinit var marvelHeroMapper: MarvelHeroMapper

    @Test
    fun `Transform MarvelHero into MarvelHeroEntity`() {
        // Given
        val marvelHero = MarvelHero(0,"Iron Man", "Description", MarvelThumbnail("\"http://ironman.com\"","jpg"))

        // When
        val marvelHeroEntity = marvelHeroMapper.transform(marvelHero)

        // Then
        Assertions.assertThat(marvelHeroEntity.name).isEqualTo(marvelHero.name)
        Assertions.assertThat(marvelHeroEntity.thumbnail).isEqualTo(marvelHero.thumbnail)
    }

}