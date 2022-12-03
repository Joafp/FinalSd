import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.List;

public class cotizacion {
    String banco;
	String moneda;
	String valor_compra;
	String valor_venta;
	public cotizacion(){		
	}
	public cotizacion(String banco,String moneda, String valor_compra, String valor_venta){
		this.banco=banco;
		this.moneda=moneda;
		this.valor_compra=valor_compra;
		this.valor_venta=valor_venta;
	}
	public String getBanco(){
		return banco;
	}
	public String getmoneda(){
		return moneda;
	}
	public String getvalorcompra(){
		return valor_compra;
	}
	public String getvalorventa(){
		return valor_venta;
	}	
	public void setbanco(String banco){
		this.banco=banco;
	}
	public void setmoneda(String moneda){
		this.moneda=moneda;
	}
	public void setvalorcompra(String compra){
		this.valor_compra=compra;
	}
	public void setvalorventa(String venta){
		this.valor_venta=venta;
	}
}