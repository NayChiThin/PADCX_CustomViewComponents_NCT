package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.R

import kotlinx.android.synthetic.main.activity_modify_custom_view.*

class ModifyCustomViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_custom_view)
        setUpListener()
    }
    private fun setUpListener() {
        btnNavigate.setOnClickListener {
            startActivity(CustomComponentActivity.newIntent(this))
        }
    }
    companion object{
        fun newIntent(context: Context):Intent {
            return Intent(context,ModifyCustomViewActivity::class.java)
        }
    }

}
