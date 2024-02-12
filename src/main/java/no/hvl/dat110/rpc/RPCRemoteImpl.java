package no.hvl.dat110.rpc;

import no.hvl.dat110.system.display.DisplayImpl;

// RPC server-side method implementations must extend this class

public abstract class RPCRemoteImpl {
	
	public RPCRemoteImpl(byte rpcid, RPCServer displayserver) {
		displayserver.register(rpcid, this);
	}

	// method that will be invoked by the server
	// params
	public abstract byte[] invoke(byte[] params);
	
}
