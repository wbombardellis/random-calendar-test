package tests

import common.WEBDRIVER_URL_CONFIG
import org.apache.logging.log4j.LogManager
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.RemoteWebDriver
import pages.RandomCalendar
import java.net.URL
import java.time.LocalDate
import java.time.Month


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

    private fun test(block: () -> Unit) = runCatching(block)
        .onFailure { driver.quit() }
        .onSuccess { driver.quit() }
        .getOrThrow()

    @Test
    fun testAmountOfDates() = test {
        val n = 4u
        val startDate = LocalDate.of(2024, Month.JANUARY, 5)
        val endDate = LocalDate.of(2025, Month.NOVEMBER, 25)
        logger.info("Started test for amount of dates ($n dates between $startDate and $endDate")

        randomCalendar.run {
            load()
            setTotalOfDates(n)
            setTimeFrame(startDate, endDate)
            getDates()

            Assertions.assertEquals(n, generatedDates.size.toUInt())
            pickedDates.let { (start, end) ->
                Assertions.assertEquals(startDate, start)
                Assertions.assertEquals(endDate, end)
            }
        }
        logger.info("Finished test for amount of dates ($n dates between $startDate and $endDate")
    }
}