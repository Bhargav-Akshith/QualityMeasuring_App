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

    enum Unit {
        FEET(12.0),
        INCH(1.0);

        private final double toInch;

        Unit(double toInch) {
            this.toInch = toInch;
        }

        public double toBase(double value) {
            return value * toInch;
        }
    }

    public static class Quantity {
        private final double value;
        private final Unit unit;

        public Quantity(double value, Unit unit) {
            this.value = value;
            this.unit = unit;
        }

        private double toInches() {
            return unit.toBase(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Quantity that = (Quantity) obj;
            return Double.compare(this.toInches(), that.toInches()) == 0;
        }
    }

    public static boolean areFeetEqual(double v1, double v2) {
        return new Quantity(v1, Unit.FEET).equals(new Quantity(v2, Unit.FEET));
    }

    public static boolean areInchesEqual(double v1, double v2) {
        return new Quantity(v1, Unit.INCH).equals(new Quantity(v2, Unit.INCH));
    }

    public static boolean areFeetAndInchesEqual(double feet, double inches) {
        return new Quantity(feet, Unit.FEET).equals(new Quantity(inches, Unit.INCH));
    }

    public static void main(String[] args) {
        System.out.println("Feet Equal (" + areFeetEqual(1.0, 1.0) + ")");
        System.out.println("Inches Equal (" + areInchesEqual(1.0, 1.0) + ")");
        System.out.println("Feet-Inches Equal (" + areFeetAndInchesEqual(1.0, 12.0) + ")");
    }
}