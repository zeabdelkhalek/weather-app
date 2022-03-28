package co.bildit.abdel.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.bildit.abdel.weatherapp.databinding.ActivityMainBinding
import android.widget.Toast




class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLookup.setOnClickListener {
            val city: String = binding.cityName.getText().toString()
            if (city.isEmpty()) {
                Toast.makeText(this@MainActivity, "Please enter a City", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this@MainActivity, TemperatureListActivity::class.java)
                val bundle = Bundle()
                bundle.putString("city", city)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }

    }
}