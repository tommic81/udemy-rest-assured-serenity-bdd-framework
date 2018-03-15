# Rest Assured, Serenity bdd framework

#### Course files

1. Run **rest.jar** from StudentApp

   ```shell
   java -jar rest.jar
   # OR
   java -jar rest.jar --server.port=8085
   # for java 9
   java -jar --add-modules java.xml.bind rest.jar
   ```

#### SERENITY BDD

- open source test automation framework
- integrated to work with many popular testing libraries like:JUNIT, SELENIUM2, APPIUM, JBEHAVE, CUCUMBER etc.
- out of the box features for UI Automation (Web Driver Handling, Synchronization, Screenshots, Parallel testing, DataDriver testing, Cross browser testing, logging etc)
- integrated with Rest-Assured to create API test automation without any hassle
- integrates with popular build tools like MAVEN, GRADLE, ANT
- reports
- [project home page](http://www.thucydides.info/#/)
- [user guide](http://www.thucydides.info/docs/serenity/)
- [serenity on github](https://github.com/serenity-bdd/)
- [serenity properties](http://www.thucydides.info/docs/serenity/#_serenity_system_properties_and_configuration)
- [modeling requirements in junit](http://www.thucydides.info/docs/serenity/#_modelling_requirements_in_junit)


##### Set-up

- maven

  ```xml
  <properties>
  	<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
       <serenity.version>1.8.20</serenity.version>
       <serenity.maven.version>1.0.47</serenity.maven.version>
       <webdriver.driver>firefox</webdriver.driver>
  </properties>

  <repositories>
  	<repository>
     		<snapshots>
      		<enabled>false</enabled>
      	</snapshots>
      	<id>central</id>
      	<name>bintray</name>
      	<url>http://jcenter.bintray.com</url>
       </repository>
  </repositories>
  <pluginRepositories>
  	<pluginRepository>
      	<snapshots>
          	<enabled>false</enabled>
           </snapshots>
    	   <id>central</id>
         <name>bintray-plugins</name>
         <url>http://jcenter.bintray.com</url>
      </pluginRepository>
  </pluginRepositories>
  <dependencies>
  	<dependency>
      	<groupId>com.google.code.gson</groupId>
          <artifactId>gson</artifactId>
          <version>2.8.2</version>
      </dependency>
      <dependency>
      	<groupId>net.serenity-bdd</groupId>
         	<artifactId>serenity-junit</artifactId>
          <version>${serenity.version}</version>
      </dependency>
      <dependency>
      	<groupId>net.serenity-bdd</groupId>
          <artifactId>serenity-rest-assured</artifactId>
          <version>${serenity.version}</version>
      </dependency>
      <dependency>
      	<groupId>org.slf4j</groupId>
          <artifactId>slf4j-simple</artifactId>
          <version>1.7.21</version>
      </dependency>
      <dependency>
      	<groupId>junit</groupId>
          <artifactId>junit</artifactId>
          <version>4.12</version>
          <scope>test</scope>
      </dependency>
      <dependency>
      	<groupId>org.assertj</groupId>
          <artifactId>assertj-core</artifactId>
          <version>1.7.0</version>
          <scope>test</scope>
      </dependency>
  </dependencies>

  <build>
  	<plugins>
  		<plugin>
  			<groupId>org.apache.maven.plugins</groupId>
  			<artifactId>maven-enforcer-plugin</artifactId>
  			<version>1.4</version>
  			<executions>
  				<execution>
  					<id>enforce</id>
  					<configuration>
  						<rules>
  							<requireUpperBoundDeps/>
  						</rules>
  					</configuration>
  					<goals>
  						<goal>enforce</goal>
  					</goals>
  				</execution>
  			</executions>
  		</plugin>
  		<plugin>
  			<groupId>org.apache.maven.plugins</groupId>
  			<artifactId>maven-surefire-plugin</artifactId>
  			<version>2.18</version>
  			<configuration>
  				<skip>true</skip>
  			</configuration>
  		</plugin>
  		<plugin>
  			<artifactId>maven-failsafe-plugin</artifactId>
  			<version>2.18</version>
  			<configuration>
  				<includes>
  					<include>**/studentapp/**/*.java</include>
  				</includes>
  				<forkCount>4</forkCount>
  				<reuseForks>true</reuseForks>
  				<!-- <parallel>methods</parallel>  -->
  				<!-- <threadCount>10</threadCount>   -->
  				<argLine>-Xmx1024m -XX:MaxPermSize=256m</argLine>
  			</configuration>
  			<executions>
  				<execution>
  					<goals>
  						<goal>integration-test</goal>
  						<goal>verify</goal>
  					</goals>
  				</execution>
  			</executions>
  		</plugin>
  		<plugin>
  			<groupId>org.apache.maven.plugins</groupId>
  			<artifactId>maven-compiler-plugin</artifactId>
  			<version>3.7.0</version>
  			<configuration>
  				<source>1.8</source>
  				<target>1.8</target>
  			</configuration>
  		</plugin>
  		<plugin>
  			<groupId>net.serenity-bdd.maven.plugins</groupId>
  			<artifactId>serenity-maven-plugin</artifactId>
  			<version>${serenity.version}</version>
  			<executions>
  				<execution>
  					<id>serenity-reports</id>
  					<phase>post-integration-test</phase>
  					<goals>
  						<goal>aggregate</goal>
  					</goals>
  				</execution>
  			</executions>
  		</plugin>
  	</plugins>
  </build>
  ```

- running `mvn clean verify serenity:aggregate `

  - will be generated html report
  - find **target/site/serenity/index.html**

##### Serenity test requirements

- Class level annotations

  ```java
  //Serenity runner
  @RunWith(SerenityRunner.class)
  //order in which methods are executed
  @FixMethodOrder(MethodSorters.NAME_ASCENDING)
  public class FirstSerenityTest {}
  ```

- Setting up the URL under test

  ```java
  @BeforeClass
  public static void init() {
  	RestAssured.baseURI = "http://localhost:8080/myrestservice";
  }
  ```

- Method level annotations

  ```java
  // required 
  @Test
  public void someTest01() {}
  // optionally we can provide a meaningfull description

  @Title("This test will get the information of all the students from the Student App")
  @Test
  public void someTest02() {}
  ```

##### Types of finished tests

- Passing

- Pending - under development

  ```java
  @Pending
  @Test
  public void thisIsaPendingTest() {}
  ```

- Ignored - we do not want to execute that particular test

  ```java
  @Ignore
  @Test
  public void thisIsaSkippedTest() {}
  ```

- Failing  - we received a result other then expected

- Errors - test failing due to incorrect implementation

- Compromised - some exceptions are configured causing marking some test compromised

  ```properties
  # serenity.properties
  serenity.compromised.on=java.io.FileNotFoundException
  ```

- Untested

##### Understanding reports

- Configure feature package in serenity.properties

  ```properties
  # packages under this will be treated as features
  serenity.test.root=com.studentapp.junit
  ```

- Test classes will be treated as **Stories**.

- Test methods = **Tests**. It is good to add  `@Title` with some meaningful description. 

- Methods annotated with `@Step` are displayed as **Steps** or REST request if we do not use step methods. 

##### Reusable steps

- Some operations can be extracted from test methods to make it reusable

1. Create a class with methods annotated with `@Step`

   ```java
   @Step("Creating student with firstName:{0}, lastName:{1}, email:{2},programme{3} ,courses:{4}")
   public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme, List<String> courses) {
   	StudentClass student = new StudentClass();
       student.setFirstName(firstName);
       student.setLastName(lastName);
       student.setEmail(email);
       student.setProgramme(programme);
       student.setCourses(courses);

       return SerenityRest.rest().given()
                //using reusable request specification
               .spec(ReuseableSpecifications.getGenericRequestSpec())
               .when()
               .body(student)
               .post()
               .then();
   }
   ```

2. In test class add variable of class with step methods

   ```java
   @Steps
   StudentSerenitySteps steps;
   ```

3. Use step methods

   ```java
   @Title("This test will create a new student")
   @Test
   public void test001() {
   	ArrayList<String> courses = new ArrayList<String>();
       courses.add("JAVA");
       courses.add("C++");

       steps.createStudent(firstName, lastName, email, programme, courses)
            .statusCode(201)
            .spec(ReuseableSpecifications.getGenericResponseSpec());
   }
   ```

##### Request/Response  Specifications

- RequestSpecification - Reusable request Specification. Can be used for setting cookies, headers etc..

  ```java
  RequestSpecBuilder rspec = = new RequestSpecBuilder();
  rspec.setContentType(ContentType.JSON);
  RequestSpecification requestSpecification = rspec.build();
  ```

- ResponseSpecification - Reusable response Specification. Can be used for assertions.

  ```java
  ResponseSpecBuilder respec = new ResponseSpecBuilder();
  respec.expectHeader("Content-Type","application/json;charset=UTF-8");
  respec.expectHeader("Transfer-Encoding","chunked");
  respec.expectResponseTime(lessThan(5L),TimeUnit.SECONDS);
  ResponseSpecification responseSpecification = respec.build();
  ```

  ​

##### Data Driven Testing

- [Random Data generator](https://mockaroo.com/)
- Test can be run multiple times based on parameters from file


1. Create a file under test resources ie. **studentinfo.csv**

2. Setup  test runner  `@RunWith(SerenityParameterizedRunner.class)`

3. Refere to a resource file `@UseTestDataFrom("testdata/studentinfo.csv")`

4. Add fields and setters/getters to your test class representing columns from csv file.

5. Use those fiels inside your test methods

   ```java
   @Title("DataDriven Test for adding multiple students to the Student App.")
   @Test
   public void createMultiplestudents(){
   	ArrayList<String> courses = new ArrayList<>();
       courses.add(course);
       steps.createStudent(firstName,lastName, email, programme, courses)
       	 .statusCode(201);
   }
   ```

##### Parrallel Tests

- We can execute parameterized test in parallel using annotation `@Concurrent` or with threads number `@Concurrent(threads = "4x")`

- For other test we can use  [maven fail save plugin](http://maven.apache.org/surefire/maven-failsafe-plgin/)

  - [Fork Options and Parallel Test Execution](http://maven.apache.org/surefire/maven-failsafe-plugin/examples/fork-options-and-parallel-execution.html)

  ```xml
  <!--
  All methods will be executed parallely
  Cannot be used when methods depend on one another!!!
  <parallel>methods</parallel>  
  <threadCount>10</threadCount>-->
  <!--
  Alternatively we can run test classes (not methods) in parallel
  <forkCount>4</forkCount>
  false - maven will launch as mane jvm processes as in forkCount
  true - maven will try to re-use jvm processes and run only as many as really needed 
  <reuseForks>true</reuseForks>-->


  <plugin>
  	<artifactId>maven-failsafe-plugin</artifactId>
  	<version>2.18</version>
  	<configuration>
  		<includes>
  			<include>**/studentapp/**/*.java</include>
  		</includes>
  		<!--<forkCount>4</forkCount>
  		<reuseForks>true</reuseForks>-->
  		<!-- <parallel>methods</parallel>  -->
  		<!-- <threadCount>10</threadCount>   -->
  		<argLine>-Xmx1024m -XX:MaxPermSize=256m</argLine>
  	</configuration>
  	<executions>
  		<execution>
  			<goals>
  				<goal>integration-test</goal>
  				<goal>verify</goal>
  			</goals>
  		</execution>
      </executions>
  </plugin>
  ```

##### Grouping Tests

- We can group tests tagging them with `@WithTag` or `@WithTags`

  ```java
  //adding  test to group "studentfeature:NEGATIVE"
  @WithTag("studentfeature:NEGATIVE")
  @Title("Provide a 405 status code when incorrect HTTP method is used to access a resource")
  @Test
  public void inValidMethod() {}

  //adding  test to 2 groups "SMOKE and POSITIVE"
  @WithTags(
          {
              @WithTag("studentfeature:SMOKE"),
              @WithTag("studentfeature:POSITIVE")
          }
      )
  @Title("This test will verify if a status code of 200 is returned for GET request")
  public void verifyIftheStatusCodeIs200() {}
  ```

  ​

- Running group **NEGATIVE** 

  - `mvn clean verify -Dtags="studentfeature:NEGATIVE" serenity:aggregate`

- Running groups **NEGATIVE** and **SMOKE**

  - `mvn clean verify -Dtags="studentfeature:NEGATIVE, studentfeature:SMOKE" serenity:aggregate`