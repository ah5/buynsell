import weiss.util.Collection;
import weiss.util.Iterator;
import weiss.util.Set;
import weiss.util.SortedSet;
import weiss.util.TreeSet;
import weiss.util.Collections;

public class TreeSetDemo
{
    public static void printCollection( Collection c )
    {
        Iterator itr = c.iterator( );
        while( itr.hasNext( ) )
            System.out.println( itr.next( ) );
    }
    
    public static void main( String [ ] args )
    {
        Set s = new TreeSet( Collections.reverseOrder( ) );
        s.add( "joe" );
        s.add( "bob" );
        s.add( "hal" );
        printCollection( s );    // Figure 6.8
    }
}