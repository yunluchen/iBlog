import org.apache.log4j.PropertyConfigurator;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IBlogServer
{
    private static final Logger LOGGER = LoggerFactory.getLogger( IBlogServer.class );

    public static void main( String[] args )
    {
        PropertyConfigurator.configure( "log4j.properties" );

        Server server = new Server( 8081 );
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed( false );
        resourceHandler.setWelcomeFiles( new String[]{ "index.html" } );
        resourceHandler.setResourceBase( "web" );
        HandlerList handlers = new HandlerList();
        handlers.setHandlers( new Handler[]{ resourceHandler } );
        server.setHandler( handlers );

        try
        {
            server.start();
            server.join();
        }
        catch( Exception e )
        {
            LOGGER.error( e.getMessage() );
        }
    }
}
