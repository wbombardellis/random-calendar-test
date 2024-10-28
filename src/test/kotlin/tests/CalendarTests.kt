package tests

import common.WEBDRIVER_URL_CONFIG
import org.apache.logging.log4j.LogManager
import org.junit.jupiter.api.Test
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.RemoteWebDriver
import pages.RandomCalendar
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

    private val randomCalendar by lazy { RandomCalendar(driver) }

    @Test
    fun testAmountOfDates() {
        randomCalendar.run {
            load()
            setTotalOfDates(4u)
            getDates()
        }
        driver.quit()
    }
}