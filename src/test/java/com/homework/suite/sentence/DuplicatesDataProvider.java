package com.homework.suite.sentence;

import com.homework.util.ResourcesHelper;

import org.testng.annotations.DataProvider;

import java.util.List;

public class DuplicatesDataProvider {

    @DataProvider(parallel = true)
    public Object[][] getSentencesWithDuplicates() {
        List<String> lines = ResourcesHelper
                .getResourceAsListString("sentence/sentencesAndExpectations.txt");
        Object[][] result = new Object[lines.size()][1];

        for (int i = 0; i < lines.size(); i++) {
            result[i][0] = TestData.parse(lines.get(i));
        }
        return result;
    }
}
