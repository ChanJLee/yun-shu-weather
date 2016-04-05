package com.chan.circleprogress;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;


/**
 * Created by chan on 16/3/22.
 */
public class CircleProgress  extends View {

    private static final int SIZE_PROGRESS_STOKE = 4;
    private static final int SIZE_INNER_CIRCLE_PADDING = 15;
    private static final int DEFAULT_MAX = 100;
    private static final float ANGLE_ARC_START = -90.f;
    private static final int DURATION = 1000;
    private static final float MAX_OFFSET_Y = 1000;
    private static final float MAX_OFFSET_CHECK_Y = 1200;
    private static final int PADDING = 6;

    private int m_progressStoke;
    private Paint m_progressPaint;
    private ProgressCircleState m_currentState;
    private int m_innerCircleRadius;
    private int m_currentProgress = 0;
    private int m_max = DEFAULT_MAX;
    private RectF m_outerRectF;
    private Bitmap m_innerCircleBitmap;
    private Bitmap m_tempBitmap;
    private Paint m_maskPaint;
    private Paint m_checkPaint;
    private ValueAnimator m_doneAnimator;
    private float m_offsetY;
    private Paint m_circlePaint;
    private Canvas m_canvas;
    private Bitmap m_checkBitmap;
    private float m_offsetCheckY;
    private ValueAnimator m_checkAnimator;

    private int m_colorProgress;
    private int m_colorDone;

    public CircleProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircleProgress(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);
    }

    @SuppressWarnings("deprecation")
    private void init(Context context, AttributeSet attributeSet, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ProgressCircle, defStyleAttr, 0);
        try {
            if(typedArray != null) {
                m_progressStoke = (int) typedArray.getDimension(R.styleable.ProgressCircle_storkWidth,
                        SIZE_PROGRESS_STOKE);
                m_colorDone = typedArray.getColor(R.styleable.ProgressCircle_doneColor,
                        getResources().getColor(R.color.com_chan_progress_done));
                m_colorProgress = typedArray.getColor(R.styleable.ProgressCircle_progressColor,
                        getResources().getColor(R.color.com_chan_progress_stoke));
            }
        }finally {
            if(typedArray != null) {
                typedArray.recycle();
            }
        }

        setupProgressPaint();
        setupMaskPaint();
        setupCirclePaint();
        setupCheckPaint();
        setupAnimator();
        setupCheckBitmap();
        initState();
    }

    private void setupCheckBitmap() {
        m_checkBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_check_white_24dp);
    }

    private void setupCheckPaint() {
        m_checkPaint = new Paint();
    }

    @SuppressWarnings("deprecation")
    private void setupCirclePaint() {
        m_circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        m_circlePaint.setColor(m_colorDone);
        m_circlePaint.setStyle(Paint.Style.FILL);
    }

    private void setupMaskPaint() {
        m_maskPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        m_maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    private void setupAnimator() {
        m_doneAnimator = ValueAnimator.ofFloat(MAX_OFFSET_Y, 0);
        m_doneAnimator.setInterpolator(new DecelerateInterpolator());
        m_doneAnimator.setDuration(DURATION);
        m_doneAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                m_offsetY = (float) animation.getAnimatedValue();
            }
        });

        m_checkAnimator = ValueAnimator.ofFloat(MAX_OFFSET_CHECK_Y, 0);
        m_checkAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        m_checkAnimator.setDuration(DURATION);
        m_checkAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                m_offsetCheckY = (float) animation.getAnimatedValue();
            }
        });
    }

    private void initState() {
        setProgressingState();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //当view视图大小发生变化的时候 我们也需要同时变换相应的配置
        changeConfig(w, h);
    }

    /**
     * @param width
     * @param height
     */
    private void changeConfig(int width, int height) {

        final int radius =  Math.min(width, height) / 2 - SIZE_INNER_CIRCLE_PADDING;
        m_innerCircleRadius = radius - PADDING;

        if(m_innerCircleBitmap != null) {
            m_innerCircleBitmap.recycle();
        }

        m_outerRectF = new RectF(
                m_progressStoke,
                m_progressStoke,
                radius * 2 + m_progressStoke,
                radius * 2 + m_progressStoke
        );

        //src的圆
        m_innerCircleBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(m_innerCircleBitmap);
        canvas.drawCircle(m_outerRectF.centerX(), m_outerRectF.centerY(), m_innerCircleRadius, new Paint());

        resetCanvas();
    }

    /**
     * 重置画布
     */
    private void resetCanvas() {

        final int width = getWidth();
        final int height = getHeight();

        if(width <= 0 || height <= 0) return;

        //最后呈现的图像
        m_tempBitmap = Bitmap.createBitmap(getWidth() , getHeight(), Bitmap.Config.ARGB_8888);
        m_canvas = new Canvas(m_tempBitmap);
    }

    private void setProgressingState() {
        if(m_doneAnimator.isRunning()) {
            m_doneAnimator.cancel();
        }

        if(m_checkAnimator.isRunning()) {
            m_checkAnimator.cancel();
        }

        m_currentState = new ProgressingState();
    }

    private void setDoneState() {
        m_currentState = new DoneState();
        m_doneAnimator.start();
        m_checkAnimator.start();
    }

    @SuppressWarnings("deprecation")
    private void setupProgressPaint() {
        m_progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        m_progressPaint.setStyle(Paint.Style.STROKE);
        m_progressPaint.setStrokeWidth(m_progressStoke);
        m_progressPaint.setColor(m_colorProgress);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        m_currentState.draw(m_canvas);
        canvas.drawBitmap(m_tempBitmap, 0, 0, null);
    }

    public int getCurrentProgress() {
        return m_currentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        if(currentProgress >= m_max && !(m_currentState instanceof DoneState)) {
            m_currentProgress = m_max;
            setDoneState();
        } else if(currentProgress < m_max) {

            if(m_currentProgress > currentProgress) {
                resetCanvas();
            }

            m_currentProgress = currentProgress;
            if(!(m_currentState instanceof ProgressingState)) {
                setProgressingState();
            }
        }

        invalidate();
    }

    public int getMax() {
        return m_max;
    }

    public void setMax(int max) {
        m_max = max;
    }

    private interface ProgressCircleState {
        void draw(Canvas canvas);
    }

    private final class ProgressingState implements ProgressCircleState {

        @Override
        public void draw(Canvas canvas) {
            if(m_max <= 0) {
                return;
            }

            final float arcAngleEnd = 360.f * m_currentProgress / m_max;
            canvas.drawArc(m_outerRectF, ANGLE_ARC_START, arcAngleEnd, false, m_progressPaint);
        }
    }

    private final class DoneState implements ProgressCircleState {

        @Override
        public void draw(Canvas canvas) {

            //内部的圆 绿色 即将上升的
            canvas.drawCircle(m_outerRectF.centerX(),
                    m_outerRectF.centerY() + m_offsetY,
                    m_innerCircleRadius,
                    m_circlePaint);

            //对号
            canvas.drawBitmap(m_checkBitmap, m_outerRectF.centerX() - m_checkBitmap.getWidth() / 2,
                    m_outerRectF.centerY() + m_offsetCheckY - m_checkBitmap.getHeight() / 2, m_checkPaint);

            //画上圆  由于maskPaint的模式 它只会保留两者公共交集的部分
            canvas.drawBitmap(m_innerCircleBitmap, 0, 0, m_maskPaint);

            //外部的圆
            canvas.drawArc(m_outerRectF, 0, 360, false, m_progressPaint);
            if(m_offsetY > 0) {
                postInvalidateDelayed(16);
            }
        }
    }
}
