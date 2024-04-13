rootProject.name = "oop-java"
include("presentation:src:main:java:presentation")
findProject(":presentation:src:main:java:presentation")?.name = "presentation"
include("presentation:presentation")
findProject(":presentation:presentation")?.name = "presentation"
include("presentation")
include("application")
include("infrastructure")
include("infrastructure")
include("application")
