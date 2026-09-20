package testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/",
        glue = {
                "support",
                "stepDefinitions"
        },
        tags = "@Smoke",
        plugin = {
                "de.monochromata.cucumber.report.PrettyReports:reporting/pretty/SmokeTest",
                "html:reporting/CucumberHTMLReports/SmokeTest",
                "json:reporting/cucumberOtherreports/SmokeTest.json",
                "junit:reporting/cucumber-reports/SmokeTest.xml",
                "rerun:target/rerun.txt"
        }
)
public class SmokeTest {
}