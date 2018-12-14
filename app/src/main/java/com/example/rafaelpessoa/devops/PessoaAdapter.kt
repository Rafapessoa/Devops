package com.example.rafaelpessoa.devops

import android.app.Person
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.pessoa_item.view.*

class PessoaAdapter(private val context: Context, private var pessoaList: MutableList<Pessoa>, private var click: PessoaAdapter.clickAction):
    RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder>() {

    interface clickAction {
        fun tapPerson(pessoa: Pessoa)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pessoa_item, parent, false)
        return PessoaViewHolder(view)

    }

    override fun getItemCount() = pessoaList.size

    override fun onBindViewHolder(holder: PessoaViewHolder, position: Int) {
        holder.bindView(pessoaList[position], click)
    }

    class PessoaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textViewNome = itemView.textViewNome
        val textViewEmail = itemView.textViewEmail
        val textViewTelefone = itemView.textViewTelefone

        fun bindView(pessoa: Pessoa, click: clickAction) {
            textViewNome.text = pessoa.nome
            textViewEmail.text = pessoa.email
            textViewTelefone.text = pessoa.telefone

            itemView.setOnClickListener {
                click.tapPerson(pessoa)
            }

        }
    }
}