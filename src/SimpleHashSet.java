public abstract class SimpleHashSet extends java.lang.Object  implements SimpleSet {

    protected final static float DEFAULT_HIGHER_CAPACITY = 0.75f;
    /**
     Describes the higer load factor of a newly created hash set*/

    protected  final static float DEFAULT_LOWER_CAPACITY = 0.25f;
    /**Describes the lower load factor of a newly created hash set*/

    protected final static int INITIAL_CAPACITY = 16;
    /**Describes the capacity of a newly created hash set*/

    protected  float upperLoadFactor;
    /**the upper load factor before rehashing*/

    protected float lowerLoadFactor;
    /**the lower load factor before rehashing*/
    protected int capacity;

    protected float higherCapacity;

    protected float lowerCapacity;

    protected int mySize;

    public SimpleHashSet() {
        /**
         Constructs a new hash set with the default capacities given in DEFAULT_LOWER_CAPACITY and DEFAULT_HIGHER_CAPACITY*/
        this.higherCapacity = DEFAULT_HIGHER_CAPACITY;
        this.lowerCapacity = DEFAULT_LOWER_CAPACITY;
        this.capacity = INITIAL_CAPACITY;
    }

    public SimpleHashSet(float upperLoadFactor, float lowerLoadFactor){
        /**Constructs a new hash set with capacity INITIAL_CAPACITY*/
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
        this.capacity = INITIAL_CAPACITY;
    }

    public abstract int capacity();
    /**Returns:
     The current capacity (number of cells) of the table.*/

    protected abstract int clamp(int index);
        /**Clamps hashing indices to fit within the current table capacity (see the exercise description for details)
         Parameters:
         index - the index before clamping
         Returns:
         an index properly clamped*/

    protected float getLowerLoadFactor(){
        /**Returns:
         The lower load factor of the table.*/
        return lowerLoadFactor;
    }

    protected float getUpperLoadFactor(){
        /**Returns:
         The higher load factor of the table.*/
        return upperLoadFactor;
    }
}
