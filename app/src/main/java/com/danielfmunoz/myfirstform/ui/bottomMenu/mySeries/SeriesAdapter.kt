package com.danielfmunoz.myfirstform.ui.bottomMenu.mySeries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danielfmunoz.myfirstform.R
import com.danielfmunoz.myfirstform.databinding.CardViewSerieItemBinding
import com.danielfmunoz.myfirstform.ui.model.Serie

class SeriesAdapter(
    private val seriesList: ArrayList<Serie>,
    private val onItemClicked: (Serie) -> Unit,
) : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_serie_item, parent, false)
        return SeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val serie = seriesList[position]
        holder.bind(serie)
        holder.itemView.setOnClickListener { onItemClicked(serie) }
    }

    override fun getItemCount(): Int = seriesList.size

    fun appendItems(newList: ArrayList<Serie>) {
        seriesList.clear()
        seriesList.addAll(newList)
        notifyDataSetChanged()
    }

    class SeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = CardViewSerieItemBinding.bind(itemView)

        fun bind(serie: Serie) {
            with(binding) {
                serieNameTextView.text = serie.name
                cantSeasonsTextView.text = "Temporadas: " + serie.season
            }
        }
    }
}

