import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class cotizacionJSON {


    public static void main(String[] args) throws Exception {
    	
    }
    
    public static String objetoString(cotizacion p) {	
    	
		JSONObject obj = new JSONObject();
        obj.put("Banco", p.getBanco());
        obj.put("Moneda", p.getmoneda());
        obj.put("Valor_Compra", p.getvalorcompra());
        obj.put("Valor_Venta", p.getvalorventa());
        return obj.toJSONString();
    }
    
    
    public static cotizacion stringObjeto(String str) throws Exception {
    	cotizacion p = new cotizacion();
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(str.trim());
        JSONObject jsonObject = (JSONObject) obj;
        p.setbanco((String)jsonObject.get("Empresa"));
        p.setmoneda((String)jsonObject.get("Moneda"));
        p.setvalorcompra((String)jsonObject.get("Valor_Compra"));
        p.setvalorventa((String)jsonObject.get("Valor_Venta")); 
        return p;
	}

}
