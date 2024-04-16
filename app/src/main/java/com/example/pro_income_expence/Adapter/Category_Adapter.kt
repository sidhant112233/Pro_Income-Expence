package com.example.pro_income_expence.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.pro_income_expence.AddCategory_Activity
import com.example.pro_income_expence.Model.CategoryModel
import com.example.pro_income_expence.R

class Category_Adapter(var context: Context,var Categorylist: ArrayList<CategoryModel>):
    RecyclerView.Adapter<Category_Adapter.CategoryHolder>() {

    class CategoryHolder(itemView: View): ViewHolder(itemView){
        var ctxt = itemView.findViewById<TextView>(R.id.Ctxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.category_tile,parent,false)
        return CategoryHolder(view)
    }

    override fun getItemCount(): Int {
        return Categorylist.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.ctxt.text = Categorylist.get(position).name
    }
}