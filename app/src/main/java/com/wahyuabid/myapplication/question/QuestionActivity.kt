package com.wahyuabid.myapplication.question

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.wahyuabid.myapplication.R
import com.wahyuabid.myapplication.core.ext.setOnSingleClickListener
import com.wahyuabid.myapplication.core.ext.viewBinding
import com.wahyuabid.myapplication.databinding.ActivityQuestionBinding
import com.wahyuabid.myapplication.home.ClassActivityViewModel
import com.wahyuabid.myapplication.question.bottom_sheet.InputNilaiBottomSheet
import com.wahyuabid.myapplication.question.fragment.QuestionFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionActivity : AppCompatActivity() {

    companion object {
        const val ID_CLASS = "ID_CLASS"
        fun getIntent(context: Context, classID: String): Intent {
            return Intent(context, QuestionActivity::class.java).apply {
                putExtra(ID_CLASS, classID)
            }
        }
    }

    val binding by viewBinding(ActivityQuestionBinding::inflate)

    private val questionView by lazy {
        listOf(
            QuestionFragment.getInstance(
                title = "1. Sprint",
                firstDesc = "Sprint atau lari cepat bertujuan untuk mengukur kecepatan. Kategori jarak yang harus ditempuh oleh masing-masing kelompok umur berbeda",
                image = R.drawable.il_sprint_1,
                secondDesc = "Pencatatan waktu dilakukan dalam satuan detik dengan satu angka dibelakang koma"
            ),
            QuestionFragment.getInstance(
                title = "2. Pull Up",
                firstDesc = "Pull-Up bertujuan untuk mengukur kekuatan otot lengan dan bahu. Untuk penilaian kelompok umur 06 - 09 tahun dan umur 10 -12 tahun melakukan pull-up selama 60 detik",
                secondDesc = "Untuk kelompok umur 13-15 tahun dan umur 16-19 tahun, melakukan gerakan pull-up selama 60 detik. Penilaian putra dihitung frekuensinya, sedangkan yang putri dihitung waktunya"
            ),
            QuestionFragment.getInstance(
                title = "3. SIT UP",
                firstDesc = "Sit-up bertujuan untuk mengukur kekuatan dan ketahanan otot perut. Kelompok umur 6-9 tahun dan 10-12 tahun melakukan selama 30 detik",
                secondDesc = "Sedangkan untuk kriteria penilaian kelompok umur 13-15 tahun dan 16-19 tahun yang melakukan selama 60 detik"
            ),
            QuestionFragment.getInstance(
                title = "4. Vertical Jump",
                firstDesc = "Tes ini bertujuan untuk mengukur daya ledak otot tungkai. Ukuran papan skala selebar 30 cm dan panjang 150 cm, dimana jarak antara garis skala satu denhgan yang lainnya masihng-masing 1cm. Papan skala ditempelkan" +
                        "di tembok dengan jarak skala nol(0) dengan laintai 150cm. pertama berdiri menyamping papa skala dengan mengangkat tangan keatas ukur tinggi yang didapat, kemudian lakuakn" +
                        "lompatan setinggi mungkin sebanyak tiga kalo, tiap lompatan dicatat tinggi yang diperoleh kemudian ambil yang tertinggi, selisih diantara raihan tertinggi dengan pengukuran yang pertama saat tidak melompat adalah hasil vertical jumo"
            ),
            QuestionFragment.getInstance(
                title = "5. Lari Jarak Sedang",
                firstDesc = "Lari jarak sedang dilakukan untuk mengukur daya tahan paru, jantung, dan pembuluh darah. Jarak yang ditempuh bergantung pada kelompok umur masing-masing",
                image = R.drawable.il_sprint_2,
                secondDesc = "Pencatatan waktu dilakukan dalam satuan menit koma (,) detik"
            )
        )
    }

    private val adapter: QuestionViewPager by lazy {
        QuestionViewPager(questionView, supportFragmentManager)
    }
    private var positionQuestion = 0
    private val viewModel: QuestionViewModel by viewModel()
    private val classID by lazy { intent.getStringExtra(ID_CLASS).orEmpty() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.vpQuestion.adapter = adapter

        with(viewModel) {
            onViewLoaded(classID)

            shouldShowBottomSheet.observe(this@QuestionActivity, Observer {
                if (it.second.isNotEmpty()) {
                    InputNilaiBottomSheet.getInstance(
                        isThirdType = it.first,
                        memberModels = it.second,
                        onClickSave = { idMember, listNilai ->
                            viewModel.onInputNilai(binding.vpQuestion.currentItem,idMember,listNilai)
                            Toast.makeText(this@QuestionActivity,"Berhasil menyimpan", Toast.LENGTH_SHORT).show()
                        }
                    ).show(supportFragmentManager, InputNilaiBottomSheet.TAG)
                } else {
                    Toast.makeText(
                        this@QuestionActivity,
                        "Daftar Peserta Kosong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }

        binding.apply {
            ivBack.setOnSingleClickListener {
                vpQuestion.post(Runnable { vpQuestion.setCurrentItem((vpQuestion.getCurrentItem() - 1) % questionView.size) })
            }

            ivNext.setOnSingleClickListener {
                vpQuestion.post(Runnable { vpQuestion.setCurrentItem((vpQuestion.getCurrentItem() + 1) % questionView.size) })
            }

            btnInput.setOnSingleClickListener {
                viewModel.onClickInputNilai(vpQuestion.currentItem)
            }

            vpQuestion.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    positionQuestion = position
                }

                override fun onPageSelected(position: Int) {
                }
            })
            btnDone.setOnSingleClickListener {
                finish()
            }
        }
    }

    class QuestionViewPager(val fragmentViews: List<Fragment>, fm: FragmentManager) :
        FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return fragmentViews[position]
        }

        override fun getCount(): Int {
            return fragmentViews.size
        }
    }
}