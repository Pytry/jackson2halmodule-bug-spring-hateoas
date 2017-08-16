"org.springframework.hateoas:spring-hateoas:0.23.0" 
does not work properly with version "2.9.0+" of the 
"com.fasterxml.jackson.core" libraries.

To verify, first run 

    mvn clean test
    
This build should succeed, since it is using 
"<jackson.version>2.8.5</jackson.version>"

Now run 

    mvn clean test -P fail 

The build will now fail because it is using 
"<jackson.version>2.9.0</jackson.version>".

This failure is caused by "Jackson2HalModule::line 613", where 
a null is passed in as the first constructor parameter, which 
causes a NullPointerException. 

This bug is fixed in the current SNAPSHOT version. Unfortunately,
using a version that is equal to or less than "2.8.8" is not an option 
due to the deserialization vulnerability as reported by issue 
"CVE-2017-4995" of the national vulnerability database.

http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2017-4995

The best workaround is to use jackson.version "2.8.8.1" or "2.8.9". 
