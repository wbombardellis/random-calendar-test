package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

const val RANDOM_CALENDAR_URL = "https://www.random.org/calendar-dates/"

class RandomCalendar(private val webDriver: WebDriver) {

    private val totalOfDatesInput by lazy { webDriver.findElement(By.name("num")) }
    private val getDatesButton by lazy {
        webDriver.findElement(By.cssSelector("input[type='submit'][value='Get Dates']"))
    }

    fun load() = webDriver.get(RANDOM_CALENDAR_URL)

    fun setTotalOfDates(n: UInt) = totalOfDatesInput.sendKeys(n.toString())

    fun getDates() = getDatesButton.click()
}