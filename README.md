# Project Albatross

## Members

   * JJS
   * GWJ
   * PJS
   * CSK
   * LJK
   * KHJ

## naming conventions

   * Do not capitalize filename except for class and interface files
   * jsp file name always starts with lowercase.

## Commit Rules

   * Commit message + committer 
   * Unit by Unit
   * 
   

## Coding Rules
   
### General Rules
   
   * Add whitespace before and after an operator
   * Do not use global style coding use namespace
   
#### Java / JSP

   * When writing jsp/EL with HTML, HTML indentation is used over EL jsp/EL indentation.
   * When constructing SQL string on DAO, use StringBuilder instead of String concatenation.

### CSS

   * Do not use camelCase on css class name. Instead use style-like-this.
   * Do not use global style.
   * Always comment the style.

### Javascript
   
   * Use single quote instead of double quote.
   * When mixed with jsp/EL with javacript, use secure placeholder for javascript.
   
### SQL

   * Use CAPITAL LETTERS for SQL keywords. 


   
## Project Directory
```
src/                - java source files / backend
   controller/      
   dao/             
   vo/
WebContent/         - frontend
   templates/       - jsp templates
      - home.jsp        
   static/          - static resource files
      js/           - javascript
      css/          - css
      font/         - fonts
      media/        - static media files
   index.jsp        - index page
      
```

## URL Mapping

`home` : ...
`booklist` : ...
