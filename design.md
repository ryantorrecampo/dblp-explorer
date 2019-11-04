#Design

Parses through a JSON file and checks for all objects containing the specified keyword. These objects are found using Java 8 Streams which are efficient ways at parsing through intense loads of data through it's pipelining features. We can chain different methods as the data is streamed. Elements in the stream have computations performed on them on demand, whereas a collection of data would require all of the data to be processed first.

This program will always calculate the first tier, and then will use that same first tier to continuously check n-tiers. Using Lists and maps also increases the lookup times.
