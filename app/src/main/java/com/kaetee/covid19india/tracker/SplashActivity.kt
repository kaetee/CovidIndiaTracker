package com.kaetee.covid19india.tracker

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        var handler  = Handler()
        handler.postDelayed(
            Runnable{
                 run{
                     startActivity(Intent(this,MainActivity::class.java))
                 }
            },3000)
/*

    doAsync {
        var queue: RequestQueue? = null
        queue = Volley.newRequestQueue(this@SplashActivity)
        var res = ""
        val request = StringRequest(Request.Method.GET, URL, Response.Listener<String> {
            fun onResponse(response: String) {
                res = response
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                intent.putExtra("res",res)
                startActivity(intent)
            }
        }, Response.ErrorListener { error -> Log.d("error", error.toString()) })
        queue.add(request)
*/
        //var apiResponse = URL("https://api.rootnet.in/covid19-in/stats/latest").readText()
        /*uiThread {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            intent.putExtra("res",res)
            startActivity(intent)
        }*/
    }


}
