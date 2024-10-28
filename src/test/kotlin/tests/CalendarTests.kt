package tests

import common.RANDOM_CALENDAR_URL
import common.WEBDRIVER_URL_CONFIG
import org.apache.logging.log4j.LogManager
import org.junit.jupiter.api.Test
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL


class CalendarTests {

    private val logger = LogManager.getLogger()

    private val driver by lazy {
        val remoteWebDriverURL = System.getProperty(WEBDRIVER_URL_CONFIG)
        logger.info("Remote web driver URL: $remoteWebDriverURL")
        runCatching {
            if (remoteWebDriverURL != null) {
                RemoteWebDriver(URL(remoteWebDriverURL), FirefoxOptions())
            } else {
                FirefoxDriver()
            }
        }.getOrElse {
            logger.fatal("Could not initialize web driver. Check your configuration.")
            throw it
        }
    }

    @Test
    fun testAmountOfDates() {
        driver.get(RANDOM_CALENDAR_URL)
        driver.quit()
    }
}