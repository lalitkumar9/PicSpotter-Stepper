package com.picspotter.lib.stepper.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.picspotter.lib.stepper.OnNextClickListner
import com.picspotter.lib.stepper.R
import kotlinx.android.synthetic.main.second_fragment.*

class SecondFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.second_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        secondNext.setOnClickListener {
            listner.onNextClickListner(3)
        }
    }
    companion object {
        private lateinit var listner: OnNextClickListner

        fun newInstance(listner: OnNextClickListner): SecondFragment {

            Companion.listner = listner
            return SecondFragment()
        }
    }
}