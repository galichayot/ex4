import java.util.LinkedList;

/**a wrapper class of linked list*/

public class StringLinkedListWrapper {
    private LinkedList<String> stringLinkedList;

    /**now we create a default constructor
     *
     */
    public StringLinkedListWrapper(LinkedList<String> stringLinkedList) {
        this.stringLinkedList = new LinkedList<String>();
    }

    /**this function add the  string to the linked list*/

    public void add(String value){

        this.stringLinkedList.add(value);

    }
    /**this function check if a specific string is found in the linked list*/

    public boolean contain(String value){

        return this.stringLinkedList.contains(value);
    }
    /**this function delete the requested value*/

    public void remove(String valueToDelete){

        this.stringLinkedList.remove(valueToDelete);

    }
    /**this function return the size of the linked list */

    public int size(){

        return this.stringLinkedList.size();

    }

    /** This method returns the element at the specified position in this list.*/

    public String get(int idx){

        return this.stringLinkedList.get(idx);
    }

}
