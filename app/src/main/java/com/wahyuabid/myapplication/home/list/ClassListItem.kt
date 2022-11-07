package com.wahyuabid.myapplication.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.wahyuabid.myapplication.core.ext.setOnSingleClickListener
import com.wahyuabid.myapplication.databinding.ListItemClassBinding
import com.wahyuabid.myapplication.model.ClassModel

class ClassListItem(
    private val data: ClassModel,
    private val listener: EvenListener
) : AbstractBindingItem<ListItemClassBinding>() {
    var name: String? = null

    override val type: Int
        get() = hashCode()

    override fun bindView(binding: ListItemClassBinding, payloads: List<Any>) {
        binding.apply {
            tvName.text = data.name
            cvContent.setOnSingleClickListener {
                listener.onClickContent(data)
            }
            ivEdit.setOnSingleClickListener {
                listener.onEdit(data)
            }
        }
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ListItemClassBinding {
        return ListItemClassBinding.inflate(inflater, parent, false)
    }

    interface EvenListener{
        fun onEdit(data: ClassModel)
        fun onClickContent(data: ClassModel)
    }
}
