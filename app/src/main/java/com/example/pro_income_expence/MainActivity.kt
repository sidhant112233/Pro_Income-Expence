package com.example.pro_income_expence

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_income_expence.Adapter.DataAdapter
import com.example.pro_income_expence.Helper.DBhelper
import com.example.pro_income_expence.Model.InEx_Model
import com.example.pro_income_expence.databinding.ActivityMainBinding

@SuppressLint("StaticFieldLeak")
lateinit var MainBinding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var db: DBhelper
    lateinit var DataList: ArrayList<InEx_Model>

    override fun onResume() {
        super.onResume()
        DataList = DBhelper(this).getIncomeExpence()
        Rvsetup()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        MainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(MainBinding.root)

        db = DBhelper(this)
        DataList = db.getIncomeExpence()
        MainBinding.btnfloating.setOnClickListener {
            val intent = Intent(this, AddTransaction_Activity::class.java)
            startActivity(intent)
        Rvsetup()
        }


    }

    private fun Rvsetup() {
        val adapter = DataAdapter(this, DataList)
        val lm = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        MainBinding.RvData.layoutManager = lm
        MainBinding.RvData.adapter = adapter
    }

}