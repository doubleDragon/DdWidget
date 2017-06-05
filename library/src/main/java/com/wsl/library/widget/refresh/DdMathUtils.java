package com.wsl.library.widget.refresh;

/**
 * Created by wsl on 17/4/18.
 */

class DdMathUtils {
    static int constrain(int amount, int low, int high) {
        return amount < low ? low : (amount > high ? high : amount);
    }

    static float constrain(float amount, float low, float high) {
        return amount < low ? low : (amount > high ? high : amount);
    }
}
