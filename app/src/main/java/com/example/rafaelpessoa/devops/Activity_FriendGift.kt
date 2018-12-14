package com.example.rafaelpessoa.devops

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity__friend_gift.*
import kotlinx.android.synthetic.main.activity_my_gift.*

class Activity_FriendGift : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__friend_gift)


        val Phone = intent.extras.getString( "PHONE",  "")
        var FriendGifts = ""

        //TODO  consumir rest busca presentes

        FriendGifts = "Setar aqui os presentes de amigos vindos do get "


        etGiftFriendList.setText(FriendGifts + Phone)


    }
}
