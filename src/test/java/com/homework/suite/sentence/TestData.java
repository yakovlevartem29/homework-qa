package com.homework.suite.sentence;

import com.google.common.base.MoreObjects;
import com.google.common.base.Splitter;

import java.util.Collection;
import java.util.List;

/**
 * Test data wrapper class, contains input text and collection of expected duplicated words
 */
public class TestData {

    private String text;
    private Collection<String> duplicates;

    static TestData parse(String line) {
        List<String> split = Splitter.on("|").splitToList(line);
        TestData testData = new TestData();
        testData.text = split.get(0);
        testData.duplicates = Splitter.on(",").omitEmptyStrings().splitToList(split.get(1));
        return testData;
    }

    public Collection<String> getDuplicates() {
        return duplicates;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("sentence", text)
                .toString();
    }
}
