package top.jplayer.baseprolibrary.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Obl on 2017/2/27.
 * 字典排序
 */

public class QuckieIndexView extends View {
    /**
     * 字母单元格宽度
     */
    private int cellWidth;
    /**
     * 字母集合
     */
    private static final String[] LETTERS = new String[]{
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z", "#"
    };
    //字母单元格高度
    private float cellHeight;
    /**
     * 按下的点所对应的单元格位置
     */
    private int cellIndex = -1;
    /**
     * 抬起点记录的所在单元格位置
     */
    private int finalCellIndex = -1;
    private OnIndexChangeListener monOnIndexChangeListener;

    public QuckieIndexView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QuckieIndexView(Context context) {
        this(context, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        cellWidth = getMeasuredWidth();
        cellHeight = getHeight() * 1.0f / LETTERS.length * 1.0f;
        // System.out.println(cellHeight + "----" + cellWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画笔抗锯齿
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设定画笔大小，需要注意的是设定的时候，不能写死，因为不同的屏幕大小会有不同的效果显示
        paint.setTextSize(cellHeight * 0.8f);
        //设定文本作画点
        paint.setTextAlign(Paint.Align.LEFT);
        //设定画笔的粗细--设定为粗体
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        for (int i = 0; i < LETTERS.length; i++) {
            //设定画笔颜色
            paint.setColor(Color.GRAY);
            if (finalCellIndex == i) {//设定抬起点的颜色
                paint.setColor(Color.BLACK);
            }
            //设定要画的矩形宽度
            Rect bounds = new Rect();
            //得到矩形的宽高
            paint.getTextBounds(LETTERS[i], 0, LETTERS[i].length(), bounds);
            //绘制字母在布局上，同是避免使用/2来实现一般，，通常使用*0.5f来实现，目的为了避免int转换出现的误差；
            canvas.drawText(LETTERS[i], cellWidth * 0.5f - bounds.width() * 0.5f, cellHeight * 0.5f + bounds.height() * 0.5f + i * cellHeight, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //操作一样，直接不加break穿透
            case MotionEvent.ACTION_MOVE:
                //记录当前按下的点的点
                int press_dot = (int) event.getY();
                //按下点所在的单元格位置
                cellIndex = (int) (press_dot / cellHeight);
                if (press_dot < 0) {//超出上边界
                    cellIndex = 0;
                } else if (press_dot > getHeight()) {//超出下边界
                    cellIndex = LETTERS.length - 1;
                }
                if (monOnIndexChangeListener != null) {
                    //返回监听到的数值
                    monOnIndexChangeListener.OnIndexChange(cellIndex, LETTERS[cellIndex]);
                }
                break;
            case MotionEvent.ACTION_UP:
                //抬起点的所在单元格位置
                int up_dot = (int) event.getY();
                if (up_dot < 0) {//超出上边界
                    finalCellIndex = 0;
                } else if (up_dot > getHeight()) {//超出下边界
                    finalCellIndex = LETTERS.length - 1;
                }
                finalCellIndex = (int) (up_dot / cellHeight);
                invalidate();//通知重画，画出最新点
                break;
        }
        return true;
    }

    /**
     * 创建监听
     */
    public void setOnIndexChangeListener(OnIndexChangeListener listener) {
        monOnIndexChangeListener = listener;
    }

    /**
     * 监听回调
     */
    public interface OnIndexChangeListener {
        void OnIndexChange(int cellIndex, String showIndex);
    }
}
