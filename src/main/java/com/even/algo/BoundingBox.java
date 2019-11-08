package com.even.algo;

import com.even.model.CoOrdinate;
import com.even.model.Pair;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BoundingBox {

    /**
     * Returns the list of bounding boxes with largest areas
     * @param inputLines
     * @return
     */
    public List<Pair> getDisjointLargeBoundingBoxes(List<String> inputLines ){
        char[][] box = transformToMatrix( inputLines ); //Transforming input to matrix
        List<Pair> boundingBoxes = getBoundingBoxes( box ); //Gets all the distinct bounding boxes
        List<Pair> disjointBoundingBoxes = filterOverlappingBoundingBoxes( boundingBoxes ); //Filters Out Overlapping Bounding Boxes
        return getLargestBoundingBoxes( disjointBoundingBoxes );//Gets the larges bounding box
    }

    Comparator<Pair> comparator = Comparator.comparing( Pair::getArea );
    /**
     * Gets the largest bounding boxes
     * @param boxes
     * @return
     */
    private List<Pair> getLargestBoundingBoxes( List<Pair> boxes ){
        if( boxes.size() == 0 ){
            return boxes;
        }
        int area = boxes.stream().max( comparator).get().getArea();
        return boxes.stream().filter( b -> b.getArea() == area).collect(Collectors.toList());
    }

    private List<Pair> filterOverlappingBoundingBoxes( List<Pair> boundingBoxes ){
        List<Pair> disjointBoundingBoxes = new ArrayList<>();

        for( Pair b : boundingBoxes ){
            if( isDisjoint( b, boundingBoxes )){
                disjointBoundingBoxes.add(b);
            }
        }

        return disjointBoundingBoxes;
    }

    private boolean isDisjoint( Pair box, List<Pair> boxes) {
        for( Pair p : boxes){
            if( !p.equals(box) ){
                if( isOverLapping( p, box))
                    return false;
            }
        }
        return true;
    }

    private boolean isOverLapping( Pair a, Pair b){

        if (a.getTopLeft().getX() > b.getBotRight().getX() || b.getTopLeft().getX() > a.getBotRight().getX()) {
            return false;
        }

        if (a.getTopLeft().getY() > b.getBotRight().getY() || b.getTopLeft().getY() > a.getBotRight().getY()) {
            return false;
        }

        return true;
    }

    private List<Pair> getBoundingBoxes( char[][] box ){
        List<Pair> boundingBoxes = new ArrayList<>();
        int m = box.length;
        int n = box[0].length;
        for( int i = 0; i < m; i++ ){
            for( int j=0; j < n; j++ ){
                if( box[i][j] == '*' ) {
                    markContigious(box, i, j, m, n );
                    Pair pair = getPairOfRect( box, m, n );
                    boundingBoxes.add( pair );
                    markContigiousAsVisited( box, m, n);
                }
            }
        }
        return boundingBoxes;
    }

    private void markContigiousAsVisited( char [][]box, int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (box[i][j] == 'C') {
                    box[i][j] = 'V';
                }
            }
        }
    }

    private Pair getPairOfRect( char [][]box, int m, int n){
        int minTopX = n;
        int minTopY = m;
        int maxTopX = 0;
        int maxTopY = 0;

        for(int i=0; i<m ;i++){
            for(int j=0; j<n; j++ ){
                if( box[i][j] == 'C'){
                    minTopX = Math.min( minTopX, j);
                    minTopY = Math.min( minTopY, i);
                    maxTopX = Math.max( maxTopX, j);
                    maxTopY = Math.max( maxTopY, i);
                }
            }
        }
        CoOrdinate topLeft = new CoOrdinate( minTopX, minTopY );
        CoOrdinate botRight = new CoOrdinate( maxTopX, maxTopY );
        return new Pair( topLeft, botRight );
    }

    private void printMatrix( char [][]box, int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(box[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void markContigious( char box[][], int i, int j, int m, int n ){
        if( box[i][j] == '*'){
            box[i][j] = 'C';
        }
        if( i+1 < m && box[i+1][j] == '*') {
            markContigious( box, i+1, j, m, n );
        }
        if( j+1 < n && box[i][j+1] == '*') {
            markContigious( box, i, j+1, m, n );
        }
        if( i-1 > 0 && box[i-1][j] == '*') {
            markContigious( box, i-1, j, m, n );
        }
        if( j-1 > 0 && box[i][j-1] == '*') {
            markContigious( box, i, j-1, m, n );
        }
    }

    private char[][] transformToMatrix( List<String> inputLines) {
        int m = inputLines.size();
        int n = inputLines.get(0).length(); // There is bound to be a string here, hence a naked get.

        char[][] box = new char[m][n];
        int i = 0, j = 0;
        for( String line : inputLines ) {
            j = 0;
            for( char ch : line.toCharArray() ){
                box[i][j] = ch;
                j++;
            }
            i++;
        };
        return box;
    }

}
