package com.chen.libraryresouse.costomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.chen.libraryresouse.utils.DensityUtilsKt;

/**
 * Created by yanjun on 2016/11/18.
 * <p>
 * 八条斜线
 */

public class LoadingCircleView extends View {
    private Context context;

    public LoadingCircleView(Context context) {
        super(context);
    }

    public LoadingCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    //View默认最小宽度
    private static final int DEFAULT_MIN_WIDTH = 400;
    //圆环颜色
    private int[] doughnutColors = new int[]{Color.parseColor("#afafaf"),
            Color.parseColor("#00000000")};

    private int width;
    private int height;
    private Paint paint = new Paint();


    private void resetParams() {
        width = getWidth();
        height = getHeight();
    }

    private void initPaint() {
        paint.reset();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        resetParams();
        //画背景白色圆环
        initPaint();
        float doughnutWidth = DensityUtilsKt.dip2px(context, 2);
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0 + doughnutWidth, 0 + doughnutWidth,
                width - doughnutWidth, height - doughnutWidth);
        //画彩色圆环
        initPaint();
        paint.setStrokeWidth(doughnutWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setShader(new SweepGradient(width / 2, height / 2, doughnutColors, null));
        canvas.drawArc(rectF, 0, 360, false, paint);
    }

    /**
     * 当布局为wrap_content时设置默认长宽
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    private int measure(int origin) {
        int result = DEFAULT_MIN_WIDTH;
        int specMode = MeasureSpec.getMode(origin);
        int specSize = MeasureSpec.getSize(origin);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
}
