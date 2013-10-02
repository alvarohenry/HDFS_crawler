quiz
====

This is a basic Hadoop File System (HDFS) crawler.

For hadoop configuration you must change: fs.default.name", "hdfs://localhost:54310, with your data.

-----

The result is displayed on a page in JSON format, like this:

{"start-all.sh":"","video":"avatar.png,"}

The format is key:value. If the key has a empty value, that is a file, if it's not, that is a directory. 
The values of the directory are separated by commas.

-----
