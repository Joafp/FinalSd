import java.io.*;
import java.net.*;
public class UDPServer {
	
	
    public static void main(String[] a){
        int puertoServidor = 9876;
        cotizacion p = new cotizacion(); 
        datosBancos Cotizaciones=new datosBancos();
        try {
            DatagramSocket serverSocket = new DatagramSocket(puertoServidor);
			System.out.println("Servidor Sistemas Distribuidos - UDP ");
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            while (true) {
                receiveData = new byte[1024];
                DatagramPacket receivePacket =new DatagramPacket(receiveData, receiveData.length);
                System.out.println("Esperando a algun cliente... ");
                serverSocket.receive(receivePacket);
				System.out.println("________________________________________________");
                System.out.println("Aceptamos un paquete");
                String datoRecibido = new String(receivePacket.getData());
                int aux=Integer.parseInt(datoRecibido);
                if(aux==1){
                    serverSocket.receive(receivePacket);
                    datoRecibido=new String(receivePacket.getData());
                    datoRecibido = datoRecibido.trim();
                    System.out.println("DatoRecibido: " + datoRecibido );
                    cotizacion p2 = cotizacionJSON.stringObjeto(datoRecibido);
                    InetAddress IPAddress = receivePacket.getAddress();
                    int port = receivePacket.getPort();
                    System.out.println("De : " + IPAddress + ":" + port);
                    System.out.println("Banco: "+p2.getBanco()+".\nCotizaciones:\n"+"Moneda: "+ p2.getmoneda() + "\nValor de compra" + p2.getvalorcompra() + " \nValor de venta: " + p2.getvalorventa());
                    DatagramPacket sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress,port);
                    serverSocket.send(sendPacket);
                    Cotizaciones.datos.add(p2);
                }    
            }

        } catch (Exception ex) {
        	ex.printStackTrace();
            System.exit(1);
        }

    }
}  