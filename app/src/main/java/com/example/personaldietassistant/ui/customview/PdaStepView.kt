package com.example.personaldietassistant.ui.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.personaldietassistant.R

class PdaStepView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mRadiusAll = NO_RADIUS
    private var mRadiusTop = NO_RADIUS
    private var mRadiusBottom = NO_RADIUS
    private var mRadiusTopLeft = NO_RADIUS
    private var mRadiusTopRight = NO_RADIUS
    private var mRadiusBottomLeft = NO_RADIUS
    private var mRadiusBottomRight = NO_RADIUS
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
        mRadiusTopLeft, mRadiusTopLeft,   // Top left radius in px
        mRadiusTopRight, mRadiusTopRight,   // Top right radius in px
        mRadiusBottomRight, mRadiusBottomRight,     // Bottom right radius in px
        mRadiusBottomLeft, mRadiusBottomLeft      // Bottom left radius in px
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
        mRadiusTop = a.getDimension(R.styleable.PdaStepView_pdaStepRadiusAll, mRadiusTop)
        mRadiusBottom = a.getDimension(R.styleable.PdaStepView_pdaStepRadiusAll, mRadiusBottom)
        mRadiusTopLeft = a.getDimension(R.styleable.PdaStepView_pdaStepRadiusAll, mRadiusTopLeft)
        mRadiusTopRight = a.getDimension(R.styleable.PdaStepView_pdaStepRadiusAll, mRadiusTopRight)
        mRadiusBottomLeft =
            a.getDimension(R.styleable.PdaStepView_pdaStepRadiusAll, mRadiusBottomLeft)
        mRadiusBottomRight =
            a.getDimension(R.styleable.PdaStepView_pdaStepRadiusAll, mRadiusBottomRight)
        mStepColor = a.getInt(R.styleable.PdaStepView_pdaStepColor, DEFAULT_STEP_UNSELECTED_COLOR)
        mStepSelectedColor =
            a.getInt(R.styleable.PdaStepView_pdaStepSelectedColor, DEFAULT_STEP_SELECTED_COLOR)
        mStepUnselectedColor =
            a.getInt(R.styleable.PdaStepView_pdaStepUnselectedColor, DEFAULT_STEP_UNSELECTED_COLOR)

        val stepSize = Step.StepSize(mStepWidth, mStepHeight)
        val stepColor = Step.StepColor(mStepSelectedColor, mStepUnselectedColor)

        val stepRadius = Step.StepRadius(
            mRadiusAll,
            if (mRadiusAll != NO_RADIUS) mRadiusAll else mRadiusTop,
            if (mRadiusAll != NO_RADIUS) mRadiusAll else mRadiusBottom,
            if (mRadiusAll != NO_RADIUS) mRadiusAll else if (mRadiusTop != NO_RADIUS) mRadiusTop else mRadiusTopLeft,
            if (mRadiusAll != NO_RADIUS) mRadiusAll else if (mRadiusTop != NO_RADIUS) mRadiusTop else mRadiusTopRight,
            if (mRadiusAll != NO_RADIUS) mRadiusAll else if (mRadiusBottom != NO_RADIUS) mRadiusBottom else mRadiusBottomLeft,
            if (mRadiusAll != NO_RADIUS) mRadiusAll else if (mRadiusBottom != NO_RADIUS) mRadiusBottom else mRadiusBottomRight
        )

        mCorners = floatArrayOf(
            stepRadius.topLeftRadius, stepRadius.topLeftRadius,
            stepRadius.topRightRadius, stepRadius.topRightRadius,
            stepRadius.bottomRightRadius, stepRadius.bottomRightRadius,
            stepRadius.bottomLeftRadius, stepRadius.bottomLeftRadius
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
        val radius: StepRadius
    ) {

        data class StepSize(
            val width: Float = DEFAULT_WIDTH,
            val height: Float = DEFAULT_HEIGHT
        )

        data class StepColor(
            val selectedColor: Int = DEFAULT_STEP_SELECTED_COLOR,
            val unselectedColor: Int = DEFAULT_STEP_UNSELECTED_COLOR
        )

        data class StepRadius(
            val radius: Float = NO_RADIUS,
            val topRadius: Float = NO_RADIUS,
            val bottomRadius: Float = NO_RADIUS,
            val topLeftRadius: Float = NO_RADIUS,
            val topRightRadius: Float = NO_RADIUS,
            val bottomLeftRadius: Float = NO_RADIUS,
            val bottomRightRadius: Float = NO_RADIUS
        )
    }
}