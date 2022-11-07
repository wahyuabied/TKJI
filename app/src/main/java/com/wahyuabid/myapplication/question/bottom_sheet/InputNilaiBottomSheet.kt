package com.wahyuabid.myapplication.question.bottom_sheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListPopupWindow
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wahyuabid.myapplication.R
import com.wahyuabid.myapplication.core.ext.setOnSingleClickListener
import com.wahyuabid.myapplication.databinding.BottomSheetClassBinding
import com.wahyuabid.myapplication.databinding.InputNilaiBottomSheetBinding
import com.wahyuabid.myapplication.model.ClassModel
import com.wahyuabid.myapplication.model.MemberModel
import com.wahyuabid.myapplication.question.adapter.MemberAdapter
import java.util.*

class InputNilaiBottomSheet() : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "InputNilaiBottomSheet"
        fun getInstance(
            isThirdType: Boolean,
            memberModels: MutableList<MemberModel>,
            onClickSave: (String, MutableList<Double>) -> Unit
        ): InputNilaiBottomSheet {
            return InputNilaiBottomSheet().apply {
                this.isThirdType = isThirdType
                this.onClickSave = onClickSave
                this.memberModels = memberModels
            }
        }
    }

    var isThirdType: Boolean = false
    var onClickSave: ((String, MutableList<Double>) -> Unit)? = null
    var memberModels: MutableList<MemberModel> = mutableListOf()
    private var binding: InputNilaiBottomSheetBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = InputNilaiBottomSheetBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private var memberAdapter: MemberAdapter? = null
    private var listPopupWindow: ListPopupWindow? = null
    private var selectedMember: MemberModel? = null
    private var nilai1 = 0.0
    private var nilai2 = 0.0
    private var nilai3 = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isThirdType) {
            binding?.apply {
                tilNilai2.visibility = View.GONE
                tilNilai3.visibility = View.GONE
            }
        }

        binding?.apply {
            btnName.setOnSingleClickListener {
                memberAdapter = MemberAdapter(memberModels, onClick = {
                    binding?.btnName?.setText(it.name)
                    selectedMember = it
                    listPopupWindow?.dismiss()
                })

                listPopupWindow = createListPopupWindow(
                    binding?.btnName?.width!!,
                    binding?.btnName!!,
                    memberModels
                )
                listPopupWindow?.show()
            }

            etNilai1.doAfterTextChanged {
                if (it.toString().isNotEmpty())
                    nilai1 = it.toString().toDouble()
            }

            etNilai2.doAfterTextChanged {
                if (it.toString().isNotEmpty())
                    nilai2 = it.toString().toDouble()
            }

            etNilai3.doAfterTextChanged {
                if (it.toString().isNotEmpty())
                    nilai3 = it.toString().toDouble()
            }

            btnAdd.setOnSingleClickListener {
                onClickSave?.invoke(
                    selectedMember?.id.orEmpty(),
                    mutableListOf(nilai1, nilai2, nilai3)
                )
                dismiss()
            }
        }
    }

    private fun createListPopupWindow(
        width: Int,
        anchor: View,
        items: MutableList<MemberModel>
    ): ListPopupWindow {
        val popup = ListPopupWindow(requireContext())
        popup.anchorView = anchor
        popup.width = width
        popup.height = getResources().getDimension(R.dimen.popup_window_height).toInt() * items.size
        popup.setAdapter(memberAdapter)

        return popup
    }
}
