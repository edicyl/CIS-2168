import java.util.*;

public class ListFunctions
{
    public static void main(String[] args)
    {
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(1, 4, 5, 6, 5, 5, 2));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 3, 3, 1, 3, 3, 5, 3, 4));

        removeAllInstances(list2, 3);

        System.out.println(list2);
    }

    // Big-O: O(n log n)
    public static <E> boolean unique(List<E> list)
    {
        // Compare the count of unique values to the total size
        // If equal, all values are unique
        return list.stream().distinct().count() == list.size();
    }

    // Big-O: O(n)
    public static List<Integer> allMultiples(List<Integer> list, int factor)
    {
        List<Integer> multiplesList = new ArrayList<>();

        // If the integer is divisible, add it to the new list
        for (Integer i : list)
        {
            if (i % factor == 0)
            {
                multiplesList.add(i);
            }
        }

        return multiplesList;
    }

    // Big-O: O(n)
    public static List<String> allStringsOfSize(List<String> list, int length)
    {
        List<String> sizedList = new ArrayList<>();

        for (String i : list)
        {
            if (i.length() == length)
            {
                sizedList.add(i);
            }
        }

        return sizedList;
    }

    // Big-O: O(n log n)
    public static <E extends Comparable<? super E>> boolean isPermutation(List<E> list1, List<E> list2)
    {
        // Make copies to not destroy the original order
        list1 = new ArrayList<>(list1);
        list2 = new ArrayList<>(list2);

        // Sort both lists to make them the same order
        Collections.sort(list1);
        Collections.sort(list2);

        // If they are equal, the sorted order is the same
        return list1.equals(list2);
    }

    // Big-O: O(n)
    public static List<String> stringToListOfWords(String str)
    {
        // Replace punctuation with empty character, save in new string
        String sanitizedStr = str.replaceAll("\\p{P}", "");

        // Return new array list of string split by whitespaces
        return new ArrayList<>(Arrays.asList(sanitizedStr.split("\\s+")));
    }

    // Big-O: O(n^2)
    public static <E> void removeAllInstances(List<E> list, E item)
    {
        // If the element equals item, remove it
        // Correct the index by decreasing by one
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).equals(item))
            {
                list.remove(i);
                i--;
            }
        }
    }
}