package no.hvl.dat110.rpc;

import java.sql.Connection;
import java.sql.SQLException;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.*;

public class RPCClient {
	


	// underlying messaging client used for RPC communication
	private MessagingClient msgclient;
	//private Connection c;

	// underlying messaging connection used for RPC communication
	private MessageConnection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}
	
	public void connect() {
		
		// TODO - START
		// connect using the RPC client
		connection = msgclient.connect();
		
		//if (true)
		//	throw new UnsupportedOperationException(TODO.method());
		
		// TODO - END
	}
	
	public void disconnect() {
		
		// TODO - START
		// disconnect by closing the underlying messaging connection
		connection.close();
		
		//if (true)
		//	throw new UnsupportedOperationException(TODO.method());
		
		// TODO - END
	
	}

	/*
	 Make a remote call om the method on the RPC server by sending an RPC request message and receive an RPC reply message

	 rpcid is the identifier on the server side of the method to be called
	 param is the marshalled parameter of the method to be called
	 */

	public byte[] call(byte rpcid, byte[] param) {
		
		
		
		// TODO - START
        byte[] returnval = RPCUtils.encapsulate(rpcid, param);
        Message msg = new Message(returnval);

		connection.send(msg);

		Message reply = connection.receive();

		returnval = reply.getData();
		/*

		The rpcid and param must be encapsulated according to the RPC message format

		The return value from the RPC call must be decapsulated according to the RPC message format

		*/

		
		
		////if (true)
	//	throw new UnsupportedOperationException(TODO.method());
		
		
		
		// TODO - END
		return returnval;
		
	}

}
