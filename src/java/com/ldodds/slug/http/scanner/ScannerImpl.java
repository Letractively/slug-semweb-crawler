package com.ldodds.slug.http.scanner;

import java.net.URL;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.hp.hpl.jena.rdf.model.Model;
import com.ldodds.slug.framework.config.ComponentImpl;

public abstract class ScannerImpl extends ComponentImpl implements Scanner {

	protected Logger logger;
	
	public ScannerImpl() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public Set<URL> findURLs(Model toScan, URL origin) {
		Set<URL> urls = scan(toScan, origin);
		if ( urls.contains(origin) ) {
			urls.remove(origin);
		}
		logger.log(Level.FINEST, "Extracted " + urls.size()  + " urls using data from " + origin);
		return urls;
	}

	protected Logger getLogger() {
		return logger;
	}

	protected abstract Set<URL> scan(Model toScan, URL origin);
}
