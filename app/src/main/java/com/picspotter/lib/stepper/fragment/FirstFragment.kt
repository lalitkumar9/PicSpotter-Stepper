package com.picspotter.lib.stepper.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.picspotter.lib.stepper.OnNextClickListner
import com.picspotter.lib.stepper.R
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment: Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstNext.setOnClickListener {
            listner.onNextClickListner(2)
        }
    }

    companion object {
        private lateinit var listner: OnNextClickListner

        fun newInstance(listner: OnNextClickListner): FirstFragment {

            Companion.listner = listner
          return FirstFragment()
        }
    }
}