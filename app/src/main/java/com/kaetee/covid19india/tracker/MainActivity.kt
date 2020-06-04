package com.kaetee.covid19india.tracker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_state_data.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL


val stateCodeList = arrayListOf("A&N", "AP", "ArP", "AS", "BR", "CH", "CG", "DNH", "DL", "GO", "GJ", "HR", "HP", "JK", "JH", "KA", "KR", "LD", "MP", "MH", "MR", "MG", "NG", "OD", "PD", "PB", "RJ", "SK", "TN", "TL", "TR", "UK", "UP", "WB")
val stateCodesList: HashMap<String,String> = hashMapOf()
class MainActivity : AppCompatActivity() {
    lateinit var stateDataList: List<StateWiseData>
    val APIURL = "https://api.rootnet.in/covid19-in/stats/latest/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Covid-19 India"
        progressBar.visibility = View.VISIBLE
            var res = ""
            /*var queue: RequestQueue? = null
            queue = Volley.newRequestQueue(this@MainActivity)
            val request = StringRequest(Request.Method.GET, URL, Response.Listener<String> {
                fun onResponse(response: String) {
                    res = response
                }
            }, Response.ErrorListener { error -> Log.d("error", error.toString()) })
            queue.add(request)*/



        doAsync {
            res = URL(APIURL).readText()
            var gson = Gson()
            var jsonObject = JSONObject(res)

            val stateArray = jsonObject.getJSONObject("data").getJSONArray("regional")
            val type = object : TypeToken<List<StateWiseData>>() {}.type
            stateDataList = gson.fromJson(stateArray.toString(), type)
            Log.d("list", stateDataList[1].loc)

            uiThread {
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerView.adapter = StateListAdapter(stateDataList)
                progressBar.visibility = View.GONE
            }
        }

    }

    }

    class StateListAdapter(val stateList: List<StateWiseData>) : RecyclerView.Adapter<StateListAdapter.StateListViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateListViewHolder {
            return StateListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_state_data, parent, false))
        }

        override fun getItemCount() = stateList.size

        override fun onBindViewHolder(holder: StateListViewHolder, position: Int) {
            holder.bindTo(stateList[position])
        }

        inner class StateListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            fun bindTo(stateData: StateWiseData) {
                itemView.locText.text = stateData.loc
                itemView.deathsText.text = "Total Deaths: " + stateData.deaths.toString()
                itemView.dischargedText.text = "Total Recovered: " + stateData.discharged.toString()
                itemView.totalConfirmed.text = "Total Confirmed: " + stateData.totalConfirmed.toString()
                }
            }
        }




