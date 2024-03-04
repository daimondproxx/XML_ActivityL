package com.example.activitylist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.activitylist.databinding.ActivityListBinding

class AddContactActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        val okButton: Button = findViewById(R.id.ok_button)
        val cancelButton: Button = findViewById(R.id.cancel_button)

        cancelButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        })

        okButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        }


    }
