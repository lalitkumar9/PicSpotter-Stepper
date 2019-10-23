package com.picspotter.lib.stepper.main

class MainConfigure {

    companion object Singleton {
        fun configure(activity: MainActivity) {

            val presenter = MainPresenter()
            presenter.activity = activity

            val interactor = MainInteractor()
            interactor.presenter = presenter

            activity.interactor = interactor

        }
    }
}