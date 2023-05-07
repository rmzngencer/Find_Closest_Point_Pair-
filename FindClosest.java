import java.awt.geom.Point2D;

public class FindClosest {

    private PointPair closestPointPair;
    private final QuickSort quicksort = new QuickSort();

    /** Constructor
     *
     * @param points --> point array
     */
    public FindClosest(Point2D.Double[] points)
    {
        //Sort points by X coordinate
        quicksort.sort(points, 0, points.length - 1, "compareX");
        this.closestPointPair = calculateClosestPointPair(points, 0, points.length - 1);
        //*********************************do nothing***************************************//
    }

    /** Get closest Point Pair
     *
     * @return closestPointPair
     */
    public PointPair getClosestPointPair()
    {
        return this.closestPointPair;
    }

    /** Main method for calculate and return closest point pair
     *
     * @param p --> point array
     * @param startIndex --> First index of p[]
     * @param lastIndex --> last index of p[]
     * @return
     */
    private PointPair calculateClosestPointPair(Point2D.Double[] p, int startIndex, int lastIndex)
    {
        if (lastIndex - startIndex == 1) {
            return new PointPair(p[startIndex], p[lastIndex]);
        } else if (lastIndex - startIndex == 2) {
            return getClosestPointPair(p[startIndex], p[startIndex+1], p[lastIndex]);
        } else {
            int midIndex = (startIndex + lastIndex) / 2;
            PointPair leftShortest = calculateClosestPointPair(p, startIndex, midIndex);
            PointPair rightShortest = calculateClosestPointPair(p, midIndex + 1, lastIndex);
            PointPair shortestLine = getClosestPointPair(leftShortest, rightShortest);
            Point2D.Double midPoint = p[midIndex];

            // Filter strip of points closer than shortestLine
            Point2D.Double[] strip = new Point2D.Double[lastIndex - startIndex + 1];
            int j = 0;
            for (int i = startIndex; i <= lastIndex; i++) {
                if (Math.abs(p[i].getX() - midPoint.getX()) < shortestLine.getDistance()) {
                    strip[j] = p[i];
                    j++;
                }
            }
            // Sort strip by Y coordinate
            quicksort.sort(strip, 0, j-1, "compareY");

            // Check if any of the points in the strip are closer than shortestLine
            PointPair stripClosest = stripClosest(strip, j, shortestLine);
            if (stripClosest.getDistance() < shortestLine.getDistance()) {
                return stripClosest;
            } else {
                return shortestLine;
            }
        }
    }

    /** calculate and return closest point pair from 3 points
     *
     * @param p1 --> point 1
     * @param p2 --> point 2
     * @param p3 --> point 3
     * @return
     */
    private PointPair getClosestPointPair(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
        double dist1 = p1.distance(p2);
        double dist2 = p1.distance(p3);
        double dist3 = p2.distance(p3);
        if (dist1 <= dist2 && dist1 <= dist3) {
            return new PointPair(p1, p2);
        } else if (dist2 <= dist1 && dist2 <= dist3) {
            return new PointPair(p1, p3);
        } else {
            return new PointPair(p2, p3);
        }
    }

    private PointPair getClosestPointPair(PointPair p1, PointPair p2){
        double dist1 = p1.getDistance();
        double dist2 = p2.getDistance();
        if (dist1 < dist2) {
            return p1;
        } else {
            return p2;
        }
    }

    /**
     * A utility function to find the distance between the closest points of
     * strip of given size. All points in strip[] are sorted according to
     * y coordinate. They all have an upper bound on minimum distance as d.
     * Note that this method seems to be a O(n^2) method, but it's a O(n)
     * method as the inner loop runs at most 6 times
     *
     * @param strip --> point array
     * @param size --> strip array element count
     * @param shortestLine --> shortest line calculated so far
     * @return --> new shortest line
     */
    private PointPair stripClosest(Point2D.Double strip[], int size, PointPair shortestLine) {
        double minDist = shortestLine.getDistance();
        for (int i = 0; i < size; i++) {
            for (int j = i+1; j < size && (strip[j].getY() - strip[i].getY()) < minDist; j++) {
                double dist = strip[i].distance(strip[j]);
                if (dist < minDist) {
                    shortestLine = new PointPair(strip[i], strip[j]);
                    minDist = dist;
                }
            }
        }
        return shortestLine;
    }

}