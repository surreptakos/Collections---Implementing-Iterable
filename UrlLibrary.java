import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;

/*
 * Reasons for doing this:
 *  1. It looks nice being able to iterate over your object
 *  2. Want to iterate over the objects you're storing in a custom order
 *  3. There is no iterator for the objects you're storing
 *  4. You want to fetch the objects as you're iterating through them
 *  
 *  We will examine option 4 in this tutorial. 
 *  We will download the specified webpages and print out the html of 
 *  the page.
 */

public class UrlLibrary implements Iterable<String> {
	private LinkedList<String> urls = new LinkedList<String>();

	private class UrlIterator implements Iterator<String> {

		private int index = 0;

		@Override
		public boolean hasNext() {
			return index < urls.size();
		}

		@Override
		public String next() {
			StringBuilder sb = new StringBuilder();
			try {
				URL url = new URL(urls.get(index));
				BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
				
				String line = null;
				
				while ((line = br.readLine()) != null) {
					sb.append(line);
					sb.append("\n");
				}
				
				br.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			index++;
			
			return sb.toString();
		}

		@Override
		public void remove() {
			urls.remove(index);
		}
	}

	public UrlLibrary() {
		urls.add("http://www.caveofprogramming.com");
		urls.add("http://www.facebook.com/caveofprogramming");
		urls.add("http://news.bbc.co.uk");
	}

	/*
	 * @Override public Iterator<String> iterator() { // now we can use a
	 * for-each loop to iterate through the class return urls.iterator(); }
	 */

	// If you want to return the objects you're iterating through:
	@Override
	public Iterator<String> iterator() {
		// Need to create your own Iterator
		return new UrlIterator();
	}

}
