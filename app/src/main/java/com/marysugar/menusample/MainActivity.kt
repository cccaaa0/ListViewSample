package com.marysugar.menusample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter

class MainActivity : AppCompatActivity() {
    private var _menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
    private val _from = arrayOf("name", "price")
    private val _to = intArrayOf(R.id.tvMenuNameRow, R.id.tvMenuPriceRow)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _menuList = createTeishokuList()
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        val adapter = SimpleAdapter(this@MainActivity, _menuList, R.layout.row, _from, _to)
        lvMenu.adapter = adapter
        lvMenu.onItemClickListener = ListItemClickListener()
    }
    private fun createTeishokuList(): MutableList<MutableMap<String, Any>> {
        // 定食メニューリスト用のListオブジェクトを用意
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        var menu = mutableMapOf<String, Any>(
            "name" to "から揚げ定食",
            "price" to 800,
            "desc" to "若鶏のから揚げにサラダ、ご飯とお味噌汁が付きます。"
        )
        menuList.add(menu)
        menu = mutableMapOf<String, Any>(
            "name" to "ハンバーグ定食",
            "price" to 850,
            "desc" to "手捏ねハンバーグにサラダ、ご飯とお味噌汁が付きます。"
        )
        menuList.add(menu)
        return menuList
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val item = parent?.getItemAtPosition(position) as MutableMap<String, Any>

            val menuName = item["name"] as String
            val menuPrice = item["price"] as Int

            val intent2MenuThanks = Intent(this@MainActivity, MenuThanksActivity::class.java)
            intent2MenuThanks.putExtra("menuName", menuName)
            intent2MenuThanks.putExtra("menuPrice", "${menuPrice}円")
            startActivity(intent2MenuThanks)
        }
    }
}