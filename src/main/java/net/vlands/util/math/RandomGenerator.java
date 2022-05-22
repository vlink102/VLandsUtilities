package net.vlands.util.math;

import org.bukkit.Material;
import org.bukkit.util.Vector;

import java.util.Random;

@SuppressWarnings("unused")
public class RandomGenerator {
    public static final Random random = new Random(System.nanoTime());

    /**
     * @return A random vector in any direction
     */
    public static Vector getRandomVector() {
        double x, y, z;
        x = random.nextDouble() * 2 - 1;
        y = random.nextDouble() * 2 - 1;
        z = random.nextDouble() * 2 - 1;

        return new Vector(x, y, z).normalize();
    }

    /**
     * @return A random circular vector
     */
    public static Vector getRandomCircleVector() {
        double rnd, x, z;
        rnd = random.nextDouble() * 2 * Math.PI;
        x = Math.cos(rnd);
        z = Math.sin(rnd);

        return new Vector(x, 0, z);
    }

    /**
     * @param materials The list of materials
     * @return Random material in a material list
     */
    public static Material getRandomMaterial(Material[] materials) {
        return materials[random.nextInt(materials.length)];
    }

    /**
     * @return Get a random angle
     */
    public static double getRandomAngle() {
        return random.nextDouble() * 2 * Math.PI;
    }

    public static boolean checkProbability(double probability) {
        return probability >= 1 || random.nextDouble() < probability;
    }

    /**
     * Returns a random number between 0 (inclusive) and the specified value (inclusive).
     */
    static public int random(int range) {
        return random.nextInt(range + 1);
    }

    /**
     * Returns a random number between start (inclusive) and end (inclusive).
     */
    static public int random(int start, int end) {
        return start + random.nextInt(end - start + 1);
    }

    /**
     * Returns a random boolean value.
     */
    static public boolean randomBoolean() {
        return random.nextBoolean();
    }

    /**
     * Returns true if a random value between 0 and 1 is less than the specified value.
     */
    static public boolean randomBoolean(float chance) {
        return random() < chance;
    }

    /**
     * Returns random number between 0.0 (inclusive) and 1.0 (exclusive).
     */
    static public float random() {
        return random.nextFloat();
    }

    /**
     * Returns a random number between 0 (inclusive) and the specified value (exclusive).
     */
    static public float random(float range) {
        return random.nextFloat() * range;
    }

    /**
     * Returns a random number between start (inclusive) and end (exclusive).
     */
    static public float random(float start, float end) {
        return start + random.nextFloat() * (end - start);
    }
}
