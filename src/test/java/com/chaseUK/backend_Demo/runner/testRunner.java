package com.chaseUK.backend_Demo.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "@smoke",
        features = {"src/test/resources/features"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true,
        publish = true,
        glue = "com.chaseUK.backend_Demo.stepDefinition")

class testRunner extends AbstractTestNGCucumberTests {

}