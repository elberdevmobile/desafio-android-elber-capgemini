package desafio.android.elber.ribeiro.ui.adapters.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import desafio.android.elber.ribeiro.R
import desafio.android.elber.ribeiro.model.data.MarvelHeroesModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_hero.*
import kotlinx.android.synthetic.main.row_hero.view.*

class DashboardRecyclerViewAdapter(private val onHeroClicked: (MarvelHeroesModel) -> Unit) : RecyclerView.Adapter<DashboardRecyclerViewAdapter.ItemViewHolder>() {

    private var heroList: MutableList<MarvelHeroesModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_hero, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val hero = heroList[position]

        holder.heroName.text = hero.name
        Picasso.get().load(hero.thumbnail).into(holder.heroImageView.heroImageView)

        holder.itemView.setOnClickListener { onHeroClicked(hero) }
    }

    fun setHeroesList(heroList: MutableList<MarvelHeroesModel>) {
        this.heroList.clear()
        this.heroList.addAll(heroList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return heroList.size
    }

    class ItemViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
}




