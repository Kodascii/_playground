package simplex.math;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

/**
 * @author Kodascii
 * @version 1.0
 * @since 1.0
 **/
public class Vector {

    public Vector(double... values)
    {
        this.values = values;
        this.dimension = values.length;
    }



    /**
     * Array containing the vector's values.
     **/
    private double values[];

    /**
     * Gets the values of the vector
     **/
    public double[] get()
    {
        return values;
    }

    /**
     * Gets the value of the vector, at a given index.
     **/
    public double get(int index)
    {
        return values[index];
    }

    /**
     * Sets the values of the vector.
     **/
    public void set(double... values)
    {
        this.values = values;
    }

    /**
     * Sets the value of the vector, at a given index.
     * @param index The position at which the value should be placed. If {@code index > dimension || index < 0}, the following
     * method will return {@link java.lang.ArrayIndexOutOfBoundsException}.
     **/
    public void set(int index, double value)
    {
        this.values[index] = value;
    }


    /**
     * The dimension of the vector.
     **/
    private int dimension;

    /**
     * @return {@link Vector#dimension}.
     **/
    public int getDimension()
    {
        return dimension;
    }



    /**
     * Value represents the "length" of the vector, or it can also represent the kind, the object belongs to.
     **/
    public double magnitude()
    {
        double magnitude = 0;
        for (double value : values)
            magnitude += value * value;
        return magnitude;
    }

    /**
     * For a given vector, the method returns the same instance, with the {@link Vector#magnitude()} being {@code 1}.
     * @param vector the vector to be normalized.
     **/
    public static Vector normalize(Vector vector)
    {
        double magnitude = vector.magnitude();
        for (int i = 0; i < vector.dimension; i++)
            vector.values[i] /= magnitude;
        return vector;
    }

    /**
     * The method {@link Vector#normalize(Vector)}, this.
     **/
    public Vector normalize()
    {
        return normalize(this);
    }


    /**
     * @return For a given array of Vectors, takes the vector with the greatest dimension, and sums each of the vectors components in
     *         their corresponding dimension.
     **/
    public static Vector add(Vector... vectors)
    {
        // Base case : P(2)
        if (vectors.length == 2)
        {
            int minDimension = Arrays.stream(vectors).min(Comparator.comparing(Vector::getDimension)).get().dimension;
            for (int i = 0; i < minDimension; i++)
                vectors[0].set(i, vectors[0].get(i) + vectors[1].get(i));
            return vectors[0];
        }

        // Recurrence : P(N)
        int maxDimension = Arrays.stream(vectors).max(Comparator.comparing(Vector::getDimension)).get().dimension;
        Vector maxVector = new Vector(new double[maxDimension]);
        Arrays.stream(vectors).forEach(vector -> add(maxVector, vector));
        return maxVector;
    }

    /**
     * Calculate the dot product of two given vectors.
     **/
    public static double dot(Vector vector1, Vector vector2)
    {
        if (vector1.dimension != vector2.dimension)
            return 0;

        double result = 0;
        for (int i = 0; i < vector1.dimension; i++)
            result += vector1.get(i) * vector2.get(i);
        return result;
    }

    /**
     * <p>Maps each value of the vector, by a list of rows that specifies the {@link XMath#clamp(double, double, double, double, double)}
     * parameters.</p>
     * <p><strong>Example :</strong></p>
     * <pre>{@code
     *      Vector vec = new Vector(10, 20);
     *      double[][] map = new double[][]
     *      {
     *          {0, 50, 0, 1},
     *          {0, 100, 0, 1}
     *      };
     *      Vector mappedVector = vec.map(map);
     *      System.out.println(mappedVector);
     * }</pre>
     * <p><strong>Output :</strong> {@code [0.2, 0.1]}</p>
     * <p><strong>Description :</strong> The example code maps the value {@code 10} from {@code [0, 50]} to the interval {@code [0, 1]}</p>
     **/
    public Vector map(double[][] map)
    {
        double[] mapped = new double[dimension];

        for (int i = 0; i < dimension; i++)
            mapped[i] = XMath.clamp(get(i), map[i][0], map[i][1], map[i][2], map[i][3]);
        return new Vector(mapped);
    }

    /**
     * Create a copy of the current vector, as a new instance variable.
     **/
    public Vector copy()
    {
        return new Vector(Arrays.copyOf(values, dimension));
    }

    /**
     * Display the vector in the form of {@code [1, -5, ..., 3]}.
     **/
    @Override
    public String toString()
    {
        return Arrays.toString(values);
    }
}
