package net.jay.pluto.entity;

import lombok.Getter;
import lombok.Setter;
import net.jay.pluto.util.Vector2;

/**
 * A basic entity, pretty much straight ported from Terraria
 * @author Jay
 */
public class Entity {
    @Getter
    @Setter
    protected boolean active;

    /** The entity's position */
    @Getter
    @Setter
    protected Vector2 position;
    /** The entity's velocity */
    @Getter
    @Setter
    protected Vector2 velocity;

    protected int direction = 1;

    protected int width;
    protected int height;

    public boolean wet;
    public boolean honeyWet;
    public boolean lavaWet;
    public byte wetCount;

    public float getDistance(Entity other) {
        return getDistance(other.position);
    }

    public float getDistanceSquared(Entity other) {
        return getDistanceSquared(other.position);
    }

    public float getAngleTo(Entity other) {
        return getAngleTo(other.position);
    }

    public float getAngleFrom(Entity other) {
        return getAngleFrom(other.position);
    }

    public Vector2 getDirectionTo(Entity other) {
        return getDirectionTo(other.position);
    }

    public Vector2 getDirectionFrom(Entity other) {
        return getDirectionFrom(other.position);
    }

    public boolean isWithinRange(Entity target, float range) {
        return isWithinRange(target.position, range);
    }

    public float getDistance(Vector2 other) {
        return getCenter().getDistance(other);
    }

    public float getDistanceSquared(Vector2 other) {
        return getCenter().getDistanceSquared(other);
    }

    public float getAngleTo(Vector2 destination) {
        Vector2 center = getCenter();
        return (float)Math.atan2(destination.getY() - center.getY(), destination.getX() - center.getX());
    }

    public float getAngleFrom(Vector2 source) {
        Vector2 center = getCenter();
        return (float)Math.atan2(center.getY() - source.getY(), center.getX() - source.getX());
    }

    public Vector2 getDirectionTo(Vector2 destination) {
        Vector2 vector = Vector2.subtract(destination, getCenter());
        vector.normalize();
        return vector;
    }

    public Vector2 getDirectionFrom(Vector2 source) {
        Vector2 vector = Vector2.subtract(getCenter(), source);
        vector.normalize();
        return vector;
    }

    public boolean isWithinRange(Vector2 target, float range) {
        return getCenter().getDistanceSquared(target) <= range * range;
    }

    public Vector2 getSize() {
        return new Vector2(width, height);
    }

    public Vector2 getCenter() {
        return new Vector2(position.getX() + (float)(width / 2), position.getY() + (float)(height / 2));
    }

    public Vector2 getLeft() {
        return new Vector2(position.getX(), position.getY() + (float)(height / 2));
    }

    public Vector2 getRight() {
        return new Vector2(position.getX() + (float)width, position.getY() + (float)(height / 2));
    }

    public Vector2 getTop() {
        return new Vector2(position.getX() + (float)(width / 2), position.getY());
    }

    public Vector2 getTopLeft() {
        return position;
    }

    public Vector2 getTopRight() {
        return new Vector2(position.getX() + (float)width, position.getY());
    }

    public Vector2 getBottom() {
        return new Vector2(position.getX() + (float)(width / 2), position.getY() + (float)height);
    }

    public Vector2 getBottomLeft() {
        return new Vector2(position.getX(), position.getY() + (float)height);
    }

    public Vector2 getBottomRight() {
        return new Vector2(position.getX() + (float)width, position.getY() + (float)height);
    }
}
