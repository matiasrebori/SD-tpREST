package service;
/*
 * LTService means LocationTransportService
 */

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.LocationDAO;
import dao.TransportDAO;
import model.Location;
import model.LocationQuery;
import model.Transport;

//imports for Date
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class LTService {
    TransportDAO tDAO = new TransportDAO();
    LocationDAO lDAO = new LocationDAO();
    @Inject
    private Logger log;

    @Inject
    //private TransportDAO tDAO;
    //private LocationDAO lDAO;

    public List<Transport> listTransport() { return tDAO.listTransport(); }

    public void recordTransport(Transport t) throws Exception {
        log.info("Registrando movil: " + " " + t.getType() );
        try {
            tDAO.insert(t);
        }catch(Exception e) {
            log.severe("ERROR al registrar movil: " + e.getLocalizedMessage() );
            throw e;
        }
        log.info("Movil registrado con exito " );
    }

    public void specifyLocation( Location l) throws SQLException {
        l.setDate( dateToString() );
        log.info("Registrando movil de id " + l.getTransporte().getId() +" en ubicacion: " + l.getPosX()+","+l.getPosY() + " en fecha:" + l.getDate() );
        //System.out.println( "Registrando movil de id" + l.getTransporte().getId() +" en ubicacion: " + l.getPosX()+","+l.getPosY()  );
        try {
            lDAO.insert(l);
        }catch(Exception e) {
            log.severe("ERROR al registrar movil en ubicacion especifica: " + e.getLocalizedMessage() );
            throw e;
        }
        log.info("Movil en ubicacion especifica registrado con exito " );
    }

    public List<Location> nearLocation( LocationQuery query ) throws SQLException {
        //log.info("Buscando moviles cerca de:" + query.getPosX()+","+query.getPosY() +" en un radio de " + query.getRadius()  );
        List<Location> result = new ArrayList<Location>();
        try {
            List<Location> lista = lDAO.listLocation();

            for ( Location l : lista ) {
                if( isInside(query.getPosX(), query.getPosY(), query.getRadius(), l.getPosX(),l.getPosY() ) )
                {
                    result.add(l);
                }
            }
        }catch(Exception e) {
            //log.severe("ERROR al registrar movil en ubicacion especifica: " + e.getLocalizedMessage() );
            throw e;
        }finally {
            //log.info("Busqueda exitosa" );
            return result;
        }

    }
    static boolean isInside(int circle_x, int circle_y,
                            int rad, int x, int y)
    {
        // Compare radius of circle with
        // distance of its center from
        // given point
        if ((x - circle_x) * (x - circle_x) +
                (y - circle_y) * (y - circle_y) <= rad * rad)
            return true;
        else
            return false;
    }
    static String dateToString()
    {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public static void main(String[] args) throws SQLException {
        //Transport te = new Transport(8, "autosq");
        //TransportDAO d = new TransportDAO();
        LTService lt = new LTService();
        //d.insert(te);
        List<Transport> lista = lt.listTransport();
        for (Transport t: lista)
        {
            System.out.println(t.getId());
        }
        LocationQuery query = new LocationQuery(2,2,16);
        List<Location> lista1 = lt.nearLocation( query );
        for ( Location l : lista1) {
            System.out.println( l.getPosX() +":"+ l.getPosY() +":"+ l.getTransporte().getId() +":"+ l.getTransporte().getType() );
        }
        System.out.println( dateToString() );

    }
}
