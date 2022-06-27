# Hoffman_Sabrina_AlexAndNova_CaseStudy
[GitHub Link](https://github.com/sabhoff/Hoffman_Sabrina_AlexAndNova_CaseStudy.git)

## System Requirements
* Access to internet connection
* Java 8
* TestNG
* Jenkins
* Chrome Browser and Driver Installed
* Firefox Browser and Driver Installed
* Edge Browser and Driver Installed

## Project Description
This project is used to test the funtions of the [Alex and Nova Ecommerce Website](https://www.alexandnova.com/). Within this project there are automated tests written to test the function of user registration, user login, search function, product add to cart function, discount application, and the checkout process. A select browser class is used, a base class, TestNG annotations, and the page factory model is used. Page object model is used in the LoginPage class **ONLY** to show proof of knowledge. This project cannot runheadless due to captcha forms needing to be filled out manually. 

## Project Challenges
During this project I encountered many challenges. These challenges are listed below and some could cause issues with running these tests in the future.
1. The [Alex and Nova Website](https://www.alexandnova.com/) had elements that changed often which lead to tests running one day and not the next due to the element not being found. 
2. Captcha forms popped up even when entering invalid data. Due to this I had to add a large amount of sleeps to allow for manual bypass of captcha.
3. Many pages would not load fast enough creating issues when trying to find specific elements.
4. After a few runs of the user registration test, the website would no longer allow me to register any new users. Therefore, always causing my registration test to fail.
5. While running the search bar test on testNG and on Jenkins the search button brings up different pages every time, making the test fail occaisionally.
6. The original version of Jenkins I had installed would not open the browser when I ran my tests so I had to download the .war version and configure it to open the browser.

## Dependencies
<dependencies>
  
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.seleniumhq.selenium</groupId>
      <artifactId>selenium-java</artifactId>
      <version>4.0.0</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/org.testng/testng -->
    <dependency>
      <groupId>org.testng</groupId>
      <artifactId>testng</artifactId>
      <version>6.14.3</version>
    </dependency>

    <dependency>
      <groupId>org.testng</groupId>
      <artifactId>reportng</artifactId>
      <version>1.2.2</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>commons-io</groupId>
      <artifactId>commons-io</artifactId>
      <version>2.11.0</version>
    </dependency>

    <!--- this is third-party report for capstone project-->
    <dependency>
      <groupId>com.aventstack</groupId>
      <artifactId>extentreports</artifactId>
      <version>3.1.5</version>
    </dependency>
  </dependencies>
  
  ## Plugins
  
  <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
  
      <plugins>
        <!-- clean lifecycle, see https://maven.apache.org/ref/current/maven-core/lifecycles.html#clean_Lifecycle -->
        <plugin>
          <artifactId>maven-clean-plugin</artifactId>
          <version>3.1.0</version>
        </plugin>
        
        <!-- default lifecycle, jar packaging: see https://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_jar_packaging -->
        <plugin>
          <artifactId>maven-resources-plugin</artifactId>
          <version>3.0.2</version>
        </plugin>
        
        <plugin>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.8.0</version>
        </plugin>
        
        <plugin>
          <artifactId>maven-jar-plugin</artifactId>
          <version>3.0.2</version>
        </plugin>
        
        <plugin>
          <artifactId>maven-install-plugin</artifactId>
          <version>2.5.2</version>
        </plugin>
        
        <plugin>
          <artifactId>maven-deploy-plugin</artifactId>
          <version>2.8.2</version>
        </plugin>
        
        <!-- site lifecycle, see https://maven.apache.org/ref/current/maven-core/lifecycles.html#site_Lifecycle -->
        <plugin>
          <artifactId>maven-site-plugin</artifactId>
          <version>3.7.1</version>
        </plugin>
        
        <plugin>
          <artifactId>maven-project-info-reports-plugin</artifactId>
          <version>3.0.0</version>
        </plugin>
        
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.5.1</version>
          <configuration>
            <source>8</source>
            <target>8</target>
          </configuration>
        </plugin>
  
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>3.0.0-M6</version>
          <configuration>
            <suiteXmlFiles>
              <suiteXmlFile>testng.xml</suiteXmlFile>
            </suiteXmlFiles>
          </configuration>
        </plugin>
      </plugins>
    </pluginManagement>

