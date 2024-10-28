package common

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

fun WebDriver.wait() = WebDriverWait(this, Duration.ofSeconds(10))