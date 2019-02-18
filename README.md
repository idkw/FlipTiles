# FlipTiles

A basic program to flip the x or y coordinates in tiled files.

I'm using this for example to transfer tiled heatmap between 
WorldCreator 2 and Unreal Engine 4 where Y axis is reversed.

The program will look for tiled files in the specified directory 
and flip the x or y part of it.

# Build

Linux:
```
./mvnw clean install
```
    
Windows:
```
mvnw.cmd clean install
```


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


Example output :

```
$ java -jar target/flip-tiles*.jar -d "C:\\Users\\val\\Documents\\projects\\Rover_4.21\\WC_project\\demo\\Export" --flip-x
INFO : Arguments Args(dirPath=C:\Users\val\Documents\projects\Rover_4.21\WC_project\demo\Export, filePrefix=heatmap, flipX=true, flipY=false)
INFO : Flipped heatmap_New Layer0_Rock10_x0_y0.png >>>> flipped_heatmap_New Layer0_Rock10_x1_y0.png
INFO : Flipped heatmap_New Layer0_Rock10_x0_y1.png >>>> flipped_heatmap_New Layer0_Rock10_x1_y1.png
INFO : Flipped heatmap_New Layer0_Rock10_x1_y0.png >>>> flipped_heatmap_New Layer0_Rock10_x0_y0.png
INFO : Flipped heatmap_New Layer0_Rock10_x1_y1.png >>>> flipped_heatmap_New Layer0_Rock10_x0_y1.png
INFO : Flipped heatmap_New Layer0_Sand10_x0_y0.png >>>> flipped_heatmap_New Layer0_Sand10_x1_y0.png
INFO : Flipped heatmap_New Layer0_Sand10_x0_y1.png >>>> flipped_heatmap_New Layer0_Sand10_x1_y1.png
INFO : Flipped heatmap_New Layer0_Sand10_x1_y0.png >>>> flipped_heatmap_New Layer0_Sand10_x0_y0.png
INFO : Flipped heatmap_New Layer0_Sand10_x1_y1.png >>>> flipped_heatmap_New Layer0_Sand10_x0_y1.png
```
