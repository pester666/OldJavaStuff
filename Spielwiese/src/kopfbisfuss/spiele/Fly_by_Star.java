package kopfbisfuss.spiele;


import java.awt.Color;
import java.awt.Graphics;

public class Fly_by_Star {
	int   Xmax, Ymax, z;
	double x, y;

     Fly_by_Star( int width, int height )
          {
          Xmax = width/2;
          Ymax = height/2;
          x = (Math.random()*width) - Xmax;
          y = (Math.random()*height) - Ymax;
          if( (x == 0) && (y == 0 ) ) x = 10;
          z = (int)(Math.random()*100);
          }

     public void Draw( Graphics g, int mx, int my, int dx, int dy, double crot, double srot )
          {
          double X, Y;
          int   h, v;
          int   d;
          z-=2;
          x = x - ((double)mx*crot/25 -(double)my*srot/25)*(z+62)/162;
          y = y + ((double)mx*srot/25 -(double)my*crot/25)*(z+62)/162;
          if (x<-Xmax)x+=2*Xmax;
          if (x>Xmax)x-=2*Xmax;
          if (y<-Ymax)y+=2*Ymax;
          if (y>Ymax)y-=2*Ymax;
          X = (x*128/(64+z));
          Y = (y*128/(64+z));
          if( (X < -Xmax) || (X > Xmax)) z = 100;
          if( (Y < -Ymax) || (Y > Xmax)) z = 100;
          h = (int)(X*crot-Y*srot)+Xmax;
          v = (int)(X*srot+Y*crot)+Ymax;
          if ( z > 50 )g.setColor( Color.gray );
          else if ( z > 25 )g.setColor( Color.lightGray );
          else g.setColor( Color.white );
          d=(100-z)/40;
          if( d == 0 ) d = 1;
          if( d == 1 ) g.setColor( Color.white );
          g.fillRect( h, v, d, d );
          }
     }

class Background_Star {
     int   Xmax, Ymax, z;
     double x, y;
     Background_Star( int width, int height )
          {
          Xmax = width/2;
          Ymax = height/2;
          x = (Math.random()*width) - Xmax;
          y = (Math.random()*height) - Ymax;
          if( (x == 0) && (y == 0 ) ) x = 10;
          z = (int)(Math.random()*100);   /* Here it means brightness */
          }

     public void BDraw( Graphics g, int mx, int my, int dx, int dy, double crot, double srot)
          {
          int  h, v;
          int  d;

          x = x - (double)mx*crot/25 - (double)my*srot/25;
          y = y + (double)mx*srot/25 - (double)my*crot/25;
          if (x<-Xmax)x+=2*Xmax;
          if (x>Xmax)x-=2*Xmax;
          if (y<-Ymax)y+=2*Ymax;
          if (y>Ymax)y-=2*Ymax;
          h = (int)(x*crot-y*srot)+Xmax;
          v = (int)(x*srot+y*crot)+Ymax;
          if (h<0)h+=2*Xmax;
          if (h>2*Xmax)h-=2*Xmax;
          if (v<0)v+=2*Ymax;
          if (v>2*Ymax)v-=2*Ymax;
          if ( z > 50 )g.setColor( Color.gray );
          else if( z > 25 )g.setColor( Color.lightGray );
          else g.setColor( Color.lightGray );
          d=(100-z)/50;
          if( d == 0 ) d = 1;
          g.fillRect( h, v, d, d );
          }
     }


