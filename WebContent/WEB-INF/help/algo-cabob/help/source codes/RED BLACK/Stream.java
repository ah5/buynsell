/*****************************************************************************\
*  Stream.java                   by:        Prof. John Franco                 *
*                                                                             *
*  Created: 09/13/1999             Last Modified: 12/05/2002                  *
*                                                                             *
*  Copyright (c) 2002 by John Franco                                          *
*       This code may be freely distributed under the terms of the            *
*       GNU General Public License.                                           *
*                                                                             *
\*****************************************************************************/
import java.awt.*;

abstract class Stream implements Runnable {
   TokenObject value;
   Thread runner = null;
   
   public Stream ()  {  }
   
   synchronized public void putIt (TokenObject t) {
      value = t;
      notify ();
      if (value != null) try {  wait ();  } catch (Exception e) {  }
   }

   synchronized public TokenObject next () {
      if (runner != null)  notify ();
      else {
         runner = new Thread(this);
         runner.start();
      }

      try {  wait (); } catch (Exception e) {  }
      return value;
   }
}
