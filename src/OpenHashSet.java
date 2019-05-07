public class OpenHashSet extends SimpleHashSet{
private StringLinkedListWrapper[] hashTable;


    public OpenHashSet(float upperLoadFactor,
                       float lowerLoadFactor){
        /**Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
         Parameters:
         upperLoadFactor - The upper load factor of the hash table.
         lowerLoadFactor - The lower load factor of the hash table.*/
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
        this.capacity = INITIAL_CAPACITY;
        this.mySize = 0;
        this.hashTable = new StringLinkedListWrapper[INITIAL_CAPACITY];
    }

    public OpenHashSet(){
        /**A default constructor. Constructs a new, empty table with default initial capacity (16), upper load factor (0.75) and lower load factor (0.25).
         *
         */
        this.higherCapacity = DEFAULT_HIGHER_CAPACITY;
        this.lowerCapacity = DEFAULT_LOWER_CAPACITY;
        this.capacity = INITIAL_CAPACITY;
        this.mySize = 0;
        this.hashTable = new StringLinkedListWrapper[INITIAL_CAPACITY];
    }

    public OpenHashSet(java.lang.String[] data){
     /** Data constructor - builds the hash set by adding the elements one by one.
      *  Duplicate values should be ignored. The new table has the default values of initial capacity (16), upper load factor (0.75),
      *  and lower load factor (0.25).
      Parameters:
      data - Values to add to the set.*/
        this.higherCapacity = DEFAULT_HIGHER_CAPACITY;
        this.lowerCapacity = DEFAULT_LOWER_CAPACITY;
        this.capacity = INITIAL_CAPACITY;
        this.mySize = 0;
        this.hashTable = new StringLinkedListWrapper[INITIAL_CAPACITY];
        for (int i=0; i< data.length ; i++){ {
            this.add(data[i]);}
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

        return index & (this.capacity-1);
            }

    public void reHashingIncrease(){

        float currentLoad = (this.size() + 1 )/this.capacity();

        if (currentLoad > this.upperLoadFactor){

            /**copy all the element in the old set*/

            String[] data = new String[this.mySize];

            int  counter = 0 ;

            for (int i=0; i< hashTable.length; i++){

                if (this.hashTable[i].size()!=0){


                    for (int j=0 ;j<this.hashTable[i].size();j++){

                    data[counter]=this.hashTable[i].get(j);
                    counter++;

                }
            }}

            /**increase our set multiply 2*/

            this.capacity =  this.capacity*2;

            /**creating a new set with double size*/

            this.hashTable = new StringLinkedListWrapper[this.capacity];

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

        /**check if the wanted value is already exist in the array*/

        if (this.contains(newValue)){
            return false;
        }
        /**check if we need to increase the array first*/
        this.reHashingIncrease();
        this.addElement(newValue);
        return true;
    }

    public void addElement(java.lang.String newValue){

        /**add single element to the hash table */

        int hashIdx= this.clamp(newValue.hashCode());
        this.hashTable[hashIdx].add(newValue);
        this.mySize++;
    }

    public boolean contains(java.lang.String searchVal){
        /**
        Description copied from interface: supplied.SimpleSet
        Look for a specified value in the set.
        Parameters:
        searchVal - Value to search for
        Returns:
        True iff searchVal is found in the set*/
        for (int i=0 ; i<this.hashTable.length; i++) {
            if (this.hashTable[i].size() != 0 & this.hashTable[i].contain(searchVal)) {
                return true;
            }
        }
            return false;}

    public void reHashingDecrease(){

        float currentLoad = (this.size() - 1 )/this.capacity();

        if (this.size()>1 & currentLoad < this.lowerLoadFactor){

            /**copy all the element in the old set*/

            String[] data = new String[this.mySize];

            int  counter = 0 ;

            for (int i=0; i< hashTable.length; i++){

                if (this.hashTable[i].size()!=0){


                    for (int j=0 ;j<this.hashTable[i].size();j++){

                        data[counter]=this.hashTable[i].get(j);
                        counter++;

                    }
                }}

            /**increase our set multiply 2*/

            this.capacity =  this.capacity/2;

            /**creating a new set with half size*/

            this.hashTable = new StringLinkedListWrapper[this.capacity];

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

        /**if the element is not in the hash table- return false*/
        if (!this.contains(toDelete)){
            return false;
        }

        /**resize the hash table in case we should do it*/
        this.reHashingDecrease();

        /**delete the requested element*/
        this.mySize = this.mySize - 1 ;
        int idxToDelete = this.clamp(toDelete.hashCode());
        this.hashTable[idxToDelete].remove(toDelete);
        return true;

    }


    public int size(){
    /** Returns:
     The number of elements currently in the set*/
    return this.mySize;

    }
    public int capacity(){
        /**   Specified by:
         capacity in class SimpleHashSet
         Returns:
         The current capacity (number of cells) of the table.*/
            return this.capacity;
         }
}
