import java.io.*;
import java.net.*;
import java.util.Scanner;

class UDPCliente {

    public static void main(String a[]) throws Exception {
        String direccionServidor = "127.0.0.1";
        Scanner leer=new Scanner(System.in);
        if (a.length > 0) {
            direccionServidor = a[0];
        }
        int puertoServidor = 9876;
        
        try {
            BufferedReader inFromUser =
                    new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(direccionServidor);
            System.out.println("Conectando a = " + IPAddress + ":" + puertoServidor );
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
            System.out.print("Ingrese opcion 1 para cargar una cotizacion o opcion 2 para ver el mejor banco para una moneda: ");
            String opcion = inFromUser.readLine();
            sendData=opcion.getBytes();
            DatagramPacket sendPacket1=new DatagramPacket(sendData,sendData.length,IPAddress,puertoServidor);
            int aux=Integer.parseInt(opcion);
            DatagramPacket receivePacket1 =new DatagramPacket(receiveData, receiveData.length);
            System.out.println("Esperamos si viene la respuesta.");
            clientSocket.setSoTimeout(10000);
            try {
                clientSocket.receive(receivePacket1);
                String respuesta = new String(receivePacket1.getData());
                System.out.println(respuesta);
            } catch (SocketTimeoutException ste) {
                System.out.println("TimeOut: El paquete udp se asume perdido.");
            }
            if (aux==1){
                System.out.print("Favor Ingresar nombre del banco: ");
                String banco = inFromUser.readLine();
                System.out.print("Ingrese la moneda (favor escribir dolar, euros etc)");
                String moneda = inFromUser.readLine();
                System.out.print("Ingrese el valor de compra");
                String compra = inFromUser.readLine();
                System.out.print("Ingrese el valor de venta");
                String venta = inFromUser.readLine();
                cotizacion p = new cotizacion(banco,moneda,compra,venta);
                String datoPaquete = cotizacionJSON.objetoString(p); 
                sendData = datoPaquete.getBytes();
                System.out.println("Enviando los datos de cotizacion: " + datoPaquete + " al servidor. ("+ sendData.length + " bytes)");
                DatagramPacket sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);
                clientSocket.send(sendPacket);
            }else if (aux==2){

            }
            DatagramPacket receivePacket =new DatagramPacket(receiveData, receiveData.length);
            System.out.println("Esperamos si viene la respuesta.");
            clientSocket.setSoTimeout(10000);
            try {
                clientSocket.receive(receivePacket);
                String respuesta = new String(receivePacket.getData());
                cotizacion presp = cotizacionJSON.stringObjeto(respuesta.trim());
                InetAddress returnIPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                System.out.println("Respuesta desde =  " + returnIPAddress + ":" + port);

            } catch (SocketTimeoutException ste) {

                System.out.println("TimeOut: El paquete udp se asume perdido.");
            }
            clientSocket.close();
        } catch (UnknownHostException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
} 