import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class CircularLinkedList<E> implements Iterable<E>
{
    Node<E> head;
    Node<E> tail;
    int size;

    // Technically not needed because the default constructor does the same thing
    public CircularLinkedList()
    {
        head = null;
        tail = null;
        size = 0;
    }

    private Node<E> getNode(int index)
    {
        // Throw exception if the index is out of bounds
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("The index " + index + " is out of bounds for size " + size);
        }

        // Set the current node to head first
        Node<E> current = head;

        // Loop through the list until the current node is the node at the index or the end of the list is reached
        for (int i = 0; i < index && current != null; i++)
        {
            current = current.next;
        }

        // Return the current node
        return current;
    }

    public boolean add(E item)
    {
        this.add(size, item);
        return true;
    }

    public void add(int index, E item)
    {
        // Throw exception if the index is out of bounds
        if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException("The index " + index + " is out of bounds for size " + size);
        }

        Node<E> newNode = new Node<E>(item);

        // If the list is empty, set the head and tail to the new node
        if (size == 0)
        {
            head = newNode;
            tail = newNode;
            newNode.next = newNode;
        }

        // If adding to the beginning of the list, set the head to the new node and the new node to the tail
        else if (index == 0)
        {
            newNode.next = head;
            head = newNode;
            tail.next = head;
        }

        // If adding to the end of the list, set the tail to the new node and the new node to the head
        else if (index == size)
        {
            tail.next = newNode;
            tail = newNode;
            newNode.next = head;
        }

        // If adding to the middle of the list, set the new node to the next node and the current node to the new node
        else
        {
            Node<E> current = getNode(index - 1);
            newNode.next = current.next;
            current.next = newNode;
        }

        size++;
    }

    public E remove(int index)
    {
        // Throw exception if the index is out of bounds
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("The index " + index + " is out of bounds for size " + size);
        }

        Node<E> current = head;

        // If it's the last item in the list, set the head and tail to null
        if (size == 1)
        {
            head = null;
            tail = null;
        }

        // If it's the first item in the list, set the head to the next item and the tail to the head
        else if (index == 0)
        {
            head = head.next;
            tail.next = head;
        }

        else if (index == size - 1)
        {
            for (int i = 0; i < index - 1; i++)
            {
                current = current.next;
            }

            tail = current;
            current.next = head;
        }

        else
        {
            for (int i = 0; i < index - 1; i++)
            {
                current = current.next;
            }

            current.next = current.next.next;
        }

        // Decrement the size
        size--;

        return current.item;
    }

    // Returns the list turned into a string
    public String toString()
    {
        Node<E> current = head;
        StringBuilder result = new StringBuilder();

        if (size == 0)
        {
            return "";
        }

        if (size == 1)
        {
            return head.item.toString();
        } else
        {
            do
            {
                result.append(current.item);
                result.append(" ==> ");
                current = current.next;
            } while (current != head);
        }

        return result.toString();
    }

    public Iterator<E> iterator()
    {
        return new ListIterator<E>();
    }

    // Given class for ListIterator
    private class ListIterator<E> implements Iterator<E>
    {
        Node<E> nextItem;
        Node<E> prev;
        int index;

        @SuppressWarnings("unchecked")
        public ListIterator()
        {
            nextItem = (Node<E>) head;
            index = 0;
        }

        public boolean hasNext()
        {
            // TODO Auto-generated method stub
            return size != 0;
        }

        public E next()
        {
            // TODO Auto-generated method stub
            prev = nextItem;
            nextItem = nextItem.next;
            index = (index + 1) % size;
            return prev.item;

        }

        public void remove()
        {
            int target;

            if (nextItem == head)
            {
                target = size - 1;
            } else
            {
                target = index - 1;
                index--;
            }

            CircularLinkedList.this.remove(target);
        }
    }

    // Node class for a single-linked list
    private static class Node<E>
    {
        E item;
        Node<E> next;

        public Node(E item)
        {
            this.item = item;
            this.next = null;
        }

        public Node(E item, Node<E> next)
        {
            this.item = item;
            this.next = next;
        }
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of soldiers (n): ");
        int n = input.nextInt();
        System.out.print("Enter the count to the k-th soldier (k): ");
        int k = input.nextInt();
        input.close();

        CircularLinkedList<Integer> soldierCircle = new CircularLinkedList<>();

        // Add to the list the numbers of soldiers
        for (int i = 1; i <= n; i++)
        {
            soldierCircle.add(i);
        }

        // Print the initial list of soldiers
        System.out.println(soldierCircle);

        // Use an iterator to remove the k-th soldier
        Iterator<Integer> iterator = soldierCircle.iterator();

        // While there is more than one soldier left
        while (soldierCircle.size > 1)
        {
            for (int i = 0; i < k; i++)
            {
                // If the iterator has reached the end of the list, reset it to the beginning
                if (!iterator.hasNext())
                {
                    iterator = soldierCircle.iterator();
                }

                iterator.next();
            }

            iterator.remove();
            System.out.println(soldierCircle);
        }

        System.out.println("The last lonely soldier: " + soldierCircle.getNode(0).item);
    }
}