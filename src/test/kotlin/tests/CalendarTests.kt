package tests

import org.apache.logging.log4j.LogManager
import org.junit.jupiter.api.Test
import org.openqa.selenium.firefox.FirefoxDriver

import org.openqa.selenium.firefox.FirefoxOptions

import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL


private const val WEBDRIVER_URL_CONFIG = "org.wbombardellis.plexus.webdriver"

class CalendarTests {

    private val logger = LogManager.getLogger()

    @Test
    fun testAmountOfDates() {
        val remoteWebDriverURL = System.getProperty(WEBDRIVER_URL_CONFIG)
        logger.info("Remote web driver URL: $remoteWebDriverURL")
        val driver = if (remoteWebDriverURL != null) {
            RemoteWebDriver(URL(remoteWebDriverURL), FirefoxOptions())
        } else {
            FirefoxDriver()
        }
        driver.quit()
    }
}