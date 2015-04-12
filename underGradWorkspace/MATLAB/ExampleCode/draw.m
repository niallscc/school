%Program 3.7 Freehand draw program using B\'ezier curves
%Left click in Matlab figure window to locate endpoint, and click three
%     more times to specify 2 control points and the next spline endpoint.
%     Continue with groups of 3 points to add more to
%     the curve. Right-click to terminate program.
function draw
hold off
plot([-1 1],[0,0],'k',[0 0],[-1 1],'k');hold on
xlist=[];ylist=[];t=0:.02:1;
button=1;k=0;
while(button ~= 3)                 % if right click, terminate
  [xnew,ynew,button] = ginput(1);  % get one mouse click
  if button == 1                   % current click is a left click
    k=k+1;                         % k counts clicks
    xlist(k)=xnew; ylist(k)=ynew;  % add new point to the list
    if k>1                         % After first input point:
      if mod(k,3) == 1             % Wait for three new input points,
        for i=1:4                  % gather the previous four points
          x(i)=xlist(k-i+1);
          y(i)=ylist(k-i+1);
        end                        % Plot spline points and control pts
        plot([x(1) x(2)],[y(1) y(2)],'c:',x(2),y(2),'cs');
        plot([x(3) x(4)],[y(3) y(4)],'c:',x(3),y(3),'cs');
        plot(x(1),y(1),'bo',x(4),y(4),'bo');
        bx=3*(x(2)-x(1)); by=3*(y(2)-y(1)); % spline equations ...
        cx=3*(x(3)-x(2))-bx;cy=3*(y(3)-y(2))-by;
        dx=x(4)-x(1)-bx-cx;dy=y(4)-y(1)-by-cy;
        xp=x(1)+t.*(bx+t.*(cx+t*dx)); % nested multiplication
        yp=y(1)+t.*(by+t.*(cy+t*dy));
        plot(xp,yp)                % Plot spline curve and 
                                   %   wait for more pts
      end
    end
  end
end
