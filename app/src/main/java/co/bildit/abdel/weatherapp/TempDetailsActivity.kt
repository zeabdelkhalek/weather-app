package co.bildit.abdel.weatherapp

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import co.bildit.abdel.weatherapp.databinding.ActivityTempDetailsBinding

class TempDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTempDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTempDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val city = intent.extras!!.getString("city")
        val temp = intent.extras!!.getInt("temp")
        val feels_like = intent.extras!!.getInt("feels_like")
        val weather = intent.extras!!.getString("weather")
        val weather_desc = intent.extras!!.getString("weather_desc")


        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setTitle(city)
        }

        binding.temperature.setText(temp.toString() + " C");
        binding.temperatureLike.setText("Feels like: " + feels_like.toString() + " C");
        binding.weather.setText(weather);
        binding.description.setText(weather_desc);
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}