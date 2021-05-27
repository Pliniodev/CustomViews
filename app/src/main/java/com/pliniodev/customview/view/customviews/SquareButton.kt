package com.pliniodev.customview.view.customviews

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import com.pliniodev.customview.R
import com.pliniodev.customview.databinding.SquareButtonBinding

class SquareButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr){

    private var textActive: String? = null
    private var textDeactive: String? = null
    private var imgActive: Drawable? = null
    private var imgDeactive: Drawable? = null

    private var binding = SquareButtonBinding
    .inflate(LayoutInflater.from(context), this, true)

    private var state: SquareButtonState = SquareButtonState.Active//default
    set(value) {
        field = value
        refreshState()
    }

    init {
        setLayout(attrs)
        refreshState()
    }

    private fun setLayout(attrs: AttributeSet?) {
        attrs?.let { attributeSet ->
            val attributes = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.SquareButton
            )
            setBackgroundResource(R.drawable.selector_square_button)//definindo seletor

            val textActiveResId = attributes.getResourceId(
                R.styleable.SquareButton_square_button_active, 0)
            if (textActiveResId != 0) {
                textActive = context.getString(textActiveResId)
            }
            val textDeactiveResId = attributes.getResourceId(
                R.styleable.SquareButton_square_button_deactive, 0)
            if (textDeactiveResId != 0) {
                textDeactive = context.getString(textDeactiveResId)
            }

            val imgActiveResId = attributes.getResourceId(
                R.styleable.SquareButton_square_img_src_active, 0)
            if (imgActiveResId != 0) {
                imgActive = AppCompatResources.getDrawable(context, imgActiveResId)
            }
            val imgDeactiveResId = attributes.getResourceId(
                R.styleable.SquareButton_square_img_src_deactive, 0)
            if (imgDeactiveResId != 0) {
                imgDeactive = AppCompatResources.getDrawable(context, imgDeactiveResId)
            }


            attributes.recycle()
        }
    }

    private fun refreshState() {
        isActivated = state.isActivated
        isClickable = true

        refreshDrawableState()
        //desativando foco
        binding.imageView.run {
            isFocusable = false
        }
        binding.tvSquareButton.run {
            isFocusable = false
        }

        when(state){
            SquareButtonState.Active ->{
                binding.tvSquareButton.text = textActive
                binding.imageView.setImageDrawable(imgActive)
            }
            SquareButtonState.Deactive -> {
                binding.tvSquareButton.text = textDeactive
                binding.imageView.setImageDrawable(imgDeactive)
            }
        }
    }

    sealed class SquareButtonState(val isActivated: Boolean) {
        object Active : SquareButtonState(true)
        object Deactive : SquareButtonState(false)
    }

    private fun setActive() {
        state = SquareButtonState.Active
    }
    private fun setDeactive() {
        state = SquareButtonState.Deactive
    }

    fun setSquareButton() {
        if (state.isActivated) {
            setDeactive()
        } else {
            setActive()
        }
    }


}