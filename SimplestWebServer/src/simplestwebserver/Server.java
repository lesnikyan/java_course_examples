
package simplestwebserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author less
 */
public class Server {
    
    int port;
    
    HttpServer hserver = null;
    
    public Server(int port){
        this.port = port;
    }
    
    public void start() throws IOException{
        hserver = HttpServer.create(new InetSocketAddress(port), 0);
        hserver.createContext("/", new HelloHandler());
        hserver.setExecutor(null);
        hserver.start();
        System.out.println("Server started!");
    }
    
    class HelloHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange ec) throws IOException {
            try(OutputStream out = ec.getResponseBody()){
                System.out.println("hello handler");
                System.out.println(ec.getRequestURI());
                String urlPath = ec.getRequestURI().getPath();
                String requestedFile = urlPath;
                String strPath = "./files" + requestedFile;
                System.out.println("strPath: " + strPath);
                Path path = Paths.get(strPath);

                byte[] responseContent;
                int code = 200;
                if(Files.isReadable(path)){
                    responseContent = Files.readAllBytes(path);
                } else {
                    responseContent = "file not found!".getBytes();
                    code = 404;
                }
                
                ec.sendResponseHeaders(code, responseContent.length);
                out.write(responseContent);
                out.flush();
                out.close();
            }
        }
        
    }
    
}
