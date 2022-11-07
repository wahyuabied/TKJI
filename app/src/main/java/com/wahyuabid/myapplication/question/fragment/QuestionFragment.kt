package com.wahyuabid.myapplication.question.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wahyuabid.myapplication.R
import com.wahyuabid.myapplication.core.ext.viewBinding
import com.wahyuabid.myapplication.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment(R.layout.fragment_question) {

    companion object {
        const val TAG = "QuestionFragment"
        fun getInstance(
            title: String,
            firstDesc: String,
            image: Int = 0,
            secondDesc: String = ""
        ): QuestionFragment {
            return QuestionFragment().apply {
                this.title = title
                this.firstDesc = firstDesc
                this.image = image
                this.secondDesc = secondDesc
            }
        }
    }

    val binding by viewBinding(FragmentQuestionBinding::bind)
    var title = ""
    var firstDesc = ""
    var image = 0
    var secondDesc = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvTitle.setText(title)
            tvDesc1.setText(firstDesc)
            tvDesc2.setText(secondDesc)
            if(image != 0)
                ivDesc.setImageResource(image)
            else
                ivDesc.visibility = View.GONE
        }
    }


}