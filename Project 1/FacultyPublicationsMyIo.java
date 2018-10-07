import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

public class FacultyPublicationsMyIo
{
  public static void main(String[] args) throws IOException
  {
    String filename = "in.txt";
    String line = null;

    //Arrays of FacultyMember and Publication objects created
    FacultyMember[] FacultyMembers = new FacultyMember[100];
    Publication[] Publications = new Publication[100];

    int facCounter = 0;
    int pubCounter = 0;

    //Input
    try
    {
      Scanner sc = new Scanner(new File("in.txt"));
      MyIo io = new MyIo(System.in, System.out);
      int numFacultyMembers = io.getInt();

        //for each FacultyMember
      for(int n = 0; n < numFacultyMembers; n++)
      {
        String nName = io.getLine();
        String nPosition = io.getLine();
        int nNumPubs = io.getInt();
        Publication[] nPublications = new Publication[nNumPubs];
          //for each publication
        for(int p = 0; p < nNumPubs; p++)
        {
          String pAuthors = io.getLine();
          String pTitle = io.getLine();
          String pConfName = io.getLine();
          String pConfLocation = io.getLine();
          int pYear = io.getInt();

          Publication pPublication = new Publication(pAuthors, pTitle, pConfName, pConfLocation, pYear);
          Publications[pubCounter] = pPublication;
          nPublications[p] = pPublication;
          pubCounter++;
        }
        FacultyMember nFacultyMember = new FacultyMember(nName, nPosition, nNumPubs, nPublications);
        FacultyMembers[facCounter] = nFacultyMember;
        facCounter++;
      }

      //Output

      //Raw Info Output
      FileWriter fileWriter = new FileWriter("RawInfo.txt");
      PrintWriter printWriter = new PrintWriter(fileWriter);
      for(int f = 0; f < facCounter; f++)
      {
        FacultyMember curFacMember = FacultyMembers[f];
        printWriter.println(curFacMember.getName() + " - " + curFacMember.getPosition());
        printWriter.println(curFacMember.getNumPubs() + " publication(s)\n");
        Publication[] curPubs = curFacMember.getPublications();
        //for each publication by curFacMember
        for(int p = 0; p < curFacMember.getNumPubs(); p++)
        {
          Publication curPub = curPubs[p];
          printWriter.println(curPub.getTitle() + " by: " + curPub.getAuthors());
          printWriter.println(curPub.getConfName() + " held in: " + curPub.getConfLocation());
          printWriter.println(curPub.getYear() + "\n");
        }
        printWriter.print("\n");
      }
      printWriter.close();

      //Alphabetical FacMembers Output
      FileWriter fileWriter1 = new FileWriter("AlphabeticalFac.txt");
      PrintWriter printWriter1 = new PrintWriter(fileWriter1);
      //Array of FacMembers' Names
      String[] facNames = new String[facCounter];
      for(int f = 0; f < facCounter; f++)
      {
        facNames[f] = FacultyMembers[f].getName();
      }
      Arrays.sort(facNames);
      for(int n = 0; n < facCounter; n++)
      {
        printWriter1.println(facNames[n]);
      }
      printWriter1.close();

      //Chronological Publications Output
      FileWriter fileWriter2 = new FileWriter("ChronologicalPubs.txt");
      PrintWriter printWriter2 = new PrintWriter(fileWriter2);
      //for every remaining publication
      for(int i = 0; i < pubCounter; i++)
      {
        int mostRecentYear = -1;
        Publication mostRecentPublication = null;
        //iterate to find most recent
        for(int p = 0; p < pubCounter; p++)
        {
          if(Publications[p].getYear() > mostRecentYear)
          {
            mostRecentPublication = Publications[p];
          }
        }
        printWriter2.println(mostRecentPublication.getTitle() + " by: " + mostRecentPublication.getAuthors());
        printWriter2.println(mostRecentPublication.getConfName() + " held in: " + mostRecentPublication.getConfLocation());
        printWriter2.println(mostRecentPublication.getYear() + "\n");
        mostRecentPublication.setYear(-2);
      }
      printWriter2.close();
    }
    catch(FileNotFoundException ex)
    {
      System.out.println("Unable to open file");
    }
  }

  //class Structure
  public static class FacultyMember
  {
    private String name;
    private String position;
    private int numPubs;
    private Publication[] publications;

    public FacultyMember()
    {
      name = "";
      position = "";
      numPubs = 0;
      publications = new Publication[99];
    }

    public FacultyMember(String inName, String inPosition, int inNumPubs)
    {
      name = inName;
      position = inPosition;
      numPubs = inNumPubs;
      publications = new Publication[inNumPubs];
    }

    public FacultyMember(String inName, String inPosition, int inNumPubs, Publication[] inPublications)
    {
      name = inName;
      position = inPosition;
      numPubs = inNumPubs;
      publications = new Publication[inNumPubs];
      publications = inPublications;
    }
    public String getName() {return name;}
    public void setName(String inName) {name = inName;}
    public String getPosition() {return position;}
    public void setPosition(String inPosition) {position = inPosition;}
    public int getNumPubs() {return numPubs;}
    public void setNumPubs(int inNumPubs) {numPubs = inNumPubs;}
    public Publication[] getPublications() {return publications;}
    public void setPublications(Publication[] inPublications) {publications = inPublications;}
  }

  public static class Publication
  {
    private String authors;
    private String title;
    private String confName;
    private String confLocation;
    private int year;

    public Publication()
    {
      authors = "";
      title = "";
      confName = "";
      confLocation = "";
      year = -1;
    }

    public Publication(String inAuthors, String inTitle, String inConfName, String inConfLocation, int inYear)
    {
      authors = inAuthors;
      title = inTitle;
      confName = inConfName;
      confLocation = inConfLocation;
      year = inYear;
    }
    public String getAuthors() {return authors;}
    public void setAuthors(String inAuthors) {authors = inAuthors;}
    public String getTitle() {return title;}
    public void setTitle(String inTitle) {title = inTitle;}
    public String getConfName() {return confName;}
    public void setConfName(String inConfName) {confName = inConfName;}
    public String getConfLocation() {return confLocation;}
    public void setConfLocation(String inConfLocation) {confLocation = inConfLocation;}
    public int getYear() {return year;}
    public void setYear(int inYear) {year = inYear;}
  }
}
