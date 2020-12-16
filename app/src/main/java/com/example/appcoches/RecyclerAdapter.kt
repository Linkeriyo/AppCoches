package com.example.appcoches

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appcoches.model.Car
import kotlinx.android.synthetic.main.guitarras_row.view.*
import java.lang.IllegalArgumentException

class RecyclerAdapter(val context: Context, val carList: List<Car>,
                      private val itemClickListener: OnCarClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnCarClickListener {
        fun onImageClick(imagen: String)
        fun onItemClick(marca: String, modelo: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        // inflamos vista
        return CarsViewHolder(
            LayoutInflater.from(context).inflate(R.layout.guitarras_row, parent, false)
        )

    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        //carga datos en lista
        if (holder is CarsViewHolder)
            holder.bind(carList[position], position)
        else
            throw IllegalArgumentException("Error viewHolder erroneo")
    }

    override fun getItemCount(): Int = carList.size          //número de items


    inner class CarsViewHolder(itemView: View) :
        BaseViewHolder<Car>(itemView)// nos aseguramos de que cuando la clase padre muera, muera esta también
    {
        override fun bind(item: Car, position: Int) {
            Glide.with(context).load(item.image).into(itemView.image)
            itemView.nameView.text = item.brand + " " + item.model
            itemView.yearView.text = item.year.toString()
            itemView.countryView.text = item.country
            itemView.drivetrainView.text = item.driveTrain

            itemView.setOnClickListener {
                itemClickListener.onItemClick(item.brand, item.model)
            }
            itemView.image.setOnClickListener {
                itemClickListener.onImageClick(item.image)
            }

        }
    }

}