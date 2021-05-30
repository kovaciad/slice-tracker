# Slice Tracker

***

**Design Document**
Aidan Kovacic, Joseph Schmidt, Jamal Brown, Austin Garrison, Kyler Severance

***

## Introduction

***

Do you have a hard time keeping track of your slice files for your 3D printer? Slice Tracker can help you:
* Organize your slices
* Keep track of the time each one will take to print
* Keep track of how much material will be used by each print
* Keep track of how much material you have left
* Send prints to your printer via octoprint (?)

***

## Functional Requirements

### Requirement 100: Save Slice Files
**Scenario**

As a user interested in 3D printing, I want to be able to add or edit print files of my choosing so that I can easily keep track of what I want to print.
**Examples**
1.1
**Given** a slice file of an Eevee model 
**When** I input the Eevee slice file and a group of parameters (title, author, printer, material, time, URL)
* Eevee, PatrickArt, Ender 3 Pro, PLA+, 3 hours 9 minutes, https://www.thingiverse.com/thing:3857781)

**Then** the Eevee model should be listed with its parameters viewable if clicked

1.2
**Given** multiple slice files for a dragon figure
**When** I input the slice files and a group of parameters + group name (title, author, printer, material, time, URL)
* Forest Dragon, dutchmogul, HALOT-ONE, Resin, 8 hours (total, will need to be added up by various files), https://www.thingiverse.com/thing:87458/files)

**Then** the Dragon model should be listed with multiple subfiles visible with data about each.

1.3
**Given** a non-slice file (or no file at all)
**When** no data is entered in any capacity
**Then** the file should be rejected and no data saved
*All models listed have a Creative Commons license*

### Requirement 101: Save Printers and Materials
**Scenario**

As a user interested in 3D printing, I want to be able to save data on the materials and printers I will be using for 3D printing.

**Examples**
2.1
**Given** an Ender 3 Pro 3D Printer
**When** "Ender 3 Pro" is inputted as the name, brand as "Creality," and type as "FDM"
**Then** The Ender 3 Pro should become viewable and editable and become an option in the "printer" parameter when inputting models.

2.2
**Given** a HALOT-One resin printer
**When** "HALOT-One" is inputted as the name, brand as "Creality," and type as "Resin"
**Then** The HALOT-One should become viewable and editable and become an option in the "printer" parameter when inputting models.

2.3
**Given** PLA+ Filament
**When** "PLA+" is entered as the type, "Blue PLA+ 1" as the name, "blue" as the color, "205°C" as printing temperature, and "1 kg" as the amount
**Then** The PLA+ should become viewable and editable and become an option in the "material" parameter when inputting models.

2.4
**Given** Resin Material
**When** "Resin" is entered as the type, "Red Resin 1" as the name, "red" as the color, "27°C" as printing temperature, and "10 L" as the amount
**Then** The Resin should become viewable and editable and become an option in the "material" parameter when inputting models.

### Requirement 102: Search for files
**Scenario**

As a user interested in 3D printing, I want to be able to search for the models I have input into the application.

**Examples**
3.1
**Given** a feed of print files is available
**When** a search term of "Eevee" under "Model Name" is input
**Then** I should receive a list of models with the name "Eevee"

3.2
**Given** a feed of print files is available
**When** a search term of "FDM" under "Print Method" is input
**Then** I should receive a list of models with a printer with attribute "FDM"

3.3
**Given** a feed of print files is available
**When** a search term of "PLA+" is input
**Then** I should receive a list of models with PLA+ material

3.4
**Given** a feed of print files is available
**When** "kjhdfskljsadyfipu8w" is input under any filter
**Then** no models should be listed

### Requirement 103: View a summary of files
**Scenario**

As a user interested in 3D printing, I want to be able to view a summary of my files and view how much time they will take to print in total.

**Examples**
4.1
**Given** a feed of print files is available
* Eevee model with total print time of "3 hours and 9 minutes"
* Forest Dragon model with total print time of "8 hours"
**When** the summary button is pressed
**Then** a list with the Eevee and Forest Dragon models should be visible with a total time of "11 hours and 9 minutes"

4.2
**Given** no models are available
**When** the summary button is pressed
**Then** no models should be listed and the total time should be "0 hours and 0 minutes"


## Class Diagram
***A diagram goes here***

## Scrum Roles
**Product Owner/Scrum Master** - Joseph Schmidt

**DevOps/Integration** - Aidan Kovacic

**Integration Developer** - Jamal Brown

**UI/UX Developer** - Kyler Severance

**UI/UX Developer** Austin Garrison


## Weekly Meeting
A weekly meeting will be held on Microsoft Teams every Sunday at 5:30-6:00.
**Meeting Recordings**
* Week 1 - https://web.microsoftstream.com/video/2f531466-d66a-477f-9678-ace9df0a6fe3
