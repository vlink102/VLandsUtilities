package net.vlands.util.math;

@SuppressWarnings("unused")
public class YawAndPitch {
    public static final class Yaw {
        public static final int NORTH = -180;
        public static final int NORTH_EAST = -135;
        public static final int EAST = -90;
        public static final int SOUTH_EAST = -45;
        public static final int SOUTH = 0;
        public static final int SOUTH_WEST = 45;
        public static final int WEST = 90;
        public static final int NORTH_WEST = 135;
    }

    public static final class Pitch {
        public static final int UP = -90;
        public static final int DOWN = 90;
        public static final int STRAIGHT = 0;
    }
}
