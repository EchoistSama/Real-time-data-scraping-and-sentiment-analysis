package com.example.senttrial;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class PieChartView extends View {

    private float[] data;
    private String[] labels;
    private int[] colors;

    public PieChartView(Context context) {
        super(context);
    }

    public PieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setData(float[] data, String[] labels, int[] colors) {
        this.data = data;
        this.labels = labels;
        this.colors = colors;
        invalidate(); // Redraw the view with new data
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (data == null || labels == null || colors == null) {
            return;
        }

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(centerX, centerY);
        RectF rectF = new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);

        float startAngle = 0;

        for (int i = 0; i < data.length; i++) {
            paint.setColor(colors[i]);
            float sweepAngle = 360 * (data[i] / 100);
            canvas.drawArc(rectF, startAngle, sweepAngle, true, paint);

            // Draw percentage text
            Paint textPaint = new Paint();
            textPaint.setColor(Color.BLACK);
            textPaint.setTextSize(24);
            textPaint.setTextAlign(Paint.Align.CENTER);
            float textAngle = startAngle + sweepAngle / 2;
            float x = (float) (centerX + radius / 2 * Math.cos(Math.toRadians(textAngle)));
            float y = (float) (centerY + radius / 2 * Math.sin(Math.toRadians(textAngle)));
            canvas.drawText(String.format("%.1f%%", data[i]), x, y, textPaint);

            // Draw label text
            Paint labelPaint = new Paint();
            labelPaint.setColor(Color.BLACK);
            labelPaint.setTextSize(30);
            labelPaint.setTextAlign(Paint.Align.CENTER);
            float labelAngle = startAngle + sweepAngle / 2;
            float labelX = (float) (centerX + radius * 0.7 * Math.cos(Math.toRadians(labelAngle)));
            float labelY = (float) (centerY + radius * 0.7 * Math.sin(Math.toRadians(labelAngle)));
            canvas.drawText(labels[i], labelX, labelY, labelPaint);

            startAngle += sweepAngle;
        }
    }
}
