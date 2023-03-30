# Backend

When I just use Spring, it compiles JIT out of the box. JVM doesn't have support for AOT compilation. 
With the use of GraalVM, I can compile my Spring application to native image, which results in another dependency.

This can lead to the first issue, that dependencies should be reduced to a minimum and only be added when needed.
I researched a bit for Spring and GraalVM and just found statements, that it`s developed over time, but that is still
more for a experimental reasons. A use case would be to compile a codebase which is written in Spring and Python.

## AOT

-) Faster startup time

-) Smaller memory footprint

because the native configuration is more accurate, less reflection is required, and less Spring infrastructure is required at runtime.

-) Improved compatibility

it doesn’t try to analyze Spring annotations or the various types to replicate what Spring does at runtime.

-) Runtime flexibility

Performing those optimizations at build time means that there is less runtime flexibility than with the regular 
Spring Boot auto-configuration model. You can still change the HTTP port or the log level of your application when 
running an already compiled Spring Boot application, but you cannot add new beans at runtime by using a profile, for example.

Research Source: https://spring.io/blog/2021/12/09/new-aot-engine-brings-spring-native-to-the-next-level

The BIG disadvantage is that there a lot of drawbacks at the moment, which are listed here: 
https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-with-GraalVM

For what I read so far, I would say that this drawbacks will be tackled in the future. 

Another disadvantage is that you have to compile the application for every platform you want to use it on.

## Conclusion

For now, with everything I read, I would use JIT compilation for Spring applications every time. Maybe AOT can be used
in special edge cases, where I need the codebase just for one platform or really need one of the advantages above. 
I think, that one day, the big drawbacks of AOT in Spring will be tackled, and it will be a good and usable alternative to JIT.
