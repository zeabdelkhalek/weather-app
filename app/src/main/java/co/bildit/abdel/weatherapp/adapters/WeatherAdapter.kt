package co.bildit.abdel.weatherapp.adapters

import android.R.attr
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.bildit.abdel.weatherapp.databinding.WeatherElementBinding
import co.bildit.abdel.weatherapp.models.WeatherForecast
import android.R.attr.data
import android.content.Context

import co.bildit.abdel.weatherapp.MainActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import co.bildit.abdel.weatherapp.TempDetailsActivity

import com.google.gson.Gson




class WeatherAdapter(private var itemList:ArrayList<WeatherForecast>, var ctx: Context): RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

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

        holder.binding.weatherRow.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val gson = Gson()
                val intent = Intent(ctx, TempDetailsActivity::class.java)
                val bundle = Bundle()

                bundle.putString("city", itemList[position].city)
                bundle.putInt("temp", itemList[position].temp)
                bundle.putInt("feels_like", itemList[position].feels_like)
                bundle.putString("weather", itemList[position].weather)
                bundle.putString("weather_desc", itemList[position].weather_desc)
                intent.putExtras(bundle)
                ctx.startActivity(intent)
            }
        })
    }

    fun setList(newData: ArrayList<WeatherForecast>) {
        itemList = newData
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: WeatherElementBinding) :
        RecyclerView.ViewHolder(binding.root)
}