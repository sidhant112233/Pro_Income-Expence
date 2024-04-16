package com.example.pro_income_expence.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.pro_income_expence.AddTransaction_Activity
import com.example.pro_income_expence.MainActivity
import com.example.pro_income_expence.Model.InEx_Model
import com.example.pro_income_expence.R

class DataAdapter(var context: Context, var DataList: ArrayList<InEx_Model>) :
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : ViewHolder(itemView) {
        var Datatital = itemView.findViewById<TextView>(R.id.Datatital)
        var Dataid = itemView.findViewById<TextView>(R.id.Dataid)
        var Dataammount = itemView.findViewById<TextView>(R.id.Dataammount)
        var Datanote = itemView.findViewById<TextView>(R.id.DataNote)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.data_tile, parent, false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return DataList.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.Dataid.text = DataList[position].id
        holder.Datatital.text = DataList[position].tital
        holder.Dataammount.text = DataList[position].ammount.toString()
        holder.Datanote.text = DataList[position].note

        holder.Dataammount.setOnClickListener {
            var intent = Intent(context, AddTransaction_Activity::class.java)
            intent.putExtra("tital", DataList[position].tital)
            intent.putExtra("id", DataList[position].id)
            intent.putExtra("ammount", DataList[position].ammount)
            intent.putExtra("date", DataList[position].date)
            intent.putExtra("time", DataList[position].time)
            intent.putExtra("category", DataList[position].category)
            intent.putExtra("note", DataList[position].note)
            context.startActivity(intent)
        }
    }

}