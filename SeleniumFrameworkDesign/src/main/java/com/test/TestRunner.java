package com.test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/cucumber",
    glue = {"stepDefinitions"},
    plugin = {"pretty", "html:target/cucumber-report.html"},
    tags="@Regression",
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
