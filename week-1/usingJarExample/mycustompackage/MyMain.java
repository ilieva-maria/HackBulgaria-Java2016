package mycustompackage;

import hacktools.ArrayTools;

class MyMain{
    public static void main(String[] args) {
        String[] arr = {"This", "is", "an", "array"};

        String line = ArrayTools.glue(arr, " ");
        System.out.println(line);
    }
}