package com.wahyuabid.myapplication.question.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.wahyuabid.myapplication.R
import com.wahyuabid.myapplication.core.ext.setOnSingleClickListener
import com.wahyuabid.myapplication.model.MemberModel

class MemberAdapter(var items: List<MemberModel>, var onClick: ((MemberModel) -> Unit)) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(i: Int): MemberModel {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View? {
        var newConvertView = convertView
        val holder: ViewHolder
        if (newConvertView == null) {
            newConvertView =
                LayoutInflater.from(parent.context).inflate(R.layout.custom_member, null)
            holder = ViewHolder(newConvertView)
            newConvertView.tag = holder
        } else {
            holder = newConvertView.tag as ViewHolder
        }
        holder.tvTitle?.text = items.getOrNull(position)?.name.orEmpty()
        holder.clMember?.setOnSingleClickListener {
            items.getOrNull(position)?.let {
                onClick?.invoke(it)
            }
        }
        return newConvertView
    }


    private class ViewHolder(view: View?) {
        var tvTitle: TextView? = null
        var clMember: ConstraintLayout? = null

        init {
            tvTitle = view?.findViewById(R.id.tv_title_member)
            clMember = view?.findViewById(R.id.cl_member)
        }
    }
}