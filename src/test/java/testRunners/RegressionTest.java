package testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = {
                "support",
                "stepDefinitions"
        },
        plugin = {
                "de.monochromata.cucumber.report.PrettyReports:reporting/pretty/RegressionTest",
                "html:reporting/CucumberHTMLReports/RegressionTest",
                "json:reporting/cucumberOtherreports/RegressionTest.json",
                "junit:reporting/cucumber-reports/RegressionTest.xml",
                "rerun:target/rerun.txt" //Creates a text file with failed scenarios
        },
        tags = "@Regression"
)

public class RegressionTest {
}
