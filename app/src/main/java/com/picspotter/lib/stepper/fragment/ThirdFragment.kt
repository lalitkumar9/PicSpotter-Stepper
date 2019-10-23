package com.picspotter.lib.stepper.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.picspotter.lib.stepper.OnNextClickListner
import com.picspotter.lib.stepper.R

class ThirdFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.third_fragment, container, false)
    }

    companion object {
        private lateinit var listner: OnNextClickListner

        fun newInstance(listner: OnNextClickListner): ThirdFragment {

            Companion.listner = listner
            return ThirdFragment()
        }
    }
}