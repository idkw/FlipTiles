package com.idkw.fliptiles;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class FileRenamer {

    private static final String PREFIX_GROUP = "prefix";
    private static final String X_GROUP = "x";
    private static final String Y_GROUP = "y";
    private static final String SUFFIX_GROUP = "suffix";

    private final File path;
    private final Pattern pattern;
    private final boolean flipX;
    private final boolean flipY;


    public FileRenamer(String dirPath, String prefix, boolean flipX, boolean flipY) {
        this.path = new File(dirPath);
        Preconditions.checkArgument(this.path.exists() && this.path.isDirectory(),
                String.format("Path %s is not a directory", dirPath)
        );
        this.pattern = Pattern.compile("(?<prefix>" + prefix + ".*)_x(?<x>[0-9]+)_y(?<y>[0-9]+)(?<suffix>.*)");
        this.flipX = flipX;
        this.flipY = flipY;
    }

    public void renameFiles() {
        List<FileMatch> matches = Arrays.stream(Objects.requireNonNull(path.listFiles()))
                .map(f -> {
                    String name = f.getName();
                    Matcher matcher = pattern.matcher(name);
                    return matcher.matches() ? FileMatch.of(f, matcher) : null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (matches.isEmpty()) {
            log.warn("No matching tiled files found in directory {}", path);
        } else {
            int minX = matches.stream().mapToInt(FileMatch::getX).min().getAsInt();
            int maxX = matches.stream().mapToInt(FileMatch::getX).max().getAsInt();
            int minY = matches.stream().mapToInt(FileMatch::getY).min().getAsInt();
            int maxY = matches.stream().mapToInt(FileMatch::getY).max().getAsInt();

            matches.forEach(m -> {
                String fileName = m.getFile().getName();
                String newFileName =
                        "flipped_" + m.getPrefix()
                                + "_x" + flipX(m.getX(), minX, maxX)
                                + "_y" + flipY(m.getY(), minY, maxY)
                                + m.getSuffix();
                File newFile = new File(m.getFile().getParent(), newFileName);
                try {
                    Files.copy(m.getFile().toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    log.error(String.format("Could not save file %s", newFileName));
                }
                log.info(String.format("Flipped %s >>>> %s", fileName, newFileName));
            });
        }

    }

    private int flipX(int current, int min, int max) {
        return flipX ? doFlipInt(current, min, max) : current;
    }

    private int flipY(int current, int min, int max) {
        return flipY ? doFlipInt(current, min, max) : current;
    }

    private int doFlipInt(int current, int min, int max) {
        return max - (current - min);
    }

    @Value
    @AllArgsConstructor(staticName = "of")
    private static class FileMatch {
        File file;
        Matcher matcher;

        String getPrefix() {
            return matcher.group(PREFIX_GROUP);
        }

        int getX() {
            return Integer.parseInt(matcher.group(X_GROUP));
        }

        int getY() {
            return Integer.parseInt(matcher.group(Y_GROUP));
        }

        String getSuffix() {
            return matcher.group(SUFFIX_GROUP);
        }
    }

}
