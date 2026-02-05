# Flipkart Selenium Automation – TestNG Project

This project automates multiple product search and filter scenarios on Flipkart using **Selenium WebDriver**, **Java**, and **TestNG**. Wrapper methods are used for every UI action to keep the code reusable, clean, and maintainable.

## 📌 Test Scenarios Covered

**TestCase01**

* Open Flipkart
* Search for *Washing Machine*
* Sort by *Popularity*
* Count and print number of items with rating ≤ 4 stars

**TestCase02**

* Search for *iPhone*
* Extract and print product titles and discount % where discount > 17%

**TestCase03**

* Search for *Coffee Mug*
* Apply *4 stars & above* filter
* Find top 5 products with highest review count
* Print product title and image URL

---

## 🧱 Framework Design

* Test framework: **TestNG**
* Language: **Java**
* Automation tool: **Selenium WebDriver**
* Browser: **Chrome**
* Design pattern: **Wrapper-based abstraction**
* Wait strategy: **Explicit waits (WebDriverWait)**

All browser actions like click, type, get elements, filters, and extraction logic are implemented inside the `Wrappers` class.

---

## 📂 Project Structure

```
demo/
 ├── TestCases.java
 └── wrappers/
      └── Wrappers.java
```

* `TestCases.java` → Contains TestNG test flows
* `Wrappers.java` → Contains reusable Selenium helper methods

---

## ⚙️ Setup Instructions

### 1️⃣ Prerequisites

* Java 11+
* Chrome browser
* ChromeDriver in system PATH
* Gradle or Maven project setup
* TestNG dependency added

### 2️⃣ Install Dependencies (example Gradle)

```
testImplementation 'org.seleniumhq.selenium:selenium-java:4.x.x'
testImplementation 'org.testng:testng:7.x.x'
```

---

## ▶️ How to Run

Using TestNG:

```
Right click → TestCases.java → Run as TestNG Test
```

Or with Gradle:

```
gradlew test --no-daemon
```

(daemon disabled helps in CI / assessment runners)

---

## ⚠️ Notes & Limitations

* Flipkart is a live site — DOM and locators may change.
* Captcha may appear during execution. If that happens:

  * Pause execution (Thread.sleep already added in places)
  * Manually solve captcha
* Some elements are dynamically loaded — explicit waits are used but timing can still vary.

---

## ✅ Wrapper Methods Included

* openUrl
* typeText (safe clear + type)
* click
* getElements
* getCount
* setFilter
* iphoneTitleAndDiscount
* coffeeMugTitleAndImageURL

---

## 🤝 Contributions

Not open for contributions yet, but ideas, improvements, and suggestions are welcome.

---

## 📎 Author

**Tejaswini Thambabathula**  
Automation & Testing Enthusiast | Aspiring SDET  
GitHub: https://github.com/TejaswiniSruthi
