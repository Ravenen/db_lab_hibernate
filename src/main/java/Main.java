import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.lviv.iot.hiberlab.view.View;


public class Main {

  public static void main(final String[] args) {
    Logger hibernateLog = Logger.getLogger("org.hibernate.SQL");
    hibernateLog.setLevel(Level.ERROR);
    View view = new View();
    view.show();
  }
}