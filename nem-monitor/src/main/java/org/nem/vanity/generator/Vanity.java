package org.nem.vanity.generator;

import org.nem.core.crypto.KeyPair;
import org.nem.core.model.Address;

import java.util.concurrent.TimeUnit;
import javax.swing.ListModel;

public class Vanity {
	final private VanityAddressVisitor addressVisitor;

	public Vanity(final ListModel<String> addressVisitor) {
		if (addressVisitor instanceof VanityAddressVisitor) {
			this.addressVisitor = (VanityAddressVisitor) addressVisitor;
		} else {
			throw new IllegalArgumentException(String.format("Model of type VanityAddressVisitor expected: <%s>", addressVisitor.getClass()));
		}
	}
	
	public Long generate(final String vanity) {
		final String wantedAddress = vanity.toUpperCase();
		KeyPair keyPair = null;
		Long count = 0L;
		int found = 0;
		while (found < 100) {
			keyPair = new KeyPair();
			final String address = Address.fromPublicKey(keyPair.getPublicKey()).getEncoded();
			count++;

			if (address.contains(wantedAddress)) {
				found++;
				addressVisitor.addressFound(keyPair);
				System.out.println(count + " " + keyPair.getPrivateKey().toString() + " : " + keyPair.getPublicKey().toString() + " : " + address);
			}
			
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				//leave the endless loop
				break;
			}
		}
		
		return count;
	}
}
