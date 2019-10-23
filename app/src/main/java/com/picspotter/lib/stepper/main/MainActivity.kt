package com.picspotter.lib.stepper.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.picspotter.lib.stepper.*
import com.picspotter.lib.stepper.fragment.FirstFragment
import com.picspotter.lib.stepper.fragment.SecondFragment
import kotlinx.android.synthetic.main.activity_main.*
import mycimb.digital.cimbthai.com.base.customview.stepper.CustomStepperModel

class MainActivity : AppCompatActivity(), MainView, OnNextClickListner {

    lateinit var interactor: MainInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialInstance()
    }

    private fun initialInstance() {

        MainConfigure.configure(this)
        interactor.setStepperArray()
        interactor.setStepperPosition()
        interactor.addFirstFragment()
    }

    override fun setStepperArray(stepperArray: ArrayList<CustomStepperModel.StepperModel>) {
        stepProgress.setStepperData(stepperArray)
    }

    override fun setStepperPosition(stepperPosition: Int) {
        stepProgress.selected(stepperPosition)
    }

    override fun addFirstFragment() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FirstFragment.newInstance(this), "first_fragment")
            .commit()
    }

    override fun addSecondFragment() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SecondFragment.newInstance(this), "second_fragment")
            .commit()
    }

    override fun addThirdFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SecondFragment.newInstance(this), "third_fragment")
            .commit()

    }


    override fun onNextClickListner(position: Int) {

        when(position) {
            1 -> {
                interactor.addFirstFragment()
                stepProgress.selected(0)
            }

            2-> {
                interactor.addSecondFragment()
                stepProgress.selected(1)
            }

            3-> {

                interactor.addThirdFragment()
                stepProgress.selected(2)
            }
        }
    }

    override fun onBackPressed() {
        var secondFrag = supportFragmentManager.findFragmentByTag("second_fragment") != null
        var thirdFrag = supportFragmentManager.findFragmentByTag("third_fragment") != null
        if (secondFrag && !thirdFrag) {
            addFirstFragment()
            stepProgress.selected(0)
        } else if (thirdFrag && !secondFrag) {
            addSecondFragment()
            stepProgress.selected(1)
        } else {
            finish()
        }
    }
}
