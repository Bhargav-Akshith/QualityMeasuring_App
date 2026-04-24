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
        INCH(1.0 / 12.0);

        private final double toFeet;

        LengthUnit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double toBase(double value) {
            return value * toFeet;
        }
    }

    public static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toBase(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Quantity that = (Quantity) obj;
            return Double.compare(this.toFeet(), that.toFeet()) == 0;
        }
    }

    public static void main(String[] args) {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);
        Quantity q3 = new Quantity(1.0, LengthUnit.INCH);

        System.out.println("Feet-Inch Equal (" + q1.equals(q2) + ")");
        System.out.println("Inch-Inch Equal (" + q3.equals(new Quantity(1.0, LengthUnit.INCH)) + ")");
        System.out.println("Feet-Feet Equal (" + q1.equals(new Quantity(1.0, LengthUnit.FEET)) + ")");
    }
}