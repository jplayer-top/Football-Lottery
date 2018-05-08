package top.jplayer.baseprolibrary.utils;

import android.support.annotation.NonNull;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Obl on 2017/8/29.
 * com.ilanchuang.xiaoi.utils
 */

public class NumAnimUtil {
    //每秒刷新多少次
    private static final int COUNTPERS = 100;

    public static void startAnim(TextView textV, @NonNull float num,String correctMoney) {
        startAnim(textV, num, 500,correctMoney);
    }

    public static void startAnim(TextView textV, float num, long time,String correctMoney) {
        if (num == 0) {
            textV.setText(NumUtil.NumberFormat(num, 2));
            return;
        }

        Float[] nums = splitnum(num, (int) ((time / 1000f) * COUNTPERS));

        Counter counter = new Counter(textV, nums, time,correctMoney);

        textV.removeCallbacks(counter);
        textV.post(counter);
    }

    private static Float[] splitnum(float num, int count) {
        Random random = new Random();
        float numtemp = num;
        float sum = 0;
        LinkedList<Float> nums = new LinkedList<>();
        nums.add(0f);
        while (true) {
            float nextFloat = NumUtil.NumberFormatFloat(
                    (random.nextFloat() * num * 2f) / (float) count,
                    2);
            System.out.println("next:" + nextFloat);
            if (numtemp - nextFloat >= 0) {
                sum = NumUtil.NumberFormatFloat(sum + nextFloat, 2);
                nums.add(sum);
                numtemp -= nextFloat;
            } else {
                nums.add(num);
                return nums.toArray(new Float[0]);
            }
        }
    }

    static class Counter implements Runnable {

        private final TextView view;
        private Float[] nums;
        private long pertime;
        private int i = 0;
        private String correctMoney;

        Counter(TextView view, Float[] nums, long time, String correctMoney) {
            this.view = view;
            this.nums = nums;
            this.pertime = time / nums.length;
            this.correctMoney = correctMoney;
        }

        @Override
        public void run() {
            if (i > nums.length - 1) {
                view.removeCallbacks(Counter.this);
                view.setText(correctMoney);
                return;
            }
            view.setText(NumUtil.NumberFormat(nums[i++], 2));
            view.removeCallbacks(Counter.this);
            view.postDelayed(Counter.this, pertime);
        }
    }

    private static class NumUtil {
        /**
         * @param f 传入的float值，
         * @param m 传入的执行时间
         * @return
         */
        public static String NumberFormat(float f, int m) {
            return String.format("%." + m + "f", f);
        }

        public static float NumberFormatFloat(float f, int m) {
            String strfloat = NumberFormat(f, m);
            return Float.parseFloat(strfloat);
        }
    }
}
