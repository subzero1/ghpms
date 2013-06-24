package com.netsky.base.Office2Html;

import java.io.File;
import java.net.ConnectException;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class Office2Html {

	public static void convert(File inputFile, File outputFile) throws ConnectException {
		OpenOfficeConnection con = new SocketOpenOfficeConnection(8100);
		con.connect();
		DocumentConverter converter = new OpenOfficeDocumentConverter(con);
		converter.convert(inputFile, outputFile);
		con.disconnect();
	}

}
