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
    private File filepath1 = new File("/src/main/java/hexlet/code/file1.json");

    @Parameters(index = "1", description = "path to second file")
    private File filepath2 = new File("app/src/main/java/hexlet/code/file2.json");

    @Option(names = {"-f", "--format"}, paramLabel = "format",
            description = "output format [default: stylish]")
    private String format = "stylish";

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    boolean versionInfoRequested;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    boolean usageHelpRequested;

    @Override
    public String call() throws Exception {

        if (Files.exists(filepath1.toPath())){
            throw new Exception("File" + filepath1.getName() + "does not exist");
        }
        if (Files.exists(filepath2.toPath())){
            throw new Exception("File" + filepath2.getName() + "does not exist");
        }
        return Differ.generate(filepath1, filepath2);
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
