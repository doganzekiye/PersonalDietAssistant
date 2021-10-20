package com.example.personaldietassistant.ui.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.personaldietassistant.R

class PdaStepView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var stepSize: Step.StepSize
    var stepColor: Step.StepColor
    var stepRadius: Float

    private var mRadiusAll = NO_RADIUS
    private var mStepColor = NO_STEP_COLOR
    private var mStepSelectedColor = NO_STEP_COLOR
    private var mStepUnselectedColor = NO_STEP_COLOR
    private var mStepTotalCount = DEFAULT_STEP_TOTAL_COUNT
    private var mStepSelectedCount = DEFAULT_STEP_SELECTED_COUNT
    private var mStepWidth = DEFAULT_WIDTH
    private var mStepHeight = DEFAULT_HEIGHT
    private var mStepsList: MutableList<Step> = mutableListOf()
    private var mStepSpace: Float = DEFAULT_SPACE

    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }
    private val mPathSelected = Path()
    private val mPathUnselected = Path()
    private val mRect = RectF(0f, mStepHeight, mStepWidth, 0f)

    private var mCorners = floatArrayOf(
        mRadiusAll, mRadiusAll,   // Top left radius in px
        mRadiusAll, mRadiusAll,   // Top right radius in px
        mRadiusAll, mRadiusAll,     // Bottom right radius in px
        mRadiusAll, mRadiusAll      // Bottom left radius in px
    )

    init {
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.PdaStepView, defStyleAttr, 0
        )

        mStepWidth = a.getDimension(R.styleable.PdaStepView_pdaStepWidth, DEFAULT_WIDTH)
        mStepHeight = a.getDimension(R.styleable.PdaStepView_pdaStepHeight, DEFAULT_HEIGHT)
        mStepSpace = a.getDimension(R.styleable.PdaStepView_pdaSpace, DEFAULT_SPACE)
        mStepTotalCount =
            a.getInt(R.styleable.PdaStepView_pdaStepTotalCount, DEFAULT_STEP_TOTAL_COUNT)
        mStepSelectedCount =
            a.getInt(R.styleable.PdaStepView_pdaStepSelectedCount, DEFAULT_STEP_SELECTED_COUNT)
        mRadiusAll = a.getDimension(R.styleable.PdaStepView_pdaStepRadiusAll, mRadiusAll)
        mStepColor = a.getInt(R.styleable.PdaStepView_pdaStepColor, DEFAULT_STEP_UNSELECTED_COLOR)
        mStepSelectedColor =
            a.getInt(R.styleable.PdaStepView_pdaStepSelectedColor, DEFAULT_STEP_SELECTED_COLOR)
        mStepUnselectedColor =
            a.getInt(R.styleable.PdaStepView_pdaStepUnselectedColor, DEFAULT_STEP_UNSELECTED_COLOR)

        stepSize = Step.StepSize(mStepWidth, mStepHeight)
        stepColor = Step.StepColor(mStepSelectedColor, mStepUnselectedColor)
        stepRadius = mRadiusAll
        mCorners = floatArrayOf(
            stepRadius, stepRadius,
            stepRadius, stepRadius,
            stepRadius, stepRadius,
            stepRadius, stepRadius
        )

        for (i in 0 until mStepTotalCount) {
            if (i in 0 until mStepSelectedCount) {
                mStepsList.add(Step(isSelected = true, stepSize, stepColor, stepRadius))
            } else {
                mStepsList.add(Step(isSelected = false, stepSize, stepColor, stepRadius))
            }
        }

        a.recycle()
    }

    private fun loadStepList() {
        mStepsList.clear()
        for (i in 0 until mStepTotalCount) {
            if (i in 0 until mStepSelectedCount) {
                mStepsList.add(Step(isSelected = true, stepSize, stepColor, stepRadius))
            } else {
                mStepsList.add(Step(isSelected = false, stepSize, stepColor, stepRadius))
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            for (i in 0 until mStepTotalCount) {

                mPaint.color =
                    if (mStepsList[i].isSelected) mStepSelectedColor else mStepUnselectedColor

                if (i in 0 until mStepSelectedCount) {
                    mPathSelected.addRoundRect(mRect.apply {
                        this.left = i * mStepWidth + i * mStepSpace
                        this.right = (i + 1) * mStepWidth + i * mStepSpace
                        this.top = mStepHeight
                    }, mCorners, Path.Direction.CW)

                    canvas.drawPath(mPathSelected, mPaint)
                } else {
                    mPathUnselected.addRoundRect(mRect.apply {
                        this.left = i * mStepWidth + i * mStepSpace
                        this.right = (i + 1) * mStepWidth + i * mStepSpace
                        this.top = mStepHeight
                    }, mCorners, Path.Direction.CW)

                    canvas.drawPath(mPathUnselected, mPaint)
                }
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val paddingHorizontal = paddingStart + paddingEnd
        val paddingVertical = paddingTop + paddingBottom
        val height = mStepHeight * resources.displayMetrics.density + paddingVertical
        val width =
            (mStepWidth * resources.displayMetrics.density * mStepTotalCount + mStepSpace * mStepTotalCount) + paddingHorizontal

        setMeasuredDimension(
            measureDimension(width.toInt(), widthMeasureSpec),
            measureDimension(height.toInt(), heightMeasureSpec)
        )
    }

    private fun measureDimension(desiredSize: Int, measureSpec: Int): Int {
        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)

        var measuredSize = 0

        //Measure Width
        measuredSize = when (mode) {
            MeasureSpec.EXACTLY -> {
                //Must be this size
                size
            }
            MeasureSpec.AT_MOST -> {
                //Can't be bigger than...
                desiredSize.coerceAtMost(size)
            }
            else -> {
                //Be whatever you want
                desiredSize
            }
        }

        return measuredSize
    }

    fun setStepCount(selected: Int, total: Int) {
        this.mStepSelectedCount = selected
        this.mStepTotalCount = total
        loadStepList()
        postInvalidate()
    }

    companion object {
        const val NO_STEP_COLOR = -1
        const val DEFAULT_STEP_UNSELECTED_COLOR = Color.LTGRAY
        const val DEFAULT_STEP_SELECTED_COLOR = Color.GREEN
        const val DEFAULT_STEP_SELECTED_COUNT = 1
        const val DEFAULT_STEP_TOTAL_COUNT = 5
        const val DEFAULT_RADIUS = 10f
        const val NO_RADIUS = 5f
        const val DEFAULT_WIDTH = 50f
        const val DEFAULT_HEIGHT = 10f
        const val DEFAULT_SPACE = DEFAULT_WIDTH / 2
    }

    data class Step(
        var isSelected: Boolean = false,
        val size: StepSize,
        val color: StepColor,
        val radius: Float = NO_RADIUS
    ) {

        data class StepSize(
            val width: Float = DEFAULT_WIDTH,
            val height: Float = DEFAULT_HEIGHT
        )

        data class StepColor(
            val selectedColor: Int = DEFAULT_STEP_SELECTED_COLOR,
            val unselectedColor: Int = DEFAULT_STEP_UNSELECTED_COLOR
        )
    }
}