/**
 * QuantityMeasurementApp - UC1: Feet measurement equality
 *
 * This class is responsible for checking the equality of two numerical values
 * measured in feet in the Quantity Measurement Application.
 */

/**
 * Override equals() method to compare two Feet objects based on their value
 *
 * <p>Important Checks:</p>
 * 1. Reference Check: If both references point to the same object, return true
 * 2. Null Check: If the compared object is null, return false
 * 3. Type Check: If the compared object is not of type Feet, return false
 * 4. Value Comparison: Use Double.compare() to compare the double values for equality
 *
 * @param obj The object to compare with
 * @return true if both Feet objects have the same value, false otherwise
 */

// Main method to demonstrate Feet equality check
public class QuantityMeasurementApp {

    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.393701 / 12.0);

        private final double toFeet;

        LengthUnit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double toBase(double value) {
            return value * toFeet;
        }

        public double fromBase(double baseValue) {
            return baseValue / toFeet;
        }
    }

    public static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null || !Double.isFinite(value)) {
                throw new IllegalArgumentException();
            }
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toBase(value);
        }

        public Quantity convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException();
            }
            double base = this.toFeet();
            double converted = targetUnit.fromBase(base);
            return new Quantity(converted, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Quantity that = (Quantity) obj;
            return Double.compare(this.toFeet(), that.toFeet()) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (source == null || target == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException();
        }
        double base = source.toBase(value);
        return target.fromBase(base);
    }

    public static double demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {
        return convert(value, from, to);
    }

    public static Quantity demonstrateLengthConversion(Quantity q, LengthUnit to) {
        return q.convertTo(to);
    }

    public static boolean demonstrateLengthEquality(Quantity q1, Quantity q2) {
        return q1.equals(q2);
    }

    public static boolean demonstrateLengthComparison(double v1, LengthUnit u1, double v2, LengthUnit u2) {
        return new Quantity(v1, u1).equals(new Quantity(v2, u2));
    }

    public static void main(String[] args) {
        System.out.println(convert(1.0, LengthUnit.FEET, LengthUnit.INCH));
        System.out.println(convert(3.0, LengthUnit.YARDS, LengthUnit.FEET));
        System.out.println(convert(36.0, LengthUnit.INCH, LengthUnit.YARDS));
        System.out.println(convert(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCH));
        System.out.println(convert(0.0, LengthUnit.FEET, LengthUnit.INCH));
    }
}