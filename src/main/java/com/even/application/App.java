package com.even.application;

import com.even.algo.BoundingBox;
import com.even.exception.InvalidInputFormatException;
import com.even.input.BoundingBoxInputReader;
import com.even.model.Pair;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        try {

            BoundingBoxInputReader bir = new BoundingBoxInputReader();
            List<String> inputLines = bir.readInput(); // Read the input lines from stdin

            BoundingBox box = new BoundingBox();
            List<Pair> pairs = box.getDisjointLargeBoundingBoxes( inputLines ); //Finds the largest disjointedboxes
            if( pairs.size() == 0){
                System.exit( -1);
            }
            pairs.stream().forEach(pair -> System.out.println(pair));

        } catch ( InvalidInputFormatException iex ){ //Exceptions if there are any stdin invalid inputs
            System.out.println(iex);
            System.exit(-1);
        }
        System.exit(0);
    }
}
