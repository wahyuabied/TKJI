package com.wahyuabid.myapplication.detail_home

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.*
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import com.wahyuabid.myapplication.*
import com.wahyuabid.myapplication.core.ext.*
import com.wahyuabid.myapplication.databinding.ActivityDetailClassBinding
import com.wahyuabid.myapplication.detail_home.bottom_sheet.MemberBottomSheet
import com.wahyuabid.myapplication.detail_home.list.MemberListItem
import com.wahyuabid.myapplication.home.ClassActivity
import com.wahyuabid.myapplication.home.bottom_sheet.ClassBottomSheet
import com.wahyuabid.myapplication.model.ClassModel
import com.wahyuabid.myapplication.model.MemberModel
import com.wahyuabid.myapplication.question.QuestionActivity
import com.wahyuabid.myapplication.question.bottom_sheet.InputNilaiBottomSheet.Companion.TAG
import org.apache.poi.hssf.usermodel.HSSFCellStyle
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.hssf.util.HSSFColor
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Workbook
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class DetailClassActivity : AppCompatActivity() {

    companion object {
        const val CLASS_ID = "CLASS_ID"
        fun getIntent(context: Context, classID: String): Intent {
            return Intent(context, DetailClassActivity::class.java).apply {
                putExtra(CLASS_ID, classID)
            }
        }
    }

    private val classID by lazy(LazyThreadSafetyMode.NONE) {
        intent.getStringExtra(CLASS_ID).orEmpty()
    }
    private val binding by viewBinding(ActivityDetailClassBinding::inflate)
    private val viewModel: DetailClassActivityViewModel by viewModel()
    private val adapters: FastItemAdapter<UnspecifiedTypeItem> = FastItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            rvMember.adapter = adapters
            rvMember.itemAnimator = null
            ivBack.setOnSingleClickListener {
                onBackPressed()
            }

            btnStart.setOnSingleClickListener {
                startActivity(QuestionActivity.getIntent(this@DetailClassActivity,classID))
            }

            btnUnduh.setOnSingleClickListener {
                viewModel.onDownloadExcel()
            }

        }

        with(viewModel) {
            onViewLoaded(classID)

            shouldShowData.observe(this@DetailClassActivity, Observer(::onPopulateMember))

            shouldShowTitle.observe(this@DetailClassActivity, Observer {
                binding.tvTitle.setText(it)
            })

            shouldShowCountTitle.observe(this@DetailClassActivity, Observer {
                binding.tvTitleList.setText(it)
            })

            shouldShowEmpty.observe(this@DetailClassActivity, Observer {
                if (it) {
                    binding.apply {
                        rvMember.visibility = View.GONE
                        tvEmpty.visibility = View.VISIBLE
                    }
                } else {
                    binding.apply {
                        rvMember.visibility = View.VISIBLE
                        tvEmpty.visibility = View.GONE
                    }
                }
            })

            shouldDownload.observe(this@DetailClassActivity, Observer {
                requestCameraPermission(it)
            })
        }



        binding.btnAdd.setOnSingleClickListener {
            MemberBottomSheet.getInstance(
                onCreate = {
                   viewModel.onAddMember(it)
                },
                onDelete = {}
            ).show(supportFragmentManager, ClassBottomSheet.TAG)
        }
    }

    private fun requestCameraPermission(data: ClassModel) {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            arrayOf(
                Manifest.permission.ACCESS_MEDIA_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        } else {
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
        onRequestPermission(
            *permission,
            callback = {
                onGranted { onCreateExcel(data) }
                onDenied {
                    Toast.makeText(
                        this@DetailClassActivity,
                        "Permission Desnied",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                onPermanentDenied {
                    Toast.makeText(
                        this@DetailClassActivity,
                        "Permission Denied Permanent, Silahkan Aktifkan Melalui Setting",
                        Toast.LENGTH_SHORT
                    ).show().also {
                        Handler(Looper.getMainLooper()).postDelayed(Runnable {
                            startActivity(Intent(Settings.ACTION_APPLICATION_SETTINGS), null)
                        },2000)
                    }
                }
            }
        )
    }

    private fun onPopulateMember(listClass: MutableList<MemberModel>) {
        with(listClass.mapIndexed { index, classModel ->
            MemberListItem(classModel, object : MemberListItem.EvenListener {
                override fun onEdit(data: MemberModel) {
                    MemberBottomSheet.getInstance(
                        data = data,
                        onCreate = {
                            viewModel.onEditMember(it)
                        },
                        onDelete = {
                            viewModel.onDeleteMember(it)
                        }
                    ).show(supportFragmentManager, ClassBottomSheet.TAG)
                }
            })
        }) {
            adapters.performUpdates(this)
            adapters.notifyAdapterDataSetChanged()
        }
    }

    private fun onCreateExcel(data: ClassModel){
        Log.e("Abid","MASUK")
        val workbook = HSSFWorkbook()
        val cellStyle: CellStyle = workbook.createCellStyle()
        cellStyle.fillForegroundColor = HSSFColor.AQUA.index
        cellStyle.fillPattern = HSSFCellStyle.SOLID_FOREGROUND
        cellStyle.alignment = CellStyle.ALIGN_CENTER

        val midStyle = workbook.createCellStyle().apply {
            this.alignment = CellStyle.ALIGN_CENTER
        }
        val sheet = workbook.createSheet("SHEET1")
        sheet.setColumnWidth(0, (15 * 500))
        sheet.setColumnWidth(1, (15 * 500))
        sheet.setColumnWidth(2, (15 * 500))
        sheet.setColumnWidth(3, (15 * 500))
        sheet.setColumnWidth(4, (15 * 500))
        sheet.setColumnWidth(5, (15 * 500))
        sheet.setColumnWidth(6, (15 * 500))
        sheet.setColumnWidth(7, (15 * 500))
        sheet.setColumnWidth(8, (15 * 500))
        sheet.setColumnWidth(9, (15 * 500))
        sheet.setColumnWidth(10, (15 * 500))
        sheet.setColumnWidth(11, (15 * 500))
        sheet.setColumnWidth(12, (15 * 500))
        sheet.setColumnWidth(13, (15 * 500))
        sheet.setColumnWidth(14, (15 * 500))
        sheet.setColumnWidth(15, (15 * 500))
        sheet.setColumnWidth(16, (15 * 500))
        var row = sheet.createRow(0)
        row.createCell(0).apply {
            setCellValue("No")
            setCellStyle(cellStyle)
        }
        row.createCell(1).apply {
            setCellValue("NAMA")
            setCellStyle(cellStyle)
        }
        row.createCell(2).apply {
            setCellValue("Jenis Kelamin")
            setCellStyle(cellStyle)
        }
        row.createCell(3).apply {
            setCellValue("Usia")
            setCellStyle(cellStyle)
        }
        row.createCell(4).apply {
            setCellValue("Lari Cepat")
            setCellStyle(cellStyle)
        }
        row.createCell(5).apply {
            setCellValue("Lari Cepat")
            setCellStyle(cellStyle)
        }
        row.createCell(6).apply {
            setCellValue("Pull Up")
            setCellStyle(cellStyle)
        }
        row.createCell(7).apply {
            setCellValue("Pull Up")
            setCellStyle(cellStyle)
        }
        row.createCell(8).apply {
            setCellValue("Sit Up")
            setCellStyle(cellStyle)
        }
        row.createCell(9).apply {
            setCellValue("Sit Up")
            setCellStyle(cellStyle)
        }
        row.createCell(10).apply {
            setCellValue("Vert. Jump")
            setCellStyle(cellStyle)
        }
        row.createCell(11).apply {
            setCellValue("Vert. Jump")
            setCellStyle(cellStyle)
        }
        row.createCell(12).apply {
            setCellValue("Vert. Jump")
            setCellStyle(cellStyle)
        }
        row.createCell(13).apply {
            setCellValue("Vert. Jump")
            setCellStyle(cellStyle)
        }
        row.createCell(14).apply {
            setCellValue("Lari Sedang")
            setCellStyle(cellStyle)
        }
        row.createCell(15).apply {
            setCellValue("Lari Sedang")
            setCellStyle(cellStyle)
        }
        row.createCell(16).apply {
            setCellValue("Jumlah")
            setCellStyle(cellStyle)
        }

        row = sheet.createRow(1)
        row.createCell(4).apply {
            setCellValue("Hasil")
            setCellStyle(cellStyle)
        }
        row.createCell(5).apply {
            setCellValue("Nilai")
            setCellStyle(cellStyle)
        }
        row.createCell(6).apply {
            setCellValue("Hasil")
            setCellStyle(cellStyle)
        }
        row.createCell(7).apply {
            setCellValue("Nilai")
            setCellStyle(cellStyle)
        }
        row.createCell(8).apply {
            setCellValue("Hasil")
            setCellStyle(cellStyle)
        }
        row.createCell(9).apply {
            setCellValue("Nilai")
            setCellStyle(cellStyle)
        }
        row.createCell(10).apply {
            setCellValue("1")
            setCellStyle(cellStyle)
        }
        row.createCell(11).apply {
            setCellValue("2")
            setCellStyle(cellStyle)
        }
        row.createCell(12).apply {
            setCellValue("3")
            setCellStyle(cellStyle)
        }
        row.createCell(13).apply {
            setCellValue("Nilai")
            setCellStyle(cellStyle)
        }
        row.createCell(14).apply {
            setCellValue("Hasil")
            setCellStyle(cellStyle)
        }
        row.createCell(15).apply {
            setCellValue("Nilai")
            setCellStyle(cellStyle)
        }
        row.createCell(16).apply {
            setCellValue("Nilai")
            setCellStyle(cellStyle)
        }

        data.listMember.forEachIndexed { index, it ->
            row = sheet.createRow(index + 2)
            row.createCell(0).apply {
                setCellValue((index + 1) .toString())
                setCellStyle(midStyle)
            }
            row.createCell(1).apply {
                setCellValue(it.name)
                setCellStyle(midStyle)
            }
            row.createCell(2).apply {
                setCellValue(it.sex)
                setCellStyle(midStyle)
            }
            row.createCell(3).apply {
                setCellValue(it.dateBirth)
                setCellStyle(midStyle)
            }
            row.createCell(4).apply {
                setCellValue(it.tes1.toString() + " Meter")
                setCellStyle(midStyle)
            }
            row.createCell(5).apply {
                setCellValue(it.getSprintValue().toString())
                setCellStyle(midStyle)
            }
            row.createCell(6).apply {
                setCellValue(it.tes2.toString() + " Detik")
                setCellStyle(midStyle)
            }
            row.createCell(7).apply {
                setCellValue(it.getPullUpValue().toString())
                setCellStyle(midStyle)
            }
            row.createCell(8).apply {
                setCellValue(it.tes3.toString() +" Kali")
                setCellStyle(midStyle)
            }
            row.createCell(9).apply {
                setCellValue(it.getSitUpValue().toString())
                setCellStyle(midStyle)
            }
            row.createCell(10).apply {
                setCellValue(it.tes4.get(0).toString() +" cm")
                setCellStyle(midStyle)
            }
            row.createCell(11).apply {
                setCellValue(it.tes4.get(1).toString() +" cm")
                setCellStyle(midStyle)
            }
            row.createCell(12).apply {
                setCellValue(it.tes4.get(2).toString() +" cm")
                setCellStyle(midStyle)
            }
            row.createCell(13).apply {
                setCellValue(it.getVerticalJumpValue().toString())
                setCellStyle(midStyle)
            }
            row.createCell(14).apply {
                setCellValue(it.tes5.toString() +" Menit")
                setCellStyle(midStyle)
            }
            row.createCell(15).apply {
                setCellValue(it.getLariJarakSedangValue().toString())
                setCellStyle(midStyle)
            }
            row.createCell(16).apply {
                setCellValue(it.getTotal())
                setCellStyle(midStyle)
            }
        }.also {
            val isSuccessSave = storeExcelInStorage(workbook,"${data.name}.xlsx")
            if(isSuccessSave){
                Toast.makeText(this,"Berhasil Menyimpan document/TKJI/${data.name}.xlsx",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Gagal Menyimpan",Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        handleRequestPermission(requestCode, permissions, grantResults)
    }

    private fun storeExcelInStorage(workbook: Workbook, fileName: String): Boolean {
        var isSuccess: Boolean
        val myDir = File(Environment.getExternalStorageDirectory() , "documents/TKJI")
        if(!myDir.exists()){
            myDir.mkdirs()
        }
        val file = File(myDir, fileName)
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(file)
            workbook.write(fileOutputStream)
            Log.e("Abid", "Writing file$file")
            isSuccess = true
        } catch (e: IOException) {
            Log.e("Abid", "Error writing Exception: ", e)
            isSuccess = false
        } catch (e: Exception) {
            Log.e("Abid", "Failed to save file due to Exception: ", e)
            isSuccess = false
        } finally {
            try {
                if (null != fileOutputStream) {
                    fileOutputStream.close()
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                Log.e("Abid"," Error: ${ex.toString()}")
            }
        }
        return isSuccess
    }
}
