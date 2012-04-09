LESS CSS Compiler Ant Task
==========================

This library extends <a href="https://github.com/marceloverdijk/lesscss-java">Official LESS CSS Compiler for Java</a> with an ant task so you can use it in an ant or gradle build.

Building from source
--------------------
Can be built with <a href="http://maven.apache.org">Maven</a> by using the following command:
	mvn package

To build it and install it into your local maven repo use the following command:
	mvn install

Example - gradle build
----------------------
First make sure you have the snapshot in your local repository (see Building from source)

	configurations { lessClasspath }
	
	dependencies {
		lessClasspath group: 'org.lesscss', name: 'lesscss-ant', version: '1.0.0-SNAPSHOT'
	}
	
	repositories {
		mavenLocal()
		mavenCentral()
	}
	
	task lesscss << {
		ant.taskdef(name: 'compileLess', classname: 'org.lesscss.LessCompilerTask', classpath: configurations.lessClasspath.asPath)
		ant.compileLess(input:  "${projectDir}/less/main.less", output: "${projectDir}/src/assets/css/main.css", compress: 'true') {
		}
	}
