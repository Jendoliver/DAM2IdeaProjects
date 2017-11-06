import domain.MainInputException;
import domain.Package;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main
{
    private Map<Integer, List<Package>> bugIdToPackage;

    public static void main(String[] args)
    {
        // if(args.length != 1 ||  args[0] is not numeric)
            // throw new MainInputException("Bad argument");
        File bugPackage = new File("rcBugPackage.txt");
        File packageMaintainers = new File("packageMaintainer.txt");

        BufferedReader buffer;
        try {
            buffer = new BufferedReader(new FileReader(bugPackage));
        } catch (FileNotFoundException e) {
            throw new MainInputException("Unable to locate file rcBugPackage.txt");
        }

        String line;
        try {
            while ((line = buffer.readLine()) != null)   {
                System.out.println(line);
                String[] splittenLine = line.split("^[,;]+$");
                Integer bugId = Integer.parseInt(splittenLine[0]);
                List<Package> packageList = new ArrayList<>();
                for(int i = 1; i < splittenLine.length; i++) {
                    packageList.add(new Package().setPackageName(splittenLine[i]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
