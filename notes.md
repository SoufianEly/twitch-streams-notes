@REM  => Comment
---------------------------------------------
SETLOCAL

Set options to control the visibility of environment variables in a batch file.
Syntax
      SETLOCAL

      SETLOCAL {EnableDelayedExpansion | DisableDelayedExpansion}  {EnableExtensions | DisableExtensions}

Key
   EnableDelayedExpansion  Expand variables at execution time rather than at parse time.

   DisableDelayedExpansion Expand variables at parse time rather than at execution time.

   EnableExtensions        Attempt to enable Command extensions. 

   DisableExtensions       Attempt to disable Command extensions. 

-----------------------------------------------
Bash scripting ..

Like fi for if and done for for, esac is the required way to end a case statement.

-----------------------------------------------

A symlink or a Symbolic Link is simply enough a shortcut to another file. It is a file that points to another file.

ln -s target_path link_path

ln -s ~/Desktop/rad_folder ~/Desktop/awesome_link

cd ~/Desktop/awesome_link

-----------------------------------------------

Maven wrapper : https://www.baeldung.com/maven-wrapper & https://github.com/takari/maven-wrapper

These files are from Maven wrapper. It works similarly to the Gradle wrapper.

This allows you to run the Maven project without having Maven installed and present on the path. It downloads the correct Maven version if it's not found (as far as I know by default in your user home directory).

To create or update all necessary Maven Wrapper files execute the following command:

mvn -N io.takari:maven:wrapper
To use a different version of maven you can specify the version as follows:

mvn -N io.takari:maven:wrapper -Dmaven=3.3.3
Both commands require maven on PATH (add the path to maven bin to Path on System Variables) if you already have mvnw in your project you can use ./mvnw instead of mvn in the commands.

-----------------------------------------------

Filter SuppressionFilter rejects audit events for Check violations according to a suppressions XML document in a file. If there is no configured suppressions file or the optional is set to true and suppressions file was not found the Filter accepts all audit events.

A suppressions XML document contains a set of suppress elements, where each suppress element can have the following attributes:

files - a Regular Expression matched against the file name associated with an audit event. It is optional.
checks - a Regular Expression matched against the name of the check associated with an audit event. Optional as long as id or message is specified.
message - a Regular Expression matched against the message of the check associated with an audit event. Optional as long as checks or id is specified.
id - a string matched against the check id associated with an audit event. Optional as long as checks or message is specified.
lines - a comma-separated list of values, where each value is an integer or a range of integers denoted by integer-integer. It is optional.
columns - a comma-separated list of values, where each value is an integer or a range of integers denoted by integer-integer. It is optional.

-----------------------------------------------

editorconfig => https://medium.com/@edvinsantonovs/why-development-teams-should-use-editorconfig-f58011b1cc56

https://editorconfig.org/

EditorConfig helps developers define and maintain consistent coding styles between different editors and IDEs. The EditorConfig project consists of a file format for defining coding styles and a collection of text editor plugins that enable editors to read the file format and adhere to defined styles.

-----------------------------------------------

The glassfish-web.xml file configures a web application (WAR file). The element hierarchy is as follows:

-----------------------------------------------

Cargo is a thin wrapper that allows you to manipulate various type of application containers (Java EE and others) in a standard way.

Cargo provides the following APIs and tools:

A Java API to start/stop/configure any of the supported containers.
A Java API to (remotely or locally) deploy modules into these containers, be it a server alone, a farm or a cluster.
A Java API to parse/create/merge J2EE modules.
ANT tasks wrapping the Java API for configuring, starting, stopping and deploying applications to all supported containers.
A Web interface that wraps the Java API that can be used to configure, start and stop all containers supported by Cargo remotely and at any time.
Maven2/Maven3 plugins wrapping the Java API for configuring, starting, stopping and deploying applications to all supported containers as well as parsing, creating and merging J2EE modules.
These tools and APIs can be used in a standalone fashion or via various IDEs.


-----------------------------------------------

org.springframework.boot.test.web.client.TestRestTemplate
Convenient alternative of RestTemplate that is suitable for integration tests. 

Note: To prevent injection problems this class intentionally does not extend RestTemplate. If you need access to the underlying RestTemplate use getRestTemplate().

If you are using the @SpringBootTest annotation, a TestRestTemplate is automatically available and can be @Autowired into your test. If you need customizations (for example to adding additional message converters) use a RestTemplateBuilder @Bean.


public org.springframework.web.client.RestTemplate getRestTemplate()
Returns the underlying RestTemplate that is actually used to perform the REST operations.

 RequestEntity request = RequestEntity.post(new URI("https://example.com/foo")).accept(MediaType.APPLICATION_JSON).body(body);
 ResponseEntity<MyResponse> response = template.exchange(request, MyResponse.class);

HTTP Req

TestRestTemplate testRestTemplate = new TestRestTemplate();
ResponseEntity<String> response = testRestTemplate.
  getForEntity(FOO_RESOURCE_URL + "/1", String.class);
  
assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

TestRestTemplate provides a constructor with which we can create a template with specified credentials for basic authentication.

TestRestTemplate testRestTemplate = new TestRestTemplate("user", "passwd");
ResponseEntity<String> response = testRestTemplate.getForEntity(URL_SECURED_BY_AUTHENTICATION, String.class);
  
assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

-----------------------------------------------

TestRestTemplate can work as a wrapper for RestTemplate, e.g. if we are forced to use it because we are dealing with legacy code. You can see below how to create such a simple wrapper:

RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
restTemplateBuilder.configure(restTemplate);
TestRestTemplate testRestTemplate = new TestRestTemplate(restTemplateBuilder);
ResponseEntity<String> response = testRestTemplate.getForEntity(FOO_RESOURCE_URL + "/1", String.class);
assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

-----------------------------------------------

TestRestTemplate is not an extension of RestTemplate, but rather an alternative that simplifies integration testing and facilitates authentication during tests. It helps in customization of Apache HTTP client, but also it can be used as a wrapper of RestTemplate.

-----------------------------------------------

Integration Testing with the Maven Cargo plugin

A very common need in the lifecycle of a project is setting up integration testing. Luckily, Maven has built-in support for this exact scenario, with the following phases of the default build lifecycle (from the Maven documentation):

pre-integration-test: Perform actions required before integration tests are executed. This may involve things such as setting up the required environment.

integration-test: Process and deploy the package if necessary into an environment where integration tests can be run.

post-integration-test: Perform actions required after integration tests have been executed. This may including cleaning up the environment.

In order for the package maven phase to generate a deployable war file, the packaging of the project must be: <packaging>war</packaging>.


-----------------------------------------------

pre-clean	execute processes needed prior to the actual project cleaning
clean	remove all files generated by the previous build
post-clean	execute processes needed to finalize the project cleaning

-----------------------------------------------

SpringBootServletInitializer

Extending the SpringBootServletInitializer class also allows us to configure our application when it's run by the servlet container, by overriding the configure() method.

Now, if we package our application as a WAR, we'll be able to deploy it on any web container in a traditional way, which will also execute the logic we added in the configure() method.

If we want to package it as a JAR file, then we'll need to add the same logic to the main() method so that the embedded container can pick it up as well.

-----------------------------------------------

Configuration properties - Spring boot


We use @Configuration so that Spring creates a Spring bean in the application context.

We also use @PropertySource to define the location of our properties file. Otherwise, Spring uses the default location (classpath:application.properties).

@ConfigurationProperties works best with hierarchical properties that all have the same prefix. So we add a prefix of mail.

The Spring framework uses standard Java bean setters, so it is essential that we declare setters for each of the properties.

Note: If we don't use @Configuration in the POJO, then we need to add @EnableConfigurationProperties(ConfigProperties.class) in the main Spring application class to bind the properties into the POJO:



@Configuration
@PropertySource("classpath:configprops.properties")
@ConfigurationProperties(prefix = "mail")
public class ConfigProperties {
     
    private String hostName;
    private int port;
    private String from;
 
    // standard getters and setters
}

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

#Simple properties
mail.hostname=host@mail.com
mail.port=9000
mail.from=mailer@mail.com

Nested properties 

public class Credentials {
    private String authMethod;
    private String username;
    private String password;
 
    // standard getters and setters
}

public class ConfigProperties {
 
    private List<String> defaultRecipients;
    private Map<String, String> additionalHeaders;
    private Credentials credentials;
  
    // standard getters and setters
}

mail.credentials.username=john
mail.credentials.password=password
mail.credentials.authMethod=SHA1

We have explored the @ConfigurationProperties annotation and saw some of the handy features it provides like relaxed binding and Bean Validation.

-----------------------------------------------

Spring Boot jars are shipped with meta-data files that provide details of all supported configuration properties. The files are designed to allow IDE developers to offer contextual help and “code completion” as users are working with application.properies or application.yml files.

The majority of the meta-data file is generated automatically at compile time by processing all items annotated with @ConfigurationProperties.


-----------------------------------------------

public class WebServerPortFileWriter
extends Object
implements org.springframework.context.ApplicationListener<WebServerInitializedEvent>

An ApplicationListener that saves embedded server port and management port into file. This application listener will be triggered whenever the server starts, and the file name can be overridden at runtime with a System property or environment variable named "PORTFILE" or "portfile".

-----------------------------------------------
Interface BiFunction<T,U,R>

Functional Interface:

This is a functional interface and can therefore be used as the assignment target for a lambda expression or method reference.

-----------------------------------------------

org.springframework.util.StringUtils


This class delivers some simple functionality that should really be provided by the core Java String and StringBuilder classes. It also provides easy-to-use methods to convert between delimited strings, such as CSV strings, and collections and arrays.


-----------------------------------------------

Interface BeforeTestExecutionCallback (org.junit.jupiter.api.extension)

BeforeTestExecutionCallback defines the API for Extensions that wish to provide additional behavior to tests immediately before each test is executed.


Callback that is invoked immediately before each test is executed.

beforeTestExecution
void beforeTestExecution(ExtensionContext context)
                  throws Exception

-----------------------------------------------
Junit 5 Extensions

As the name suggests, the purpose of Junit 5 extensions is to extend the behavior of test classes or methods, and these can be reused for multiple tests.

-----------------------------------------------

public File getParentFile()

Returns the abstract pathname of this abstract pathname's parent, or null if this pathname does not name a parent directory.

-----------------------------------------------

public boolean mkdirs()
Creates the directory named by this abstract pathname, including any necessary but nonexistent parent directories. Note that if this operation fails it may have succeeded in creating some of the necessary parent directories.

Returns:
true if and only if the directory was created, along with all necessary parent directories; false otherwise

-----------------------------------------------

org.springframework.util.FileSystemUtils

Utility methods for working with the file system.

static void	copyRecursively(File src, File dest)
Recursively copy the contents of the src file/directory to the dest file/directory.

static void	copyRecursively(Path src, Path dest)
Recursively copy the contents of the src file/directory to the dest file/directory.

static boolean	deleteRecursively(File root)
Delete the supplied File - for directories, recursively delete any nested directories or files as well.

static boolean	deleteRecursively(Path root)
Delete the supplied File - for directories, recursively delete any nested directories or files as well.

Methods inherited from class java.lang.Object
clone, equals, finalize, getClass, hashCode, notify, notifyAll, toString, wait, wait, wait

-----------------------------------------------

@ParameterizedTest is used to signal that the annotated method is a parameterized test method.
@ParameterizedTest methods must not be private or static.

@MethodSource is an ArgumentsSource which provides access to values returned by methods of the class in which this annotation is declared.

By default such methods must be static unless the test class is annotated with @TestInstance(Lifecycle.PER_CLASS).

The values returned by such methods will be provided as arguments to the annotated @ParameterizedTest method.



-----------------------------------------------

https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/File.html

getParentFile()	

Returns the abstract pathname of this abstract pathname's parent, or null if this pathname does not name a parent directory.

-----------------------------------------------
File location = new File(this.testClass.getProtectionDomain().getCodeSource().getLocation().toURI());

public ProtectionDomain getProtectionDomain()

Returns the ProtectionDomain of this class. If there is a security manager installed, this method first calls the security manager's checkPermission method with a RuntimePermission("getProtectionDomain") permission to ensure it's ok to get the ProtectionDomain.


public final CodeSource getCodeSource()
Returns the CodeSource of this domain.

public final URL getLocation()
Returns the location associated with this CodeSource.

public URI toURI() throws URISyntaxException
Returns a URI equivalent to this URL. This method functions in the same way as new URI (this.toString()).
Note, any URL instance that complies with RFC 2396 can be converted to a URI. However, some URLs that are not strictly in compliance can not be converted to a URI.

-----------------------------------------------

Spring boot - Docker 

DockerClient docker = createClient();
String imageId = buildImage(os, version, docker);
String container = createContainer(docker, imageId, script);
docker.startContainerCmd(container).exec();


Here, we apply to the DockerClientBuilder class to create a connection by accepting the default settings:
DockerClient dockerClient = DockerClientBuilder.getInstance().build();
Properties properties = new Properties();
properties.setProperty("registry.email", "info@baeldung.com");
properties.setProperty("registry.password", "baeldung");
properties.setProperty("registry.username", "baaldung");
properties.setProperty("DOCKER_CERT_PATH", "/home/baeldung/.docker/certs");
properties.setProperty("DOCKER_CONFIG", "/home/baeldung/.docker/");
properties.setProperty("DOCKER_TLS_VERIFY", "1");
properties.setProperty("DOCKER_HOST", "tcp://docker.baeldung.com:2376");
DefaultDockerClientConfig config
  = DefaultDockerClientConfig.createDefaultConfigBuilder()
    .withProperties(properties).build();
DockerClient dockerClient = DockerClientBuilder.getInstance(config).build();


Now that we have an established connection, we can list all the running containers located on the Docker host:
List<Container> containers = dockerClient.listContainersCmd()
  .withShowSize(true)
  .withShowAll(true)
  .withStatusFilter("exited").exec()


It's an equivalent of:
$ docker ps -a -s -f status=exited
# or 
$ docker container ls -a -s -f status=exited

Container 
----
Creating a container is served with the createContainerCmd method. We can declare more complex declaration using the available methods starting with the “with” prefix.
Let's assume that we have a docker create command defining a host-dependent MongoDB container listening internally on port 27017:
CreateContainerResponse container
  = dockerClient.createContainerCmd("mongo:3.6")
    .withCmd("--bind_ip_all")
    .withName("mongo")
    .withHostName("baeldung")
    .withEnv("MONGO_LATEST_VERSION=3.6")
    .withPortBindings(PortBinding.parse("9999:27017"))
    .withBinds(Bind.parse("/Users/baeldung/mongo/data/db:/data/db")).exec();

dockerClient.startContainerCmd(container.getId()).exec();
dockerClient.stopContainerCmd(container.getId()).exec();
dockerClient.killContainerCmd(container.getId()).exec();

Image
----
String snapshotId = dockerClient.commitCmd("3464bb547f88")
  .withAuthor("Baeldung <info@baeldung.com>")
  .withEnv("SNAPSHOT_YEAR=2018")
  .withMessage("add git support")
  .withCmd("git", "version")
  .withRepository("alpine")
  .withTag("3.6.git").exec();

$ docker image ls alpine --format "table {{.Repository}} {{.Tag}}"
REPOSITORY TAG
alpine     3.6.git

List<Image> images = dockerClient.listImagesCmd().exec();
$ docker image ls --format "table {{.Repository}} {{.Tag}}"

DOCKERFILE 

FROM alpine:3.6
RUN apk --update add git openssh && \
  rm -rf /var/lib/apt/lists/* && \
  rm /var/cache/apk/*
ENTRYPOINT ["git"]
CMD ["--help"]

String imageId = dockerClient.buildImageCmd()
  .withDockerfile(new File("path/to/Dockerfile"))
  .withPull(true)
  .withNoCache(true)
  .withTag("alpine:git")
  .exec(new BuildImageResultCallback())
  .awaitImageId();

Push

dockerClient.pushImageCmd("baeldung/alpine")
  .withTag("git")
  .exec(new PushImageResultCallback())
  .awaitCompletion(90, TimeUnit.SECONDS);

Pull

dockerClient.pullImageCmd("baeldung/alpine")
  .withTag("git")
  .exec(new PullImageResultCallback())
  .awaitCompletion(30, TimeUnit.SECONDS);

dockerClient.removeImageCmd("beaccc8687ae").exec();
List<SearchItem> items = dockerClient.searchImagesCmd("Java").exec();


Docker Volumes

Volumes are easier to back up or migrate than bind mounts.
You can manage volumes using Docker CLI commands or the Docker API.
Volumes work on both Linux and Windows containers.
Volumes can be more safely shared among multiple containers.
Volume drivers let you store volumes on remote hosts or cloud providers, to encrypt the contents of volumes, or to add other functionality.
New volumes can have their content pre-populated by a container.

List volumes 
ListVolumesResponse volumesResponse = dockerClient.listVolumesCmd().exec();
List<InspectVolumeResponse> volumes = volumesResponse.getVolumes();

InspectVolumeResponse volume 
  = dockerClient.inspectVolumeCmd("0220b87330af5").exec();

CreateVolumeResponse namedVolume 
  = dockerClient.createVolumeCmd().withName("myNamedVolume").exec();

dockerClient.removeVolumeCmd("myNamedVolume").exec();

Network Management 

List<Network> networks = dockerClient.listNetworksCmd().exec();

CreateNetworkResponse networkResponse = dockerClient.createNetworkCmd()
  .withName("baeldung")
  .withIpam(new Ipam()
    .withConfig(new Config()
    .withSubnet("172.36.0.0/16")
    .withIpRange("172.36.5.0/24")))
  .withDriver("bridge").exec();

$ docker network create \
  --subnet=172.36.0.0/16 \
  --ip-range=172.36.5.0/24\
  baeldung

dockerClient.removeNetworkCmd("baeldung").exec();

-----------------------------------------------

Awaitility — a library which provides a simple domain-specific language (DSL) for asynchronous systems testing.

With Awaitility, we can express our expectations from the system in an easy-to-read DSL.

DSL
----
A domain specific language is a language that's written to deal with a specific domain or set of concerns. There are a lot of them around, like make, ant, and rake for describing software builds, or lexx and yacc for language construction. In recent years, they've become popular as some things have combined to make them easier to build. Big among those things has been the increasing popularity of Ruby, which has several features that make it easy to build new DSLs.

    DSLs are very common in computing: examples include CSS, regular expressions, make, rake, ant, SQL, HQL, many bits of Rails, expectations in JMock, graphviz's dot language, FIT, strut's configuration file....

-----------------------------------------------

jmustache : Zero dependencies. You can include this single tiny library in your project and start making use of templates. +++

proguard - jarjar

https://github.com/spullara/mustache.java +++

-----------------------------------------------

List<String> arrlist = new ArrayList<String>(); 

arrlist.add("Ram"); 
arrlist.add("Gopal"); 
arrlist.add("Verma"); 
Enumeration<String> e = Collections.enumeration(arrlist); 
System.out.println("\nEnumeration over list: "); 
while (e.hasMoreElements()) 
System.out.println("Value is: " + e.nextElement()); 

-----------------------------------------------

Undertow is a flexible performant web server written in java, providing both blocking and non-blocking API’s based on NIO.

-----------------------------------------------

http://tutorials.jenkov.com/java-nio/index.html

Working with non-blocking I/O

Using non-blocking I/O in the right situation will improve throughput, latency, and/or responsiveness of your application. It also allows you to work with a single thread, potentially getting rid of synchronization between threads and all the problems associated with it. Node.js is single-threaded, yet can handle millions of connections with a couple of GB RAM without problems.

-----------------------------------------------

Apache ActiveMQ™ is the most popular open source, multi-protocol, Java-based messaging server. It supports industry standard protocols so users get the benefits of client choices across a broad range of languages and platforms.

-----------------------------------------------

CapturedOutput

Provides access to System.out and System.err output that has been captured by the OutputCaptureExtension. Can be used to apply assertions either using AssertJ or standard JUnit assertions. For example:
 assertThat(output).contains("started"); // Checks all output
 assertThat(output.getErr()).contains("failed"); // Only checks System.err
 assertThat(output.getOut()).contains("ok"); // Only checks System.put

-----------------------------------------------

Spring Boot provides two interfaces, CommandLineRunner and ApplicationRunner, to run specific pieces of code when an application is fully started. These interfaces get called just before run() once SpringApplication completes.

-----------------------------------------------
CommandLineRunner(+) and ApplicationRunner

When you want to execute some piece of code exactly before the application startup completes, you can use it then. In one of our projects, we used these to source data from other microservices via service discovery, which was registered in Consul.


-----------------------------------------------
import org.springframework.stereotype.Component;
@Component

Indicates that an annotated class is a "component". Such classes are considered as candidates for auto-detection when using annotation-based configuration and classpath scanning.

Other class-level annotations may be considered as identifying a component as well, typically a special kind of component: e.g. the @Repository annotation or AspectJ's @Aspect annotation.



-----------------------------------------------

JmsTemplate 

JmsTemplate class handles the creation and releasing of resources when sending or synchronously receiving messages.

In order to connect and be able to send/receive messages, we need to configure a ConnectionFactory.

SingleConnectionFactory – is an implementation of ConnectionFactory interface, that will return the same connection on all createConnection() calls and ignore calls to close()

CachingConnectionFactory – extends the functionality of the SingleConnectionFactory and adds enhances it with a caching of Sessions, MessageProducers, and MessageConsumers

The SimpleMessageConverter is able to handle TextMessages, BytesMessages, MapMessages, and ObjectMessages. This class implements the MessageConverter interface.

MessageConventer 

Moreover, we can create custom message conversion functionality simply by implementing the MessageConverter interface's toMessage() and FromMessage() methods.

implements MessageConverter  : fromMessage(Message) - toMessage(Object,Session)

public class SampleJmsMessageSender {
 
    private JmsTemplate jmsTemplate;
    private Queue queue;
 
    // setters for jmsTemplate & queue
 
    public void simpleSend() {
        jmsTemplate.send(queue, s -> s.createTextMessage("hello queue world"));
    }

    public void sendMessage(Employee employee) { 
        System.out.println("Jms Message Sender : " + employee); 
        Map<String, Object> map = new HashMap<>(); 
        map.put("name", employee.getName()); map.put("age", employee.getAge()); 
        jmsTemplate.convertAndSend(map); 
    }
}

Basic configuration with java annotations 
@JmsListener(destination = "myDestination")
@SendTo("outbound.topic")
public void SampleJmsListenerMethod(Message<Order> order) { ... }


@Configuration
@EnableJms
public class AppConfig {
 
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory 
          = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        return factory;
    }
}

Error handler : 

@Service
public class SampleJmsErrorHandler implements ErrorHandler

-----------------------------------------------

In essence, Actuator brings production-ready features to our application.

Monitoring our app, gathering metrics, understanding traffic or the state of our database becomes trivial with this dependency.

-----------------------------------------------
InfoContributor - import org.springframework.boot.actuate.info.InfoContributor; / /info endpoint.

Now let's go one step further and expose some useful data from the persistence storage.

To achieve this, we need to implement InfoContributor interface and override the contribute() method:

@Component
public class TotalUsersInfoContributor implements InfoContributor {
 
    @Autowired
    UserRepository userRepository;
 
    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Integer> userDetails = new HashMap<>();
        userDetails.put("active", userRepository.countByStatus(1));
        userDetails.put("inactive", userRepository.countByStatus(0));
 
        builder.withDetail("users", userDetails);
    }
}

This approach provides us a lot of flexibility regarding what we can expose to our /info endpoint:

{
  ...other /info data...,
  ...
  "users": {
    "inactive": 2,
    "active": 3
  }
}
-----------------------------------------------

Besides using the existing endpoints provided by Spring Boot, we could also create an entirely new one.
Firstly, we'd need to have the new endpoint implement the Endpoint<T> interface:

-----------------------------------------------

Src: https://www.baeldung.com/spring-boot-actuators

Enable All Endpoints

management.endpoint.shutdown.enabled=true
-----------------------------------------------
org.springframework.boot.actuate.endpoint.annotation

public @interface Endpoint

Identifies a type as being an actuator endpoint that provides information about the running application. Endpoints can be exposed over a variety of technologies including JMX and HTTP.

Most @Endpoint classes will declare one or more @ReadOperation, @WriteOperation, @DeleteOperation annotated methods which will be automatically adapted to the exposing technology (JMX, Spring MVC, Spring WebFlux, Jersey etc.).
-----------------------------------------------
import org.springframework.boot.actuate.autoconfigure.web.server.LocalManagementPort;

Annotation at the field or method/constructor parameter level that injects the HTTP management port that got allocated at runtime. Provides a convenient alternative for @Value("${local.management.port}").

-----------------------------------------------
WebSecurityConfigurerAdapter  - 
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests((requests) -> {
			requests.mvcMatchers("/actuator/beans").hasRole("BEANS");
			requests.requestMatchers(EndpointRequest.to("health", "info")).permitAll();
			requests.requestMatchers(EndpointRequest.toAnyEndpoint().excluding(MappingsEndpoint.class))
					.hasRole("ACTUATOR");
			requests.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
			requests.antMatchers("/foo").permitAll();
			requests.antMatchers("/**").hasRole("USER");
		});
		http.cors(Customizer.withDefaults());
		http.httpBasic();
	}
-----------------------------------------------

-----------------------------------------------

-----------------------------------------------

-----------------------------------------------
-----------------------------------------------

-----------------------------------------------

-----------------------------------------------

-----------------------------------------------

-----------------------------------------------
-----------------------------------------------

-----------------------------------------------

-----------------------------------------------

-----------------------------------------------

-----------------------------------------------
-----------------------------------------------

-----------------------------------------------

-----------------------------------------------

-----------------------------------------------

-----------------------------------------------
-----------------------------------------------

-----------------------------------------------

-----------------------------------------------

-----------------------------------------------

-----------------------------------------------