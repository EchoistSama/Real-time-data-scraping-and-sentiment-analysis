package com.example.senttrial;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class BarGraphBar extends View {

    private float percentage;
    private int color;
    private String label;

    public BarGraphBar(Context context, float percentage, int color, String label) {
        super(context);
        this.percentage = percentage;
        this.color = color;
        this.label = label;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int barWidth = getWidth();
        int barHeight = (int) (getHeight() * (percentage / 100));

        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);

        // Draw bar
        canvas.drawRect(0, getHeight() - barHeight, barWidth, getHeight(), paint);

        // Draw percentage text
        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(24);
        textPaint.setTextAlign(Paint.Align.CENTER);

        canvas.drawText(String.format("%.1f%%", percentage), barWidth / 2, getHeight() - barHeight - 10, textPaint);

        // Draw label text
        Paint labelPaint = new Paint();
        labelPaint.setColor(Color.BLACK);
        labelPaint.setTextSize(30);
        labelPaint.setTextAlign(Paint.Align.CENTER);

        canvas.drawText(label, barWidth / 2, getHeight() - barHeight - 40, labelPaint);
    }
}
