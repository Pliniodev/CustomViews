package com.pliniodev.customview.view.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class ChordView(
    context: Context,
    attrs: AttributeSet
) : View(context, attrs) {

    private val drawPaint = Paint()
    private var size = 320//tamanho da view
    private val path = Path()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        setupPaint()
        drawSquareLine(canvas)
        drawStrings(canvas)
        drawGuitarFlavors(canvas)
        drawCircleFingering(canvas)
        drawTextFingering(canvas)
    }

    private fun drawStrings(canvas: Canvas?) {
        path.moveTo(size * 0.32f, size * 0.10f)
        path.lineTo(size * 0.32f, size * 0.90f)

        path.moveTo(size * 0.44f, size * 0.10f)
        path.lineTo(size * 0.44f, size * 0.90f)

        path.moveTo(size * 0.56f, size * 0.10f)
        path.lineTo(size * 0.56f, size * 0.90f)

        path.moveTo(size * 0.68f, size * 0.10f)
        path.lineTo(size * 0.68f, size * 0.90f)

        path.close()
        canvas?.drawPath(path, drawPaint)
    }

    private fun drawGuitarFlavors(canvas: Canvas?) {
        path.moveTo(size * 0.20f, size * 0.26f)
        path.lineTo(size * 0.80f, size * 0.26f)

        path.moveTo(size * 0.20f, size * 0.42f)
        path.lineTo(size * 0.80f, size * 0.42f)

        path.moveTo(size * 0.20f, size * 0.58f)
        path.lineTo(size * 0.80f, size * 0.58f)

        path.moveTo(size * 0.20f, size * 0.74f)
        path.lineTo(size * 0.80f, size * 0.74f)

        path.close()
        canvas?.drawPath(path, drawPaint)
    }

    private fun drawCircleFingering(canvas: Canvas?){
        val radius = 60f
        val casa1x1 = size * 0.20f
        val casa1y1 = size * 0.18f

        canvas?.drawCircle(casa1x1,casa1y1,radius,drawPaint)
    }

    private fun drawTextFingering(canvas: Canvas?) {

        val textCasa1x = size * 0.183f
        val textCasa1y = size * 0.20f
        drawPaint.color = Color.BLACK
        drawPaint.textSize = 60f
        canvas?.drawText("1", textCasa1x, textCasa1y, drawPaint)
    }

    //calcula o tamanho da tela do dispositivo
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //coerceAtMost garante que o valor não seja maior que o especificado(no paramentro)
        //assim a altura não será maior que a largura, portanto um quadrado
        size = measuredWidth.coerceAtMost(measuredHeight)
        setMeasuredDimension(size, size)
    }

    private fun drawSquareLine(canvas: Canvas?){

        //linha esquerda
        path.moveTo(size * 0.20f, size * 0.10f)
        path.lineTo(size * 0.20f, size * 0.90f)

        //linha direita
        path.moveTo(size * 0.80f, size * 0.10f)
        path.lineTo(size * 0.80f, size * 0.90f)

        //linha baixo
        path.moveTo(size * 0.20f, size * 0.90f)
        path.lineTo(size * 0.80f, size * 0.90f)

        //linha cima
        path.moveTo(size * 0.20f, size * 0.10f)
        path.lineTo(size * 0.80f, size * 0.10f)

        path.close()
        canvas?.drawPath(path, drawPaint)
    }

    private fun setupPaint() {
        drawPaint.color = Color.GRAY
        drawPaint.isAntiAlias = true
        drawPaint.strokeWidth = 7F
        drawPaint.style = Paint.Style.FILL_AND_STROKE
        drawPaint.strokeJoin = Paint.Join.ROUND
        drawPaint.strokeCap = Paint.Cap.ROUND
    }
}

