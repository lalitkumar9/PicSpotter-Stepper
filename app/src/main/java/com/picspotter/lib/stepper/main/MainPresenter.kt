package com.picspotter.lib.stepper.main

import mycimb.digital.cimbthai.com.base.customview.stepper.CustomStepperModel

interface MainView {
    fun setStepperArray(stepperArray: ArrayList<CustomStepperModel.StepperModel>)
    fun setStepperPosition(stepperPosition: Int)
    fun addThirdFragment()
    fun addSecondFragment()
    fun addFirstFragment()

}

class MainPresenter {

    lateinit var activity: MainView
    var stepperPosition: Int = 0

    fun setStepperArray() {

        var stepperArray = ArrayList<CustomStepperModel.StepperModel>()
        stepperArray.apply {
            add(
                CustomStepperModel.StepperModel(
                    "First Fragment",
                    true
                )
            )

            add(
                CustomStepperModel.StepperModel(
                    "Second Fragment",
                    false
                )
            )

            add(
                CustomStepperModel.StepperModel(
                    "Third Fragment",
                    false
                )
            )
        }
        activity.setStepperArray(stepperArray)
    }

    fun setStepperPosition() {
        activity.setStepperPosition(stepperPosition)
    }

    fun addFirstFragment() {
        activity.addFirstFragment()
    }

    fun addSecondFragment() {
        activity.addSecondFragment()
    }

    fun addThirdFragment() {
        activity.addThirdFragment()
    }

}