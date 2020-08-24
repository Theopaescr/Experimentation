package br.com.mpc.experimentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.mpc.experimentation.R
import br.com.mpc.experimentation.models.charactersRequest.Results
import coil.load
import kotlinx.android.synthetic.main.characters_adapter_row.view.*

class CharactersAdapter(listOfItem: List<Results>): RecyclerView.Adapter<CharactersViewHolder>() {
    var mList = listOfItem

    fun replaceList(list: List<Results>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.characters_adapter_row, parent, false)
        return CharactersViewHolder(view)
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val item = mList[position]
        holder.init(item)
    }
}

class CharactersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun init(item: Results) {
        itemView.imageCharacterPicture.load(item.thumbnail.getImageUrl())
        itemView.textName.text = item.name
    }
}