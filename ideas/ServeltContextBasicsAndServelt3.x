MyAppServletContextListener.java
package com.mkyong.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyAppServletContextListener 
               implements ServletContextListener{
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContextListener destroyed");
	}

        //Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContextListener started");	
	}
}
1.2 Put it in the deployment descriptor.

web.xml
<web-app ...>
   <listener>
	<listener-class>
             com.mkyong.listener.MyAppServletContextListener 
        </listener-class>
   </listener>
</web-app>
1.3 Starting Tomcat….

//...
Dec 2, 2009 10:11:46 AM org.apache.catalina.core.StandardEngine start
INFO: Starting Servlet Engine: Apache Tomcat/6.0.20

ServletContextListener started   <-------------- Your code here, before we application --->

Dec 2, 2009 10:11:46 AM org.apache.coyote.http11.Http11Protocol start
INFO: Starting Coyote HTTP/1.1 on http-8080
//...
INFO: Server startup in 273 ms
--------------------



JBoss release 6.0.0 has been just released. One of the most interesting news is the integration of Servlet 3.0 specification in the Web Server container. Let's see some of the most interesting news.
The most important features introduced with the new Servlet specification can be divided into the following main areas:

- Easier development with annotations: following the path outlined by Java EE 1.5, the Servlet specification 3.0 heavily uses annotations to declare Servlet, Filters, Listeners and Security. The configuration file web.xml is now optional.

- Web application modularity: now you can even break your configuration into fragment xml files which can be added as well to an EJB application (As soon as EJB 3.1 will be available too on JBoss AS 6 this will simplify a lot application deployment)

- Asynchronous servlet: Using Asynchronous processing avoids blocking requests by allowing the thread to perform some other operation, meanwhile the input is returned to the client.

- Programmatic instantiation of Servlets/Filters: one of my favourite features of Servlet 3.0 specification is the introduction of additional methods to the ServletContext to register Web components like Servlets or Filters or even Roles.

Let's see some concrete examples:
Servlet Annotations

package sample;
 
import java.io.IOException;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
 
  
@WebServlet(name="testServlet", urlPatterns={"/hello"},
        initParams={ @WebInitParam(name="simpleParam", value="paramValue") } )
      
public class TestServlet extends HttpServlet {
            
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String simpleParam = getServletConfig().getInitParameter("simpleParam");
            out.println("Hello World "+simpleParam);
                out.close();
    }
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request,response);
    }
 
}
In the above code we have registered out TestServlet under the urlPatterns "/hello" (notice the plural as there can be more then one). Additionally we've set an init param named "simpleParam". No web.xml needed to run this Servlet.

You can declare as well Filters using annotations, like in this example:

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
 
@WebFilter(urlPatterns={"/*"},
        initParams={ @WebInitParam(name="simpleParam", value="paramValue") })
        public class TestFilter implements Filter {
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
 
        writer.println("===============");
        writer.println("Filter intercepted!");
        writer.println("===============");
 
        // Log the resulting string
        writer.flush();
        filterConfig.getServletContext().
        log(sw.getBuffer().toString());
 
        chain.doFilter(request, response);
 
    }
 
    private FilterConfig filterConfig = null;
    public void init(FilterConfig filterConfig) 
    throws ServletException {
        this.filterConfig = filterConfig;
    }
 
    @Override
    public void destroy() {    }
}
The above filter will intercept all request issued to the Web Context.

Another useful annotation introduced is the @WebListener annotation which can be used to tag a Java Class as WebListener:

package sample;
 
import javax.servlet.*;
 
@javax.servlet.annotation.WebListener
public class SessionListener implements ServletContextListener {
 
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context destroyed!");
         
    }
 
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context created!");
    }
 
      
}
Aysnchronous Servlets


Asynchronous processing allows the thread to issue a call to the resource and return back to the container without getting blocked. The thread can perform other tasks, which makes Servlet 3.0's performance more efficient. Implementing an Asynchronous Servlet requires using the AsyncContext, which can be obtainer from the HTTP request object. Here's an example:

package sample;
 
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;
 
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 
import java.util.Queue;
 
 
@WebServlet(urlPatterns = {"/synch"}, asyncSupported = true)
public class AsynchServlet extends HttpServlet  {
    private static final Queue queue = new ConcurrentLinkedQueue();
 
       public void doGet(HttpServletRequest request, HttpServletResponse response) {
           final AsyncContext ac = request.startAsync();
           ac.setTimeout(1 * 60 * 1000);
           queue.add(ac);
           ac.addListener(new AsyncListener() {
            
        @Override
        public void onError(AsyncEvent arg0) throws IOException {
            System.out.println("onError"); 
             
        }
          
        public void onComplete(AsyncEvent event) throws IOException {
            System.out.println("onComplete"); 
            queue.remove(ac);
            }
        public void onTimeout(AsyncEvent event) throws IOException {
            System.out.println("onTimeout"); 
        queue.remove(ac);
        }
        @Override
        public void onStartAsync(AsyncEvent arg0) throws IOException {
            System.out.println("onStartAsync"); 
             
        }
          
           });
           queue.add(ac);
       }
 
       public void doPost(HttpServletRequest request, HttpServletResponse response) {
          doGet(request,response);
       }
 
  
}
The Asynch listener interface needs implementing a few callback methods (onComplete, onTimeout, onStartAsync, onError) which are triggered during the lifecycle of the Asynchronous Servlet.
Programmatic Servlets/Filters setup


One of my favourite adds-on is the ability of declare Servlets or Filters programmatically: think about mapping a Servlet with a name collected by an external source:

public void contextInitialized(ServletContextEvent sce) {
    System.out.println("Context created!");
    ServletContext sc = sce.getServletContext();
 
        // Get it from any resource
        String servletMapping = "/dynamic"
 
    ServletRegistration sr = sc.addServlet ("DynaServlet","sample.DynamicServlet");
         
    sr.setInitParameter("servletInitName", "servletInitValue");
    sr.addMapping(servletMapping);
}
Web Fragments


For better pluggability and less configuration for developers, in Servlet 3.0has been introduced the notion of web descriptor fragments. A web fragment is a part or all of the web.xml that can be specified and included in a library or framework jar file.

Here's an example of web fragment:

<web-fragment>
  <servlet>
    <description></description>
    <display-name>TestServlet</display-name>
    <servlet-name>TestServlet</servlet-name>
    <servlet-class>sample.TestServlet</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>TestServlet</servlet-name>
    <url-pattern>/TestServlet</url-pattern>
  </servlet-mapping>
</web-fragment>
Where to place web fragments ? If a framework is packaged as a jar file and has metadata information in the form of deployment descriptor then the web fragment needs to be placed in the META-INF/ folder of the jar file.

On the other hand, if a framework wants its web-fragment.xml honored in such a way that it augments a web application's web.xml, the framework must be bundled within the web application's WEB-INF/lib directory

----------------------


Setting context-params in servlet 3.0
If you are using spring boot or working towards java config, you might have or are looking to get away from web.xml based configured web applications. Various frameworks, such as JSF, rely heavily on context parameters for initialization and configuration. If you aren't familiar, < context-param> is available to all parts of the web application and typically can be retrieved by getServletContext().getInitParameter("facelets.DEVELOPMENT");

As you are working at ripping away each piece of the web.xml, one item that wasn't so obvious was how to set < context-param> in java config. The Servlet 3.0 ServletContext API allows for setting init-params, context-params among other things programmatically. One way to get a hook into servlet context in spring is to implement WebApplicationInitializer. WebApplicationInitializer classes are detected automatically by SpringServletContainerInitializer which itself is bootstrapped by a servlet 3.0 container.

Below is snapshot of how to configure context parameters in servlet 3.0 while using spring.

Servlet 2.0
<context-param>
    <param-name>facelets.DEVELOPMENT</param-name>
    <param-value>true</param-value>
</context-param>


	Servlet 3.0
	
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application implements ServletContextInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException {
        
        servletContext.setInitParameter(
                "facelets.DEVELOPMENT",
                "true");
    }
}

Servlet 3.0 Annotations
By Shing Wai Chan on Dec 02, 2008

The JSR 315: Java Servlet 3.0 Specification expert group is in the process of making Public Review available. You can look at Rajiv's blog for more details. The reference implementation is available in GlassFish v3 nightly build. In Servlet 3.0, for ease of development, several new annotations are defined. These annotations are resided in the package javax.servlet.annotation. They are intended to provide meta data only. In other words, one still need to extend the corresponding class or implement the corresponding interface.
Now, one can have Servlet, Filter and ServletContextListener in a war file without web.xml. In this blog, I will discuss the following annotations:

@WebServlet
@ServletFilter
@WebServletContextListener
Servlet Annotation ( @WebServlet )

In JSR 315, one can specify the servlet meta data by using @WebServlet. For instance,
    @WebServlet(name="mytest", 
        urlPatterns={"/myurl"}, 
        initParams={ @InitParam(name="n1", value="v1"), @InitParam(name="n2", value="v2") }) 
    public class TestServlet extends javax.servlet.http.HttpServlet { 
        .... 
    }

In this example, the class TestServlet is a servlet as it extends HttpServlet. The @WebServlet provides the following meta data:

the name of the servlet, mytest, corresponds to <servlet-name> under <servlet> in web.xml
the url pattern of the servlet, /myurl, corresponds to <url-pattern> under <servlet-mapping> in web.xml
initialization parameters of the servlet, n1=v1, n2=v2, corresponds to <init-param> under <servlet> in web.xml 
  <init-param> 
    <param-name>n1</param-name> 
    <param-value>v1</param-value> 
  </init-param> 
  <init-param> 
    <param-name>n2</param-name> 
    <param-value>v2</param-value> 
  </init-param> 
Note that in this case, @InitParam is used to specify the name/value pairs.
Servlet Filter Annotation ( @ServletFilter )

One can specify the servlet filter meta data by using @ServletFilter. For instance,
    @ServletFilter(urlPatterns={"/myurl"}. 
        initParams={ @InitParam(name="mesg", value="my filter") }) 
    public class TestFilter implements javax.servlet.Filter { 
        .... 
        public void init(FilterConfig filterConfig) throws ServletException { 
            .... 
        } 

        public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException { 
            .... 
        } 

        public void destroy() { 
            .... 
        } 
    }

In this example, the class TestFilter is a servlet filter as it implements Filter. The @ServletFilter provides the following meta data:

the url pattern of the filter applied, /myurl
initialization parameter of the filter, mesg=my filter, corresponds to <init-param> under <filter> in web.xml 
Note that in this case, @InitParam is used to specify the name/value pairs.
Servlet Context Listener Annotation ( @WebServletContextListener )

One can specify the servlet content listener met data by using @WebServletContextListener. For instance,
    @WebServletContextListener 
    public class TestServletContextListener implements javax.servlet.ServletContextListener { 
        .... 
        public void contextInitialized(ServletContextEvent sce) { 
            .... 
        } 

        public void contextDestroyed(ServletContextEvent sce) { 
            .... 
        } 
    }

In this example, the class TestServletContextListener is a servlet context listener as it implements ServletContextListener. The @WebServletContextListener provides the meta data that this is a servlet context listener in a given war file.
