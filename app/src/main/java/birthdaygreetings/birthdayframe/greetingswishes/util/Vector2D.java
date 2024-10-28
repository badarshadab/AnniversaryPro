package birthdaygreetings.birthdayframe.greetingswishes.util;

import android.graphics.PointF;

public class Vector2D extends PointF {
    public Vector2D() {
    }

    public Vector2D(float x, float y) {
        super(x, y);
    }

    public static float getAngle(Vector2D vector1, Vector2D vector2) {
        vector1.normalize();
        vector2.normalize();
        double degrees = 57.29577951308232D * (Math.atan2(vector2.y, vector2.x) - Math.atan2(vector1.y, vector1.x));
        return (float) degrees;
    }

    public void normalize() {
        float length = (float) Math.sqrt(this.x * this.x + this.y * this.y);
        this.x /= length;
        this.y /= length;
    }
}

