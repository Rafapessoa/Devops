package com.example.rafaelpessoa.devops

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.rafaelpessoa.devops.Constants.EXTRA_FRIEND_GIFT
import kotlinx.android.synthetic.main.activity__friend_gift.*
import kotlinx.android.synthetic.main.activity_my_gift.*

class Activity_FriendGift : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__friend_gift)


        val presentes = intent.extras.getStringArrayList(EXTRA_FRIEND_GIFT)
        var friendsGift = ""

        //TODO  consumir rest busca presentes
        if (!presentes.isEmpty()) {
            for (i in 0..(presentes.size - 1)) {
                if (friendsGift.isEmpty()) {
                    friendsGift += presentes.get(i)
                } else {
                    friendsGift += " - " + presentes.get(i)
                }
            }
        } else {
            friendsGift = "Esse amigo não colocou uma lista de presentes ainda"
        }



        etGiftFriendList.setText(friendsGift)


    }
}
