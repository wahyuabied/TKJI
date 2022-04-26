package com.wahyuabid.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import com.wahyuabid.myapplication.core.ext.viewBinding
import com.wahyuabid.myapplication.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel : MainActivityViewModel by viewModel()
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.shouldShowMessage.observe(this, Observer {
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })

        binding.etUsername.apply {
            doAfterTextChanged {
                viewModel.onChangeUsername(this.text.toString())
            }
        }

        binding.etPassword.apply {
            doAfterTextChanged {
                viewModel.onChangePass(this.text.toString())
            }
        }

        binding.btnConfirm.setOnClickListener {
            viewModel.onButtonConfirm()
        }
    }
}