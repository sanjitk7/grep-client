package com.grepmp.app;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import com.grepmp.app.App;

import java.io.File;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;  

import org.junit.Test;

public class AppTest {

    /**
     * TC #1: Tests the basic functionality: able to connect to VMs and receive back data
     */
    @Test
    public void e2eTest() {
        String input = "grep -c PUT";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        App app = new App();
        String[] strArr = {"", ""};
        app.main(strArr);

        File file = new File("output.txt");
        assertTrue(file.exists());

        try {
            String expectedOutput = Files.readString(Paths.get(
                "src/test/java/com/grepmp/app/output/expected_output1.txt"), StandardCharsets.US_ASCII);
            String[] expectedOutputArr = expectedOutput.split("\n");
            Arrays.sort(expectedOutputArr);

            String actualOutput = Files.readString(Paths.get("output.txt"), StandardCharsets.US_ASCII);
            String[] actualOutputArr = actualOutput.split("\n");
            Arrays.sort(actualOutputArr);
            
            assertEquals(expectedOutput.length(), actualOutput.length());
            assertArrayEquals(expectedOutputArr, actualOutputArr);
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
}
