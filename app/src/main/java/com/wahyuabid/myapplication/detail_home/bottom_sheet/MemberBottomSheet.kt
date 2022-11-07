package com.wahyuabid.myapplication.detail_home.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wahyuabid.myapplication.R
import com.wahyuabid.myapplication.core.ext.setOnSingleClickListener
import com.wahyuabid.myapplication.databinding.BottomSheetClassBinding
import com.wahyuabid.myapplication.databinding.BottomSheetMemberBinding
import com.wahyuabid.myapplication.model.ClassModel
import com.wahyuabid.myapplication.model.MemberModel
import java.util.*

class MemberBottomSheet(): BottomSheetDialogFragment() {
    companion object {
        const val TAG = "ClassBottomSheet"
        fun getInstance(data: MemberModel?=null ,onCreate:(data: MemberModel)->Unit, onDelete:(id: String)-> Unit): MemberBottomSheet {
            return MemberBottomSheet().apply {
                this.data = data
                this.onCreate = onCreate
                this.onDelete = onDelete
            }
        }
    }
    var data: MemberModel? = null
    var onCreate: ((data: MemberModel)->Unit)? = null
    var onDelete: ((id: String)->Unit)? = null

    private var binding:BottomSheetMemberBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetMemberBinding.inflate(inflater, container, false)
        return binding?.root
    }

    var sex = "L"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            rgSex.check(R.id.rb_l)
            data?.let {
                etName.setText(it.name)
                etDate.setText(it.dateBirth.toString())
                if(it.sex == "L"){
                    rgSex.check(R.id.rb_l)
                }else{
                    rgSex.check(R.id.rb_p)
                }
                btnAdd.text = "Edit"
            }?:run {
                btnDelete.visibility = View.GONE
            }

            rgSex.setOnCheckedChangeListener { group, checkedId ->
                if(checkedId == R.id.rb_l){
                    sex = "L"
                }else{
                    sex = "P"
                }
            }

            btnAdd.setOnSingleClickListener {
                onCreate?.invoke(
                    MemberModel(
                        id = data?.id ?: UUID.randomUUID().toString(),
                        name = etName.text.toString(),
                        dateBirth = etDate.text.toString().toDouble(),
                        sex = sex,
                        placeBirth = ""
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
