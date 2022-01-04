# Epidemic Simulator
A Java application created to model the spread of diseases. Various parameters such as infectivity, asymptomatic carriers, and levels of social distancing can be adjusted by the user.
Link to download the [installer](https://github.com/D-S-Software/EpidemicSimulator/releases/download/v1.5.0/EpidemicSimulatorSetup_v1.5.0.exe) for v.1.5.0

# Motivation and Inspiration
Created during the COVID-19 lockdown of spring 2020 and inspired by Grant Sanderson's of [3Blue1Brown epidemic simulation](https://www.youtube.com/watch?v=gxAaO2rsdIs), this application was created in order to further explore and model the effect of various adjustments to a disease outbreak scenario.

# Features 
- Intuitive and Expansive Design
  - Adjust individual parameters to model various simulation aspects
  - Live graphs and statistics available on a side panel
- Adjustable parameters
  - Number of people
  - Disease lethality and infectivity
  - Single, quad and octo board subdivision
      - Variable percentage of "Travelers" who wander between the subsections
  - Quarantine section
  - Asysmptomatic carriers
  - Many others!
- Easy Setup
  - [Download installer](https://github.com/D-S-Software/EpidemicSimulator/releases/download/v1.5.0/EpidemicSimulatorSetup_v1.5.0.exe) (Windows only)
  

# Simple Demo
The application can be configured to represent a rectanglular board for a various number of individuals to inhabit: 

![Simple Demo](EpidemicSimGifs/EpidemicSimGeneralShowcase.gif)

# Board Divisions and Quarantine Demo
The board can be subdivided into seperate sections with and adjustable percentage of people able to move between boards. With a quarantine board, there is the capability of sending a given percentage individuals to the quarantine section:

![2nd Demo](EpidemicSimGifs/EpidemicSimQuadQuarShowcase.gif)

# Asympotmatic Individuals Demo
A percentage of cases can be set to asympotmatic that ignore the set quarantine procedures:

![3rd Demo](EpidemicSimGifs/EpidemicSimAsymptomaticShowcase.gif)

# CSV Capabilities
Access to data through an auto-generated CSV file in program folder allows for saving and exporting of any particular simulation.
Data can be imported to any spreadsheet software.

# Frameworks and Libraries used
- Swing
- knowm/XChart https://github.com/knowm/XChart#-xchart
- Fonts used under SIL Open Font License
- Music used under royalty free tracks from Scott Buckley ([licence](https://creativecommons.org/licenses/by/3.0/legalcode)).
