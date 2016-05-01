package corba;

import corba.Tax;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.util.Random;

public class TaxClient {

    private static Tax taxImpl;
    private static Tax taxImpl2;
    private static Tax taxImpl3;
    private static Tax clientHandle;

    public static void main(String args[]) {
        try {
            // create and initialize the ORB
            ORB orb = ORB.init(new String[]{"-ORBInitialPort", "1050", "-ORBInitialHost", "localhost"}, null);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            Random rand = new Random();
            int clientID = rand.nextInt(200);

//            // get client names
//            String name1 = String.valueOf(rand.nextInt(200));
//            String name2 = String.valueOf(rand.nextInt(200));
//            String name3 = String.valueOf(rand.nextInt(200));
//            String[] name = {name1, name2, name3};
//            String handle = "handle";
//
//            taxImpl = TaxHelper.narrow(ncRef.resolve_str(name1));
//            taxImpl2 = TaxHelper.narrow(ncRef.resolve_str(name2));
//            taxImpl3 = TaxHelper.narrow(ncRef.resolve_str(name3));
//
//            clientHandle = TaxHelper.narrow(ncRef.resolve_str(handle));
//            System.out.println("Sending Client Names To Server...");
//            String[] send = {name1, name2, name3};
            System.out.println(taxImpl.getTaxRate(50000.00));

        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }

    private int[] getNames() {
        Random rand = new Random();
        int[] names = new int[3];
        names[0] = rand.nextInt(4);
        names[1] = rand.nextInt(4);
        names[2] = rand.nextInt(4);
        //Checks well enough if names are the same.
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < names.length; j++) {
                if (names[j] == names[i] && i != j) {
                    names[j] = rand.nextInt(5000);
                    i = 0;
                }

            }
        }

        return names;
    }

}
