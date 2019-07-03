package com.example.testesantanderandroidv2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.testesantanderandroidv2.R
import com.example.testesantanderandroidv2.extension.*
import com.example.testesantanderandroidv2.model.Statement
import kotlinx.android.synthetic.main.activity_statement_recycler_view.view.*

class StatementAdapter(private val context: Context, private val statementList: ArrayList<Statement>) :
        RecyclerView.Adapter<StatementAdapter.StatementViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): StatementViewHolder {
        val view = LayoutInflater
                .from(context)
                .inflate(R.layout.activity_statement_recycler_view, p0, false)
        return StatementViewHolder(view)
    }

    override fun getItemCount(): Int {
        return statementList.size
    }

    override fun onBindViewHolder(p0: StatementViewHolder, position: Int) {
        val statement = statementList[position]

        p0.title.text = statement.title
        p0.date.text = formatForBrazilianDate(statement.date)
        p0.desc.text = statement.desc
        p0.value.text = formatForBrazilianCurrency(statement.value)
    }

    inner class StatementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.activity_statement_recycler_view_title
        val date: TextView = itemView.activity_statement_recycler_view_date
        val desc: TextView = itemView.activity_statement_recycler_view_description
        val value: TextView = itemView.activity_statement_recycler_view_value
    }
}
