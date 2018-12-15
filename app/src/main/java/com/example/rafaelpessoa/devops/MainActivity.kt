package com.example.rafaelpessoa.devops

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.rafaelpessoa.devops.Constants.EXTRA_FRIEND_GIFT
import com.example.rafaelpessoa.devops.Services.UserService

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    val pessoaList: MutableList<Pessoa> = mutableListOf()

    lateinit var pessoaAdapter: PessoaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val proximaTela1 = Intent(this, MyGiftActivity::class.java)
        val proximaTela2 = Intent(this, Activity_FriendGift::class.java)



        btAddGift.setOnClickListener { view ->

            proximaTela1.putExtra("PHONE", "11-91111-1111")
            startActivity(proximaTela1)

        }

        pessoaAdapter = PessoaAdapter(this, pessoaList, object : PessoaAdapter.clickAction {
            override fun tapPerson(pessoa: Pessoa) {
                proximaTela2.putExtra(EXTRA_FRIEND_GIFT, pessoa.presentes)
                startActivity(proximaTela2)
            }
        })
        recyclerViewPessoas.adapter = pessoaAdapter
        recyclerViewPessoas.layoutManager = LinearLayoutManager(this)
        recyclerViewPessoas.smoothScrollToPosition(pessoaList.size)

        recyclerViewPessoas.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }



    }

    override fun onResume() {
        super.onResume()
        consultausuarios()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun consultausuarios(){
        UserService.findUsers(this){usuarioSucess->
            Log.d("findall", "SUCESSO EU ACHO: " + usuarioSucess.toString())
            if (!usuarioSucess.isEmpty()){
                pessoaList.clear()
                pessoaList.addAll(usuarioSucess)
                pessoaAdapter.notifyDataSetChanged()
            }

        }
    }
}