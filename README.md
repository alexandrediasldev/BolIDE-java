# BolIDE

A custom-made IDE to improve the working conditions and increase the happiness of
its developers.
Reports about the project (in french) are located in french_reports/

## Running the project

- Install java and maven
- mvn install
- java -cp target/bolIDE-1.0.0-SNAPSHOT-jar-with-dependencies.jar fr.epita.assistants.gui.Main

## Features

### Editor:
- Syntaxic coloration
- Auto-identation
- Font choice
- Search and Replace

### Logic:
- Windows and Linux support
- Loading projects and editing files
- Support editing multiple files at the same time
- File hierarchy
- Embedded Shell
- Maven projects support (compile, clean, test, package, install, exec, tree)
- Git interface (add, commit, pull, push)

### Custom
- Configurable pause pop-up to remind the developer to take a break
- Light and Dark theme
- Custom names and logo for git and maven command:
    - Git -> Hapiness  
    - Add -> amplify | Commit -> pledge | Pull -> attract | Push -> send  
    - Maven -> Pleasure  
    - Compile | Clean | Test -> experiment | Package -> wrap | Install -> Introduce | Exec -> Achieve | Tree -> Conifer

## Screenshots:

### Dark theme:
![Dark theme](images/darktheme.png)
### Light theme:
![Light theme](images/lighttheme.png)

## Design documents (French):

### Main window:
![Design main](images/design_main.png)
### Options:
![Design option](images/design_option.png)
### Loading project:
![Design Load project](images/load.png)

