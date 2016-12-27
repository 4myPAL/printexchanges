package mobi.boilr.printexchanges;

import mobi.boilr.libdynticker.core.Exchange;

public final class HTMLPrinter extends ExchangePrinter {
	public static void main(String[] args) {
		Exchange[] exchanges = getSortedExchanges();

		// Print them!
		for(Exchange e : exchanges) {
			System.out.println("<div class=\"col-md-3 col-sm-6 col-xs-6\"><p>" + e.getName() + "</p></div>");
		}
	}
}
