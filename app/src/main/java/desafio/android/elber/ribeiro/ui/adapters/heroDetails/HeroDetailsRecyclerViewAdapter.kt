package desafio.android.elber.ribeiro.ui.adapters.heroDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import desafio.android.elber.ribeiro.R
import desafio.android.elber.ribeiro.model.data.MarvelHeroesModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_hero.heroImageView
import kotlinx.android.synthetic.main.row_hero.heroName
import kotlinx.android.synthetic.main.row_hero.view.heroImageView
import kotlinx.android.synthetic.main.row_hero_details.*

class HeroDetailsRecyclerViewAdapter(private val heroList: MutableList<MarvelHeroesModel>, private val rowWidth: Int) : RecyclerView.Adapter<HeroDetailsRecyclerViewAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_hero_details, parent, false),rowWidth)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val hero = heroList[position]
        holder.heroName.text = hero.name
        holder.heroDescription.text = hero.description
        holder.heroprice.text = "Preço : $" + hero.price
        Picasso.get().load(hero.thumbnail).into(holder.heroImageView.heroImageView)
    }

    override fun getItemCount(): Int {
        return heroList.size
    }

    class ItemViewHolder(override val containerView: View, rowWidth: Int) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        init {
            itemView.layoutParams.width = rowWidth
        }
    }
}




