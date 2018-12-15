package com.example.rafaelpessoa.devops

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rafaelpessoa.devops.Services.UserService
import kotlinx.android.synthetic.main.activity_my_gift.*
import kotlinx.android.synthetic.main.pessoa_item.view.*

class MyGiftActivity : AppCompatActivity() {
    var user = Pessoa()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_gift)

        var myGifts = ""

        //TODO  consumir rest busca presentes
        UserService.findUserById(this,"2"){usuario->
            user = usuario
            if (usuario.presentes.isEmpty()){
                myGifts = "Não há preesntes na Lista"
            }else{
                for (i in 0..(usuario.presentes.size -1)){
                    if (myGifts.isEmpty()){
                        myGifts +=  usuario.presentes.get(i)
                    }else{
                        myGifts +=  " - "+usuario.presentes.get(i)
                    }
                }

            }
            etGifMyList.setText(myGifts)
        }

        btAdd.setOnClickListener{
            UserService.addPresente(this,user.id, etGift.text.toString()){usuarioRet ->
                if (usuarioRet){
                    Toast.makeText(this,"Presente Cadastrado com Sucesso!",Toast.LENGTH_SHORT)
                }else{
                    Toast.makeText(this,"Erro Ao gravar presente, tente novamente mais tarde!",Toast.LENGTH_LONG)
                }
                finish()
            }
        }

    }
}
