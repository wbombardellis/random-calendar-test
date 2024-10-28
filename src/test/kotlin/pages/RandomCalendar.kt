package pages

import common.wait
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.Select
import java.time.LocalDate

const val RANDOM_CALENDAR_URL = "https://www.random.org/calendar-dates/"

class RandomCalendar(private val webDriver: WebDriver) {

    private val pickedDatesRegex = Regex(""".*between (\d{4}-\d{2}-\d{2}) and (\d{4}-\d{2}-\d{2}).*""")

    private val totalOfDatesInput by lazy { webDriver.findElement(By.name("num")) }

    private val startDayInput by lazy { webDriver.findElement(By.name("start_day")) }
    private val startMonthInput by lazy { webDriver.findElement(By.name("start_month")) }
    private val startYearInput by lazy { webDriver.findElement(By.name("start_year")) }
    private val endDayInput by lazy { webDriver.findElement(By.name("end_day")) }
    private val endMonthInput by lazy { webDriver.findElement(By.name("end_month")) }
    private val endYearInput by lazy { webDriver.findElement(By.name("end_year")) }

    private val getDatesButton by lazy {
        webDriver.findElement(By.cssSelector("input[type='submit'][value='Get Dates']"))
    }
    private val generatedDatesElement by lazy {
        webDriver.findElement(By.xpath("/html/body/div/p[3]"))
    }
    private val pickedDatesElement by lazy {
        webDriver.findElement(By.xpath("/html/body/div/p[4]"))
    }


    fun load() = webDriver.get(RANDOM_CALENDAR_URL)

    fun setTotalOfDates(n: UInt) = totalOfDatesInput.run {
        clear()
        sendKeys(n.toString())
    }

    fun setTimeFrame(startDate: LocalDate, endDate: LocalDate) {
        Select(startDayInput).selectByValue(startDate.dayOfMonth.toString())
        Select(startMonthInput).selectByValue(startDate.monthValue.toString())
        Select(startYearInput).selectByValue(startDate.year.toString())
        Select(endDayInput).selectByValue(endDate.dayOfMonth.toString())
        Select(endMonthInput).selectByValue(endDate.monthValue.toString())
        Select(endYearInput).selectByValue(endDate.year.toString())
    }

    fun getDates() = getDatesButton.click()

    val generatedDates: List<String> get() {
        webDriver.wait().until { generatedDatesElement.isDisplayed }
        return generatedDatesElement.text.split("\n")
    }

    val pickedDates: Pair<LocalDate, LocalDate> get() {
        webDriver.wait().until { pickedDatesElement.isDisplayed }
        val match = pickedDatesRegex.matchEntire(pickedDatesElement.text)
        return match?.let {
            Pair(LocalDate.parse(it.groupValues[1]), LocalDate.parse(it.groupValues[2]))
        } ?: error("Could not match picked dates")
    }
}