package mobi.boilr.printexchanges;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

import org.reflections.Reflections;

import mobi.boilr.libdynticker.core.Exchange;

public final class XMLArrayPrinter {
	public static void main(String[] args) {

		// Get all Exchange subclasses.
		Reflections reflections = new Reflections("mobi.boilr.libdynticker");
		Set<Class<? extends Exchange>> classes = reflections.getSubTypesOf(Exchange.class);

		// Filter asbtract classes.
		Iterator<Class<? extends Exchange>> it = classes.iterator();
		while(it.hasNext()) {
			if(Modifier.isAbstract(it.next().getModifiers()))
				it.remove();
		}

		// Initialize objects.
		Exchange[] exchanges = new Exchange[classes.size()];
		it = classes.iterator();
		for(int i = 0; it.hasNext(); i++) {
			try {
				exchanges[i] = it.next().getConstructor(long.class).newInstance(1000);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		// Sort in alphabetical order.
		Arrays.sort(exchanges, new Comparator<Exchange>() {
			@Override
			public int compare(Exchange o1, Exchange o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});

		// Print them!
		for(Exchange e : exchanges) {
			System.out.println("<item>" + e.getName() + "</item>");
		}
		for(Exchange e : exchanges) {
			System.out.println("<item>" + e.getClass().getName() + "</item>");
		}
	}
}
