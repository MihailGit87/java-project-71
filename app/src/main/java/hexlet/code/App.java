package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<String> {

    @Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, paramLabel = "format",
            description = "output format [default: stylish]")
    private String format = "stylish";

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    boolean versionInfoRequested;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    boolean usageHelpRequested;

    @Override
    public String call() throws Exception {
        File file1 = new File(filepath1).getAbsoluteFile();
        File file2 = new File(filepath2).getAbsoluteFile();

        if (!Files.exists(file1.toPath())){
            throw new Exception("File " + file1.getName() + " does not exist");
        }
        if (!Files.exists(file2.toPath())){
            throw new Exception("File " + file2.getName() + " does not exist");
        }
        return Differ.generate(file1, file2);
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
