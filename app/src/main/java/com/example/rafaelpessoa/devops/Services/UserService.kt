package com.example.rafaelpessoa.devops.Services

import android.content.Context
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.rafaelpessoa.devops.Constants.BROADCAST_USER_DATA_CHANGE
import com.example.rafaelpessoa.devops.Constants.URL_GET_USER
import com.example.rafaelpessoa.devops.Pessoa
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

object UserService {
    var userId = ""
    var pessoaList: MutableList<Pessoa> = mutableListOf()


    fun findUserById(context: Context, idUsuario: String, complete: (Pessoa) -> Unit) {
        val findUserRequest =
            object : JsonObjectRequest(Method.GET, "$URL_GET_USER/$idUsuario", null, Response.Listener { response ->

                try {
                    val nome = response.getString("nome")
                    val email = response.getString("email")
                    val telefone = response.getString("telefone")
                    var presen: ArrayList<String> = ArrayList()

                    if (!response.isNull("presentes")) {
                        val pres = response.getJSONArray("presentes")

                        for (k in 0..(pres.length() - 1)) {
                            presen.add(pres.getString(k).toString())
                        }

                    }


                    val userDataGet = Intent(BROADCAST_USER_DATA_CHANGE)
                    LocalBroadcastManager.getInstance(context).sendBroadcast(userDataGet)

                    complete(Pessoa(idUsuario, nome,email,telefone,presen))

                } catch (e: JSONException) {
                    Log.d("JSON", "Exc: " + e.localizedMessage)
                }
            }, Response.ErrorListener {
                Log.d("ERROR", "sem usuarios na busca")
                complete(Pessoa())
            }) {
                override fun getBodyContentType(): String {
                    return "application/json; charset=utf-8"
                }

            }
        Volley.newRequestQueue(context).add(findUserRequest)
    }


    fun findUsers(context: Context, complete: (MutableList<Pessoa>) -> Unit) {
        val findAllUserRequest =
            object : JsonArrayRequest(
                Request.Method.GET, URL_GET_USER, null, Response.Listener { response ->
                    try {
                        pessoaList = mutableListOf()
                        for (j in 0..(response.length() - 1)) {
                            val idusuario = response.getJSONObject(j).getString("id")
                            if (idusuario != "2"){
                                val nome = response.getJSONObject(j).getString("nome")
                                val email = response.getJSONObject(j).getString("email")
                                val telefone = response.getJSONObject(j).getString("telefone")
                                var presen: ArrayList<String> = ArrayList()

                                if (!response.getJSONObject(j).isNull("presentes")) {
                                    val pres = response.getJSONObject(j).getJSONArray("presentes")

                                    for (k in 0..(pres.length() - 1)) {
                                        presen.add(pres.getString(k).toString())
                                    }

                                }
                                pessoaList.add(Pessoa(idusuario, nome, email, telefone, presen))

                            }
                        }

                        val userDataGet = Intent(BROADCAST_USER_DATA_CHANGE)
                        LocalBroadcastManager.getInstance(context).sendBroadcast(userDataGet)

                        complete(pessoaList)

                    } catch (e: JSONException) {
                        Log.d("FINDALL", "Exc: " + e.localizedMessage)
                    }
                }, Response.ErrorListener {
                    Log.d("FINDALL", "sem usuarios na busca:  $URL_GET_USER")
                    complete(mutableListOf())
                }) {
                override fun getBodyContentType(): String {
                    return "application/json; charset=utf-8"
                }

            }
        Volley.newRequestQueue(context).add(findAllUserRequest)
    }

    fun addPresente(context: Context, idUsuario: String,presente:String, complete: (Boolean) -> Unit) {
        val addPresenteRequest =
            object : JsonObjectRequest(Method.GET, "$URL_GET_USER/$idUsuario/add/$presente", null, Response.Listener { response ->

                try {
                    val nome = response.getString("nome")
                    val email = response.getString("email")
                    val telefone = response.getString("telefone")
                    var presen: ArrayList<String> = ArrayList()

                    if (!response.isNull("presentes")) {
                        val pres = response.getJSONArray("presentes")

                        for (k in 0..(pres.length() - 1)) {
                            presen.add(pres.getString(k).toString())
                        }

                    }


                    val userDataGet = Intent(BROADCAST_USER_DATA_CHANGE)
                    LocalBroadcastManager.getInstance(context).sendBroadcast(userDataGet)

                    //complete(Pessoa(idUsuario, nome,email,telefone,presen))
                    complete(true)
                } catch (e: JSONException) {
                    Log.d("JSON", "Exc: " + e.localizedMessage)
                }
            }, Response.ErrorListener {
                Log.d("ERROR", "sem usuarios na busca")
                complete(false)
            }) {
                override fun getBodyContentType(): String {
                    return "application/json; charset=utf-8"
                }

            }
        Volley.newRequestQueue(context).add(addPresenteRequest)
    }


}