package com.pliniodev.customview.view.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.pliniodev.customview.R
import com.pliniodev.customview.databinding.ProgressButtonBinding


/**
 * JvmOverloads ajuda na criação de todos os construtores necessários que seriam
 * necessários por herdarmos de ConstraintLayout
 */

class ProgressButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    //Variáveis(attrs) que serão recebidas na criação da view, para que sejam reutilizadas
    //elas serão declaradas como nulas
    private var title:  String? = null
    private var loadingTitle: String? = null

    //Após determinar a viewBinding em buildFeatures no gradleApp fazemos o inflate
    private var binding = ProgressButtonBinding
        .inflate(LayoutInflater.from(context), this, true)

    //sempre que for setado um estado novo refreshState será chamado
    private var state: ProgressButtonState = ProgressButtonState.Normal
        set(value) {
            field = value
            refreshState()
        }

    init {
        setLayout(attrs)
        refreshState()//é chamado para setar o estado padrão na inicialização
    }

    /**
     * Se setlayout não for nulo entre no let
     * Declare uma variável que através do contexto chama
     * obtainStyledAttibutes que precisa do atributeSet e
     * do styleble que for criado em values
     */
    private fun setLayout(attrs: AttributeSet?){
        attrs?.let { attributeSet ->
            val attributes = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.ProgressButton //por convensão o nome utilizado no styleble deve ser o mesmo nome da customView
            )

            setBackgroundResource(R.drawable.progress_button_bg)

            //recuperando attrs
            val titleResId = attributes.getResourceId(
                R.styleable.ProgressButton_progress_button_title, 0)
            if (titleResId != 0) {
                title = context.getString(titleResId)
            }

            val loadingTitleResId = attributes.getResourceId(
                R.styleable.ProgressButton_progress_button_loading_title, 0)
            if (loadingTitleResId != 0) {
                loadingTitle = context.getString(loadingTitleResId)
            }

            //existe uma recomendação que sempre que o attributeSet é utilizado nas customViews
            //recycle() deve ser chamado. A própria IDE informa isso.
            attributes.recycle()
        }
    }

    /**
     * 1ª Seta isEnabled e isClickable através da var state criada
     */
    private fun refreshState() {
        isEnabled = state.isEnabled
        isClickable = state.isEnabled
        refreshDrawableState()//muda o selector

        binding.textTitle.run {
//            isEnabled = state.isEnabled
//            isClickable = state.isEnabled
            isFocusable = false
        }

        binding.progressButton.visibility = state.progressVisibility

        when (state) {
            ProgressButtonState.Normal -> binding.textTitle.text = title
            ProgressButtonState.Loading -> binding.textTitle.text = loadingTitle
        }
    }

    fun setButton() {
        if (state.isEnabled) {
            setLoading()
        } else {
            setNormal()
        }
    }

    fun setLoading() {
        state = ProgressButtonState.Loading
    }

    fun setNormal() {
        state = ProgressButtonState.Normal
    }

    /**
     * Sealed class com os estados possíveis para essa CustomView
     * utilizar a sealedClass aqui é das possíveis implementações
     * para os estados da CustomView
     */
    sealed class ProgressButtonState(val isEnabled: Boolean, val progressVisibility: Int) {
        object Normal : ProgressButtonState(true, View.GONE)
        object Loading : ProgressButtonState(false, View.VISIBLE)
    }
}