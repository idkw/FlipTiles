package com.idkw.fliptiles;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FlipTiles {

    public static void main(String[] argv) {
        Args args = parseArgs(argv);
        if (args != null) {
            log.info("Arguments {}", args);
            FileRenamer renamer = new FileRenamer(args.getDirPath(), args.getFilePrefix(), args.isFlipX(), args.isFlipY());
            renamer.renameFiles();
        }
    }

    public static Args parseArgs(String[] argv) {
        Args args = new Args();
        JCommander command = new JCommander(args);
        try {
            new JCommander(args).parse(argv);
        } catch (ParameterException e) {
            log.error(e.getMessage());
            command.usage();
            return null;
        }

        if (args.isHelp()) {
            command.usage();
            return null;
        }

        return args;
    }

}
