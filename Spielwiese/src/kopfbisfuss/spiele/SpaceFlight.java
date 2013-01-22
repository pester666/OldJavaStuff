package kopfbisfuss.spiele;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

@SuppressWarnings("serial")
public class SpaceFlight extends Applet implements Runnable, MouseMotionListener, MouseListener, Serializable 
{
int       Width, Height, Xmax, Ymax;
Thread    me = null;
boolean   suspend = false;
Image     im;
Graphics  offscreen;
Cursor    fifi;

int       speed, N_Stars;
int       mx, my, dx, dy, mxold, myold;  /* m: mouse pixel from center */
double    crot, srot, rot;
Fly_by_Star         FStar[];           /* Fly-by Star  */
Background_Star     BStar[];           /* Background   */

public void init()
     {
     Width = getSize().width;
     Height = getSize().height;
     String    Velocity = getParameter( "Velocity" );
     Show( "speed", Velocity );

     speed = (Velocity == null ) ? 50 : Integer.valueOf( Velocity ).intValue();
     String    Number_of_Stars = getParameter( "Number_of_Stars" );
     Show( "Number_of_Stars", Number_of_Stars );
     N_Stars = (Number_of_Stars == null ) ? 30 : Integer.valueOf( Number_of_Stars ).intValue();

     try
          {
          im = createImage( Width, Height );
          offscreen = im.getGraphics();
          }
     catch( Exception e)
          {
          offscreen = null;
          }
     BStar = new Background_Star[N_Stars];
     FStar = new Fly_by_Star[N_Stars];
     for( int i = 0; i < N_Stars; i++ )
          {
          BStar[i] = new Background_Star( Width, Height );
          FStar[i] = new Fly_by_Star( Width, Height );
          }
     mxold = 0;
     myold = 0;
     Xmax = Width/2;
     Ymax = Height/2;
     addMouseMotionListener(this);
     addMouseListener(this);
     }

public void paint( Graphics g )
     {
     if( offscreen != null )
          {
          paintMe( offscreen );
          g.drawImage( im, 0, 0, this );
          }
     else
          {
          paintMe( g );
          }
     }

public void paintMe( Graphics g )
     {
     g.setColor( Color.black );
     g.fillRect( 0, 0, Width, Height );
     for( int i = 0; i < N_Stars; i++ )
          {
          BStar[i].BDraw( g, mx, my, dx, dy, crot, srot );   
          FStar[i].Draw( g, mx, my, dx, dy, crot, srot );
          }
     }

@SuppressWarnings("deprecation")
public void start()
     {
 getFrame(this).setCursor(Frame.CROSSHAIR_CURSOR) ;
     if( me == null )
          {
          me = new Thread( this );
          me.start();
          }
     }

@SuppressWarnings("deprecation")
public void stop()
     {
     if( me != null )
          {
          me.stop();
          me = null;
          }
 getFrame(this).setCursor(Frame.DEFAULT_CURSOR) ;
     }

public void run()
     {
     while( me != null )
          {
          dx = mx - mxold;
          dy = my - myold;
          mxold = mx;
          myold = my;
          rot = 3.14/6*mx/Xmax*0; /*disabled*/
          crot = Math.cos(rot);    
          srot = Math.sin(rot);
          try { Thread.sleep( speed ); }
          catch (InterruptedException e){}
          repaint();
          }
     }

public void update( Graphics g )
     {
     paint( g );
     }

   public Frame getFrame(Component c) {          while( c != null && !(c instanceof java.awt.Frame) )                c = c.getParent();          return (Frame) c;        }

public void mouseMoved(MouseEvent evt)
     {
     mx = evt.getX()-Xmax;
     my = evt.getY()-Ymax;
     }

public void mouseDragged(MouseEvent evt) {}

public void mouseReleased(MouseEvent e) {   }

public void mousePressed(MouseEvent e) {    }

public void mouseClicked(MouseEvent e) {    }

public void mouseEntered(MouseEvent e) { }

public void mouseExited(MouseEvent e) {    }


@SuppressWarnings("deprecation")
public void Toggle( )
     {
     if( me != null )
          {
          if( suspend )
               {
               me.resume();
               }
          else
               {
               me.suspend();
               }
          suspend = !suspend;
          }
     }

public void Show( String theString, String theValue )
     {
     if( theValue == null )
          {
          System.out.println( theString + " : null");
          }
     else
          {
          System.out.println( theString + " : " + theValue );
          }
     }
}
