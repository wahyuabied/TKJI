package com.wahyuabid.myapplication.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import com.wahyuabid.myapplication.core.ext.UnspecifiedTypeItem
import com.wahyuabid.myapplication.core.ext.performUpdates
import com.wahyuabid.myapplication.core.ext.setOnSingleClickListener
import com.wahyuabid.myapplication.core.ext.viewBinding
import com.wahyuabid.myapplication.databinding.ActivityMainBinding
import com.wahyuabid.myapplication.detail_home.DetailClassActivity
import com.wahyuabid.myapplication.home.bottom_sheet.ClassBottomSheet
import com.wahyuabid.myapplication.home.list.ClassListItem
import com.wahyuabid.myapplication.model.ClassModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClassActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, ClassActivity::class.java)
        }
    }

    private val viewModel: ClassActivityViewModel by viewModel()
    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val adapters: FastItemAdapter<UnspecifiedTypeItem> = FastItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvClass.adapter = adapters
        binding.rvClass.itemAnimator = null

        with(viewModel) {
            shouldShowData.observe(this@ClassActivity, Observer(::onPopulateClass))

            shouldShowEmpty.observe(this@ClassActivity,Observer{
                if(it){
                    binding.apply {
                        rvClass.visibility = View.GONE
                        tvEmpty.visibility = View.VISIBLE
                    }
                }else{
                    binding.apply {
                        rvClass.visibility = View.VISIBLE
                        tvEmpty.visibility = View.GONE
                    }
                }
            })
        }

        binding.btnAdd.setOnSingleClickListener {
            ClassBottomSheet.getInstance(
                onCreate = {
                    viewModel.onAddClass(it)
                },
                onDelete = {}
            ).show(supportFragmentManager,ClassBottomSheet.TAG)
        }

    }

    override fun onStart() {
        super.onStart()
        viewModel.onViewLoaded()
    }

    private fun onPopulateClass(listClass: MutableList<ClassModel>) {
        with(listClass.mapIndexed { index, classModel ->
            ClassListItem(classModel, object : ClassListItem.EvenListener {
                override fun onEdit(data: ClassModel) {
                    ClassBottomSheet.getInstance(
                        data = data,
                        onCreate = {
                           viewModel.onEditClass(it)
                        },
                        onDelete = {
                            viewModel.onDeleteClass(it)
                        }
                    ).show(supportFragmentManager,ClassBottomSheet.TAG)
                }

                override fun onClickContent(data: ClassModel) {
                    startActivity(DetailClassActivity.getIntent(this@ClassActivity, data.id))
                }
            })
        }) {
            adapters.performUpdates(this)
            adapters.notifyAdapterDataSetChanged()
        }
    }
}