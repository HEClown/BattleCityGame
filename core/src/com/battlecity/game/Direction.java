package com.battlecity.game;

// Перечисление направлений движения танков
public enum Direction {

    UP(0, 1, 0.0f), DOWN(0, -1, 180.0f), LEFT(-1, 0, 90.0f), RIGHT(1, 0, 270.0f);

    private int velocityX;
    private int velocityY;
    private float angleTank;

    Direction(int velocityX, int velocityY, float angleTank) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.angleTank = angleTank;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public float getAngleTank() {
        return angleTank;
    }

}
