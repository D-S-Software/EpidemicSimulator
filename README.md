# Epidemic Simulator
A Java application created to model the spread of diseases. Various parameters such as infectivity, asymptomatic carriers, and levels of social distancing can be adjusted by the user.

# Motivation and Inspiration
Created during the COVID-19 lock of spring 2020 and inspired by Grant Sanderson of 3Blue1Brown's epidemic simulation, https://www.youtube.com/watch?v=gxAaO2rsdIs, this application was created in order to further explore and model the effect of various adjustments to disease outbreak scenario.

# Features 
- Intuitive and Expansive Design
  - Adjust individual parameters to model various simulation aspects
  - Live graphs and statistics available on a side panel
- Adjustable parameters
  - Number of people
  - Disease lethality and infectivity
  - Single, quad and octo board subdivision
      - Variable percentage of "Roamers" who wander between the subsections
  - Quarantine section
  - Asysmptomatic carriers
- Easy Installation
  - Complete with an installer
  

# Simple Demo
The application can be configured to represent a single rectanglular board for a various number of individuals to inhabit: 

![Simple Demo](EpidemicSimGifs/EpidemicSimGeneralShowcase.gif)

# Board Divisions and Quarantine Demo
The board can be subdivided with the capability of sending a given percentage individuals to the quarantine section:

![2nd Demo](EpidemicSimGifs/EpidemicSimQuadQuarShowcase.gif)

# Asympotmatic Individuals Demo

![3rd Demo](EpidemicSimGifs/EpidemicSimAsymptomaticShowcase.gif)

# CSV Capabilities
Access to data through an auto-generated CSV file allows for saving and exporting of any particular simulation

# Frameworks and Libraries used
- Swing
- knowm/XChart https://github.com/knowm/XChart#-xchart
