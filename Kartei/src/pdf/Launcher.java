package pdf;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * Öffnet eine PDF-Datei
 * @author Michael Karneim
 *
 */
public class Launcher
{
	  @SuppressWarnings("unchecked")
	public static void openUrl(String url) {
	        String os = System.getProperty("os.name");
	        try {
	            if (os.startsWith("Mac OS")) {
	                Class appleFileManagerClass = Class.forName("com.apple.eio.FileManager");
	                Method openURLMethod = appleFileManagerClass.getDeclaredMethod("openURL", new Class[] {
	                    java.lang.String.class
	                });
	                openURLMethod.invoke(null, new Object[] { url});
	            } else if (os.startsWith("Windows")) {
	                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
	            } else {
	                String browsers[] = {
	                              "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape"
	                };
	                String browser = null;
	                for (int i = 0, length = browsers.length; i < length && browser == null; i++) {
	                    if (Runtime.getRuntime().exec(new String[] {
	                                                  "which", browsers[i]
	                    }).waitFor() == 0) {
	                        browser = browsers[i];
	                    }
	                }

	                if (browser == null) {
	                    throw new IllegalStateException("Kein Webbrowser gefunden");
	                }
	                Runtime.getRuntime().exec(new String[] {
	                                          browser, url
	                });
	            }
	        } catch (InterruptedException ex) {
	            throw new UndeclaredThrowableException(ex);
	        } catch (IOException ex) {
	            throw new UndeclaredThrowableException(ex);
	        } catch (InvocationTargetException ex) {
	            throw new UndeclaredThrowableException(ex);
	        } catch (IllegalAccessException ex) {
	            throw new UndeclaredThrowableException(ex);
	        } catch (SecurityException ex) {
	            throw new UndeclaredThrowableException(ex);
	        } catch (NoSuchMethodException ex) {
	            throw new UndeclaredThrowableException(ex);
	        } catch (ClassNotFoundException ex) {
	            throw new UndeclaredThrowableException(ex);
	        }
	    }
}
