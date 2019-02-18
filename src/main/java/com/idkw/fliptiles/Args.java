package com.idkw.fliptiles;

import com.beust.jcommander.Parameter;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = {"help"})
public class Args {

    @Parameter(names = {"--dir-path", "-d"}, required = true, description = "Directory path to look into")
    private String dirPath;

    @Parameter(names = {"--file-prefix", "-p"}, description = "File prefix")
    private String filePrefix = "heatmap";

    @Parameter(names = {"--flip-x", "-x"}, description = "Flip x values")
    private boolean flipX;

    @Parameter(names = {"--flip-y", "-y"}, description = "Flip y values")
    private boolean flipY;

    @Parameter(names = "--help", help = true, description = "Show help")
    private boolean help = false;

}