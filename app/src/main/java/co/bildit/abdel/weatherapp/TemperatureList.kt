package co.bildit.abdel.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import co.bildit.abdel.weatherapp.databinding.ActivityTemperatureListBinding
import androidx.recyclerview.widget.LinearLayoutManager
import co.bildit.abdel.weatherapp.models.WeatherForecast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

class TemperatureList : AppCompatActivity() {
    private lateinit var binding: ActivityTemperatureListBinding
    private lateinit var adapter: WeatherAdapter

    private val API_KEY = "65d00499677e59496ca2f318eb68c049"
    private val API_URL = "https://api.openweathermap.org/data/2.5/"
    var gson = Gson()
    var requestQueue: RequestQueue? = null
    var stringRequest: StringRequest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTemperatureListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val city = intent.extras!!.getString("city")

        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setTitle(city)
        }

        val data: ArrayList<WeatherForecast> = ArrayList<WeatherForecast>()
        adapter = WeatherAdapter(data)

        binding.recyclerView.setAdapter(adapter)
        binding.recyclerView.setLayoutManager(LinearLayoutManager(this))

        val url = String.format(
            API_URL + "/forecast?q=${city}&units=metric&appid=${API_KEY}",
        )

        val requestQueue = Volley.newRequestQueue(this)
        stringRequest = object : StringRequest(
            Request.Method.GET, url,
            Response.Listener { response ->
                val json = JSONObject(response)
                val weathers = json.getJSONArray("list")
                for (i in 0 until weathers.length()) {
                    val singleObject = weathers.getJSONObject(i)
                    val temp = singleObject.getJSONObject("main").getInt("temp")
                    val feels_like = singleObject.getJSONObject("main").getInt("feels_like")
                    val weather = singleObject.getJSONArray("weather").getJSONObject(0).getString("main")
                    val weather_desc = singleObject.getJSONArray("weather").getJSONObject(0).getString("description")
                    data.add(WeatherForecast(city!!,temp, feels_like, weather, weather_desc ))
                }

                if (weathers.length() > 0) {
                    adapter.setList(data)
                }
            },
            Response.ErrorListener { error ->
                Log.d("ERROR", "error => " + error.toString())
            }
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params = HashMap<String, String>()
                params["Accept"] = "application/json"
                return params
            }
        }

        requestQueue?.add(stringRequest)

    }
}