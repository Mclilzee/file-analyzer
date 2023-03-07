# File Analyzer

Build project with `Gradle build`
Launch it using `java -jar ./build/libs/file-type-analyzer "Path to file" "Path to database"` 

Path to file or Folder, such as a PDF file while the database is the pattern to look for in files, an example database pattern is as follow.
Folder can be provided instead of a single file, it will recursively take all files inside a nest of folders.
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

The database follow pattern as semicolon separated values

- First value is priority, in case the same pattern can be found in multiple file types, than priority tells it which one it should consider first before it moves on to the next one.
Higher priority means will be considered first.

- Second is the pattern to look for.

- Third is the output to print if such pattern found.

The algorithm make use of multi threads to search the patterns for many files at the same time, when folder is provided.
Placeholder example-pattern.db is provided, and a test PDF file.

Example :
`java -jar .\build\libs\file-type-analyzer-1.0.jar .\Alice_in_Wonderland.pdf .\example-pattern.db`

Will print out Alice_in_Wonderland.pdf: Zip Archive. because PK was found in the pattern, changing the priority of PDF to higher such as 5+, will make it be recognized as PDF Document

