package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.SwingUtilities;

public class Client {
	private BufferedWriter output;
	private BufferedReader input;
	private Socket socket;
	String message;
	
	public void connect(String address, int port)throws IOException{
		socket = new Socket(address, port);
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()) );
		output = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()) );
		System.out.println("Streams are good to go!");
	}
	
	public void chatting(){
		do{
			try{
				message = input.readLine();
				showText("\n " + message);
			}catch(IOException notfound){
				showText("\n Error on recieve..." );
			}
		}while(!message.equals("END"));
	}
	
	public void showText(final String mess){
		SwingUtilities.invokeLater(arg0)
	}
	public void close(){

	}
}
