/**
 * This class represents a make-shift linklist class separate from java.util.LinkedList. This class is generic and can
 * hold any type of data. The class is singly linked, meaning that it can only be traversed in one direction. The class
 * has the ability to add, delete, search for, and modify nodes in the list. The class also has the ability to print the
 * contents of the list.
 *
 * @author Matthew Re - Clemson University - CPSC2150
 */
public class genericLL<T>
{
    /**
     * Private Class for creating the nodes that makeup the linkedlist
     */
    private class ListNode
    {
        private T data;
        private ListNode link;

        /**
         * Constructor for the ListNode class
         * @param aData data contained within the node
         * @param aLink the next node in the linked list
         */
        public ListNode(T aData, ListNode aLink)
        {
            data = aData;
            link = aLink;
        }
    }

    /**
     * There's 3 different pointers we need to use the list:
     * head, which always points to the first node in the list.
     * curr, which is the current 'active' node in the list. It's almost like an iterator for the list
     * prev, the previous node behind curr. Has a few niche uses in a singly linked list and if this were a doubly
     *      linked list, would be required.
     */
    private ListNode head;
    private ListNode curr;
    private ListNode prev;

    /**
     * Constructor for the genericLL. Sets all pointers to null because the list is empty.
     *
     * @pre none.
     *
     * @post head = null AND curr = null AND prev = null
     */
    public genericLL()
    {
        head = curr = prev = null;
    }

    /**
     * Gets the value that the curr pointer is pointing to.
     *
     * @return the value being currently being referenced by curr.
     *
     * @pre none
     *
     * @post self = #self
     *
     * Note: Does not move the curr iterator down the list.
     */
    public T getCurr()
    {
        if(curr != null)
            return curr.data;
        return null;
    }

    /**
     * Sets the value that the curr pointer is pointing to as the value passed in by the aData parameter.
     *
     * @param aData value to be set currently referenced node in the list, given it exists.
     *
     * @pre none
     *
     * @post [self = #self except the value within the node currently being referenced by curr is set to aData]
     *
     * Note: Does not move the curr iterator down the list.
     */
    public void setCurr(T aData)
    {
        if(curr!=null)
            curr.data = aData;
    }

    /**
     * Used to 'iterate' the curr pointer to the next item in the linked list. It is not possible to iterate 'off' the
     * list, and instead the function will simply do nothing.
     *
     * @pre |self| > 0
     *
     * @post [self = #self except the curr iterator has moved to the next node in the list, assuming one exists]
     */
    public void goToNext()
    {
        if(curr.link == null)//there is no next link
            return;
        prev = curr;
        curr = curr.link;
    }

    /**
     * Used to reset the curr iterator to the head. Since this is not a doubly linked list, we cannot go 'back up' the list
     * and must reset at the head when we need to go 'backwards'.
     *
     * @pre |self| > 0
     *
     * @post [self = #self except the curr iterator has reset back to the head of the list]
     *
     */
    public void resetCurr() //sets curr back to head
    {
        curr = head;
        prev = null;
    }

    /**
     * Adds a new node to the end of the list. If the list is currently empty, the curr iterator and the head will both
     * point to the new node.
     *
     * @param aData data to be added into the node that is being added to the list.
     *
     * @pre none
     *
     * @post |self| = |self|+1 AND self[0...|self|-1] = #self[0...|self|-1]
     *
     * Note: Does not move the curr iterator down the list.
     */
    public void insert(T aData)
    {
        ListNode newNode = new ListNode(aData, null);
        if(head==null)
        {
            head = newNode;
            curr = head;
            return;
        }
        ListNode temp = head;
        while(temp.link != null)
        {
            temp = temp.link;
        }
        temp.link = newNode;
    }

    /**
     * Deletes the node currently referenced by the curr pointer. Thanks to the garbage collector, we can delete the node
     * mid-list by effectively collapsing the previous and the next nodes in on the current node.
     *
     * @pre none
     *
     * @post |self| = |self|-1 AND [self = #self with the current node removed from the list.
     *
     * Note: the curr iterator will be moved to the previous node, should it exist.
     */
    public void deleteCurr()
    {
        if(curr == null)
            return;
        if(curr == head)
        {
            head = head.link;
            curr = head;
            return;  //--------------Forced Bug
        }
        prev.link = curr.link;
        curr = prev.link;
    }


    /**
     *prints the contents of the list in a single line.
     *
     * @pre none.
     *
     * @post self = #self
     *
     * Note: Does not move the curr iterator down the list.
     */
    public void print()
    {
        if(head==null)
            return;
        ListNode temp = head;
        while(temp != null)
        {
            System.out.print(temp.data + " ");
            temp = temp.link;
        }

        System.out.println("\n");
    }

    /**
     * returns true or false depending on whether a node containing aData exists in the list.
     *
     * @param aData value to search for in the list
     *
     * @return true if aData exists in the list, false OW.
     *
     * @pre none
     *
     * @post isContained = true IFF [aData exists in the list], false OW.
     *
     * Note: Does not move the curr iterator down the list.
     */
    public boolean isContained(T aData)//basically a search for the input data
    {
        return this.findNodeWith(aData) != null;
    }

    /**
     * private helper function for isContained. returns the node that contains aData, null if one D.N.E.
     *
     * @param aData value to search for in the list
     *
     * @return the ListNode containing aData, if one exists. Returns null if a node containing aData D.N.E.
     */
    private ListNode findNodeWith(T aData)
    {
        ListNode temp = head;
        while(temp != null)
        {
            if(temp.data == aData)
                return temp;
            temp = temp.link;
        }
        return null;
    }

    /**
     * moves the curr iterator to the node that contains aData, if one exists
     *
     * @param aData Moves the curr iterator to the node containing this value
     *
     * @pre none
     *
     * @post [self = #self except the curr iterator has been moved to the node containing aData. curr will not move
     *      such a node D.N.E.]
     */
    public void goToNodeWithData(T aData)
    {
        if(head == null)
            return;
        if(this.isContained(aData))
        {
            this.resetCurr();
            while(true) {
                if (!this.getCurr().equals(aData)) {
                    this.goToNext();
                } else {
                    return;
                }
            }
        }
    }

    /**
     * returns the length of the list
     *
     * @return the length of the list.
     *
     * @pre none
     *
     * @post length = |self|
     */
    public int length()
    {
        if(curr==null)
            return 0;//list is empty

        ListNode temp = head;
        int ret = 1;
        while(!(temp.link == null))//keep advancing links while we still some to in the list
        {
            ret++;
            temp = temp.link;
        }
        return ret;
    }

    /**
     * moves the curr iterator to the tail (the last node in the list). Does nothing if the list is empty or curr is
     *      already pointing to the tail
     *
     * @pre none
     *
     * @post [self = #self except the curr iterator has been moved to the tail]
     */
    public void goToTail()
    {
        if(curr == null)
            return;
        while(!(curr.link == null))
        {
            this.goToNext();
        }
    }

}
