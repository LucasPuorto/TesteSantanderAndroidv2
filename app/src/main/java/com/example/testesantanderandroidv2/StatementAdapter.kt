package com.example.testesantanderandroidv2

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_statement_recycler_view.view.*
import java.util.ArrayList

//private val recentList: ArrayList<Recents>

class StatementAdapter(private val context: Context) :
        RecyclerView.Adapter<StatementAdapter.StatementViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): StatementViewHolder {
        val view = LayoutInflater
                .from(context)
                .inflate(R.layout.activity_statement_recycler_view, p0, false)
        return StatementViewHolder(view)
    }

    override fun getItemCount(): Int {
//        return recentList.size
        return 20
    }

    override fun onBindViewHolder(p0: StatementViewHolder, position: Int) {
//        val recent = recentList[position]

//        p0.title.text = recent.title
//        p0.date.text = recent.date
//        p0.description.text = recent.description
//        p0.value.text = recent.value.toString()

        p0.title.text = "Pagamento"
        p0.date.text = "01/05/2019"
        p0.description.text = "aliança"
        p0.value.text = "R$ 200,00"

    }

    inner class StatementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.activity_statement_recycler_view_title
        val date: TextView = itemView.activity_statement_recycler_view_date
        val description: TextView = itemView.activity_statement_recycler_view_description
        val value: TextView = itemView.activity_statement_recycler_view_value
    }
}
