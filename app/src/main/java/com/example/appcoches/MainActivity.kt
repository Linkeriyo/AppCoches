package com.example.appcoches

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appcoches.model.Car
import kotlinx.android.synthetic.main.activity_main.*

import android.app.VoiceInteractor
import com.android.volley.Response
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

//https://www.youtube.com/watch?v=QIVbnR9pQfY

class MainActivity : AppCompatActivity(), RecyclerAdapter.OnCarClickListener {
    var carList = mutableListOf<Car>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        conectarJson()
        setContentView(R.layout.activity_main)
        setupRecyclerView()
    }

    private fun conectarJson() {  // conecta con una url y devuelve su contenido
        val url = "http://iesayala.ddns.net/jc2001/jsoncars.php"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener { response ->
            //conectó correctamente
            println("conectó")
            val jsonArray = JSONArray(response)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = JSONObject(jsonArray.getString(i))
                val car = Car(
                    jsonObject.get("Brand") as String,
                    jsonObject.get("Model") as String,
                    jsonObject.get("Drivetrain") as String,
                    jsonObject.get("Country") as String,
                    Integer.parseInt(jsonObject.get("Year") as String),
                    jsonObject.get("Image") as String
                )

                carList.add(car)
            }

        }, Response.ErrorListener {
            System.err.println(it.message)
            Toast.makeText(
                this,
                "Error al conectar con la base de datos",
                Toast.LENGTH_SHORT
            ).show()
        })

        queue.add(stringRequest)
    }


    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            (DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            ))
        )
        recyclerView.adapter = RecyclerAdapter(this, carList, this)
    }

    override fun onImageClick(imagen: String) {
        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra("imageURL", imagen)
        startActivity(intent)
    }

    override fun onItemClick(marca: String, modelo: String) {
        Toast.makeText(this, marca + " " + modelo, Toast.LENGTH_SHORT).show()
    }
}
