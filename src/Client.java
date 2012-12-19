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

	public void showText(final String message){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				gui.chatWindow.append(message);				//append gör att det skrivs till efter nuvarande
			}
		});
	}
	
	public void chatting(){
		do{
			try{
				message = input.readLine();					//Läser in raden som kommer från server
				if(message.startsWith("MESSAGE:")){
					String mess = message.split(":")[1];
					showText("\n " + mess); 				//showText-metoden skickar upp meddelandet på chattfältet
				}
			
			}catch(IOException notfound){
				showText("\n Error on recieve..." );
			}
		}while(!message.equals("END"));						//Om man skriver END så avslutas chatten
	}
	
	
	public void sendMessage(String message){
		try{
			output.write("MESSAGE:"+message);				//Skickar ett meddelande
			output.flush();
		}catch (IOException e){
			gui.chatWindow.append("Can't send message...");
		}
	}
	
	public void close(){
		try {
			input.close();
			output.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("Failed to close.");
		}
	}
}
