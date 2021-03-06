/*******************************************************************************
 * Copyright (c) 2014 Institute for Pervasive Computing, ETH Zurich and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 * 
 * The Eclipse Public License is available at
 *    http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *    http://www.eclipse.org/org/documents/edl-v10.html.
 * 
 * Contributors:
 *    Matthias Kovatsch - creator and main architect
 *    Stefan Jucker - DTLS implementation
 ******************************************************************************/
package org.eclipse.californium.scandium.dtls;

import java.util.List;


public class ServerCertificateTypeExtension extends CertificateTypeExtension {

	// Constructors ///////////////////////////////////////////////////
	
	/**
	 * Constructs an empty certificate type extension. If it is client-sided
	 * there is a list of supported certificate type (ordered by preference);
	 * server-side only 1 certificate type is chosen.
	 * 
	 * @param isClient
	 *            whether this instance is considered the client.
	 */
	public ServerCertificateTypeExtension(boolean isClient) {
		super(ExtensionType.SERVER_CERT_TYPE, isClient);
	}
	
	/**
	 * Constructs a certificate type extension with a list of supported
	 * certificate types. The server only chooses 1 certificate type.
	 * 
	 * @param certificateTypes
	 *            the list of supported certificate types.
	 * @param isClient
	 *            whether this instance is considered the client.
	 */
	public ServerCertificateTypeExtension(boolean isClient, List<CertificateType> certificateTypes) {
		super(ExtensionType.SERVER_CERT_TYPE, isClient, certificateTypes);
	}

	// Methods ////////////////////////////////////////////////////////

	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());

		for (CertificateType type : certificateTypes) {
			sb.append("\t\t\t\tServer certificate type: ").append(type).append("\n");
		}

		return sb.toString();
	};
	
	public static ServerCertificateTypeExtension fromByteArray(byte[] byteArray) {
		ServerCertificateTypeExtension ext = new ServerCertificateTypeExtension(byteArray.length > 1);
		ext.addCertiticateTypes(byteArray);
		return ext;
	}

}
