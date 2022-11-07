package com.wahyuabid.myapplication.detail_home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.wahyuabid.myapplication.core.ext.setOnSingleClickListener
import com.wahyuabid.myapplication.databinding.ListItemClassBinding
import com.wahyuabid.myapplication.model.ClassModel
import com.wahyuabid.myapplication.model.MemberModel

class MemberListItem(
    private val data: MemberModel,
    private val listener: EvenListener
) : AbstractBindingItem<ListItemClassBinding>() {
    var name: String? = null

    override val type: Int
        get() = hashCode()

    override fun bindView(binding: ListItemClassBinding, payloads: List<Any>) {
        binding.apply {
            tvName.text = data.name
            ivEdit.setOnSingleClickListener {
                listener.onEdit(data)
            }
        }
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ListItemClassBinding {
        return ListItemClassBinding.inflate(inflater, parent, false)
    }

    interface EvenListener{
        fun onEdit(data: MemberModel)
    }
}
