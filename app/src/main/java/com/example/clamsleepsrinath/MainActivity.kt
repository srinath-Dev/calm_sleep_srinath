package com.example.clamsleepsrinath


import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.clamsleepsrinath.databinding.ActivityMainBinding
import com.example.clamsleepsrinath.models.ItemsAdapter
import com.example.clamsleepsrinath.models.Model
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val adapter = ItemsAdapter(this)
    lateinit var bottomSheetFragmentOne:BottomSheetDialogFragment
    lateinit var dialogOne:BottomSheetDialog
    lateinit var dialogTwo:BottomSheetDialog
    lateinit var dialogThree:BottomSheetDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.root)
        setUpViewPager()
    }

    private fun setUpViewPager() {
        binding.viewPager.clipToPadding = false
        binding.viewPager.clipChildren = false
        binding.viewPager.offscreenPageLimit = 3
        binding.viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(10))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }

        binding.viewPager.setPageTransformer(compositePageTransformer)
        binding.viewPager.adapter = adapter
        adapter.setItems(getData())


        binding.back.setOnClickListener {
            val transition: Transition = Fade()
            transition.setDuration(1000)
            transition.addTarget(binding.collapaseView)
            TransitionManager.beginDelayedTransition(binding.viewPager, transition)
            binding.collapaseView.setVisibility(View.GONE)
        }

        binding.btnStackOne.setOnClickListener {
            showBottomSheetDialog(R.layout.bottom_sheet_one)
        }
    }

    private fun getData(): List<Model> {
        val item: MutableList<Model> = ArrayList()
        val data1 = Model(Name = "Image1", image = R.drawable.img1);
        item.add(data1)
        val data2 = Model(Name = "Image2", image = R.drawable.img2);
        item.add(data2)
        val data3 = Model(Name = "Image3", image = R.drawable.img3);
        item.add(data3)
        val data4 = Model(Name = "Image4", image = R.drawable.img4);
        item.add(data4)
        return item
    }

    fun collapseView(data:Model){
        binding.collImg.setBackgroundResource(data.image)
        val transition: Transition = Fade()
        transition.setDuration(1000)
        transition.addTarget(binding.collapaseView)
        TransitionManager.beginDelayedTransition(binding.viewPager, transition)
        binding.collapaseView.setVisibility(View.VISIBLE)
    }

    fun showBottomSheetDialog(
        @LayoutRes layout: Int,
        @IdRes textViewToSet: Int? = null,
        textToSet: String? = null,
        fullScreen: Boolean = true,
        expand: Boolean = true
    ) {
       dialogOne = BottomSheetDialog(this!!)
        dialogOne.setOnShowListener {
            val bottomSheet: FrameLayout = dialogOne.findViewById(com.google.android.material.R.id.design_bottom_sheet) ?: return@setOnShowListener
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
            bottomSheet.setBackgroundResource(android.R.color.transparent)
            if (fullScreen && bottomSheet.layoutParams != null) { showFullScreenBottomSheet(bottomSheet, Resources.getSystem().displayMetrics.heightPixels -200) }

            if (!expand) return@setOnShowListener


            expandBottomSheet(bottomSheetBehavior)
        }

        @SuppressLint("InflateParams") // dialog does not need a root view here
        val sheetView = layoutInflater.inflate(layout, null)
        textViewToSet?.also {
            sheetView.findViewById<TextView>(it).text = textToSet
        }
        val txtView = sheetView.findViewById<TextView>(R.id.btnStackTwo)

        txtView.setOnClickListener {
            showBottomSheetDialogTwo(R.layout.bottom_sheet_second)
        }


        dialogOne.setContentView(sheetView)
        dialogOne.show()
    }

    fun showBottomSheetDialogTwo(
        @LayoutRes layout: Int,
        @IdRes textViewToSet: Int? = null,
        textToSet: String? = null,
        fullScreen: Boolean = true,
        expand: Boolean = true
    ) {
         dialogTwo = BottomSheetDialog(this!!)
        dialogTwo.setOnShowListener {
            val bottomSheet: FrameLayout = dialogTwo.findViewById(com.google.android.material.R.id.design_bottom_sheet) ?: return@setOnShowListener
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
            bottomSheet.setBackgroundResource(android.R.color.transparent)
            if (fullScreen && bottomSheet.layoutParams != null) { showFullScreenBottomSheet(bottomSheet, Resources.getSystem().displayMetrics.heightPixels/2) }

            if (!expand) return@setOnShowListener


            expandBottomSheet(bottomSheetBehavior)
        }

        @SuppressLint("InflateParams") // dialog does not need a root view here
        val sheetView = layoutInflater.inflate(layout, null)
        textViewToSet?.also {
            sheetView.findViewById<TextView>(it).text = textToSet
        }

        val txtView = sheetView.findViewById<TextView>(R.id.btnStackThree)

        txtView.setOnClickListener {
            showBottomSheetDialogThree(R.layout.bottom_sheet_three)
        }

        dialogTwo.setContentView(sheetView)
        dialogTwo.show()
    }

    fun showBottomSheetDialogThree(
        @LayoutRes layout: Int,
        @IdRes textViewToSet: Int? = null,
        textToSet: String? = null,
        fullScreen: Boolean = true,
        expand: Boolean = true
    ) {
         dialogThree = BottomSheetDialog(this!!)
        dialogThree.setOnShowListener {
            val bottomSheet: FrameLayout = dialogThree.findViewById(com.google.android.material.R.id.design_bottom_sheet) ?: return@setOnShowListener
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
            bottomSheet.setBackgroundResource(android.R.color.transparent)
            if (fullScreen && bottomSheet.layoutParams != null) { showFullScreenBottomSheet(bottomSheet, Resources.getSystem().displayMetrics.heightPixels/3) }

            if (!expand) return@setOnShowListener


            expandBottomSheet(bottomSheetBehavior)
        }

        @SuppressLint("InflateParams") // dialog does not need a root view here
        val sheetView = layoutInflater.inflate(layout, null)
        textViewToSet?.also {
            sheetView.findViewById<TextView>(it).text = textToSet
        }

        val txtView = sheetView.findViewById<TextView>(R.id.done)

        txtView.setOnClickListener {
            dialogThree.dismiss()
            dialogTwo.dismiss()
            dialogOne.dismiss()
            val transition: Transition = Fade()
            transition.setDuration(1000)
            transition.addTarget(binding.collapaseView)
            TransitionManager.beginDelayedTransition(binding.viewPager, transition)
            binding.collapaseView.setVisibility(View.GONE)
        }


        dialogThree.setContentView(sheetView)
        dialogThree.show()
    }

    private fun showFullScreenBottomSheet(bottomSheet: FrameLayout, height:Int) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height =height
        bottomSheet.layoutParams = layoutParams
    }

    private fun expandBottomSheet(bottomSheetBehavior: BottomSheetBehavior<FrameLayout>) {
        bottomSheetBehavior.skipCollapsed = true
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }
}