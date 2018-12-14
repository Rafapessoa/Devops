package com.example.rafaelpessoa.devops

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_my_gift.*
import kotlinx.android.synthetic.main.pessoa_item.view.*

class MyGiftActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_gift)

        val Phone = intent.extras.getString( "PHONE",  "")
        var MyGifts = ""

        //TODO  consumir rest busca presentes

        MyGifts = "Setar aqui os presentes vindos get "


        etGifMyList.setText(MyGifts + Phone)

    }
}
