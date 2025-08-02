package com.goutham.javautils;

public class MathClassExamples {
    public static void main(String[] args) {
        // 1. abs() – Distance calculation (always positive)
        int x = -50;
        System.out.println("Absolute value of distance: " + Math.abs(x));

        // 2. max() and min() – Compare scores in a game
        int score1 = 85, score2 = 92;
        System.out.println("Highest score: " + Math.max(score1, score2));
        System.out.println("Lowest score: " + Math.min(score1, score2));

        // 3. sqrt() – Calculate diagonal of a rectangle (Pythagoras)
        double width = 5.0, height = 12.0;
        double diagonal = Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
        System.out.println("Diagonal length: " + diagonal);

        // 4. pow() – Compound interest calculation
        double principal = 10000, rate = 0.05, years = 10;
        double amount = principal * Math.pow((1 + rate), years);
        System.out.println("Compound Interest after 10 years: " + amount);

        // 5. floor(), ceil(), round() – Price rounding
        double price = 99.75;
        System.out.println("Price floored: " + Math.floor(price));
        System.out.println("Price ceiled: " + Math.ceil(price));
        System.out.println("Price rounded: " + Math.round(price));

        // 6. random() – Dice roll simulation
        int dice = 1 + (int)(Math.random() * 6);
        System.out.println("Dice rolled: " + dice);

        // 7. toRadians() and toDegrees() – Angle conversion for graphics
        double angleDeg = 90;
        double angleRad = Math.toRadians(angleDeg);
        System.out.println("90 degrees in radians: " + angleRad);
        System.out.println("Radians back to degrees: " + Math.toDegrees(angleRad));

        // 8. sin(), cos(), tan() – Calculate position of a point in a circle (game physics)
        double radius = 10;
        double xPos = radius * Math.cos(angleRad);
        double yPos = radius * Math.sin(angleRad);
        System.out.println("Point on circle (x,y): (" + xPos + ", " + yPos + ")");

        // 9. log() – Natural log (used in scientific computation)
        double value = 100;
        System.out.println("Natural log of 100: " + Math.log(value));
        System.out.println("Log base 10 of 100: " + Math.log10(value));

        // 10. exp() – Exponential growth (population growth prediction)
        double time = 2;
        System.out.println("e^2 = " + Math.exp(time));

        // 11. cbrt() – Cube root (engineering calculations)
        double volume = 27;
        System.out.println("Cube root of 27: " + Math.cbrt(volume));

        // 12. hypot() – Shorter way for diagonal (Pythagoras)
        System.out.println("Hypotenuse (width=5, height=12): " + Math.hypot(width, height));

        // 13. signum() – Determine direction (e.g., robot movement)
        System.out.println("Sign of -15: " + Math.signum(-15));
        System.out.println("Sign of 0: " + Math.signum(0));
        System.out.println("Sign of 20: " + Math.signum(20));

        // 14. IEEEremainder() – Precise floating-point remainder
        System.out.println("IEEE remainder of 10/3: " + Math.IEEEremainder(10, 3));

        // 15. ulp() – Machine precision (floating-point accuracy)
        System.out.println("ULP of 10.0: " + Math.ulp(10.0));

        // 16. nextAfter() – Find next floating-point value toward a direction
        System.out.println("Next after 1.0 towards 2.0: " + Math.nextAfter(1.0, 2.0));

        // 17. nextUp() and nextDown() – For precise range generation
        System.out.println("Next up from 1.0: " + Math.nextUp(1.0));
        System.out.println("Next down from 1.0: " + Math.nextDown(1.0));

        // 18. decrementExact() and incrementExact() – Overflow-safe increments
        int a = Integer.MAX_VALUE;
        try {
            System.out.println("IncrementExact on MAX_VALUE: " + Math.incrementExact(a));
        } catch (ArithmeticException e) {
            System.out.println("Overflow detected using incrementExact!");
        }

        // 19. addExact(), subtractExact(), multiplyExact() – Safe arithmetic
        int num1 = 2000000000, num2 = 1000000000;
        try {
            System.out.println("Safe addition: " + Math.addExact(num1, num2));
        } catch (ArithmeticException e) {
            System.out.println("Overflow detected using addExact!");
        }

        // 20. fma() – Floating-point multiply-add for financial precision
        double fmaResult = Math.fma(10.0, 20.0, 30.0); // 10*20 + 30
        System.out.println("FMA result: " + fmaResult);
    }
}

