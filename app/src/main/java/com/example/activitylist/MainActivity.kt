package com.example.activitylist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitylist.databinding.ActivityListBinding
import java.util.Locale

class MainActivity : AppCompatActivity(), OnContactClickListener {

    private var binding: ActivityListBinding? = null
    private var list = mutableListOf<Contact>()
    private lateinit var adapter: RecyclerListAdapter
//    private val ImageButton = findViewById<ImageButton>(R.id.deleteButton)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(LayoutInflater.from(this))
        setContentView(binding!!.root)
        addContactsToContactList()
        adapter = RecyclerListAdapter(list, this)

        binding?.let {
            it.rvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            it.rvList.adapter = adapter

            it.addButton.setOnClickListener {
                val intent = Intent(this, AddContactActivity::class.java)
                startActivity(intent)
            }

            it.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    filterList(newText)
                    return true
                }

            })
        }
    }

    override fun deleteContactClick(contact: Contact) {
        val newContactList = mutableListOf<Contact>()
        newContactList.addAll(adapter.getContactList())
        newContactList.remove(contact)
        list.remove(contact)
        adapter.updateContact(newContactList)
    }

    fun filterList(query : String?){

        if (query != null) {
            val filteredList = mutableListOf<Contact>()
            for(contact in list) {
                if (contact.name.contains(query)
                    ||
                    contact.phoneNumber.contains(query)
                    ||
                    contact.name.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(contact)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Contact with exact Name or Phone Number", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }

        }
    }

    private fun addContactsToContactList() {
        list.add(Contact(R.drawable.no_war_girl, "Jane Cooper", "(270) 555-0117"))
        list.add(Contact(R.drawable.no_war_girl, "Devon Lane", "(308) 555-0121"))
        list.add(Contact(R.drawable.no_war_girl, "Darrell Steward", "(684) 555-0102"))
        list.add(Contact(R.drawable.no_war_girl, "Devon Lane", "(704) 555-0127"))
        list.add(Contact(R.drawable.no_war_girl, "Courtney Henry", "(505) 555-0125"))
        list.add(Contact(R.drawable.no_war_girl, "Wade Warren", "(225) 555-0118"))
        list.add(Contact(R.drawable.no_war_girl, "Bessie Cooper", "(406) 555-0120"))

    }





    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
