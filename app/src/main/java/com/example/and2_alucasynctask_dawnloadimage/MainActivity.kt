package com.example.and2_alucasynctask_dawnloadimage

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
lateinit var pro:ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myAsyncTask().execute("https://www.google.com/imgres?imgurl=https%3A%2F%2Fy3k2r8y8.stackpathcdn.com%2Fwp-content%2Fuploads%2F2021%2F07%2F%25D9%2583%25D9%258A%25D9%2581%25D9%258A%25D8%25A9-%25D8%25A5%25D8%25B9%25D8%25A7%25D8%25AF%25D8%25A9-%25D8%25B6%25D8%25A8%25D8%25B7-%25D8%25A7%25D9%2584%25D9%2585%25D8%25B5%25D9%2586%25D8%25B9-%25D9%2584%25D8%25A3%25D8%25AC%25D9%2587%25D8%25B2%25D8%25A9-%25D8%25A7%25D9%2586%25D8%25AF%25D8%25B1%25D9%2588%25D9%258A%25D8%25AF.jpg&imgrefurl=https%3A%2F%2Fclock3.com%2Fhow-to-factory-reset-android%2F&tbnid=zZCAKvZspg0c2M&vet=12ahUKEwiE49jsz5r3AhUYxuAKHdNKDwsQMygFegUIARDdAQ..i&docid=PaP5rqEqHgp6LM&w=1920&h=1280&q=android&ved=2ahUKEwiE49jsz5r3AhUYxuAKHdNKDwsQMygFegUIARDdAQ")

    }

    inner class  myAsyncTask():AsyncTask<String,String,Bitmap>(){

        override fun onPreExecute() {
            super.onPreExecute()
            pro = ProgressDialog(this@MainActivity)
            pro.setMessage("جاري التحميل ..")
            pro.setCancelable(false)
            pro.show()

        }
        override fun doInBackground(vararg params: String?): Bitmap {
            val img_url = URL(params[0])//لجعل النص رابط
            var connection = img_url.openConnection() as HttpURLConnection //لعمل اتصال ع الرابط
            connection.connect()//للاتصال الفعلي
            val inputStream = connection.inputStream
            var bitmapImage = BitmapFactory.decodeStream(inputStream)

            return bitmapImage
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            pro.dismiss()
            img.setImageBitmap(result)
        }

    }
}