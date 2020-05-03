package com.hanjie.multichiocedialog;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


public class AlphabetView extends View  {

    private OnClickListener click=null;
    private ObjectAnimator animator;

    TextView text;
    public static String [] alpha=new String[]{"#","A","B","C","D","E","F","J","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    int index=-1;
    public AlphabetView(Context context) {
        super(context);
    }
    public AlphabetView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int max_width=this.getWidth();
        int max_height=this.getHeight();
        float x=event.getX();
        float y=event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                break;
            default:
                index=(int)(y/(max_height/27));
                click.onClick(alpha[index]);
                break;
        }


        return true;
    }
    public void setOnClickListener(OnClickListener l) {
        click=l;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int max_width=this.getWidth();
        int max_height=this.getHeight();
        canvas.drawColor(Color.WHITE);
        Paint paint=new Paint();
        Paint text_paint=new Paint();
        Paint textAlet_paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        text_paint.setStyle(Paint.Style.FILL);
        textAlet_paint.setStyle(Paint.Style.FILL);
        textAlet_paint.setColor(Color.GRAY);
        textAlet_paint.setTextSize(40);
        RectF rectF=new RectF(max_width-30,5,max_width-5,max_height-5);
        paint.setColor(Color.DKGRAY);
        text_paint.setColor(Color.DKGRAY);
        canvas.drawRoundRect(rectF,15,15,paint);
        text_paint.setTextSize(20);
        for(int i=0;i<27;i++){
            canvas.drawText(alpha[i],max_width-25,(max_height/27)*i+(max_height/27)-10,text_paint);
        }
    }
    public interface OnClickListener{
        void onClick(String alpha);
    }
    public void setText(TextView text){
        this.text=text;
    }
}
