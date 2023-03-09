# File Analyzer

File analyzer to look for patterns inside files, searching bytes output stream binary to determen the type of file provided inside a foler, or stand alone files.

The algorithm make use of multi threads to search the patterns for many files at the same time, when folder is provided.
Placeholder example-pattern.db is provided.

# Requirement
- Java version 17+ <a href="https://www.oracle.com/de/java/technologies/downloads/">Java download Link</a>

# Build - Run Project
- Clone repository and navigate into repo's directory
- Run project with `$ ./gradlew run --args="path/to/file path/to/pattern/database`
- `--arg=""` takes arguments saparated by spaces, if your path file include spaces in the name, use single quotes.
Example: `--args="'File with space in name' other/argument`
 
Note : Folder can be provided instead of a single file, it will recursively take all files inside a nest of folders.

# Pattern Format
```
1;"%PDF-";"PDF document"
2;"pmview";"PCP pmview config"
4;"PK";"Zip archive"
5;"vnd.oasis.opendocument.presentation";"OpenDocument presentation"
6;"W.o.r.d";"MS Office Word 2003"
6;"P.o.w.e.r.P.o.i";"MS Office PowerPoint 2003"
7;"word/_rels";"MS Office Word 2007+"
7;"ppt/_rels";"MS Office PowerPoint 2007+"
7;"xl/_rels";"MS Office Excel 2007+"
8;"-----BEGIN\ CERTIFICATE-----";"PEM certificate"
9;"ftypjp2";"ISO Media JPEG 2000"
9;"ftypiso2";"ISO Media MP4 Base Media v2"
```
An example pattern is provided in root directory for testing

The database follow pattern as semicolon separated values

- First value is priority, in case the same pattern can be found in multiple file types, than priority tells it which one it should consider first before it moves on to the next one. Higher number means will be considered first.

- Second is the pattern to look for inside the file.

- Third is the output to print if such pattern found.

# Example Usage

```
$ ./gradlew run --args="example-folder example-pattern.db"

Cats8.zip: Zip archive
prices.xls: Unknown file type
essay.doc: Zip archive
laptop-pic.jpg: Zip archive
Alice_in_Wonderland.pdf: PDF document
Book.pdf: PDF document
```
