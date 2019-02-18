# FlipTiles

A basic program to flip the x or y coordinates in tiled files

# Build

`mvn clean install`

# Usage

Linux:
```
java -jar target/flip-tiles*.jar \
    -d "/path/to/tiles/directory" \
    --flip-y \
    --flip-x
```
    
Windows:
```
java -jar target/flip-tiles*.jar \
    -d "C:\\Users\\USERNAME\\Documents\\tiles" \
    --flip-y \
    --flip-x
```
