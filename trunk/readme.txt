If you are using Eclipse then please run the build-jar target in build.xml using ant
This will populate your ivy cache correctly and compiles from Eclipse will work then.

The build file contains the following targets:

build-jar:
This builds the project and leaves the results in madura-pizzaorder-bundle/temp/pizza*.jar
If you have specified a deploy.target in build.properties then the jar file will be copied to that location 

Files and directories:

ant-targets.xml: 
This is an ant build file that is called from the main build. It contains standard
build targets so that the main build.xml file can be kept short.

build.properties:
Defines the properties used by the build. There are some externally defined properties
as well though such as the SVN location.

build.xml:
The main build file. You only need to invoke these targets, not the ones in ant-targets.xml
You probably only want the default target.

ivysettings.xml
This defines the ivy settings used to resolve the libraries.

LICENCE-2.0.html:
Text of Apache License, Version 2.0 which is packaged with the project when it is built.

ivy/:
Holds the ivy jar file

docs/:
Holds the documentation files.

temp/:
Scratch directory that us used during the build.

src/:
The java etc source files

generated/:
This contains mostly java files that are generated from the xsd file and the rules file. It is cleared and rebuilt
on every build.
