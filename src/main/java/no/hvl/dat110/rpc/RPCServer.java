package no.hvl.dat110.rpc;

import java.util.HashMap;


import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.MessageConnection;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessagingServer;

public class RPCServer {

	private MessagingServer msgserver;
	private MessageConnection connection;
	
	// hashmap to register RPC methods which are required to extend RPCRemoteImpl
	// the key in the hashmap is the RPC identifier of the method
	private HashMap<Byte,RPCRemoteImpl> services;
	
	public RPCServer(int port) {
		
		this.msgserver = new MessagingServer(port);
		this.services = new HashMap<Byte,RPCRemoteImpl>();
		
	}
	
	public void run() {
		
		// the stop RPC method is built into the server
		RPCRemoteImpl rpcstop = new RPCServerStopImpl(RPCCommon.RPIDSTOP,this);
		
		System.out.println("RPC SERVER RUN - Services: " + services.size());
			
		connection = msgserver.accept(); 
		
		System.out.println("RPC SERVER ACCEPTED");
		
		boolean stop = false;
		
		while (!stop) {
			
			   // TODO - START
			   // - receive a Message containing an RPC request
			   // - extract the identifier for the RPC method to be invoked from the RPC request
			   // - extract the method's parameter by decapsulating using the RPCUtils
			   // - lookup the method to be invoked
			   // - invoke the method and pass the param
			   // - encapsulate return value 
			   // - send back the message containing the RPC reply
				
			  // if (true)
				//	throw new UnsupportedOperationException(TODO.method());
			   
			   // TODO - END

				// stop the server if it was stop methods that was called
			
			Message requestmsg = connection.receive();
			byte[] rpcrequest = requestmsg.getData();
			
		   byte rpcid = rpcrequest[0];
		   
		   byte[] params = RPCUtils.decapsulate(rpcrequest);
		   RPCRemoteImpl method = services.get(rpcid);
		   
		   byte[] rpcreply = method.invoke(params);
		   
		   Message replymsg = new Message(rpcreply);
		   
		   if (rpcid == RPCCommon.RPIDSTOP) {
			   stop = true;
		   }
		}
	
	}
	
	// used by server side method implementations to register themselves in the RPC server
	public void register(byte rpcid, RPCRemoteImpl impl) {
		services.put(rpcid, impl);
	}
	
	public void stop() {

		if (connection != null) {
			connection.close();
		} else {
			System.out.println("RPCServer.stop - connection was null");
		}
		
		if (msgserver != null) {
			msgserver.stop();
		} else {
			System.out.println("RPCServer.stop - msgserver was null");
		}
		
	}
}
