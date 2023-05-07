import java.awt.geom.Point2D;

public class QuickSort {


    /**
     * Default Contructor
     */
    public QuickSort() {
        //Empty constructor --- do nothing
    }

    /**
     * The main function that implements QuickSort
     *
     * @param arr        --> Array to be sorted
     * @param startIndex --> First index of arr[]
     * @param lastIndex  --> Last index of arr[]
     * @param orderBy    --> compareX or compareY
     *                   compareX: sort minimum to maximum arr[] by X point
     *                   compareY: sort minimum to maximum arr[] by Y point
     */
    public void sort(Point2D.Double[] arr, int startIndex, int lastIndex, String orderBy) {
        if (startIndex < lastIndex) {
            int pivotIndex;
            if (orderBy.equals("compareX")) {
                pivotIndex = partitionX(arr, startIndex, lastIndex);
            } else {
                pivotIndex = partitionY(arr, startIndex, lastIndex);
            }
            sort(arr, startIndex, pivotIndex - 1, orderBy);
            sort(arr, pivotIndex + 1, lastIndex, orderBy);
        }

    }

    /**
     * A utility function to swap two elements
     *
     * @param arr --> Array to be sorted
     * @param i   --> first index
     * @param j   --> second index
     */
    private void swap(Point2D.Double[] arr, int i, int j) {
        Point2D.Double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Get Median of 3 order by Point.X
     *
     * @param arr   --> Array to be sorted
     * @param left  --> First index of arr[]
     * @param right --> Last index of arr[]
     * @return
     */
    private Point2D.Double getMedianX(Point2D.Double[] arr, int left, int right) {
        int mid = (left + right) / 2;
        Point2D.Double leftPoint = arr[left];
        Point2D.Double midPoint = arr[mid];
        Point2D.Double rightPoint = arr[right];
        if (leftPoint.x > midPoint.x) {
            swap(arr, left, mid);
        }
        if (leftPoint.x > rightPoint.x) {
            swap(arr, left, right);
        }
        if (midPoint.x > rightPoint.x) {
            swap(arr, mid, right);
        }
        swap(arr, mid, right - 1);
        return midPoint;
    }

    /**
     * Get Median of 3 order by Point.Y
     *
     * @param arr   --> Array to be sorted
     * @param left  --> First index of arr[]
     * @param right --> Last index of arr[]
     * @return
     */
    private Point2D.Double getMedianY(Point2D.Double[] arr, int left, int right) {

        int mid = (left + right) / 2;
        Point2D.Double leftPoint = arr[left];
        Point2D.Double midPoint = arr[mid];
        Point2D.Double rightPoint = arr[right];
        if (leftPoint.y > midPoint.y) {
            swap(arr, left, mid);
        }
        if (leftPoint.y > rightPoint.y) {
            swap(arr, left, right);
        }
        if (midPoint.y > rightPoint.y) {
            swap(arr, mid, right);
        }
        swap(arr, mid, right - 1);
        return midPoint;
    }

    /**
     * This function takes median of three as pivot, places
     * the pivot element at the end of the sorted
     * array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right of pivot
     * Sort order by Point.X
     *
     * @param arr        --> Array to be sorted
     * @param startIndex --> First index of arr[]
     * @param lastIndex  --> Last index of arr[]
     * @return pivot index
     */
    private int partitionX(Point2D.Double[] arr, int startIndex, int lastIndex) {
        double pivot = getMedianX(arr, startIndex, lastIndex).getX();
        int i = startIndex - 1;
        for (int j = startIndex; j < lastIndex; j++) {
            if (arr[j].getX() <= pivot) {
                i++;
                Point2D.Double temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Point2D.Double temp = arr[i + 1];
        arr[i + 1] = arr[lastIndex];
        arr[lastIndex] = temp;
        return i;
    }

    /**
     * This function takes median of three as pivot, places
     * the pivot element at the end of the sorted
     * array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right of pivot
     * Sort order by Point.Y
     *
     * @param arr        --> Array to be sorted
     * @param startIndex --> First index of arr[]
     * @param lastIndex  --> Last index of arr[]
     * @return pivot index
     */
    private int partitionY(Point2D.Double[] arr, int startIndex, int lastIndex) {
        double pivot = getMedianY(arr, startIndex, lastIndex).getY();
        int i = startIndex - 1;
        for (int j = startIndex; j < lastIndex; j++) {
            if (arr[j].getY() <= pivot) {
                i++;
                Point2D.Double temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Point2D.Double temp = arr[i + 1];
        arr[i + 1] = arr[lastIndex];
        arr[lastIndex] = temp;
        return i + 1;
    }

}
