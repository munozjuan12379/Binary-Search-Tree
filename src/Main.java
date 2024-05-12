// This program is meant to create a binary search tree using the array implementation
// and using methods  to resize, print, find a value, and determining if the tree is balanced
class BTreeArray{
    private String [] values;
    /**
     * Array values is resized to hold one additional level of nodes.
     */
    private void resize() {
        String[] temp = new String[values.length * 2 + 1];
        for (int i = 0; i < temp.length; i++)
            temp[i] = "";
        for (int i = 0; i < values.length; i++)
            temp[i] = values[i];
        values = temp;
    }
    /**
     * Instantiates values to size n and sets all array values
     * to default value of "" (null string).
     * @param n size of array
     */
    public BTreeArray(int n) {
        values = new String[n];
        for (int i = 0; i < values.length; i++)
            values[i] = "";
    }
    /**
     * Adds the parameter value to the array if it doesn't already appear
     * in the array.  If the value already exists, false is returned.  If the
     * value can be added to the array, it must be added in binary search
     * format and space must exist.  If there is no space left in the array,
     * the array must be resized by calling the private method resize and then the
     * parameter value should be added.
     *
     * @param s Value to be added to array
     * @return false is returned if the parameter already exists in the array
     * otherwise true is returned
     */
    public boolean addValue(String s) {
        if (values[0] == "") {
            values[0] = s;
            return true;
        }
        for (int i = 0; i < values.length; i++) {
            if (values[i] == s) {
                return false;
            }
        }
        boolean set = false;
        boolean oversize = false;
        int i = 0;
        int location = 0;
        while (!set && i < values.length) {
            if (s.compareTo(values[location]) > 0) {
                location = location * 2 + 2;
                i = location;
                if (i < values.length && values[location] == "") {
                    values[location] = s;
                    set = true;
                } else if (i >= values.length)
                    oversize = true;
            }
            else if (s.compareTo(values[location]) < 0) {
                location = location * 2 + 1;
                i = location;
                if (i < values.length && values[location] == "") {
                    values[location] = s;
                    set = true;
                } else if (i >= values.length)
                    oversize = true;
            }
            else if (values[location] == ""){
                values[location] = s;
                set = true;
            }
            if (oversize) {
                oversize = false;
                resize();
                i = 0;
                location = 0;
            }
        }
        return set;
    }
    /**
     * Prints the contents of the array in comma delimited inorder
     * traversal format.
     */
    public void print() {
        String[] temp = new String[values.length];
        for (int i = 0; i < temp.length; i++)
            temp[i] = values[i];
        String tempVal;
        for (int i = 0; i < temp.length-1; i++){
            for (int j = i + 1; j < temp.length; j++){
                if (temp[i].compareTo(temp[j]) > 0){
                    tempVal = temp[i];
                    temp[i] = temp[j];
                    temp[j] = tempVal;
                }
            }
        }
        for (int i = 0; i < temp.length; i++)
            if(temp[i] != "")
                System.out.print(temp[i] + ", ");
        System.out.println();
    }
    /**
     * Searches for the parameter value in the array using binary search tree approach.
     * @param s Search value
     * @return True is returned if the value is found, false otherwise.
     */
    public boolean findValue(String s) {
        if (values[0] == "")
            return false;
        int location = 0;
        boolean found = false;
        while (location <= values.length - 1){
            if (s == values[location]) {
                return true;
            }
            else if (s.compareTo(values[location]) < 0){
                location = location * 2 + 1;
            }
            else if (s.compareTo(values[location]) > 0){
                location = location * 2 + 2;
            }
        }
        return false;
    }
    public void basicPrint() {
       for (int i = 0; i < values.length; i++)
           System.out.println(values[i]);
    }
    /**
     * Determines if the tree is balanced.  A tree is balanced if each node's left
     * and right subtree heights differ by at most one.
     * @return True if balanced, false otherwise.
     */
    public boolean isBalanced() {
        int [] left = new int[values.length];
        int [] right = new int[values.length];
        int i = 0;
        int location = values.length - 1;
        int leftCount = 0;
        int rightCount = 0;
        boolean unbalanced = false;
        while (i < values.length && !unbalanced){
            for (int j = 0; j < left.length; i++){
                if (left[j] > right [j])
                    unbalanced = true;
                else if (right[j] > left[j])
                    unbalanced = true;
            }
            return true;
        }
        return false;
    }
}
public class Main {
    public static void main(String[] args) {
        // instantiates a as a new binary search tree with size 1 testing a tree with only the root
        BTreeArray a = new BTreeArray(1);

        // adds the string dog into the root of the tree since it is the only available place and returns true
        System.out.println(a.addValue("dog"));
        // by trying to add any word besides dog our tree will need to be resized one level
        // cat will then be added to index 1 in the array which is dogs left child and will return true
        System.out.println(a.addValue("cat"));
        // cab will need to be added to index 3 which means the array needs to be resized again
        // this is the left child of cat and will return true
        System.out.println(a.addValue("cab"));
        // cad will need to be added as the right child of cab which means the array must be resized
        // this is index 8 in the array and will then return true
        System.out.println(a.addValue("cad"));

        // testing the print method on the tree should print the entire tree in inorder comma delimited
        // since it is inorder the words will be sorted alphabetically
        a.print();

        // testing the isBalanced method should return false since this tree is not balanced
        // it has 3 levels on the left technically and none on the right since they are all filled with ""
        //a.isBalanced();

        // instantiates b as a new binary search tree with size 3 testing a tree with a root
        // and two slots for leaf nodes
        BTreeArray b = new BTreeArray(3);

        // testing addValue method
        // this should add carpet into the root which is index 0 and add dad into index 2
        // and able into index 1 and print out true for all values since there are no duplicates
        System.out.println(b.addValue("carpet"));
        System.out.println(b.addValue("dad"));
        System.out.println(b.addValue("able"));
        // this should print out false since carpet is already in the tree
        System.out.println(b.addValue("carpet"));
        // this will force the resize method to be called adding a new level to the binary tree
        // the word actual will then be added to index 4
        System.out.println(b.addValue("actual"));
        // this should make date the right child of dad at index 6
        System.out.println(b.addValue("date"));

        // testing findValue method on the root of the tree should return true
        System.out.println(b.findValue("carpet"));
        // testing findValue on the left child of the root should return true
        System.out.println(b.findValue("able"));
        // testing findValue on the right child of the root should also return true
        System.out.println(b.findValue("dad"));
        // testing findValue on any other word not in the tree should return false
        System.out.println(b.findValue("mom"));
        // slightly misspelled word will return false on findValue
        System.out.println(b.findValue("ablee"));

        // testing the print method on the tree should print the entire tree in inorder
        // since it is inorder the words will be sorted alphabetically
        b.print();

        //testing isBalanced on tree b should return true since the tree is balanced
        // with 2 levels on each side
        //b.isBalanced();
    }
}