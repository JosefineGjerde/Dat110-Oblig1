package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;

import no.hvl.dat110.rpc.*;

public class DisplayStub extends RPCLocalStub {

	public DisplayStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	public void write (int temp) {
		
		// TODO - START
		
		// implement marshalling, call and unmarshalling for write RPC method
		
		//if (true)
		//	throw new UnsupportedOperationException(TODO.method());
		
		// TODO - END
		byte[] request = RPCUtils.marshallInteger(temp);
		byte[] response = rpcclient.call((byte)Common.READ_RPCID, request);
		RPCUtils.unmarshallVoid(response);
	}

	public void write1(int temp) {
		// TODO Auto-generated method stub
		
	}
}
