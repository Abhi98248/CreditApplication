public class ArraysTraining {

    //Exercise
    public static void main(String[] args) {
        int[] array = {10, 15, 4, 90, 3, 0, -5, 134, 87, 92, 8, -99, -7};

        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < (array.length - i); j++) {
                if (array[j - 1] > array[1]) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }

        for (int e : array) {
            System.out.print(" " + e);
        }
    }
}
