package com.example.pro_income_expence

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.pro_income_expence.Helper.DBhelper
import com.example.pro_income_expence.Model.CategoryModel
import com.example.pro_income_expence.Model.InEx_Model
import com.example.pro_income_expence.databinding.ActivityAddTransactionBinding

lateinit var Binding2: ActivityAddTransactionBinding

class AddTransaction_Activity : AppCompatActivity() {

    var id: String? = null
    lateinit var db: DBhelper
    var categoryList = arrayListOf<CategoryModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding2 = ActivityAddTransactionBinding.inflate(layoutInflater)
        setContentView(Binding2.root)


        db = DBhelper(this)
        categoryList = db.getCtegory()
        initBinding()
        getinitBinding()

    }


    private fun initBinding() {

        Binding2.btnCategory.setOnClickListener {
            var intent = Intent(this, AddCategory_Activity::class.java)
            startActivity(intent)
        }

        var list = arrayListOf<String>()
        categoryList.forEach {
            list.add(it.name)
        }
        var arrayAdapter = ArrayAdapter(
            this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list
        )
        Binding2.Spinner.adapter = arrayAdapter



        Binding2.btnIncome.setOnClickListener {
            var index = Binding2.Spinner.selectedItemPosition
            var finelId = "0"
            if (id != null) {
                finelId = id!!
            }
            var model = InEx_Model(
                finelId,
                Binding2.EdtTitale.text.toString(),
                Binding2.EdtAmmount.text.toString(),
                Binding2.EdtNote.text.toString(),
                Binding2.EdtDate.text.toString(),
                Binding2.EdtTime.text.toString(),
                list[index]

            )

            if (id != null) {
                db.updateIncomeExpence(model)
            } else {
                db.addIncomeExpense(model)
            }
            finish()
        }
        Binding2.btnDelete.setOnClickListener {
            db.deletIncomeExpense(id!!)
            finish()
        }
    }


    private fun getinitBinding() {

        var notes = intent.getStringExtra("notes")
        var title = intent.getStringExtra("title")
        var amount = intent.getStringExtra("amount")
        var date = intent.getStringExtra("date")
        var id = intent.getStringExtra("id")

        var category = intent.getStringExtra("category")
        var time = intent.getStringExtra("time")

        if (amount!= null) {
            Binding2.btnDelete.visibility = View.VISIBLE
            Binding2.EdtAmmount.setText("$amount")
            Binding2.EdtNote.setText("$notes")
            Binding2.EdtNote.setText("$date")
            Binding2.EdtTime.setText("$time")
            Binding2.EdtTitale.setText("$title")

            var i = 0
            while (i < categoryList.size) {
                if (categoryList[i].name.equals(category)) {
                    Binding2.Spinner.setSelection(i)
                }
                i++
            }
        }
    }
}