package co.bildit.abdel.weatherapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import co.bildit.abdel.weatherapp.databinding.WeatherElementBinding
import co.bildit.abdel.weatherapp.models.WeatherForecast

class WeatherAdapter(private var itemList:ArrayList<WeatherForecast>): RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = WeatherElementBinding
            .inflate(
                LayoutInflater
                    .from(parent.getContext()), parent, false
            )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.temperature.text = "Temp: ${itemList[position].temp.toString()} C"
        holder.binding.weather.text = itemList[position].weather
    }

    fun setList(newData: ArrayList<WeatherForecast>) {
        itemList = newData
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: WeatherElementBinding) :
        RecyclerView.ViewHolder(binding.root)
}