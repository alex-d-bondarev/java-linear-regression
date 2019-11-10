package org.practice.app;

import org.junit.Test;
import org.practice.app.file.FileLoader;

public class CostFunctionTest {

    @Test
    public void constFunctionCanPredict(){
        String testInputFilePath = "src/test/resources/org/practice/app/testInput.txt";
        double[][] testInput = FileLoader.arrayFromFile(testInputFilePath);
    }
}
