package com.wahyuabid.myapplication.home.bottom_sheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wahyuabid.myapplication.core.ext.setOnSingleClickListener
import com.wahyuabid.myapplication.core.ext.viewBinding
import com.wahyuabid.myapplication.databinding.BottomSheetClassBinding
import com.wahyuabid.myapplication.model.ClassModel
import java.util.*

class ClassBottomSheet(): BottomSheetDialogFragment() {
    companion object {
        const val TAG = "ClassBottomSheet"
        fun getInstance(data: ClassModel?=null ,onCreate:(data: ClassModel)->Unit, onDelete:(id: String)-> Unit): ClassBottomSheet {
            return ClassBottomSheet().apply {
                this.data = data
                this.onCreate = onCreate
                this.onDelete = onDelete
            }
        }
    }
    var data: ClassModel? = null
    var onCreate: ((data: ClassModel)->Unit)? = null
    var onDelete: ((id: String)->Unit)? = null

    private var binding:BottomSheetClassBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetClassBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            data?.let {
                etName.setText(it.name)
                etDate.setText(it.date)
                etLocation.setText(it.location)
                etTeacher.setText(it.teacher)
                btnAdd.text = "Edit"
            }?:run {
                btnDelete.visibility = View.GONE
            }

            btnAdd.setOnSingleClickListener {
                onCreate?.invoke(
                    ClassModel(
                        id = data?.id ?: UUID.randomUUID().toString(),
                        name = etName.text.toString(),
                        date = etDate.text.toString(),
                        location = etLocation.text.toString(),
                        teacher = etTeacher.text.toString()
                    )
                )
                dismiss()
            }

            btnDelete.setOnSingleClickListener {
                onDelete?.invoke(data?.id.orEmpty())
                dismiss()
            }
        }

    }
}
