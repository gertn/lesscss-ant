LESS CSS Compiler Ant Task
==========================

This library extends <a href="https://github.com/marceloverdijk/lesscss-java">Official LESS CSS Compiler for Java</a> with an ant task so you can use it in an ant or gradle build.

Example - gradle build
----------------------

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
		ant.compileLess(input:  "${projectDir}/less/cfl4tb.less", output: "${projectDir}/src/assets/css/cfl4tb.css", compress: 'true') {
		}
	}