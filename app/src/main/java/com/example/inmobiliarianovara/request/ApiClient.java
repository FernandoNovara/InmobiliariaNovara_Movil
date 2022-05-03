package com.example.inmobiliarianovara.request;

import com.example.inmobiliarianovara.modelo.*;

import java.lang.reflect.Array;
import java.util.*;

import java.util.ArrayList;

public class ApiClient {
    private ArrayList<Propietario> propietarios = new ArrayList<>();
    private ArrayList<Inquilino> inquilinos = new ArrayList<>();
    private ArrayList<Inmueble> inmuebles = new ArrayList<>();
    private ArrayList<Contrato> contratos = new ArrayList<>();
    private ArrayList<Pago> pagos = new ArrayList<>();
    private static Propietario usuarioActual = null;
    private static ApiClient api = null;

    private ApiClient() {
        cargaDatos();
    }

    public static ApiClient getApi() {
        if (api == null) {
            api = new ApiClient();
        }
        return api;
    }

    public Propietario login(String mail, final String password) {
        for (Propietario propietario : propietarios) {
            if (propietario.getEmail().equals(mail) && propietario.getContraseña().equals(password)) {
                usuarioActual = propietario;
                return propietario;
            }
        }
        return null;
    }

    public Contrato obtenerContratoVigente(Inmueble inmueble){

        for(Contrato contrato:contratos){
            if(contrato.getInmueble().equals(inmueble)){
                return contrato;
            }
        }
        return null;
    }

    public Propietario obtenerUsuarioActual() {
        return usuarioActual;
    }

    public ArrayList<Inmueble> obtenerPropiedades() {
        ArrayList<Inmueble> temp = new ArrayList<>();
        for (Inmueble inmueble : inmuebles) {
            if (inmueble.getPropietario().equals(usuarioActual)) {
                temp.add(inmueble);
            }
        }
        return temp;
    }

    public ArrayList<Inmueble> obtenerPropiedadesAlquiladas() {
        ArrayList<Inmueble> temp = new ArrayList<>();
        for (Contrato contrato : contratos) {
            if (contrato.getInmueble().getPropietario().equals(usuarioActual)) {
                temp.add((contrato.getInmueble()));
            }
        }
        return temp;
    }

    public Inquilino obtenerInquilino(Inmueble inmueble) {
        for (Contrato contrato : contratos)
        {
            if (contrato.getInmueble().equals(inmueble))
            {
                return contrato.getInquilino();
            }
        }
        return null;
    }

    public ArrayList<Pago> obtenerPago(Contrato contratoVer)
    {
        ArrayList<Pago> temp = new ArrayList<>();
        for (Contrato contrato:contratos)
        {
            if (contrato.equals(contratoVer))
            {
                for (Pago pago:pagos)
                {
                    if (pago.getContrato().equals(contrato))
                    {
                        temp.add(pago);
                    }
                }
            }
            break;
        }
        return temp;
    }

    public void actualizarPerfil(Propietario propietario)
    {
        usuarioActual.setId(propietario.getId());
        usuarioActual.setDni(propietario.getDni());
        usuarioActual.setNombre(propietario.getNombre());
        usuarioActual.setApellido(propietario.getApellido());
        usuarioActual.setEmail(propietario.getEmail());
        usuarioActual.setContraseña(propietario.getContraseña());
        usuarioActual.setTelefono(propietario.getTelefono());
    }

    public void actualizarInmueble(Inmueble inmueble)
    {
        int posicion = inmuebles.indexOf(inmueble);
        if (posicion != -1)
        {
            inmuebles.set(posicion,inmueble);
        }
    }

    private void cargaDatos() {
        Propietario juan = new Propietario(1, 23492012L, "Juan", "Perez", "juan@gmail.com", "123", "2664553447");
        Propietario sonia = new Propietario(2, 17495869L, "Sonia", "Lucero", "sonia@gmail.com", "123", "266485417");
        propietarios.add(juan);
        propietarios.add(sonia);

        Inquilino mario = new Inquilino(100, 25340691L, "Mario", "Luna", "Aiello sup.", "luna@gmail.com", "2664253411", "Lucero", "Martinez");
        inquilinos.add(mario);

        //Inmuebles
        Inmueble salon = new Inmueble(501, "Colon 340", "Comercial", "salon", 2, 20000, juan, true, "http://www.secsanluis.com");
        Inmueble casa = new Inmueble(502, "Mitre 800", "particular", "casa", 2, 15000, juan, true, "http://www.secsanluis.com");
        Inmueble otraCasa = new Inmueble(503, "Salta 325", "particular", "casa", 3, 17000, sonia, true, "http://www.secsanluis.com");
        Inmueble dpto = new Inmueble(504, "Lavalle 450", "particular", "dpto", 3, 13000, sonia, true, "http://www.secsanluis.com");
        Inmueble casita = new Inmueble(505, "Belgrano 218", "particular", "casa", 2, 10000, juan, true, "http://www.secsanluis.com");

        inmuebles.add(salon);
        inmuebles.add(casa);
        inmuebles.add(otraCasa);
        inmuebles.add(dpto);
        inmuebles.add(casita);

        //Contratos
        Contrato uno = new Contrato(701, "05/01/2020", "05/01/2021", 17000, mario, otraCasa);
        contratos.add(uno);

        //Pagos
        pagos.add(new Pago(900, 1, uno, 17000, "10/02/2020"));
        pagos.add(new Pago(901, 1, uno, 17000, "10/02/2020"));
        pagos.add(new Pago(902, 1, uno, 17000, "10/02/2020"));
    }
}
