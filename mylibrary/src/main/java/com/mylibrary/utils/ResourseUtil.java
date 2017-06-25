package com.mylibrary.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;
import android.widget.TextView;


import com.mylibrary.ui.BaseApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by kson on 2017/6/25.
 */

public class ResourseUtil {

    static Context ctx = AppUtils.getAppContext();

    public static String getString(int resId) {
        return ctx.getResources().getString(resId);
    }

    public static int getColor(int resId) {
        return ContextCompat.getColor(ctx, resId);
    }

    public static Drawable getDrawable(int resId) {
        return ContextCompat.getDrawable(ctx, resId);
    }

    public static String getFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(ctx.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int changeColorOpacity(int color, int opacity) {
        return Color.argb(opacity, Color.red(color), Color.green(color), Color.blue(color));

    }

    public static void setDrawableLeft(TextView textView, int resId) {
        setDrawableLeft(textView, getDrawable(resId));
    }

    public static void setDrawableRight(TextView textView, int resId) {
        setDrawableRight(textView, getDrawable(resId));
    }

    public static void setDrawableLeft(TextView textView, Drawable drawable) {
        Drawable[] drawables = textView.getCompoundDrawables();
        textView.setCompoundDrawablesWithIntrinsicBounds(drawable, drawables[1], drawables[2], drawables[3]);
    }

    public static void setDrawableRight(TextView textView, Drawable drawable) {
        Drawable[] drawables = textView.getCompoundDrawables();
        textView.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawable, drawables[3]);
    }


    public static void setBackgroundTintList(View view, ColorStateList colors) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setBackgroundTintList(colors);
        } else {
            view.setBackgroundDrawable(setTintList(view.getBackground(), colors, true));
        }
    }

    public static void setBackgroundTint(View view, int color) {
        view.setBackgroundDrawable(setTint(view.getBackground(), color));
    }

    public static Drawable setTint(Drawable drawable, int color) {
        return setTint(drawable, color, false);
    }

    public static Drawable setTint(Drawable drawable, int color, boolean mutate) {
        if (mutate) drawable = drawable.mutate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable.setTint(color);
        } else {
            drawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(drawable, color);
        }
        return drawable;
    }


    public static Drawable setTintList(Drawable drawable, ColorStateList colors) {
        return setTintList(drawable, colors, false);
    }

    public static Drawable setTintList(Drawable drawable, ColorStateList colors, boolean mutate) {
        if (mutate) drawable = drawable.mutate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable.setTintList(colors);
        } else {
            drawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTintList(drawable, colors);
        }
        return drawable;
    }

    public static class ColorGradientHelper {
        private int startColor = Color.WHITE;

        public int getEndColor() {
            return endColor;
        }

        public int getStartColor() {
            return startColor;
        }

        private int endColor = Color.BLACK;
        private float partition = 100;
        private float deltaAlpha = 0;
        private float deltaRed = 0;
        private float deltaGreen = 0;
        private float deltaBlue = 0;

        public ColorGradientHelper(int startColor, int endColor) {
            setColorSpan(startColor, endColor);
        }

        public ColorGradientHelper() {
        }

        private void init() {
            deltaAlpha = (Color.alpha(endColor) - Color.alpha(startColor)) / partition;
            deltaRed = (Color.red(endColor) - Color.red(startColor)) / partition;
            deltaGreen = (Color.green(endColor) - Color.green(startColor)) / partition;
            deltaBlue = (Color.blue(endColor) - Color.blue(startColor)) / partition;
        }


        /**
         * @param partition 总的渐变色段
         */
        public void setPartition(int partition) {
            this.partition = partition;
            init();
        }

        public int getColor(int progress) {
            int color = startColor;
            if (progress != 0) {
                color = Color.argb(Color.alpha(startColor) + (int) (deltaAlpha * progress),
                        Color.red(startColor) + (int) (deltaRed * progress),
                        Color.green(startColor) + (int) (deltaGreen * progress),
                        Color.blue(startColor) + (int) (deltaBlue * progress));
            }
            return color;
        }

        public void setColorSpan(int startColor, int endColor) {
            this.startColor = startColor;
            this.endColor = endColor;
            init();
        }

    }

}
