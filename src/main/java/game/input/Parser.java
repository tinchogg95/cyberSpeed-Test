package game.input;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Parser{
	public static CommandLine parseArguments(String[] args) {
        Options options = new Options();
        
        Option configOption = new Option("c", "config", true, "config");
        configOption.setRequired(true);
        options.addOption(configOption);

        Option betOption = new Option("b", "betting-amount", true, "betting amount");
        betOption.setRequired(true);
        options.addOption(betOption);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);
            return cmd;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("ScratchGame", options);
            return null;
        }
    }
}
