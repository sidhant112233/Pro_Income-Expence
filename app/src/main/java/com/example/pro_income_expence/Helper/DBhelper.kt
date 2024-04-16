package com.example.pro_income_expence.Helper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.pro_income_expence.Model.CategoryModel
import com.example.pro_income_expence.Model.InEx_Model

class DBhelper(context: Context) : SQLiteOpenHelper(context, "app", null, 1) {


    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "CREATE TABLE IncomeExpense (id INTEGER PRIMARY KEY AUTOINCREMENT,tital TEXT,amount TEXT,notes TEXT,date TEXT,time TEXT,category TEXT)"
        val queryCategory = "CREATE TABLE Category (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT)"
        db!!.apply {
            execSQL(query)
            execSQL(queryCategory)
        }

    }

    fun addCategory(name: String) {
        var db = writableDatabase
        var cn = ContentValues()
        cn.put("name", name)
        db.insert("category", null, cn)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}


    @SuppressLint("Range")
    fun getCtegory(): ArrayList<CategoryModel> {
        var list = arrayListOf<CategoryModel>()
        var db = readableDatabase
        var query = "SELECT * FROM category"
        var cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                var id = cursor.getString(cursor.getColumnIndex("id"))
                var name = cursor.getString(cursor.getColumnIndex("name"))
                var model = CategoryModel(id, name)
                list.add(model)
            } while (cursor.moveToNext())
        }
        return list
    }

    fun addIncomeExpense(model: InEx_Model)
    {
        val db = writableDatabase
        val cn = ContentValues()
        cn.put("tital",model.tital)
        cn.put("note",model.note)
        cn.put("ammount",model.ammount)
        cn.put("date",model.date)
        cn.put("time",model.time)
        cn.put("category",model.category)
        db.insert("IncomeExpense",null,cn)
    }


    fun updateIncomeExpence(model: InEx_Model) {
        var db = writableDatabase
        var cn = ContentValues()
        cn.put("tital", model.tital)
        cn.put("ammount", model.ammount)
        cn.put("note", model.note)
        cn.put("date", model.date)
        cn.put("time", model.time)
        cn.put("category", model.category)
        db.update("Income_Expence", cn, "id = ?", arrayOf(model.id))
    }


    fun deletIncomeExpense(id:String)
    {
        val db = writableDatabase
        db.delete("IncomeExpense","id=?", arrayOf(id))

    }

    @SuppressLint("Range")
    fun getIncomeExpence(): ArrayList<InEx_Model> {
        var db = readableDatabase
        var query = "SELECT * FROM IncomeExpense"
        var cursor = db.rawQuery(query, null)
        var dataList = ArrayList<InEx_Model>()

        if (cursor.moveToFirst()) {

            do {
                var id = cursor.getString(cursor.getColumnIndex("id"))
                var tital = cursor.getString(cursor.getColumnIndex("tital"))
                var ammount = cursor.getString(cursor.getColumnIndex("ammount"))
                var note = cursor.getString(cursor.getColumnIndex("nome"))
                var date = cursor.getString(cursor.getColumnIndex("date"))
                var time = cursor.getString(cursor.getColumnIndex("time"))
                var category = cursor.getString(cursor.getColumnIndex("category"))

                var model = InEx_Model(id,tital,ammount,note,date,time,category)
                dataList.add(model)
            } while (cursor.moveToNext())
        }
        return dataList
    }
}