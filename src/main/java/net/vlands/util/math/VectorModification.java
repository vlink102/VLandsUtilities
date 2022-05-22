package net.vlands.util.math;


import org.bukkit.Location;
import org.bukkit.util.Vector;

@SuppressWarnings("unused")
public class VectorModification {

    /**
     * @param v Vector to be modified
     * @param angle Angle to rotate around
     * @return The modified vector
     */
    public static Vector rotateAroundAxisX(Vector v, double angle) {
        double y, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        y = v.getY() * cos - v.getZ() * sin;
        z = v.getY() * sin + v.getZ() * cos;
        return v.setY(y).setZ(z);
    }

    /**
     * @param v Vector to be modified
     * @param angle Angle to rotate around
     * @return The modified vector
     */
    public static Vector rotateAroundAxisY(Vector v, double angle) {
        double x, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = v.getX() * cos + v.getZ() * sin;
        z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }

    /**
     * @param v Vector to be modified
     * @return The inversed vector
     */
    public static Vector inverseVector(Vector v) {
        return v.multiply(-1);
    }

    /**
     * @param v Vector to be modified
     * @param angle Angle to rotate around
     * @return The modified vector
     */
    public static Vector rotateAroundAxisZ(Vector v, double angle) {
        double x, y, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = v.getX() * cos - v.getY() * sin;
        y = v.getX() * sin + v.getY() * cos;
        return v.setX(x).setY(y);
    }

    public static Vector rotateVector(Vector v, double angleX, double angleY, double angleZ) {
        /*
         * double x = v.getX(), y = v.getY(), z = v.getZ();
         * double cosX = Math.cos(angleX), sinX = Math.sin(angleX), cosY =
         * Math.cos(angleY), sinY = Math.sin(angleY), cosZ = Math.cos(angleZ),
         * sinZ = Math.sin(angleZ);
         * double nx, ny, nz;
         * nx = (x * cosY + z * sinY) * (x * cosZ - y * sinZ);
         * ny = (y * cosX - z * sinX) * (x * sinZ + y * cosZ);
         * nz = (y * sinX + z * cosX) * (-x * sinY + z * cosY);
         * return v.setX(nx).setY(ny).setZ(nz);
         */
        rotateAroundAxisX(v, angleX);
        rotateAroundAxisY(v, angleY);
        rotateAroundAxisZ(v, angleZ);
        return v;
    }

    /**
     * @param v Vector to be modified
     * @param location Location axis to rotate around
     * @return The rotated vector
     */
    public static Vector rotateVector(Vector v, Location location) {
        return rotateVector(v, location.getYaw(), location.getPitch());
    }

    /**
     * @param v Vector to be modified
     * @param yawDegrees Yaw degrees to rotate
     * @param pitchDegrees Pitch degrees to rotate
     * @return The rotated vector
     */
    public static Vector rotateVector(Vector v, float yawDegrees, float pitchDegrees) {
        double yaw = Math.toRadians(-1 * (yawDegrees + 90));
        double pitch = Math.toRadians(-pitchDegrees);

        double cosYaw = Math.cos(yaw);
        double cosPitch = Math.cos(pitch);
        double sinYaw = Math.sin(yaw);
        double sinPitch = Math.sin(pitch);

        double initialX, initialY, initialZ;
        double x, y, z;

        initialX = v.getX();
        initialY = v.getY();
        x = initialX * cosPitch - initialY * sinPitch;
        y = initialX * sinPitch + initialY * cosPitch;

        initialZ = v.getZ();
        initialX = x;
        z = initialZ * cosYaw - initialX * sinYaw;
        x = initialZ * sinYaw + initialX * cosYaw;

        return new Vector(x, y, z);
    }

    /**
     * @param vector Vector to be modified
     * @return The modified vector
     */
    public static double angleToXAxis(Vector vector) {
        return Math.atan2(vector.getX(), vector.getY());
    }
}
