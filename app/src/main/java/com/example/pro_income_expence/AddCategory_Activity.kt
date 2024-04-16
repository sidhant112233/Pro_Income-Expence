package com.example.pro_income_expence

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_income_expence.Adapter.Category_Adapter
import com.example.pro_income_expence.Helper.DBhelper
import com.example.pro_income_expence.Model.CategoryModel
import com.example.pro_income_expence.databinding.ActivityAddCategoryBinding

lateinit var AddCategoryBinding: ActivityAddCategoryBinding

class AddCategory_Activity : AppCompatActivity() {

    var list = ArrayList<CategoryModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AddCategoryBinding = ActivityAddCategoryBinding.inflate(layoutInflater)

        setContentView(AddCategoryBinding.root)

        var db = DBhelper(this)
        list = db.getCtegory()
        initBinding()

    }

    private fun initBinding() {
        var adapter = Category_Adapter(this@AddCategory_Activity, list)
        var lm = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        AddCategoryBinding.RvData.layoutManager = lm
        AddCategoryBinding.RvData.adapter = adapter

        AddCategoryBinding.btnAdd.setOnClickListener {
            var db = DBhelper(this@AddCategory_Activity)
            if (AddCategoryBinding.EdtTitale.text!!.isEmpty()) {
                AddCategoryBinding.EdtTitale.setError("Plasee insert Category")
            } else {
                db.addCategory(AddCategoryBinding.EdtTitale.text.toString())
                finish()
            }

        }
    }

}