public class ClosedHashSet extends SimpleHashSet {
    String[] mySet;
    private static String DELETED ="this cell was deleted";


    public ClosedHashSet(float upperLoadFactor,
                         float lowerLoadFactor){
        /**    Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
         Parameters:
         upperLoadFactor - The upper load factor of the hash table.
         lowerLoadFactor - The lower load factor of the hash table.*/

        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
        this.capacity = INITIAL_CAPACITY;
        this.mySet = new String[16];
        this.mySize=0;
    }
    public ClosedHashSet(){

        /**A default constructor. Constructs a new, empty table with default initial capacity (16),
         *  upper load factor (0.75) and lower load factor (0.25).*/
        this.upperLoadFactor = DEFAULT_HIGHER_CAPACITY;
        this.lowerLoadFactor = DEFAULT_LOWER_CAPACITY;
        this.capacity = INITIAL_CAPACITY;
        this.mySet = new String[16];
        this.mySize=0;
    }

    public ClosedHashSet(java.lang.String[] data) {
        /**  Data constructor - builds the hash set by adding the elements one by one.
         *  Duplicate values should be ignored. The new table has the default values of initial capacity (16),
         *  upper load factor (0.75), and lower load factor (0.25).
         Parameters:
         data - Values to add to the set.*/
        this.upperLoadFactor = DEFAULT_HIGHER_CAPACITY;
        this.lowerLoadFactor = DEFAULT_LOWER_CAPACITY;
        this.capacity = INITIAL_CAPACITY;
        this.mySet = new String[16];
        this.mySize=0;
        if (data.length != 0) {
            for (int i = 0; i < data.length; i++) {
                {
                    this.add(data[i]);
                }
            }
        }
    }

    @Override
    protected  int clamp(int index){
        /**Clamps hashing indices to fit within the current table capacity (see the exercise description for details)
        Parameters:
        index - the index before clamping
        Returns:
        an index properly clamped
         if not exist such an idx it return -1*/

        for (int i=0; i<this.capacity()-1;i++){
            int result = (int)Math.pow(i, 2);
            int currIdx = (index + (i+result)/2)&(this.capacity()-1);

            /**we check if the cell is empty if it contains null or been deleted*/

            if (this.mySet[currIdx]==null ||this.mySet[currIdx]==DELETED){

                return currIdx;
            }
        }
        return -1;
    }

    public void reHashingIncrease(){

        float currentLoad = (float) (this.size() + 1 )/(this.capacity());

        if (currentLoad > this.upperLoadFactor){

            /**copy all the element in the old set*/

            String[] data = new String[this.mySize];

            int  counter = 0 ;

            for (int i=0; i< this.capacity(); i++){

                if (this.mySet[i]!=null){

                    data[counter] =this.mySet[i];
                    counter++;
                }
            }

            /**increase our set multiply 2*/

            this.capacity =  this.capacity*2;

            /**creating a new set with double size*/

            this.mySet = new String[this.capacity];

            /**adding all the object to the new set*/
            for (int i=0; i< data.length ; i++){ {
                this.addElement(data[i]);}
            }
        }

    }

    public boolean add(java.lang.String newValue){

        /** Description copied from interface: supplied.SimpleSet
         Add a specified element to the set if it's not already in it.
         Parameters:
         newValue - New value to add to the set
         Returns:
         False iff newValue already exists in the set*/

       if (this.contains(newValue)){

           return false;
       }
       /**check if we need to resize the array */

       this.reHashingIncrease();

       /**try to insert the value to the suite place*/

       int idxClamp =this.clamp(newValue.hashCode());

       if (idxClamp!=-1){

           this.mySet[idxClamp]=newValue;

           this.mySize++;

           return true;
       }
       return false;
    }
    private int search(java.lang.String searchVal){
        if (mySet!=null){
        for (int i = 0; i < this.capacity() ; i++) {
            if (mySet[i]!=null && mySet[i].equals(searchVal))
                return i;
        }
        }
        return -1;
    }

    public boolean contains(java.lang.String searchVal) {
        /**Description copied from interface: supplied.SimpleSet
         Look for a specified value in the set.
         Parameters:
         searchVal - Value to search for
         Returns:
         True iff searchVal is found in the set*/

        return this.search(searchVal) != -1;
    }

    public void addElement(java.lang.String newValue){

        /**add single element to the hash table */

        int hashIdx= this.clamp(newValue.hashCode());
        this.mySet[hashIdx]=newValue;

    }

    public void reHashingdecrease(){

        float currentLoad = (float)(this.size() - 1 )/this.capacity();

        if (currentLoad < this.lowerLoadFactor && this.size()!=1){

            /**copy all the element in the old set*/

            String[] data = new String[this.mySize];

            int  counter = 0 ;

            for (int i=0; i< this.capacity(); i++){

                if (this.mySet[i]!=null && this.mySet[i]!=DELETED){

                    data[counter] =this.mySet[i];
                    counter++;
                }
            }

            /**decrease our set multiply 2*/

            this.capacity =  this.capacity/2;

            /**creating a new set with half size*/

            this.mySet = new String[this.capacity];

            /**adding all the object to the new set*/
            for (int i=0; i< data.length ; i++){ {
                this.addElement(data[i]);}
            }
        }

    }

    public boolean delete(java.lang.String toDelete){
        /**Description copied from interface: supplied.SimpleSet
         Remove the input element from the set.
         Parameters:
         toDelete - Value to delete
         Returns:
         True iff toDelete is found and deleted*/

        /**check if the table contain the string*/

        if (!this.contains(toDelete)){
            return false;
        }

        /**check if we need to decrease the current table*/
        this.reHashingdecrease();

        /**delete the current string*/
        int curIdx = this.search(toDelete);
        this.mySet[curIdx] = DELETED;
        this.mySize = this.mySize-1;
        return true;
    }

    public int size(){

        /**Returns:
         The number of elements currently in the set*/

        return mySize;

    }

    public int capacity(){

        /**Specified by:
         capacity in class SimpleHashSet
         Returns:
         The current capacity (number of cells) of the table.*/

    return this.capacity;

    }


}
