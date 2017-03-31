package com.hackbulgaria.static_array;

import java.lang.StringBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Arr{
	public static void main(String[] args){
		Integer[] a = {10, 33, 20, -50, 1, 80, 70, 66, -365};

	    System.out.println("Print the array to string:");
	    System.out.println(Arr.toString(a));
	    
	    System.out.println("Sort the array a itself and print it sorted:");
	    Arr.sort(a);
	    System.out.println(Arr.toString(a));
	    
	    
	    System.out.println("Print the reverse of the sorted array");
	    System.out.println(Arr.toString(reverse(a)));
	    
	    System.out.println("Output each element in a with -> between them");
	    System.out.println(Arr.join(a, "->"));
	    
	    System.out.println("Output the sum");
	    System.out.println(Arr.sum(a));
	    
	    System.out.println("Output array with elements from 1 to 10");
	    System.out.println(Arr.toString(Arr.range(1, 10)));
	    
	    System.out.println("Print only the odd numbers");
		System.out.println(Arr.toString(filterOdd(a)));
	}

	public static <T> String toString(T[] array){
		return join(array, ", ");
	}

	public static <T extends Comparable<T>> void sort(T[] array){
		//bubbleSort(array);
		selectionSort(array);
	}

	public static <T extends Comparable<T>> void bubbleSort(T[] array){
		for (int i = array.length - 1 ; i >= 0; i--){
			for (int j = 1; j <= i; j++){
				if (array[j].compareTo(array[j-1]) < 0){
					T temp = array[j-1];
					array[j-1] = array[j];
					array[j] = temp;
				}
			}
		}
	}	

	public static <T extends Comparable<T>> void selectionSort(T[] array){
		for (int i = 0; i < array.length-1; i++){
			int indexOfCurMin = i;
			for (int j = i; j < array.length; j++){
				if (array[j].compareTo(array[indexOfCurMin]) < 0){
					indexOfCurMin = j;
				}
			}
			T temp = array[i];
			array[i] = array[indexOfCurMin];
			array[indexOfCurMin] = temp;
		}
	}

	public static <T> T[] reverse(T[] array){
		int length = array.length;
		for (int i = 0; i < length/2; i++){
			T temp = array[i];
			array[i] = array[length-i-1];
			array[length-i-1] = temp;
		}
		return array;
	}

	public static <T> String join(T[] array, String glue){
		StringBuilder arrayBuilder = new StringBuilder();
		boolean isFirst = true;
		for (T number : array){
			if (isFirst){
				arrayBuilder.append(number);
				isFirst = false;
			} else {
				arrayBuilder.append(glue).append(number);
			}
		}
		return arrayBuilder.toString();
	}

	public static <T extends Number> double sum(T[] array){
		return Arrays.stream(array).mapToDouble(d-> d.doubleValue()).sum();
		
	}

	public static Integer[] range(int from, int to){
		int step = (from<to?1:-1);
		Integer[] array = new Integer[Math.abs(from-to)];
		int index = 0;
		for (int i=from; i!=to; i+=step){
			array[index] = i;
			index++;
		}
		return array;
	}

	public static Integer[] filterOdd(Integer[] array){
		List<Integer> filteredList = Arrays.stream(array).filter(number -> number%2 != 0).collect(Collectors.toList());
		Integer[] oddElements = new Integer[filteredList.size()];
		oddElements = filteredList.toArray(oddElements);
		return oddElements;
	}

}