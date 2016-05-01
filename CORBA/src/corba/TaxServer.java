
import corba.TaxHelper;
import corba.Tax;
import corba.TaxPOA;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;

class TaxImpl extends TaxPOA {

    private ORB orb;

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    public double getTaxRate(double gross) {
        double tax = getTaxScale(gross);

        double taxPay = (double) Math.round((tax * gross) * 100) / 100;

        return taxPay;
    }

    public void registerWithHost(int id) throws AdapterInactive {
        try {
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(id);
            Tax href = TaxHelper.narrow(ref);
            String name = Integer.toString(id);
            NameComponent[] path = ncRef.to_name(name);
            try {
                ncRef.rebind(path, href);
            } catch (NotFound ex) {
                Logger.getLogger(TaxImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CannotProceed ex) {
                Logger.getLogger(TaxImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidName ex) {
                Logger.getLogger(TaxImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (org.omg.CORBA.ORBPackage.InvalidName ex) {
            Logger.getLogger(TaxImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private double getTaxScale(double gross) {
        if (gross >= 0.00 && gross <= 9225.00) {
            return .1;
        } else if (gross >= 9226.00 && gross <= 37450.00) {
            return .15;
        } else if (gross >= 37451.00 && gross <= 90750.00) {
            return .25;
        } else if (gross >= 90751.00 && gross <= 189300.00) {
            return .28;
        } else if (gross >= 189301.00 && gross <= 411500.00) {
            return .33;
        } else {
            return .35;
        }
    }

    public void shutdown() {
        orb.shutdown(false);
    }
}

public class TaxServer {

    public static void main(String args[]) {
        try {
            // create and initialize the ORB
            ORB orb = ORB.init(new String[]{"-ORBInitialPort", "1050", "-ORBInitialHost", "localhost"}, null);

            // get reference to rootpoa & activate the POAManager
            // Server gets the clients send id and the
            // create servant and register it with the ORB
//            TaxImpl taxImpl2 = new TaxImpl();
//            taxImpl2.setORB(orb);
//
//            TaxImpl taxImpl3 = new TaxImpl();
//            taxImpl3.setORB(orb);
            // This!
            // get the root naming context
            // NameService invokes the name service
            // bind the Object Reference in Naming
            // Need to handle the client names here.. Brad?
//            ncRef.rebind(ncRef.to_name("Client 2"), TaxHelper.narrow(rootpoa.servant_to_reference(taxImpl2)));
//            ncRef.rebind(ncRef.to_name("Client 2"), TaxHelper.narrow(rootpoa.servant_to_reference(taxImpl3)));
            System.out.println("Server is somehow working...");

            orb.run();
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }

        System.out.println("Closing Server");

    }
}
