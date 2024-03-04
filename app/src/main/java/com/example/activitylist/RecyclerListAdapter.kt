package com.example.activitylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class RecyclerListAdapter(private var list: List<Contact>, private var clickListener: OnContactClickListener): RecyclerView.Adapter<RecyclerListAdapter.ContactViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.contact_example, parent, false)
        )
    }

    fun getContactList(): List<Contact> {
        return list
    }

    fun updateContact(newContactList: List<Contact>) {
        this.list = newContactList
        notifyDataSetChanged()
    }

    fun setFilteredList(list: List<Contact>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.contactName.text = list[position].name
        holder.contactPhoto.setImageResource(list[position].image)
        holder.contactPhoneNumber.text = list[position].phoneNumber
        holder.deleteButton.setOnClickListener {
            clickListener.deleteContactClick(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ContactViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val contactName: TextView = view.findViewById<TextView>(R.id.contact_name)
        val contactPhoto: ImageView = view.findViewById<ImageView>(R.id.contact_photo)
        val contactPhoneNumber: TextView = view.findViewById<TextView>(R.id.contact_phoneNumber)
        val deleteButton: ImageButton = view.findViewById(R.id.deleteButton)

    }

}
