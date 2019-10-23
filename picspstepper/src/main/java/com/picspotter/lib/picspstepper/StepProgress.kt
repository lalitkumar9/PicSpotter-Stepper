package mycimb.digital.cimbthai.com.base.customview.stepper

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.picspotter.lib.picspstepper.R
import com.picspotter.lib.picspstepper.StepperClickListener
import kotlinx.android.synthetic.main.stepper_layout.view.*

import java.util.*

class StepProgress : LinearLayout {

    private var stepperArray: ArrayList<CustomStepperModel.StepperModel>? = null
    private var lineBar: View? = null
    private var mContext: Context? = null
    private val storeData = ArrayList<String>()
    private var dotDefaultSize: Int = 0
    private var dotSelectedSize: Float = 0.toFloat()
    private var itemMargins: Int = 0
    private var mode: Int = 0
    private var barHeight: Int = 0
    private var textSize: Int = 0
    private var selectedTextColor: Int? = null
    private var unselectTextColor: Int? = null
    private var onClickListener: StepperClickListener? = null
    private var unselectSize: Float = 0.toFloat()
    private var unselectBarColor: Int = 0
    private var selectedBarColor: Int = 0
    private var stepperView: LinearLayout? = null

    constructor(context: Context) : super(context) {
        initView(context)
    }


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {

        this.mContext = context

        var storeSelectedSize = 0
        if (attrs != null) {
            val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.StepUi,
                0, 0
            )

            try {
                mode = a.getInteger(R.styleable.StepUi_indicator_mode, 0)
                barHeight = a.getInteger(R.styleable.StepUi_barHeight, 4)
                selectedTextColor = a.getColor(R.styleable.StepUi_selectedTextColor, Color.WHITE)
                unselectTextColor = a.getColor(R.styleable.StepUi_unselectTextColor, Color.BLACK)
                selectedBarColor = a.getColor(R.styleable.StepUi_selectedBarColor, Color.parseColor("#F3AD33"))
                unselectBarColor = a.getColor(R.styleable.StepUi_unselectBarColor, Color.parseColor("#D6D6D6"))
                itemMargins = a.getDimensionPixelSize(R.styleable.StepUi_itemMargins, 25)
                dotDefaultSize = a.getDimensionPixelSize(R.styleable.StepUi_dotDefaultSize, 60)
                storeSelectedSize = a.getDimensionPixelSize(R.styleable.StepUi_dotSelectedSize, 90)
                textSize = a.getDimensionPixelSize(R.styleable.StepUi_textSize, 12)
            } finally {
                a.recycle()
            }
        }

        val checkItemMargins = (storeSelectedSize - dotDefaultSize) / 2
        if (checkItemMargins > itemMargins) {
            itemMargins = checkItemMargins
        }
        dotSelectedSize = (1.0 / dotDefaultSize).toFloat() * storeSelectedSize
        unselectSize = (1.0 / dotDefaultSize).toFloat() * dotDefaultSize
    }

    private fun initView(context: Context) {

        this.mContext = context

        initView(context, null)

    }


    fun selected(position: Int) {

        stepperView?.let {
            var position = position
            for (j in 0 until it.childCount) {
                val layout = it.getChildAt(j) as LinearLayout

                if (layout.getChildAt(0) is TextView) {
                    val mView = layout.getChildAt(0) as TextView


                    if (position + 1 > j) {
                        mView.setBackgroundResource(R.mipmap.red_cir_check)
                        mView.setTextColor(selectedTextColor!!)
                        changeColor(mView.background, selectedBarColor)
                        if (position == j) {
                            val mParams = LinearLayout.LayoutParams(200, LinearLayout.LayoutParams.WRAP_CONTENT)
                            mView.layoutParams = mParams
                            mView.setBackgroundResource(R.drawable.stepper_button_background)
                            mView.text = stepperArray!![position].title
                            animationViewSize(mView, 350)
                        } else {

                            val mmParams = LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                            )
                            mView.layoutParams = mmParams
                            mView.text = ""
                            mView.setBackgroundResource(R.mipmap.red_cir_check)
                        }
                        position += 1
                    } else {
                        mView.setBackgroundResource(R.drawable.default_indicator)
                        mView.setTextColor(unselectTextColor!!)
                        changeColor(mView.background, unselectBarColor)

                        val mmParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        mView.layoutParams = mmParams
                        mView.text = ""

                    }
                    if (position == j) {
                        mView.animate()
                        mView.animate()
                    } else {
                        mView.animate()
                        mView.animate()
                    }
                } else if (layout.getChildAt(0) is View) {
                    val mView = layout.getChildAt(0) as View
                    val pos = position - 1
                    if (pos == j || pos > j) {

                        mView.setBackgroundColor(resources.getColor(R.color.red,null))
                        mView.animate()
                    } else {
                        mView.setBackgroundColor(resources.getColor(R.color.stepper_background_lightGrey, null))
                        mView.animate()
                    }

                }

            }
        }.run {
            return
        }
    }


    fun setStepperData(stepperArray: ArrayList<CustomStepperModel.StepperModel>) {

        var stepperLocalArray = ArrayList<CustomStepperModel.StepperModel>()
        if (storeData != null) {
            removeAllViews()
            storeData.clear()
        }
        for (i in 0 until stepperArray.size) {

            storeData.add(stepperArray[i].title.toString())
            stepperLocalArray.apply {

                stepperLocalArray.add(stepperArray[i])

                if (i < stepperArray.size-1) {
                    stepperLocalArray.add(
                            CustomStepperModel.StepperModel(
                            "",
                            false
                        )
                    )
                }
            }

        }

       this.stepperArray = stepperLocalArray

        initData(storeData)

    }

    private fun initData(storeData: List<String>) {

      val view = LayoutInflater.from(context).inflate(R.layout.stepper_layout, null)
           stepperView = view.stepperLayout
        for (i in storeData.indices) {
            val linearLayout = LinearLayout(mContext)
            linearLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )
            linearLayout.gravity = Gravity.CENTER
            val text = TextView(mContext)
            if (storeData[i] != null) {
                text.text = storeData[i]
            }
            val mParams =
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            text.setBackgroundResource(R.drawable.default_indicator)
            mParams.setMargins(itemMargins, itemMargins, itemMargins, itemMargins)
            text.layoutParams = mParams
            text.gravity = Gravity.CENTER
            text.textSize = textSize.toFloat()
            text.width = dotDefaultSize
            text.height = dotDefaultSize
            text.setOnClickListener {
                if (onClickListener != null) {
                    onClickListener!!.onClick(i)
                }
            }


            linearLayout.addView(text)
            view.stepperLayout.addView(linearLayout)

            if (i < storeData.size - 1) {
                val lineLayout = LinearLayout(mContext)
                val parms = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, 5, 1.0f
                )
                parms.setMargins(10, 0, 10, 0)
                lineLayout.layoutParams = parms

                lineBar = View(mContext)
                val barLayoutParams = LinearLayout.LayoutParams(0, 20, 1.0f)
                lineBar!!.layoutParams = barLayoutParams
                lineLayout.addView(lineBar)


                view.stepperLayout.addView(lineLayout)
            }

        }

        addView(view)
    }

    private fun animationViewSize(view: View?, width: Int) {
        val anim = ValueAnimator.ofInt(view!!.measuredWidth, width)
        anim.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            val layoutParams = view.layoutParams
            layoutParams.width = value
            view.layoutParams = layoutParams
        }
        anim.duration = 300
        anim.start()
        anim.startDelay = 100
    }

    private fun changeColor(background: Drawable, color: Int?) {

        when (background) {
            is ShapeDrawable -> (background.mutate() as ShapeDrawable).paint.color = color!!
            is GradientDrawable -> (background.mutate() as GradientDrawable).setColor(color!!)
            is ColorDrawable -> (background.mutate() as ColorDrawable).color = color!!
            else -> {

            }
        }
    }

}