package com.pliniodev.customview.view.customviews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class SmileView(
    context: Context,
    attrs: AttributeSet
) : View(context, attrs){
    // cria o objeto de pintura para colorir e estilizar
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    //cores para o background olhos e boca
    private var faceColor = Color.YELLOW
    private var eyesColor = Color.BLACK
    private var mouthColor = Color.BLACK
    private var borderColor = Color.BLACK

    //largura da borda do rosto em pixels
    private var borderWidth = 4.0f

    //Tamanho da view em pixels
    private var size = 320

    //Para desenhar caminhos curvos
    private val mouthPath = Path()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawFaceBackground(canvas)
        drawEyes(canvas)
        drawMouth(canvas)
    }

    private fun drawFaceBackground(canvas: Canvas?) {
        // Defina a cor da pintura como faceColor e faça com que ela preencha a área de
        // desenho.
        paint.color = faceColor
        paint.style = Paint.Style.FILL

        // Calcule um raio para um círculo que você deseja desenhar como o plano de fundo
        // da face
        val radius = size / 2f

        // Desenhe o círculo de fundo com um centro de (x,y), onde xe ysão iguais à metade do
        // tamanho e com o calculado radius.
        canvas?.drawCircle(size / 2f, size / 2f, radius, paint)

        //Mude a paintcor para borderColor faça com que apenas desenhe uma borda ao redor
        // da área de desenho, definindo o estilo para STROKE
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth

        // Desenhe uma borda com o mesmo centro, mas com um raio menor que o anterior
        // radiuspelo borderWidth
        canvas?.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint)
    }

    private fun drawEyes(canvas: Canvas?) {
        //Defina a paintcor como eyesColore faça com que ela preencha a área de desenho.
        paint.color = eyesColor
        paint.style = Paint.Style.FILL

        val leftEyeRect = RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.50f)
        canvas?.drawOval(leftEyeRect, paint)

        val rightEyeRect = RectF(size * 0.57f, size * 0.23f, size * 0.68f, size * 0.50f)
        canvas?.drawOval(rightEyeRect, paint)
    }


    private fun drawMouth(canvas: Canvas?) {
        mouthPath.moveTo(size * 0.22f, size * 0.7f)

        mouthPath.quadTo(size * 0.50f, size * 0.80f,
            size * 0.78f, size * 0.70f)

        mouthPath.quadTo(size * 0.50f, size * 0.90f,
            size * 0.22f, size * 0.70f)

        paint.color = mouthColor
        paint.style = Paint.Style.FILL

        canvas?.drawPath(mouthPath, paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        size = measuredWidth.coerceAtMost(measuredHeight)
        setMeasuredDimension(size, size)
    }
}