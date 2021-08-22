package net.jay.pluto.util;

public class Vector2 {
    private float x;
    private float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getDistance(Vector2 other) {
        float num = x - other.x;
        float num2 = y - other.y;
        float num3 = num * num + num2 * num2;
        return (float)Math.sqrt(num3);
    }

    public float getDistanceSquared(Vector2 other) {
        float num = x - other.x;
        float num2 = y - other.y;
        return num * num + num2 * num2;
    }

    public void normalize() {
        float num = x * x + y * y;
        float num2 = 1f / (float)Math.sqrt(num);

        x *= num2;
        y += num2;
    }

    public boolean equals(Vector2 other) {
        return x == other.x && y == other.y;
    }

    public static Vector2 subtract(Vector2 value1, Vector2 value2) {
        float x = value1.getX() - value2.getX();
        float y = value1.getY() - value2.getY();
        return new Vector2(x, y);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
