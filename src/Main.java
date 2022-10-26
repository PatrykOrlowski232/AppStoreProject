import humans.*;
import project.Project;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException , IOException {

        Project p2 = new Project();
        Programist p1 = new Programist();

        p1.project = p2;

      p1.programistInfo();
      p2.projectInfo();
      p1.work();
      p2.projectInfo();









    }


}