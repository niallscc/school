function newtPlotter( x, y )
%   NEWTPLOTTER Summary of this function goes here
%   Detailed explanation goes here
   c = newtdd(x, y, 4)
   x0 = linspace(.2,.8,110)';
   y0 = nest(3,c,x0,x);
   %out= nest(3,c, pi/2, x)
   y0
   plot (x0, y0)
   
   hold on
   plot (x(1), y(1), 'ro')
   plot (x(2), y(2), 'ro')
   plot (x(3), y(3), 'ro')
   plot (x(4), y(4), 'ro')
end

