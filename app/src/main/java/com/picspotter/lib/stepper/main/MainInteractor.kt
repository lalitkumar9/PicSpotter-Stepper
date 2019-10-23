package com.picspotter.lib.stepper.main

class MainInteractor {

    lateinit var presenter: MainPresenter

    fun setStepperArray() {
        presenter.setStepperArray()
    }

    fun setStepperPosition() {
        presenter.setStepperPosition()
    }

    fun addFirstFragment() {
        presenter.addFirstFragment()
    }

    fun addSecondFragment() {
        presenter.addSecondFragment()
    }

    fun addThirdFragment() {
        presenter.addThirdFragment()
    }
}